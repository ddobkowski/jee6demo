package com.example.jeedemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Adres;
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
	public void adresStudent(Long studentId, Long adresId) {

		Student student = em.find(Student.class, studentId);
		Adres adres = em.find(Adres.class, adresId);
		

		adres.getStudent().add(student);
	}
	public void disposeAdres(Student student, Adres adres) {

		student = em.find(Student.class, student.getId());
		adres = em.find(Adres.class, adres.getId());

		Student toRemove = null;
		// lazy loading here (person.getCars)
		for (Student aStudent : adres.getStudent())
			if (aStudent.getId().compareTo(student.getId()) == 0) {
				toRemove = aStudent;
				break;
			}

		if (toRemove != null)
			adres.getStudent().remove(toRemove);
		
		
	}
}
