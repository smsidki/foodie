package com.greenfoarfece.foodie.main.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.greenfoarfece.foodie.common.entity.CommonEntity;
import com.greenfoarfece.foodie.common.enumeration.Status;
import com.greenfoarfece.foodie.main.enumeration.Role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

/**
 * Representation of table TB_USER.
 * 
 * @since 10 Mei 2017
 * @version 0.0.1-SNAPSHOT
 * @author sayid.sidqi
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "TB_USER")
public class User extends CommonEntity<Long> {

	private static final long serialVersionUID = -8059192242695528458L;

	@Builder
	public User(Long id, String createdBy, String lastUpdatedBy, Date createdOn, Date lastUpdatedOn, Status status,
			String userName, String password, Role role) {
		super(id, createdBy, lastUpdatedBy, createdOn, lastUpdatedOn, status);
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	@Column(nullable = false, unique = true, length = 16)
	private String userName;

	@Column(nullable = false, length = 16)
	private String password;

	@Column(nullable = false, length = 8)
	@Enumerated(EnumType.STRING)
	private Role role;

}