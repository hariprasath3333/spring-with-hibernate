package com.hari.spring;

import java.util.List;

public class StudentPojo {

		private List<Student> studentList;
		
		private Student student;

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
