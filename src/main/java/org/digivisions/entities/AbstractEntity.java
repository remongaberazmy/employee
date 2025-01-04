package org.digivisions.entities;

import lombok.Getter;
import lombok.Setter;
import org.digivisions.utils.Constants;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {org.springframework.data.jpa.domain.support.AuditingEntityListener.class})
public class AbstractEntity implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedBy
	@Column(nullable = false,name = "CREATED_BY")
	private String createdBy;

	@LastModifiedBy
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@CreatedDate
	@Type(type = Constants.DATE_TIME_PERSISTER)
	@Column(nullable = false,name = "CREATION_DATE")
	private DateTime creationDate;

	@LastModifiedDate
	@Type(type = Constants.DATE_TIME_PERSISTER)
	@Column(name = "MODIFICATION_DATE")
	private DateTime modificationDate;

	@NotNull
	@Column(name = "SOFT_DELETED", nullable = false)
	private Boolean deleted = false;
}
