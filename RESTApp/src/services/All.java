package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.media.jfxmedia.Media;

import client.Request;
import json.JSON;

@Path("/")
public class All {

	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	public void logout(String json) {
		Request.logout(JSON.get(json, "token"));
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(String json) {
		return Request.login(JSON.get(json, "user"), JSON.get(json, "pass"));
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(String json) {
		return Request.register(JSON.get(json, "user"), JSON.get(json, "pass"));
	}
	
}
