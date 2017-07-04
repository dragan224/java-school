package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import client.Request;
import json.JSON;

@Path("/")
public class Operator {
  @POST
  @Path("/ban")
  @Consumes(MediaType.APPLICATION_JSON)
  public String ban(String json) {
    return Request.ban(JSON.get(json, "user"), JSON.get(json, "token"));
  }
  
  @POST
  @Path("/showusers")
  @Consumes(MediaType.APPLICATION_JSON)
  public String showusers(String json) {
    return Request.showusers(JSON.get(json, "token"));
  }

  @POST
  @Path("/removeairplane")
  @Consumes(MediaType.APPLICATION_JSON)
  public String removeairplane(String json) {
    return Request.removeairplane(JSON.get(json, "name"), JSON.get(json, "token"));
  }
  
  @POST
  @Path("/addairplane")
  @Consumes(MediaType.APPLICATION_JSON)
  public String addairplane(String json) {
    return Request.addairplane(JSON.get(json, "name"), JSON.get(json, "row"), JSON.get(json, "col"), JSON.get(json, "space"), JSON.get(json, "flight"), JSON.get(json, "token"));
  }
}
