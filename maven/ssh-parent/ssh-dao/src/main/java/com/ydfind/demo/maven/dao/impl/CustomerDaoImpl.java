package com.ydfind.demo.maven.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ydfind.demo.maven.dao.CustomerDao;
import com.ydfind.demo.maven.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	@Override
	public Customer findOne(String custId) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Customer.class, custId);
	}
	

}
