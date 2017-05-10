package com.greenfoarfece.foodie.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.greenfoarfece.foodie.common.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Provide common field (column) for all entities.
 * 
 * @since 10 Mei 2017
 * @version 0.0.1-SNAPSHOT
 * @author sayid.sidqi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class CommonEntity<PK extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = -6546784519954782063L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private PK id;
	
	@Column(nullable = false)
	private String createdBy;
	
	@Column(nullable = false)
	private String lastUpdatedBy;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedOn;
	
	@Column(nullable = false, length = 7)
	@Enumerated(EnumType.STRING)
	private Status status;
	
}
