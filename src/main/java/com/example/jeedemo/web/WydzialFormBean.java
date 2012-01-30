package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Wydzial;
import com.example.jeedemo.domain.Student;
import com.example.jeedemo.service.StudentManager;
import com.example.jeedemo.service.WydzialManager;

@SessionScoped
@Named("wydzialBean")
public class WydzialFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private WydzialManager sm;

	@Inject
	private StudentManager pm;

	private Long wydzialId;
	private Long studentId;
	
	public Long getWydzialId() {
		return wydzialId;
	}
	public void setWydzialId(Long  wydzialId) {
		this.wydzialId =  wydzialId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long personId) {
		this.studentId = personId;
	}



	public List<Student> getAllStudents() {
		return pm.getAllStudents();
	}

	public String przypiszWydzial() {
		sm.przypiszWydzial(studentId,  wydzialId);
		return null;
	}
}
