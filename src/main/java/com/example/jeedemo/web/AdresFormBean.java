package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
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
	public Adres lastAdres()
	{
		adres=pm.lastAdres(this.adres);
		return adres;
	}
	// Actions
	public void addAdres() {
		pm.addAdres(adres);
		//return "showAdresy";
		//return null;
	}
	public String editAdres() {
		pm.editAdres(this.adres);	
		return "showAdresy";
		//return null;
	}
	public String zaladujDoEdycji(){
		Adres adresToEdit = adresy.getRowData();
		this.adres=pm.zaladujDoEdycji(adresToEdit);
		return "editAdres.xhtml";
	}
	public String deleteAdres() {
		Adres adresToDelete = adresy.getRowData();
		pm.deleteAdres(adresToDelete);
		return null;
	}
	
	public String showDetails() {
		adresToShow = adresy.getRowData();
		return "detailsAdresy";
	}
	
	public String disposeStudent(){
		Student studentToDispose = students.getRowData();
		sm.disposeAdres(studentToDispose, adresToShow);
		return null;
	}

}