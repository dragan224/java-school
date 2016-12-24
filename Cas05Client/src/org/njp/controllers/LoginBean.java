package org.njp.controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.njp.ejb.LoginControllerRemote;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = -1257893135173554003L;
	
	private String ime;
	private String prezime;
	
	private String loginError;
	private String registerError;
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	private String email;
	private String password;
	
	private String regEmail;
	private String regPassword;
	
	public String getRegEmail() {
		return regEmail;
	}

	public void setRegEmail(String regEmail) {
		this.regEmail = regEmail;
	}

	public String getRegPassword() {
		return regPassword;
	}

	public void setRegPassword(String regPassword) {
		this.regPassword = regPassword;
	}

	public LoginBean() {
		
	}
	
	public String register() {
		registerError = "";
		boolean valid = remoteControler.tryAdd(ime, prezime, regEmail, regPassword);
		if (!valid) {
			registerError = new String("Postojeci/nevalid email!");
			return "newuser";
		}
		
		return "login";
	}
	
	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}

	public String getRegisterError() {
		return registerError;
	}

	public void setRegisterError(String registerError) {
		this.registerError = registerError;
	}

	public String logout() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
		         .getSession(false)).invalidate();
		return "index";
	}
	
	public String validateUser() {
		loginError = "";
		boolean valid = remoteControler.validate(email, password);
		if (valid) {
			((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
			         .getSession(false)).setAttribute("email", email);
			
			return "webshop";
		} else {
			loginError = "pogresan usernamei ili password!";
			return "index";
		}
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
	
	@EJB
	private LoginControllerRemote remoteControler;
}
