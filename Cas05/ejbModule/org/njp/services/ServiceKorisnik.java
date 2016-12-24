package org.njp.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.njp.dao.IDAOKorisnik;
import org.njp.entities.Korisnici;

@Stateless
public class ServiceKorisnik extends ServiceAbstract<Korisnici, IDAOKorisnik> implements IServiceKorisnik{
	
	public ServiceKorisnik() {
		super();
	}
	
	@EJB
	@Override
	public void setDAO(IDAOKorisnik dao) {
		this.dao = dao;
	}

	@Override
	public boolean validate(String email, String password) {
		return dao.validate(email, password);
	}

	@Override
	public boolean tryAdd(String ime, String prezime, String email, String password) {
		return dao.tryAdd(ime, prezime, email, password);
	}
	
}
