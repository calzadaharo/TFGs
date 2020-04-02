package es.upm.dit.isst.tfg.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.*;

@Entity
public class TFG implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String email;
	private String password;
	private String nombre;
	private String title;
	private int status;
	private int grade;
	@Lob
	private byte[] document;
	@ManyToOne
	private Professor advisor;
	
	

	public TFG() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TFG other = (TFG) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TFG [email=" + email + ", password=" + password + ", nombre=" + nombre + ", title=" + title
				+ ", status=" + status + ", grade=" + grade + ", document=" + Arrays.toString(document) + ", advisor="
				+ advisor + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Professor getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Professor advisor) {
		this.advisor = advisor;
	}

}
