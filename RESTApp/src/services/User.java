package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import client.Request;
import json.JSON;

@Path("/")
public class User {
  @POST
  @Path("/showairports")
  @Consumes(MediaType.APPLICATION_JSON)
  public String showairports(String json) {
    return Request.showairports(JSON.get(json, "token"));
  }
  
  @POST
  @Path("/showflight")
  @Consumes(MediaType.APPLICATION_JSON)
  public String showflight(String json) {
    return Request.showflight(JSON.get(json, "flight"), JSON.get(json, "token"));
  }

  @POST
  @Path("/showhistory")
  @Consumes(MediaType.APPLICATION_JSON)
  public String showhistory(String json) {
    return Request.showhistory(JSON.get(json, "user"), JSON.get(json, "token"));
  }

  @POST
  @Path("/search")
  @Consumes(MediaType.APPLICATION_JSON)
  public String search(String json) {
    return Request.search(JSON.get(json, "source"), JSON.get(json, "dest"), JSON.get(json, "token"));
  }
  @POST
  @Path("/reserve")
  @Consumes(MediaType.APPLICATION_JSON)
  public String reserve(String json) {
    return Request.reserve(JSON.get(json, "name"), JSON.get(json, "row"), JSON.get(json, "col"), JSON.get(json, "token"));
  }
  @POST
  @Path("/buy")
  @Consumes(MediaType.APPLICATION_JSON)
  public String buy(String json) {
    return Request.buy(JSON.get(json, "name"), JSON.get(json, "row"), JSON.get(json, "col"), JSON.get(json, "token"));
  }
  @POST
  @Path("/cancel")
  @Consumes(MediaType.APPLICATION_JSON)
  public String cancel(String json) {
    return Request.cancel(JSON.get(json, "name"), JSON.get(json, "row"), JSON.get(json, "col"), JSON.get(json, "token"));
  }
  @POST
  @Path("/showseats")
  @Consumes(MediaType.APPLICATION_JSON)
  public String showseats(String json) {
    return Request.showseats(JSON.get(json, "name"), JSON.get(json, "token"));
  }
} 