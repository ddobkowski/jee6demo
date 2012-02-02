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
public class AdresManager {

	@PersistenceContext
	EntityManager em;
	public void addAdres(Adres adres) {
		adres.setId(null);
		em.persist(adres);
	}

	public void deleteAdres(Adres adres) {
		adres = em.find(Adres.class, adres.getId());
		em.remove(adres);
	}
	public void editAdres(Adres adres){
		em.merge(adres);
		
	}
	public Adres zaladujDoEdycji(Adres adres){
		adres=em.find(Adres.class, adres.getId());
		return adres;
		
	}
public Adres lastAdres(Adres adres){
	adres=em.find(Adres.class, adres.getId());
	return adres;
}
	@SuppressWarnings("unchecked")
	public List<Adres> getAllAdres() {
		return em.createNamedQuery("adres.all").getResultList();
	}
	public List<Student> getOwnedStudents(Adres adres) {
		adres = em.find(Adres.class, adres.getId());
		// lazy loading here - try this code without this (shallow) copying
		List<Student> student = new ArrayList<Student>(adres.getStudent());
		return student;
	}

	
}