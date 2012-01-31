package com.example.jeedemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Adres;
import com.example.jeedemo.domain.Wydzial;
import com.example.jeedemo.domain.Student;


/* 
 * This is a Stateless EJB Bean
 * All its methods are transactional
 */
@Stateless
public class WydzialManager {

	@PersistenceContext
	EntityManager em;

	public void addWydzial(Wydzial wydzial) {
		wydzial.setId(null);
		em.persist(wydzial);
	}

	public void deleteWydzial(Wydzial wydzial) {
		wydzial = em.find(Wydzial.class, wydzial.getId());
		em.remove(wydzial);
	}
	public void przypiszWydzial(Long studentId, Long wydzialId) {

		Student student = em.find(Student.class, studentId);
		Wydzial wydzial = em.find(Wydzial.class, wydzialId);
		

		student.getWydzial().add(wydzial);
	}

	@SuppressWarnings("unchecked")
	public List<Wydzial> getAvailableWydzial() {
	return em.createNamedQuery("wydzial.all").getResultList();
	}
	public List<Student> getOwnedStudent(Wydzial wydzial) {
		wydzial = em.find(Wydzial.class, wydzial.getId());
		// lazy loading here - try this code without this (shallow) copying
		List<Student> student = new ArrayList<Student>(wydzial.getStudent());
		return student;
	}
	public void disposeWydzial(Student student, Wydzial wydzial) {

		student = em.find(Student.class, student.getId());
		wydzial = em.find(Wydzial.class, wydzial.getId());

		Wydzial toRemove = null;
		// lazy loading here (person.getCars)
		for (Wydzial aWydzial : student.getWydzial())
			if (aWydzial.getId().compareTo(wydzial.getId()) == 0) {
				toRemove = aWydzial;
				break;
			}

		if (toRemove != null)
			student.getWydzial().remove(toRemove);
		
		
	}
}
