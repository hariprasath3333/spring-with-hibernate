package com.hari.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
	@Autowired
	private StudentDao studentDao;

//	@Autowired
//	public void setStudentDao(StudentDao studentDao) {
//		this.studentDao = studentDao;
//	}


	@RequestMapping(value ="/hi", method = RequestMethod.GET)
	public ModelAndView hello(){
		ModelAndView model = new ModelAndView("StudentEntry");
		StudentEntity studentEntity = new StudentEntity(); 
		studentEntity.setAge(19);
		studentEntity.setName("Jack");
		studentEntity.setMobileNumber(9870);
		studentDao.updateRecord(studentEntity);
		return model;
	}


	@ResponseBody
	@RequestMapping(value = "/students",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Student> getStudentList(){
		ArrayList<Student> sudentList = new ArrayList<>();
		Student student = new Student(); 
		student.setAge(19);
		student.setName("Jack");
		//		Address a = new Address();
		//		a.setCountry("UK");
		//		student.setAddress(a);
		sudentList.add(student);
		return sudentList;
	}


	@ResponseBody
	@RequestMapping(value = "/updateStudentList",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateStudentList(@RequestBody StudentPojo studentPojo){
		List<Student> sudentList = studentPojo.getStudentList();
		Student student = new Student(); 
		student.setAge(19);
		student.setName("Jack");

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/updateStudent",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateStudent(@RequestBody StudentPojo studentPojo){
		Student student = studentPojo.getStudent();
		HttpStatus httpStatus;
		Boolean status;
		try{
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setAge(student.getAge());
			studentEntity.setMobileNumber(student.getMobileNumber());
			studentEntity.setName(student.getName());
			//			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(StudentEntity.class).buildSessionFactory();
			studentDao.updateRecord(studentEntity);
			httpStatus = HttpStatus.OK;
			status = true;

		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			status = false;
		}
		return new ResponseEntity<Boolean>(status, httpStatus);
	}
}
