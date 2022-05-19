package sv.edu.udb.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import sv.edu.udb.rest.model.Alumno;
import sv.edu.udb.rest.model.dao.AlumnosDao;
import sv.edu.udb.rest.model.dto.ResponseEntity;

@Path("alumnos")
public class AlumnoService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlumnos() {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		try {
			AlumnosDao alumnosDAO = new AlumnosDao();
			alumnos = alumnosDAO.findAll();
			
			if(alumnos.isEmpty()) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
								"Recurso no encontrado"))
						.build();
			}
			
			
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		
		return Response.status(Status.OK.getStatusCode()).entity(alumnos).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		Alumno singleAlumno = null;
		try {
			AlumnosDao alumnosDAO = new AlumnosDao();
			singleAlumno = alumnosDAO.findById(id);
			
			if (singleAlumno == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
								"Recurso no encontrado"))
						.build();
			}
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		
		return Response.status(Status.OK.getStatusCode()).entity(singleAlumno).build();
	}
}
