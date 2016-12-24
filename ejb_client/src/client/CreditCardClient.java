package client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import Stateless.CreditCardValidatorRemote;

public class CreditCardClient {
    
	public static void main(String[] args) {
		try {
			Properties jndiProps = new Properties();
			jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProps.put("jboss.naming.client.ejb.context", true);
			jndiProps.put(Context.SECURITY_PRINCIPAL, "jboss");
			jndiProps.put(Context.SECURITY_CREDENTIALS, "jboss");
			Context ctx = new InitialContext(jndiProps);
			CreditCardValidatorRemote ccRemote = (CreditCardValidatorRemote) ctx.lookup("ejb_server/CreditCardValidator!Stateless.CreditCardValidatorRemote");
			
			System.out.println(ccRemote.Validate("38520000023237")); 
			System.out.println(ccRemote.Validate("5555555555554444"));
			System.out.println(ccRemote.Validate("4012888888881881"));
			System.out.println(ccRemote.Validate("8888888888888888"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
