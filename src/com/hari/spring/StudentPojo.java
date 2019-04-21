package com.hari.spring;

import java.util.List;

public class StudentPojo {

		private List<Student> studentList;
		
		private Student student;
		
		private int rollNo;
		

		public int getRollNo() {
			return rollNo;
		}

		public void setRollNo(int rollNo) {
			this.rollNo = rollNo;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		public List<Student> getStudentList() {
			return studentList;
		}

		public void setStudentList(List<Student> studentList) {
			this.studentList = studentList;
		}
}
