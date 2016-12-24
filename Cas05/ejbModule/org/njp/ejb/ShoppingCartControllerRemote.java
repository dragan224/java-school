package org.njp.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.njp.entities.Proizvodi;

@Remote
public interface ShoppingCartControllerRemote {
	public List<Proizvodi> listAllProizvodi();
	boolean validateCart(List<Proizvodi> cart);
	boolean sendEmail(String email);
	boolean updateStore(List<Proizvodi> cart);
	
}
