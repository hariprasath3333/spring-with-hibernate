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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
	
//	@Autowired
//	private StudentDao studentDao;

	@Autowired
	private AServiceFile aServiceFile;


	@RequestMapping(value ="/hi", method = RequestMethod.GET)
	public ModelAndView hello(){
		ModelAndView model = new ModelAndView("StudentEntry");
		Student studentEntity = new Student(); 
		studentEntity.setRollNo(1);
		studentEntity.setAge(19);
		studentEntity.setName("Jack");
		studentEntity.setMobileNumber(9870);
		aServiceFile.updateStudent(studentEntity);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/getStudent",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(@RequestParam int rollNo){
//		int rollNo = studentPojo.getRollNo();
		Student s = new Student();
		s.setRollNo(rollNo);
		return aServiceFile.displayStudent(s);
	}

	@ResponseBody
	@RequestMapping(value = "/getStudentList",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> getStudentList(){
		
		return aServiceFile.displayStudentList();
		
	}


	@ResponseBody
	@RequestMapping(value = "/updateStudentList",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateStudentList(@RequestBody StudentPojo studentPojo){
		List<Student> studentList = studentPojo.getStudentList();
//		Student student = new Student(); 
//		student.setRollNo(1);
//		student.setAge(19);
//		student.setName("Jack");
//		student.setMobileNumber(9870);
//		studentList.add(student);
		aServiceFile.updateStudentList(studentList);   
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/updateStudent",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateStudent(@RequestBody StudentPojo studentPojo){
		Student student = studentPojo.getStudent();
		HttpStatus httpStatus;
		Boolean status;
		try{
//			student.setRollNo(1);
//			student.setAge(19);
//			student.setName("Jack");
//			student.setMobileNumber(9870);
			aServiceFile.updateStudent(student);
			httpStatus = HttpStatus.OK;
			status = true;
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			status = false;
		}
		return new ResponseEntity<Boolean>(status, httpStatus);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletetudentList",method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deletetudentList(@RequestBody StudentPojo studentPojo){
		List<Student> studentList = studentPojo.getStudentList();
//		Student student = new Student(); 
//		student.setRollNo(1);
//		student.setAge(19);
//		student.setName("Jack");
//		student.setMobileNumber(9870);
//		studentList.add(student);
		aServiceFile.deleteStudentList(studentList);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteStudent",method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteStudent(@RequestBody StudentPojo studentPojo){
		Student student = studentPojo.getStudent();
//		student.setRollNo(1);
//		student.setAge(19);
//		student.setName("Jack");
//		student.setMobileNumber(9870);
		aServiceFile.deleteStudent(student);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
	//wont work in mysql if ID generator is used
	@ResponseBody
	@RequestMapping(value = "/insertStudentList",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> insertStudentList(@RequestBody StudentPojo studentPojo){
		List<Student> studentList = studentPojo.getStudentList();
//		Student student = new Student(); 
//		student.setRollNo(1);
//		student.setAge(19);
//		student.setName("Jack");
//		student.setMobileNumber(9870);
//		studentList.add(student);    
		aServiceFile.insertStudentList(studentList);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertStudent",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> insertStudent(@RequestBody StudentPojo studentPojo){
		Student student = studentPojo.getStudent();
//		student.setRollNo(1);
//		student.setAge(19);
//		student.setName("Jack");
//		student.setMobileNumber(9870);
		aServiceFile.insertStudent(student);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
