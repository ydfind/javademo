package com.ydfind.demo.maven.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ydfind.demo.maven.domain.Customer;
import com.ydfind.demo.maven.service.CustomerService;
             
public class CustomerAction extends ActionSupport {
	private CustomerService customerService;
//	// url : http://localhost:8080/mvn_struct2_web_demo/customerAction_save.action
//	public String save() throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("CustomerAction.save()已被调用");
//		return SUCCESS;
//	}
	//url:http://localhost:8080/mvn_ssh2_demo/test.action?custId=1
	//    http://localhost:8080/mvn_ssh2_demo/customerAction_findOne.action?custId=1
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	private String custId;
	public void setCustId(String custId) {
		System.out.println("setCustId(String custId)");
		this.custId = custId;
	}
	public String findOne() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CustomerAction setCustId(String custId)");
		Customer customer =  customerService.findOne(custId);
		ActionContext.getContext().getValueStack().push(customer);
		return super.execute();
	}
	
	public String hello() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CustomerAction setCustId(String custId)-----Hello World!");
		Customer customer =  customerService.findOne(custId);
		ActionContext.getContext().getValueStack().push(customer);
		return super.execute();
	}

}
