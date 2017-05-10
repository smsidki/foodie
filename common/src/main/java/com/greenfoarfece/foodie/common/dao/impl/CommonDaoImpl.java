package com.greenfoarfece.foodie.common.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.greenfoarfece.foodie.common.dao.CommonDao;
import com.greenfoarfece.foodie.common.entity.CommonEntity;
import com.greenfoarfece.foodie.common.enumeration.Status;

/**
 * Implementation of {@link CommonDao}
 * 
 * @since 8 Mei 2017
 * @version 0.0.1-SNAPSHOT
 * @author sayid.sidqi
 */
public abstract class CommonDaoImpl<T extends CommonEntity<PK>, PK extends Serializable> implements CommonDao<T, PK> {
	
	private static final Logger LOG = Logger.getLogger(CommonDaoImpl.class);
	
	private T entity;
	private Session session;
	private Transaction transaction;
	private IdentifierLoadAccess byKey;
	
	@Autowired
	@Qualifier("mainSessionFactory")
	private SessionFactory mainSF;
	
	/**
	 * Method to get entity type.
	 * @return {@link Class} of the entity
	 */
	@SuppressWarnings("rawtypes")
	protected abstract Class getEntityClass();
	
	/**
	 * Provide session. The session will be closed when transaction ends through commit or rollback.
	 * You're responsible to end the transaction, otherwise the session never closed.
	 * @return {@link Session} current session.
	 * @see <a href="https://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html_single/">Hibernate - Loading and Storing Objects<a/>
	 */
	protected Session getSession() {
		return mainSF.getCurrentSession();
	}
	
	@Override
	public boolean isExist(PK key) {
		boolean exist = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			Long count = (Long) session.createCriteria(getEntityClass())
					.add(Restrictions.eq("id", key))
					.add(Restrictions.eq("status", Status.ACTIVE))
					.setProjection(Projections.rowCount())
					.uniqueResult();
			transaction.commit();
			exist = (count != 0);
		} catch (HibernateException e) {
			LOG.error(e.getMessage(), e);
			transaction.rollback();
		}
		return exist;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T find(PK key) {
		entity = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			entity = (T) session.createCriteria(getEntityClass())
					.add(Restrictions.eq("id", key))
					.add(Restrictions.eq("status", Status.ACTIVE))
					.uniqueResult();
			if (entity == null) {
				LOG.info("There's no active record found for pk = " + key);
			}
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error(e.getMessage(), e);
			transaction.rollback();
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findIncludeDeleted(PK key) {
		entity = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			byKey = session.byId(getEntityClass());
			entity = (T) byKey.load(key);
			if (entity == null) {
				LOG.info("There's no record found for pk = " + key);
			}
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error(e.getMessage(), e);
			transaction.rollback();
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T save(T entity) {
		this.entity = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			entity.setStatus(Status.ACTIVE);
			this.entity = (T) session.merge(entity);
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error(e.getMessage(), e);
			transaction.rollback();
		}
		return this.entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean delete(PK key) {
		boolean isDeleted = false;
		entity = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			byKey = session.byId(getEntityClass());
			entity = (T) byKey.load(key);
			if (entity != null) {
				entity.setStatus(Status.DELETED);
				session.merge(entity);
				isDeleted = true;
			}
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error(e.getMessage(), e);
			isDeleted = false;
			transaction.rollback();
		}
		return isDeleted;
	}
	
	@Override
	public boolean deletePermanently(T entity) {
		boolean isDeleted = false;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			transaction.rollback();
		}
		return isDeleted;
	}
	
}
