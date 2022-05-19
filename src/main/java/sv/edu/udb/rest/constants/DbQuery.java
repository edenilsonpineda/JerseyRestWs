package sv.edu.udb.rest.constants;

public final class DbQuery {
	public static final String INSERT_INTO_ALUMNOS = "INSERT INTO cursosUDB.ALUMNOS(nombreAlumno, fechaNacimiento, numeroTelefono, email, curso) VALUES(?,?,?,?,?)";
	public static final String SELECT_FINDALL_ALUMNOS = "SELECT id, nombreAlumno, fechaNacimiento, numeroTelefono,email curso FROM cursosUDB.ALUMNOS";
	public static final String SELECT_CURSO_BY_ID = "SELECT id,nombreCurso,nombreProfesor,numeroTelefono,email FROM cursosUDB.CURSOS WHERE id = ?";
	public static final String SELECT_FINDALL_CURSOS = "SELECT id, nombreCurso, nombreProfesor, numeroTelefono, email FROM cursosUDB.CURSOS";
	public static final String FIND_ALUMNO_BY_ID = "SELECT id,nombreAlumno, fechaNacimiento, numeroTelefono, email, curso FROM cursosUDB.ALUMNOS WHERE id = ?";
	public static final String INSERT_INTO_CURSOS = "INSERT INTO cursosUDB.CURSOS(nombreCurso, nombreProfesor, numeroTelefono, email) VALUES(?,?,?,?)";
	public static final String UPDATE_CURSO	= "UPDATE cursosUDB.CURSOS SET nombreCurso = ?, nombreProfesor = ?, numeroTelefono = ?, email = ? WHERE id = ?";
	public static final String DELETE_CURSO	= "DELETE FROM cursosUDB.CURSOS WHERE id = ?";
	public static final String SELECT_ALUMNOS_POR_CURSO = "SELECT alum.id, alum.nombreAlumno, alum.fechaNacimiento, alum.numeroTelefono, alum.email, alum.curso FROM cursosUDB.ALUMNOS alum, cursosUDB.CURSOS CURSOS "
			+ "WHERE alum.curso = CURSOS.id AND CURSOS.id = ?";
	
	public static final String SELECT_DETALLE_ALUMNO_POR_CURSO = "SELECT alum.id, alum.nombreAlumno, alum.fechaNacimiento, alum.numeroTelefono, alum.email, alum.curso FROM cursosUDB.ALUMNOS alum, cursosUDB.CURSOS CURSOS "
			+ "WHERE alum.curso = CURSOS.id AND alum.id = ? AND CURSOS.id = ?";
	
	public static final String UPDATE_ALUMNO_POR_CURSO = 
			"UPDATE cursosUDB.ALUMNOS a, cursosUDB.CURSOS c "
			+ "SET a.curso  = c.id, a.nombreAlumno = ?, a.fechaNacimiento = ?, a.numeroTelefono = ?, a.email = ? "
			+ "WHERE c.id = a.curso AND a.id = ? AND c.id = ?";
	
	public static final String DELETE_ALUMNO	= "DELETE FROM cursosUDB.ALUMNOS WHERE id = ? AND curso = ?";
}
