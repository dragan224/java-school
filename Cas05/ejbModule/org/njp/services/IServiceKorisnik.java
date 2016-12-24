package org.njp.services;

import org.njp.entities.Korisnici;

public interface IServiceKorisnik extends IServiceAbstract<Korisnici> {

	public boolean validate(String email, String password);
	
	public boolean tryAdd(String ime, String prezime, String email, String password);
}
