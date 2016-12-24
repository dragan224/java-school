package org.njp.dao;

import java.util.Random;

import javax.ejb.Stateless;
import javax.mail.MessagingException;

import org.njp.ejb.MailSender;
import org.njp.entities.Korisnici;

@Stateless
public class DAOKorisnik extends DAOAbstractDatabase<Korisnici> implements IDAOKorisnik {

	public DAOKorisnik() {
		super(Korisnici.class);
	}

	@Override
	public boolean validate(String email, String password) {
		
		Long sz = (java.lang.Long) entityManager.createQuery("SELECT COUNT(k) FROM Korisnici k WHERE k.email = :email AND k.password = :passw")
		.setParameter("email", email)
		.setParameter("passw", password)
		.getResultList().get(0);
		
		return sz == 1;
	}

	@Override
	public boolean tryAdd(String ime, String prezime, String email, String password) {
		Long sz = (Long) entityManager.createQuery("SELECT COUNT(k) FROM Korisnici k WHERE k.email = :email")
				.setParameter("email", email)
				.getResultList().get(0);
		
		
		if (sz == 1) {
			return false;
		}
		
		try {
			new MailSender(email, "REGISTROVAO SI SE", "RAF! RAF! RAF!");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		//mora ovako posto je unutar EJB
		entityManager.createNativeQuery("INSERT INTO Korisnici (Ime, Prezime, email, Password) VALUES (:ime, :prezime, :email, :password)")
					 .setParameter("ime", ime)
					 .setParameter("prezime", prezime)
					 .setParameter("email", email)
					 .setParameter("password", password).executeUpdate();
		//pucice pre ove linije ako nesto ne valja...
		return true;
		
	}
}
