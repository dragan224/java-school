package org.njp.dao;

import java.util.List;

import org.njp.entities.Korisnici;
import org.njp.entities.Proizvodi;

public interface IDAOProizvod extends IDAOAbstract<Proizvodi> {
	boolean validateCart(List<Proizvodi> cart);
	boolean sendEmail(String email);
	boolean updateStore(List<Proizvodi> cart);
}

