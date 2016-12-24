package org.njp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.njp.ejb.MailSender;
import org.njp.entities.Korisnici;
import org.njp.entities.Proizvodi;

@Stateless
public class DAOProizvod extends DAOAbstractDatabase<Proizvodi> implements IDAOProizvod {
	public DAOProizvod() {
		super(Proizvodi.class);
	}

	@Override
	public boolean validateCart(List<Proizvodi> cart) {
		List<Proizvodi> baza = this.getAll();
		for (Proizvodi i: baza) {
			for (Proizvodi j: cart) {
				if (i.getIme().equals(j.getIme())) {
					if (j.getKolicina() > i.getKolicina()) {
						// u korpi ima vise nego u prodavnici
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean sendEmail(String email) {
		try {
			new MailSender(email, "Hvala na kupovini!", "body poruke...");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateStore(List<Proizvodi> cart) {
		List<Proizvodi> baza = this.getAll();
		for (Proizvodi i: baza) {
			for (Proizvodi j: cart) {
				if (i.getIme().equals(j.getIme())) {
					entityManager
					.createQuery("update Proizvodi set kolicina = :kolicina where Ime= :ime")
				    .setParameter("kolicina", i.getKolicina()-j.getKolicina())
				    .setParameter("ime", i.getIme())
					.executeUpdate();
					entityManager.flush();
				}
			}
		}
		return true;
	}
}
