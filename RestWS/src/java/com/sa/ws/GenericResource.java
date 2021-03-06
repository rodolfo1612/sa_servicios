/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.ws;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mysql.Conectar;

/**
 * REST Web Service
 *
 * @author rodolfo
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of com.sa.ws.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
     @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@QueryParam("dpi")String dpi) {
        
        System.out.println("Conectando con base de datos.");
        
        Conectar con = new Conectar();
        Connection cn = con.getConexion();
        String sql = "SELECT * FROM paciente WHERE dpi='"+ dpi +"'";
        String []datos = new String [3];
        
        datos[0]="";
        datos[1]="";
        datos[2]="";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                //datos[0]=rs.getString(0);
                datos[1]=rs.getString(3);
                datos[2]=rs.getString(4);
            }
        } catch (SQLException ex) {
            
        }
        
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("reply", "Se recibio: " + dpi);
        job.add("estado_bd", con.getEstado());
        //job.add("id_paciente", datos[0]);
        job.add("nombre", datos[1]);
        job.add("apellido", datos[2]);
        JsonObject jo = job.build();
        return Response.ok(jo.toString())
                .header("Acces-Control-Allow-Origin", "*")
                .header("Acces-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Acces-Control-Allow-Header", "Content-Type, Accept, X-Requested-With").build();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
