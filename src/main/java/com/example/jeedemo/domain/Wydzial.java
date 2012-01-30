package com.example.jeedemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "wydzial.all", query = "Select c from Wydzial c ")
public class Wydzial {
	
	private Long id;
	private String nazwa;
	private String skrot;
	private boolean editable;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSkrot() {
		return skrot;
	}
	public void setSkrot(String skrot) {
		this.skrot = skrot;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public boolean isEditable() {
		return editable;
		}
		public void setEditable(boolean editable) {
		this.editable = editable;
		}
}
