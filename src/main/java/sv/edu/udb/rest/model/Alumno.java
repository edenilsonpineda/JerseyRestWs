package sv.edu.udb.rest.model;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Alumno implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nombreAlumno;
	private String fechaNacimiento;
	private String numeroTelefono;
	private String email;
	@JsonInclude(Include.NON_NULL)
	private int cursoId;
	@JsonInclude(Include.NON_NULL)
	private Curso curso;
	
}
