package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Wydzial;
import com.example.jeedemo.domain.Student;
import com.example.jeedemo.service.StudentManager;
import com.example.jeedemo.service.WydzialManager;

@SessionScoped
@Named("studentBean")
public class StudentFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Student student = new Student();
	private ListDataModel<Student> students = new ListDataModel<Student>();
	
	private Student studentToShow = new Student();
	private ListDataModel<Wydzial> wydzialy = new ListDataModel<Wydzial>();


	@Inject
	private StudentManager pm;
	
	@Inject
	private WydzialManager sm;

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public ListDataModel<Student> getAllStudents() {
		students.setWrappedData(pm.getAllStudents());
		return students;
	}

	public ListDataModel<Wydzial> getOwnedWydzial() {
		wydzialy.setWrappedData(pm.getOwnedWydzial(studentToShow));
		return wydzialy;
	}
	
	// Actions
	public String addStudent() {
		pm.addStudent(student);
		return "showStudents";
		//return null;
	}

	public String deleteStudent() {
		Student studentToDelete = students.getRowData();
		pm.deleteStudent(studentToDelete);
		return null;
	}
	
	public String showDetails() {
		studentToShow = students.getRowData();
		return "details";
	}
	
	public String disposeWydzial(){
		Wydzial wydzialToDispose = wydzialy.getRowData();
		sm.disposeWydzial(studentToShow, wydzialToDispose);
		return null;
	}
}
