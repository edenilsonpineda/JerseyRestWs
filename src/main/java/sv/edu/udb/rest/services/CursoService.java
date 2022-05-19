package sv.edu.udb.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import sv.edu.udb.rest.model.Alumno;
import sv.edu.udb.rest.model.Curso;
import sv.edu.udb.rest.model.dao.AlumnosDao;
import sv.edu.udb.rest.model.dao.CursosDao;
import sv.edu.udb.rest.model.dto.ResponseEntity;
import sv.edu.udb.rest.util.PatternUtils;

@Path("curso")
public class CursoService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Curso> cursos = new ArrayList<Curso>();
		try {
			CursosDao cursosDao = new CursosDao();
			cursos = cursosDao.findAll();

			if (cursos.isEmpty()) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(), "Recurso no encontrado")).build();
			}
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

		return Response.status(Status.OK.getStatusCode()).entity(cursos).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id) {
		Curso singleCurso = null;

		try {
			CursosDao cursosDao = new CursosDao();
			singleCurso = cursosDao.findById(id);

			if (singleCurso == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(), "Recurso no encontrado")).build();
			}
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		return Response.status(Status.OK.getStatusCode()).entity(singleCurso).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response newCurso(@FormParam("nombreCurso") String nombreCurso,
			@FormParam("nombreProfesor") String nombreProfesor, @FormParam("numeroTelefono") String numeroTelefono,
			@FormParam("email") String email) {
		Curso curso = new Curso();
		try {

			if (nombreCurso.contentEquals("") || nombreProfesor.contentEquals("")) {
				return Response.status(Status.BAD_REQUEST.getStatusCode())
						.entity(new ResponseEntity(Status.BAD_REQUEST.getStatusCode(),
								"Nombre del curso y del docente son requeridos"))
						.build();
			}

			if (email == null || (email.contentEquals("") && !PatternUtils.checkValidEmail(email))) {
				return Response.status(Status.BAD_REQUEST.getStatusCode())
						.entity(new ResponseEntity(Status.BAD_REQUEST.getStatusCode(),
								"El correo proporcionado no contiene un formato valido"))
						.build();
			} else {
				CursosDao cursosDao = new CursosDao();

				if (nombreCurso != null && nombreProfesor != null) {

					curso.setNombreCurso(nombreCurso);
					curso.setNombreProfesor(nombreProfesor);
					curso.setNumeroTelefono(numeroTelefono);
					curso.setEmail(email);

					cursosDao.save(curso);
				}
			}

		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

		return Response.status(Status.CREATED.getStatusCode()).entity(curso).build();
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCurso(@PathParam("id") int id, @FormParam("nombreCurso") String nombreCurso,
			@FormParam("nombreProfesor") String nombreProfesor, @FormParam("numeroTelefono") String numeroTelefono,
			@FormParam("email") String email) {
		Curso curso = null;
		try {
			CursosDao cursosDao = new CursosDao();

			curso = cursosDao.findById(id);

			if (curso == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
								"No existen registros para el Curso con id {" + id + "}"))
						.build();
			} else {
				if (nombreCurso.contentEquals("") || nombreProfesor.contentEquals("")) {
					return Response.status(Status.BAD_REQUEST.getStatusCode())
							.entity(new ResponseEntity(Status.BAD_REQUEST.getStatusCode(),
									"Nombre del curso y del docente son requeridos"))
							.build();
				}

				if (email == null || (email.contentEquals("") && !PatternUtils.checkValidEmail(email))) {
					return Response.status(Status.BAD_REQUEST.getStatusCode())
							.entity(new ResponseEntity(Status.BAD_REQUEST.getStatusCode(),
									"El correo proporcionado no contiene un formato valido"))
							.build();
				}

				if (nombreCurso != null && nombreProfesor != null) {
					curso.setNombreCurso(nombreCurso);
					curso.setNombreProfesor(nombreProfesor);
					curso.setNumeroTelefono(numeroTelefono);
					curso.setEmail(email);

					cursosDao.update(curso);
				}

			}

		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

		return Response.status(Status.OK.getStatusCode()).entity(curso).build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCurso(@PathParam("id") int id) {
		Curso curso = null;
		try {
			CursosDao cursosDao = new CursosDao();

			curso = cursosDao.findById(id);

			if (curso == null)
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
								"No existen registros para el Curso con id {" + id + "}"))
						.build();

			// Si existe, eliminar el recurso
			cursosDao.delete(curso);

		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

		return Response.status(Status.OK.getStatusCode()).entity(curso).build();
	}

	@GET
	@Path("{id}/alumnos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAlumnoPorCursos(@PathParam("id") int id) {
		Curso curso = null;
		try {
			CursosDao cursosDao = new CursosDao();
			AlumnosDao alumnosDao = new AlumnosDao();
			curso = cursosDao.findById(id);
			if (curso == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
								"No existen registros para el Curso con id {" + id + "}"))
						.build();
			}

			List<Alumno> alumnos = alumnosDao.findAlumnosPorCurso(curso.getId());

			if (alumnos != null)
				curso.setAlumnos(alumnos);

		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

		return Response.status(Status.OK.getStatusCode()).entity(curso).build();
	}

	@GET
	@Path("{id}/alumnos/{alumnoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAlumnoPorCursos(@PathParam("id") int id, @PathParam("alumnoId") int alumnoId) {
		Curso curso = null;
		Alumno alumno = null;
		try {
			CursosDao cursosDao = new CursosDao();
			AlumnosDao alumnosDao = new AlumnosDao();
			curso = cursosDao.findById(id);
			if (curso == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
								"No existen registros para el Curso con id {" + id + "}"))
						.build();
			}

			alumno = alumnosDao.findAlumnoPorCurso(id, alumnoId);

			if (alumno == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode()).entity(new ResponseEntity(
						Status.NOT_FOUND.getStatusCode(),
						"No existen registros para el ALUMNO con id [" + alumnoId + "] en el CURSO [" + id + "]"))
						.build();
			}

		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

		return Response.status(Status.OK.getStatusCode()).entity(alumno).build();
	}

	@POST
	@Path("{id}/alumnos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAlumnoConIdCurso(@PathParam("id") int id, @FormParam("nombreAlumno") String nombreAlumno,
			@FormParam("fechaNacimiento") String fechaNacimiento, @FormParam("numeroTelefono") String numeroTelefono,
			@FormParam("email") String email) {
		Curso curso = null;
		Alumno alumno = null;
		try {
			CursosDao cursosDao = new CursosDao();
			AlumnosDao alumnosDao = new AlumnosDao();
			curso = cursosDao.findById(id);
			if (curso == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
								"No existen registros para el Curso con id [" + id + "]"))
						.build();
			}

			if (nombreAlumno == null || fechaNacimiento == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode()).entity(new ResponseEntity(
						Status.NOT_FOUND.getStatusCode(),
						"Nombre del Alumno y Fecha de Nacimiento son requeridos"))
						.build();
			}

			if (fechaNacimiento.contentEquals("") || !PatternUtils.checkValidDate(fechaNacimiento)) {
				return Response.status(Status.NOT_FOUND.getStatusCode()).entity(new ResponseEntity(
						Status.NOT_FOUND.getStatusCode(),
						"Fecha de nacimiento ingresada no contiene un formato valido, debe ingresarla en formato (YYYY-MM-DD)"))
						.build();
			}

			if (nombreAlumno.contentEquals("")) {
				return Response.status(Status.BAD_REQUEST.getStatusCode()).entity(
						new ResponseEntity(Status.BAD_REQUEST.getStatusCode(), "Nombre del alumno es requerido"))
						.build();
			}
			
			if (email == null || !PatternUtils.checkValidEmail(email)) {
				return Response.status(Status.BAD_REQUEST.getStatusCode())
						.entity(new ResponseEntity(Status.BAD_REQUEST.getStatusCode(),
								"El correo proporcionado no contiene un formato valido"))
						.build();
			}

			alumno = new Alumno();
			alumno.setNombreAlumno(nombreAlumno);
			alumno.setFechaNacimiento(fechaNacimiento);
			alumno.setNumeroTelefono(numeroTelefono);
			alumno.setEmail(email);
			alumno.setCursoId(id);

			alumnosDao.save(alumno);

		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

		return Response.status(Status.OK.getStatusCode()).entity(alumno).build();
	}
	
	@PUT
	@Path("{id}/alumnos/{alumnoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAlumno(@PathParam("id") int id, @PathParam("alumnoId") int alumnoId, 
			@FormParam("nombreAlumno") String nombreAlumno,
			@FormParam("fechaNacimiento") String fechaNacimiento, 
			@FormParam("numeroTelefono") String numeroTelefono,
			@FormParam("email") String email) {
		Curso curso = null;
		Alumno alumno = null;
		try {
			CursosDao cursosDao = new CursosDao();
			AlumnosDao alumnosDao = new AlumnosDao();

			curso = cursosDao.findById(id);

			if (curso == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
								"No existen registros para el Curso con id [" + id + "]"))
						.build();
			}
			
			alumno = alumnosDao.findAlumnoPorCurso(id, alumnoId);

			if (alumno == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode()).entity(new ResponseEntity(
						Status.NOT_FOUND.getStatusCode(),
						"No existen registros para el ALUMNO con id [" + alumnoId + "] en el CURSO [" + id + "]"))
						.build();
			}
			
			if (nombreAlumno == null || fechaNacimiento == null || nombreAlumno == "") {
				return Response.status(Status.NOT_FOUND.getStatusCode()).entity(new ResponseEntity(
						Status.NOT_FOUND.getStatusCode(),
						"Nombre del Alumno y Fecha de Nacimiento son requeridos"))
						.build();
			}

			if (fechaNacimiento.contentEquals("") || !PatternUtils.checkValidDate(fechaNacimiento)) {
				return Response.status(Status.NOT_FOUND.getStatusCode()).entity(new ResponseEntity(
						Status.NOT_FOUND.getStatusCode(),
						"Fecha de nacimiento ingresada no contiene un formato valido, debe ingresarla en formato (YYYY-MM-DD)"))
						.build();
			}
			
			if (email == null || !PatternUtils.checkValidEmail(email)) {
				return Response.status(Status.BAD_REQUEST.getStatusCode())
						.entity(new ResponseEntity(Status.BAD_REQUEST.getStatusCode(),
								"El correo proporcionado no contiene un formato valido"))
						.build();
			}
			
			alumno.setNombreAlumno(nombreAlumno);
			alumno.setFechaNacimiento(fechaNacimiento);
			alumno.setNumeroTelefono(numeroTelefono);
			alumno.setEmail(email);
			
			alumnosDao.update(alumno);

		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

		return Response.status(Status.OK.getStatusCode()).entity(alumno).build();
	}
	
	@DELETE
	@Path("{id}/alumnos/{alumnoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAlumno(@PathParam("id") int id, @PathParam("alumnoId") int alumnoId) {
		Curso curso = null;
		Alumno alumno = null;
		try {
			CursosDao cursosDao = new CursosDao();
			AlumnosDao alumnosDao = new AlumnosDao();

			curso = cursosDao.findById(id);

			if (curso == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode())
						.entity(new ResponseEntity(Status.NOT_FOUND.getStatusCode(),
								"No existen registros para el Curso con id [" + id + "]"))
						.build();
			}
			
			alumno = alumnosDao.findAlumnoPorCurso(id, alumnoId);

			if (alumno == null) {
				return Response.status(Status.NOT_FOUND.getStatusCode()).entity(new ResponseEntity(
						Status.NOT_FOUND.getStatusCode(),
						"No existen registros para el ALUMNO con id [" + alumnoId + "] en el CURSO [" + id + "]"))
						.build();
			}
			
			alumnosDao.delete(alumno);

		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

		return Response.status(Status.OK.getStatusCode()).entity(alumno).build();
	}
}
