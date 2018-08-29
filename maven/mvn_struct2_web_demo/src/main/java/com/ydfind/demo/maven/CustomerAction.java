package com.ydfind.demo.maven;

import com.opensymphony.xwork2.ActionSupport;
             
public class CustomerAction extends ActionSupport {
	// url : http://localhost:8080/mvn_struct2_web_demo/customerAction_save.action
	public String save() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CustomerAction.save()已被调用");
		return SUCCESS;
	}

}
