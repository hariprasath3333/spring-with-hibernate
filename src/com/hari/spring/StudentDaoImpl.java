package com.hari.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
//@RequiredArgsConstructor(onConstructor=@_({@Autowired}))
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private  SessionFactory sessionFactory;
	
//	@Autowired
//	StudentDaoImpl(SessionFactory sessionFactory){
//		this.sessionFactory = sessionFactory;
//	}

	//	public SessionFactory getSessionFactory() {
	//		return sessionFactory;
	//	}
	//
	//	public void setSessionFactory(SessionFactory sessionFactory) {
	//		this.sessionFactory = sessionFactory;
	//	}


//
//	public StudentDaoImpl(SessionFactory sessionFactory2) {
//		this.sessionFactory=sessionFactory2;
//	}

	@Override
	public Boolean updateRecord(StudentEntity studentEntity) {
		System.out.println("DAOOOOOOO");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//		studentEntity = (StudentEntity) session.get(StudentEntity.class, 1);

//		session.update(studentEntity);
		session.save(studentEntity);

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		System.out.println("END");
		return true;
	}

}
