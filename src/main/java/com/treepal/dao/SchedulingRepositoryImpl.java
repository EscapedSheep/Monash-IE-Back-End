package com.treepal.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Service;

@Service
public class SchedulingRepositoryImpl implements SchedulingRepository {
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	@Override
	public void resetTodayGrowth() {
		EntityManager em = emf.createEntityManager();
		String querySql = "update tree set todayGrowth=0";
		em.createNativeQuery(querySql);
		em.close();

	}

}
