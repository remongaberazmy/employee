package org.digivisions.repositories;

import org.digivisions.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, JpaSpecificationExecutor<EmployeeEntity> {
	Optional<EmployeeEntity> findByIdAndDeletedFalse(Long id);
	@Query("SELECT e FROM EmployeeEntity e WHERE e.deleted = false")
	List<EmployeeEntity> findAllAndDeletedFalse();

	boolean existsByEmailAndDeletedFalse(String email);
}
