package com.ydfind.demo.maven.dao;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring/applicationContext-*.xml")
class CustomerDaoTest {

//	@Autowired
//	public CustomerDao dao;
//	
	@Test
	public void testFindOne() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		CustomerDao dao = (CustomerDao)classPathXmlApplicationContext.getBean("customerDao");
		dao.findOne("1");
	}

}
