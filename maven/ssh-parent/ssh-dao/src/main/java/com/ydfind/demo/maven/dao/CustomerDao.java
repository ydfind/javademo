package com.ydfind.demo.maven.dao;

import com.ydfind.demo.maven.domain.Customer;

public interface CustomerDao {

	Customer findOne(String custId);

}
