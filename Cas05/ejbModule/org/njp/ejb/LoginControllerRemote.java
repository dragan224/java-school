package org.njp.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.njp.entities.Korisnici;

@Remote
public interface LoginControllerRemote {
	public boolean validate(String email, String password);
	public boolean tryAdd(String ime, String prezime, String email, String password);
}
