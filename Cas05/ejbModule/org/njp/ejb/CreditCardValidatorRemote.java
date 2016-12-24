package org.njp.ejb;
import javax.ejb.Remote;
import javax.jws.WebService;

@WebService
@Remote
public interface CreditCardValidatorRemote {
	boolean Validate(String number);
}
