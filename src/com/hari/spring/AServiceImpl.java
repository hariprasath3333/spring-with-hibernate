package com.hari.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class AServiceImpl implements AServiceFile {

	private StudentDao studentDao;
	
	@Autowired
	AServiceImpl(StudentDao studentDao){
		this.studentDao = studentDao;
	}
	
	@Override
	public boolean updateStudentList(List<Student> studentList) {
		return studentDao.updateStudentList(studentList);
	}

	@Override
	public boolean deleteStudentList(List<Student> studentList) {
		return studentDao.deleteStudentList(studentList);
	}

	@Override
	public boolean insertStudentList(List<Student> studentList) {
		return studentDao.insertStudentList(studentList);
	}

	@Override
	public List<Student> displayStudentList() {
		return studentDao.displayStudentList();
	}

	@Override
	public boolean updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}

	@Override
	public boolean deleteStudent(Student student) {
		return studentDao.deleteStudent(student);
	}

	@Override
	public boolean insertStudent(Student student) {
		return studentDao.insertStudent(student);
	}

	@Override
	public Student displayStudent(Student student) {
		return studentDao.displayStudent(student);
	}
	

}
