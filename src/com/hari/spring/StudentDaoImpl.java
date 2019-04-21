package com.hari.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sun.util.logging.resources.logging;

//@RequiredArgsConstructor(onConstructor=@_({@Autowired}))
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private  SessionFactory sessionFactory;

	public Boolean updateRecord(StudentEntity studentEntity) {
		System.out.println("DAOOOOOOO");
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		StudentEntity studentEntityFromDB =  session.get(StudentEntity.class, studentEntity.getRollNo());
		studentEntityFromDB.setAge(studentEntity.getAge());
		studentEntityFromDB.setMobileNumber(studentEntity.getMobileNumber());
		studentEntityFromDB.setName(studentEntity.getName());

		session.update(studentEntityFromDB);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		System.out.println("END");
		return true;
	}

	@Override
	public boolean updateStudentList(List<Student> studentList) {
		List<Integer>studentKeys = new ArrayList<>();
		studentList.stream().forEach(action ->{
			studentKeys.add(action.getRollNo());
		});
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		MultiIdentifierLoadAccess<StudentEntity> multiLoadAccess = session.byMultipleIds(StudentEntity.class);
		List<StudentEntity> studentEntityList = multiLoadAccess.multiLoad(studentKeys);
		studentEntityList.stream().forEach(action ->{
			studentList.stream().forEach(action2 ->{
				if(action2.getRollNo() == action.getRollNo()){
					action.setAge(action2.getAge());
					action.setMobileNumber(action2.getMobileNumber());
					action.setName(action2.getName());
				} 
			});
		});
		
		int totalNoOfRecords = studentEntityList.size();
		for(int i = 0; i < totalNoOfRecords; i++){
			session.update(studentEntityList.get(i));
			if((i >= 2)){
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		ScrollableResults studentScrollEntity = session.getNamedQuery("from StudentEntity")
//			    .setCacheMode(CacheMode.IGNORE)
//			    .scroll(ScrollMode.FORWARD_ONLY);
//			int count=0;
//			while ( studentScrollEntity.next() ) {
//			    StudentEntity studentFromDB = (StudentEntity) studentScrollEntity.get(0);
//			   // student.updateStuff(...);
//			    if ( ++count % 50 == 0 ) {
//			        session.flush();
//			        session.clear();
//			    }
//			}
//		session.getTransaction().commit();
//		session.close();
//		sessionFactory.close();
		System.out.println("updateStudentList");
		return true;
	}

	@Override
	public boolean deleteStudentList(List<Student> studentList) {
		List<StudentEntity> studentEntityList = new ArrayList<>();
		studentList.stream().forEach(action ->{
			StudentEntity studentEntity = getStudentEntity(action);
			studentEntityList.add(studentEntity);
		});
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int totalNoOfRecords = studentEntityList.size();
		for(int i = 0; i < totalNoOfRecords; i++){
			session.delete(studentEntityList.get(i));
			if((i >= 50) || (i == totalNoOfRecords) ){
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		System.out.println("deleteStudentList");
		return true;
	}

	@Override
	public boolean insertStudentList(List<Student> studentList) {
		List<StudentEntity> studentEntityList = new ArrayList<>();
		studentList.stream().forEach(action ->{
			StudentEntity studentEntity = getStudentEntity(action);
			studentEntityList.add(studentEntity);
		});
		
		System.out.println("Size 1: "+ studentList.size());
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int totalNoOfRecords = studentEntityList.size();
		
		System.out.println("Size 2: "+ studentEntityList.size());
		
		for(int i = 0; i < totalNoOfRecords; i++){
			session.save(studentEntityList.get(i));
//			if((i >= 50) || (i == totalNoOfRecords) )
			if((i >= 50)){
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		System.out.println("insertStudentList");
		return true;
	}

	@Override
	public List<Student> displayStudentList() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Student> studentList = new ArrayList<>();
		List<StudentEntity> studentEntityList = session.createQuery("from StudentEntity", StudentEntity.class).list();
		studentEntityList.stream().forEach(action ->{
			Student student = getStudent(action);
			studentList.add(student);
		});
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		System.out.println("displayStudentList");
		return studentList; 
	}

	@Override
	public boolean updateStudent(Student student) {
		StudentEntity studentEntity = getStudentEntity(student);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
//		StudentEntity studentEntityFromDB =  session.get(StudentEntity.class, studentEntity.getRollNo());
//		studentEntityFromDB.setAge(studentEntity.getAge());
//		studentEntityFromDB.setMobileNumber(studentEntity.getMobileNumber());
//		studentEntityFromDB.setName(studentEntity.getName());
//		session.update(studentEntityFromDB);
		session.update(student);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		System.out.println("updateStudent");
		return true;
	}

	@Override
	public boolean deleteStudent(Student student) {
		StudentEntity studentEntity = getStudentEntity(student);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(studentEntity);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		System.out.println("deleteStudent");
		return true;
	}

	@Override
	public boolean insertStudent(Student student) {
		StudentEntity studentEntity = getStudentEntity(student);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(studentEntity);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		System.out.println("insertStudent");
		return true;
	}

	@Override
	public Student displayStudent(Student student) {
		StudentEntity studentEntity = getStudentEntity(student);
		StudentEntity studentEntityFromDB;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		studentEntityFromDB = session.get(StudentEntity.class, studentEntity.getRollNo());
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		Student studentFromDB = getStudent(studentEntityFromDB);
		System.out.println("displayStudent");
		return studentFromDB;
	}

	private StudentEntity getStudentEntity(Student student){
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setAge(student.getAge());
		studentEntity.setMobileNumber(student.getMobileNumber());
		studentEntity.setName(student.getName());
		studentEntity.setRollNo(student.getRollNo());
		return studentEntity;
	}

	private Student getStudent(StudentEntity studentEntity){
		Student student = new Student();
		student.setAge(studentEntity.getAge());
		student.setMobileNumber(studentEntity.getMobileNumber());
		student.setName(studentEntity.getName());
		student.setRollNo(studentEntity.getRollNo());
		return student;
	}

}
