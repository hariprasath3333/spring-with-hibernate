package com.hari.spring;

import java.util.List;

public interface StudentDao {
	
	public boolean updateStudentList(List<Student> studentList);
	public boolean deleteStudentList(List<Student> studentList);
	public boolean insertStudentList(List<Student> studentList);
	public List<Student> displayStudentList();
	
	public boolean updateStudent(Student student);
	public boolean deleteStudent(Student student);
	public boolean insertStudent(Student student);
	public Student displayStudent(Student student);
}
