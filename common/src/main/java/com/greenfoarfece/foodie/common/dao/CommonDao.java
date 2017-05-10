package com.greenfoarfece.foodie.common.dao;

import java.io.Serializable;

import com.greenfoarfece.foodie.common.entity.CommonEntity;

/**
 * Provide basic CRUD operation.
 * 
 * @since 8 Mei 2017
 * @version 0.0.1-SNAPSHOT
 * @author sayid.sidqi
 */
public interface CommonDao<T extends CommonEntity<PK>, PK extends Serializable> {
	
	public boolean isExist(PK key);
	public T find(PK key);
	public T findIncludeDeleted(PK key);
	public T save(T entity);
	public boolean delete(PK key);
	public boolean deletePermanently(T entity);
	
}
