package com.ydfind.demo.maven.service;

import com.ydfind.demo.maven.domain.Customer;

public interface CustomerService {

	Customer findOne(String custId);
}
