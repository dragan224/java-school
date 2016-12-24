package org.njp.services;

import java.util.List;

import org.njp.entities.Korisnici;
import org.njp.entities.Proizvodi;

public interface IServiceProizvod extends IServiceAbstract<Proizvodi> {
	boolean validateCart(List<Proizvodi> cart);
	boolean sendEmail(String email);
	boolean updateStore(List<Proizvodi> cart);
}
