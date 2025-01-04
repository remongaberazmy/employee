package org.digivisions.configurations;

import org.digivisions.components.PostInsertEventListenerImpl;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Configuration
public class EventHandlerConfiguration {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private PostInsertEventListenerImpl postInsertEventListener;

    @PostConstruct
    public void initializeHandlers(){
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_COMMIT_INSERT).appendListener(postInsertEventListener);

    }
}
