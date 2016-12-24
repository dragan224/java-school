package org.njp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.njp.dao.IDAOProizvod;
import org.njp.entities.Proizvodi;

@Stateless
public class ServiceProizvod extends ServiceAbstract<Proizvodi, IDAOProizvod> implements IServiceProizvod {

	public ServiceProizvod() {
		super();
	}
	
	@EJB
	@Override
	public void setDAO(IDAOProizvod dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean add(Proizvodi object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Proizvodi object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Proizvodi getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateCart(List<Proizvodi> cart) {
		return dao.validateCart(cart);
	}

	@Override
	public boolean sendEmail(String email) {
		return dao.sendEmail(email);
	}

	@Override
	public boolean updateStore(List<Proizvodi> cart) {
		return dao.updateStore(cart);
	}
}
