package Stateful;

import javax.ejb.Remote;

@Remote
public interface CommandPromptRemote {
	public String query(String command);
}
