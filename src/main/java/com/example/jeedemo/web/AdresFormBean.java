package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Adres;
import com.example.jeedemo.domain.Wydzial;
import com.example.jeedemo.domain.Student;
import com.example.jeedemo.service.AdresManager;
import com.example.jeedemo.service.StudentManager;
import com.example.jeedemo.service.WydzialManager;

@SessionScoped
@Named("adresBean")
public class AdresFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Adres adres = new Adres();
	private ListDataModel<Adres> adresy = new ListDataModel<Adres>();
	
	private Adres adresToShow = new Adres();
	private ListDataModel<Student> students = new ListDataModel<Student>();


	@Inject
	private StudentManager sm;
	
	@Inject
	private AdresManager pm;

	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	public ListDataModel<Adres> getAllAdresy() {
		adresy.setWrappedData(pm.getAllAdres());
		return adresy;
	}

	public ListDataModel<Student> getOwnedStudent() {
		students.setWrappedData(pm.getOwnedStudents(adresToShow));
		return students;
	}
	
	// Actions
	public String addAdres() {
		pm.addAdres(adres);
		return "showAdresy";
		//return null;
	}

	public String deleteAdres() {
		Adres adresToDelete = adresy.getRowData();
		pm.deleteAdres(adresToDelete);
		return null;
	}
	
	public String showDetails() {
		adresToShow = adresy.getRowData();
		return "details";
	}
	
	public String disposeStudent(){
		Student studentToDispose = students.getRowData();
		sm.disposeAdres(studentToDispose, adresToShow);
		return null;
	}
}