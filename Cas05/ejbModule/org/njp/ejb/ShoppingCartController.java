package org.njp.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.njp.entities.Proizvodi;
import org.njp.services.IServiceProizvod;

/**
 * Session Bean implementation class ShoppingCartController
 */
@Stateless
@LocalBean
public class ShoppingCartController implements ShoppingCartControllerRemote {

    /**
     * Default constructor. 
     */
    public ShoppingCartController() {
        // TODO Auto-generated constructor stub
    }
    
  
    @EJB
	private IServiceProizvod serviceProizvod;


	@Override
	public List<Proizvodi> listAllProizvodi() {
		return serviceProizvod.getAll();
	}


	@Override
	public boolean validateCart(List<Proizvodi> cart) {
		return serviceProizvod.validateCart(cart);
	}


	@Override
	public boolean sendEmail(String email) {
		return serviceProizvod.sendEmail(email);
	}


	@Override
	public boolean updateStore(List<Proizvodi> cart) {
		return serviceProizvod.updateStore(cart);
	}
}
