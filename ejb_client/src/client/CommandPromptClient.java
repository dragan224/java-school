package client;

import java.util.Properties;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import Stateful.CommandPromptRemote;


public class CommandPromptClient {
	public static void main(String[] args) {
		try {
			
			Properties jndiProps = new Properties();
			jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProps.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
			jndiProps.put("jboss.naming.client.ejb.context", true);
			jndiProps.put(Context.SECURITY_PRINCIPAL, "jboss");
			jndiProps.put(Context.SECURITY_CREDENTIALS, "jboss");
			Context ctx = new InitialContext(jndiProps);
			CommandPromptRemote cp = (CommandPromptRemote) ctx.lookup("ejb_server/CommandPrompt!Stateful.CommandPromptRemote");
			Scanner s=new Scanner(System.in);  
			while (true) { 
				String str = s.nextLine();
				System.out.println(cp.query(str));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
