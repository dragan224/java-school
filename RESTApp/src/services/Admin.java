package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import client.Request;
import json.JSON;

@Path("/")
public class Admin {
	@POST
	@Path("/addoperator")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addoperator(String json) {
		return Request.addoperator(JSON.get(json, "user"), JSON.get(json, "pass"), JSON.get(json, "token"));
	}
}
