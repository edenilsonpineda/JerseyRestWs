package sv.edu.udb.rest.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Curso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nombreCurso;
	private String nombreProfesor;
	private String numeroTelefono;
	private String email;
	@JsonInclude(Include.NON_NULL)
	private List<Alumno> alumnos; // Se utiliza para mapear los alumnos pertenecientes a X curso.
}
