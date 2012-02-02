package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
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
import org.richfaces.validator.NullValueValidator;
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
	
	@Inject
	private AdresManager am;
	
	private Long adresId;
	private Long studentId;
	
	public Long getAdresId() {
		return adresId;
	}
	public void setAdresId(Long  adresId) {
		this.adresId =  adresId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
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
	public List<Adres> getAllAdresy() {
		return am.getAllAdres();
	}
	public String przypiszAdres() {
		pm.adresStudent(studentId,  adresId);
		return null;
	}
	// Actions
	public void addStudent() {
		pm.addStudent(student);
		//return "showStudents";
		//return null;
	}
	public String editStudent() {
		pm.editStudent(this.student);	
		return "showStudents";
		//return null;
	}
	public String zaladujDoEdycji(){
		Student studentToEdit = students.getRowData();
		this.student=pm.zaladujDoEdycji(studentToEdit);
		return "editStudent.xhtml";
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
	public void uniquePin(FacesContext context, UIComponent component,
			Object value) {

			String pin = (String) value;

			for (Student student : pm.getAllStudents()) {
			if (student.getPin().equals(pin)) {
			FacesMessage message = new FacesMessage(
			"Student z tym Pinem juz istnieje w bazie danych");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
			}
			}
			}
	public void uniqueIndeks(FacesContext context, UIComponent component,
			Object value) {

			String indeks = (String) value;

			for (Student student : pm.getAllStudents()) {
			if (student.getIndeks().equals(indeks)) {
			FacesMessage message = new FacesMessage(
			"Student z tym Indeksem juz istnieje w bazie danych");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
			}
			}
			}
			// Multi field validation with <f:event>
			// Rule: first two digits of PIN must match last two digits of the year of
			// birth
			public void validatePinDob(ComponentSystemEvent event) {

			UIForm form = (UIForm) event.getComponent();
			UIInput pin = (UIInput) form.findComponent("pin");
			UIInput dateOfBirth = (UIInput) form.findComponent("dateOfBirth");

			if (pin.getValue() != null && dateOfBirth .getValue() != null
			&& pin.getValue().toString().length() >= 2) {
			String twoDigitsOfPin = pin.getValue().toString().substring(0, 2);
			Calendar cal = Calendar.getInstance();
			cal.setTime(((Date) dateOfBirth .getValue()));

			String lastDigitsOfDob = ((Integer) cal.get(Calendar.YEAR))
			.toString().substring(2);

			if (!twoDigitsOfPin.equals(lastDigitsOfDob)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(form.getClientId(), new FacesMessage(
			"PIN nie pasuje do roku urodzin"));
			context.renderResponse();
			}
			}
			}
}
