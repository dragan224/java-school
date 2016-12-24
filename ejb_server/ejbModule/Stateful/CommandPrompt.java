package Stateful;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

@Stateful
@LocalBean
public class CommandPrompt implements CommandPromptRemote {

    /**
     * Default constructor. 
     */
    public CommandPrompt() {
        // TODO Auto-generated constructor stub
    }
    private static final String ERROR = "Doslo je do greske - nepoznata komanda?";
	private static final String ERROR_CMD = "Nepostojaci direktorijum ";
	
	
	ArrayList<String> dirs = new ArrayList<>();
	
	@Override
	public String query(String command) {
		String lower_case = command.toLowerCase();
		if (lower_case.startsWith("dir")) {
			return "Trenutni direktorijum:" + cwd() + "\n" + handleDir();
		} else if (lower_case.startsWith("cd")) {
			return handleCd(command.substring(2)) + "Trenutni direktorijum:" + cwd();
		} else {
			return ERROR;
		}
	}

	private String handleDir() {
		try {
			return runCommand("ls " + cwd());
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	private String handleCd(String command) {
		String[] cmd_dirs = command.split("/");
		StringBuilder sb = new StringBuilder();
		
		ArrayList<String> dirs_backup = new ArrayList<>();
		saveDirs(dirs_backup);
		
		for (String dir: cmd_dirs) {
			if (dir.trim().equals("..")) {
				if (dirs.size() > 0) dirs.remove(dirs.size()-1);
			} else {
				try {
					String cwd = cwd();
					if (cwd.equals("/")) {
						cwd = "";
					}
					sb.append(runCommand("cd " + cwd + "/" + dir.trim()));
					System.out.println("cd " + cwd + "/" + dir.trim());
					dirs.add(dir.trim());
				} catch (Exception e) {
					restoreDirs(dirs_backup);
					return ERROR_CMD + " " + command + "!";
				}
			}
		}
		return sb.toString();
	}
	
	private void saveDirs(ArrayList<String> backup) {
		backup.clear();
		for (String dir: dirs) {
			backup.add(dir);
		}
	}
	
	private void restoreDirs(ArrayList<String> backup) {
		dirs.clear();
		for (String dir: backup) {
			dirs.add(dir);
		}
	}
	
	private String cwd() {
		StringBuilder sb = new StringBuilder();
		if (dirs.isEmpty()) return "/";
		for (String dir: dirs) {
			sb.append("/"+dir);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	private String runCommand(String command) throws Exception {
		Runtime r = Runtime.getRuntime();
		Process p = r.exec(command);
		p.waitFor();

		if (p.exitValue() != 0) throw new Exception();
		
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		
		StringBuilder sb = new StringBuilder();
		while ((line = b.readLine()) != null) {
		  sb.append(line + " ");
		}

		b.close();
		return sb.toString();
	}
}
