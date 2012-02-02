package com.example.jeedemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name = "wydzial.all", query = "Select c from Wydzial c ")
public class Wydzial {
	
	private Long id;
	private String nazwa;
	private String skrot;
	private List<Student> student = new ArrayList<Student>();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotNull
	@Pattern(regexp="[A-Z]+")
	public String getSkrot() {
		return skrot;
	}
	public void setSkrot(String skrot) {
		this.skrot = skrot;
	}
	@Size(min = 2)
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}	
}
