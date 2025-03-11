package com.spring5.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.spring5.type.PhoneType;

@Entity
@Table(name = "CONTACT")
public class Contact {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "First_Name")
	private String firstName;

	@Column(name = "Last_Name")
	private String lastName;

	@Column(name = "Email")
	private String email;

	@Column(name = "Phone_Number")
	private String phoneNumber;

	@Column(name = "Phone_Type")
	@Enumerated(EnumType.STRING)
	private PhoneType phoneType;

	@OneToMany(mappedBy="contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = ContactNote.class)
	private List<ContactNote> notes;

	public Contact() {
	}

	public Contact(String firstName, String lastName, String email, PhoneType phoneType, String phone) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneType = phoneType;
		this.phoneNumber = phone;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public List<ContactNote> getNotes() {
		if (this.notes == null) {
			this.notes = new ArrayList<>();
		}
		return this.notes;
	}
	
	public void addNote(ContactNote note){
		getNotes().add(note);
		note.setContact(this);
	}

	public void setNotes(List<ContactNote> addresses) {
		this.notes = addresses;
	}

}
