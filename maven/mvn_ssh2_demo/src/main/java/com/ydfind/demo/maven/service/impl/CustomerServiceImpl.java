package com.ydfind.demo.maven.service.impl;

import com.ydfind.demo.maven.dao.CustomerDao;
import com.ydfind.demo.maven.domain.Customer;
import com.ydfind.demo.maven.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public Customer findOne(String custId) {
		return customerDao.findOne(custId);
	}


}
