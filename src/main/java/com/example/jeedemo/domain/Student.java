package com.example.jeedemo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "student.all", query = "Select p from Student p")
})
public class Student {

	private Long id;

	private String firstName = "unknown";
	private String lastName = "unknown";
	private String pin = "";
	private Date dateOfBirth = new Date();
	private String indeks="";
	
	private List<Wydzial> wydzial = new ArrayList<Wydzial>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Size(min = 2, max = 20)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Size(min = 2, max = 20)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Size(min = 2)
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Size(min = 2)
	public String getIndeks() {
		return indeks;
	}
	public void setIndeks(String indeks ) {
		this.indeks = indeks;
	}

	@Temporal(TemporalType.DATE)
	public Date getdateOfBirth() {
		return dateOfBirth;
	}
	public void setdateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	// Be careful here, both with lazy and eager fetch type
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Wydzial> getWydzial() {
		return wydzial;
	}
	public void setWydzial(List<Wydzial> wydzial) {
		this.wydzial = wydzial;
	}


}
