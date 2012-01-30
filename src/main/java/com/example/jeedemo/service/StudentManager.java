package com.example.jeedemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Wydzial;
import com.example.jeedemo.domain.Student;

@Stateless
public class StudentManager {

	@PersistenceContext
	EntityManager em;

	public void addStudent(Student student) {
		student.setId(null);
		em.persist(student);
	}

	public void deleteStudent(Student student) {
		student = em.find(Student.class, student.getId());
		em.remove(student);
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		return em.createNamedQuery("student.all").getResultList();
	}

	public List<Wydzial> getOwnedWydzial(Student student) {
		student = em.find(Student.class, student.getId());
		// lazy loading here - try this code without this (shallow) copying
		List<Wydzial> wydzial = new ArrayList<Wydzial>(student.getWydzial());
		return wydzial;
	}

}
