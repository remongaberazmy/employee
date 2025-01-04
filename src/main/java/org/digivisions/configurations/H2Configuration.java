package org.digivisions.configurations;

import org.apache.commons.dbcp2.BasicDataSource;
import org.digivisions.utils.EntityAuditAware;
import org.flywaydb.core.Flyway;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages =
		{"org.digivisions.repositories"},
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager")
@EnableJpaAuditing(auditorAwareRef = "auditAware", modifyOnCreate = false)
@EnableAspectJAutoProxy
@EnableSpringConfigured
@EnableTransactionManagement(proxyTargetClass = true)
public class H2Configuration {

	@Autowired
	Environment env;

	@Value("${flyway.repair:n}")
	private String flywayRepair;

	@Bean
	public EntityAuditAware auditAware() {
		return new EntityAuditAware();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean(name = "dataSource", destroyMethod = "close")
	@Primary
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("H2.db.driver"));
		dataSource.setUrl(env.getProperty("H2.db.url"));
		dataSource.setUsername(env.getProperty("H2.db.username"));
		dataSource.setPassword(env.getProperty("H2.db.password"));
		dataSource.setTimeBetweenEvictionRunsMillis(1800000);
		dataSource.setNumTestsPerEvictionRun(3);
		dataSource.setMinEvictableIdleTimeMillis(1800000);
		return dataSource;
	}

	@Bean(name = "transactionManager")
	@Primary
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		return jpaTransactionManager;
	}

	@Bean
	public HibernatePersistenceProvider hibernatePersistenceProvider() {
		return new HibernatePersistenceProvider();
	}

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean(name = "flyway")
	@DependsOn(value = "dataSource")
	public Flyway commonFlyWay(DataSource dataSource) {
		Flyway flyway = new Flyway();
		try {
			flyway.setDataSource(dataSource);
			flyway.setLocations("classpath:db/migration/H2");
			flyway.setTable("H2_SCHEMA_VERSION");
			flyway.setBaselineVersionAsString("0");
			flyway.setBaselineOnMigrate(true);
			if (flywayRepair.equalsIgnoreCase("y")) {
				flyway.repair();
			}
			flyway.migrate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return flyway;
	}
	@Primary
	@Bean(name = "entityManagerFactory")
	@DependsOn(value = "flyway")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("EmployeePU");
		emf.setDataSource(dataSource());
		emf.setPersistenceProvider(hibernatePersistenceProvider());
		emf.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		emf.setPackagesToScan("org.digivisions.entities");

		Properties properties = new Properties();

		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("H2.db.creationStrategy"));
		properties.setProperty("hibernate.default_catalog", env.getProperty("H2.db.catalog"));
		properties.setProperty("hibernate.default_schema", env.getProperty("H2.db.mainScheme"));
		properties.setProperty("hibernate.dialect", env.getProperty("H2.db.dialect"));
		properties.setProperty("hibernate.use_nationalized_character_data", "false");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.generate_statistics", "false");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.use_sql_comments", "true");
		properties.setProperty("hibernate.jdbc.batch_size", "100");
		properties.setProperty("hibernate.order_inserts", "true");
		properties.setProperty("hibernate.order_updates", "true");
		properties.setProperty("hibernate.connection.useUnicode", "true");
		properties.setProperty("hibernate.connection.charSet", "UTF-16");
		properties.setProperty("hibernate.connection.characterEncoding", "UTF-16");
		properties.setProperty("jadira.usertype.autoRegisterUserTypes", "true");
		properties.setProperty("jadira.usertype.javaZone", "UTC");
		properties.setProperty("jadira.usertype.databaseZone", "UTC");
		emf.setJpaProperties(properties);

		emf.afterPropertiesSet();
		return emf.getObject();
	}
}
