package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Adres;
import com.example.jeedemo.domain.Wydzial;
import com.example.jeedemo.domain.Student;
import com.example.jeedemo.service.StudentManager;
import com.example.jeedemo.service.WydzialManager;

@SessionScoped
@Named("wydzialBean")
public class WydzialFormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Wydzial wydzial = new Wydzial();
	private ListDataModel<Wydzial> wydzialy = new ListDataModel<Wydzial>();
	private Wydzial wydzialToShow = new Wydzial();
	private ListDataModel<Student> studenci = new ListDataModel<Student>();

	@Inject
	private WydzialManager sm;

	@Inject
	private StudentManager pm;

	private Long wydzialId;
	private Long studentId;
	
	public Wydzial getWydzial() {
		return wydzial;
	}
	public void setWydzial(Wydzial wydzial) {
		this.wydzial = wydzial;
	}
	public Long getWydzialId() {
		return wydzialId;
	}
	public void setWydzialId(Long  wydzialId) {
		this.wydzialId =  wydzialId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public List<Wydzial> getAvailableWydzial() {
		return sm.getAvailableWydzial();
		}

	public List<Student> getAllStudents() {
		return pm.getAllStudents();
	}
	public ListDataModel<Wydzial> getAllWydzialy() {
		wydzialy.setWrappedData(sm.getAvailableWydzial());
		return wydzialy;
	}
	public ListDataModel<Student> getOwnedStudent() {
		studenci.setWrappedData(sm.getOwnedStudent(wydzialToShow));
		return studenci;
	}
	public String przypiszWydzial() {
		sm.przypiszWydzial(studentId,  wydzialId);
		pm.przypiszWydzial(studentId, wydzialId);
		return null;
	}
	// Actions
		public String addWydzial() {
			sm.addWydzial(wydzial);
			return "showWydzialy";
			//return null;
		}

		public String deleteWydzial() {
			Wydzial wydzialToDelete = wydzialy.getRowData();
			sm.deleteWydzial(wydzialToDelete);
			return null;
		}
		
		public String showDetails() {
			wydzialToShow = wydzialy.getRowData();
			return "detailsWydzialy";
		}
		public String disposeStudent(){
			Student studentToDispose = studenci.getRowData();
			pm.disposeStudent( studentToDispose, wydzialToShow);
			return null;
		}
	}
