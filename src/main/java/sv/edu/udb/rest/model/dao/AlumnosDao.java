package sv.edu.udb.rest.model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sv.edu.udb.rest.connection.DbConnection;
import sv.edu.udb.rest.constants.DbQuery;
import sv.edu.udb.rest.model.Alumno;
import sv.edu.udb.rest.model.Curso;
import sv.edu.udb.rest.model.dao.interfaces.Dao;

public class AlumnosDao extends DbConnection implements Dao<Alumno>{

	@Override
	public Alumno findById(int id) {
		Alumno alumno = null;
		try {
			connect();
			pst = conn.prepareStatement(DbQuery.FIND_ALUMNO_BY_ID);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			CursosDao cursosDao = new CursosDao();
			
			while(rs.next()) {
				alumno = new Alumno();
				alumno.setId(rs.getInt("id"));
				alumno.setNombreAlumno(rs.getString("nombreAlumno"));
				alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toString());
				alumno.setEmail(rs.getString("email"));
				alumno.setNumeroTelefono(rs.getString("numeroTelefono"));
				alumno.setCursoId(rs.getInt("curso"));
				
				Curso curso = new Curso(); // Obtener curso
				if(alumno.getCursoId() > 0) { curso = cursosDao.findById(alumno.getCursoId()); }
				alumno.setCurso(curso);
			}
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		return alumno;
	}

	@Override
	public List<Alumno> findAll() {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		CursosDao cursosDao = new CursosDao();
		try {
			connect();
			st = conn.createStatement();
			rs = st.executeQuery(DbQuery.SELECT_FINDALL_ALUMNOS);
			while (rs.next()) {
				Alumno alumno = new Alumno();
				alumno.setId(rs.getInt("id"));
				alumno.setNombreAlumno(rs.getString("nombreAlumno"));
				alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toString());
				alumno.setNumeroTelefono(rs.getString("numeroTelefono"));
				alumno.setCursoId(rs.getInt("curso"));
				alumno.setEmail(rs.getString("email"));
				
				Curso curso = new Curso(); // Obtener curso
				if(alumno.getCursoId() > 0) { curso = cursosDao.findById(alumno.getCursoId()); }
				
				alumno.setCurso(curso);
				
				alumnos.add(alumno);
			}
			close();
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		return alumnos;
	}

	@Override
	public void save(Alumno t) {
		try {
			connect();
			pst = this.conn.prepareStatement(DbQuery.INSERT_INTO_ALUMNOS, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, t.getNombreAlumno());
			pst.setDate(2, Date.valueOf(t.getFechaNacimiento()));
			pst.setString(3, t.getNumeroTelefono());
			pst.setString(4, t.getEmail());
			pst.setInt(5, t.getCursoId());
			
			pst.executeUpdate();
			// Ultimo ID generado
			ResultSet keys = pst.getGeneratedKeys();
			keys.next();
						
			int id = keys.getInt(1);
			t.setId(id);
			
			close();
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void update(Alumno t) {
		try {
			connect();
			pst = this.conn.prepareStatement(DbQuery.UPDATE_ALUMNO_POR_CURSO);
			pst.setString(1, t.getNombreAlumno());
			pst.setDate(2, Date.valueOf(t.getFechaNacimiento()));
			pst.setString(3, t.getNumeroTelefono());
			pst.setString(4, t.getEmail());
			pst.setInt(5, t.getId());
			pst.setInt(6, t.getCursoId());
			pst.executeUpdate();
			close();
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		
	}

	@Override
	public void delete(Alumno t) {
		try {
			connect();
			pst = conn.prepareStatement(DbQuery.DELETE_ALUMNO);
			pst.setInt(1, t.getId());
			pst.setInt(2, t.getCursoId()); // Revisar
			pst.executeUpdate();
			close();
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public List<Alumno> findAlumnosPorCurso(int id) {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		try {
			connect();
			pst = this.conn.prepareStatement(DbQuery.SELECT_ALUMNOS_POR_CURSO);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Alumno a = new Alumno();
				a.setId(rs.getInt("id"));
				a.setNombreAlumno(rs.getString("nombreAlumno"));
				a.setFechaNacimiento(rs.getDate("fechaNacimiento").toString());
				a.setEmail(rs.getString("email"));
				a.setNumeroTelefono(rs.getString("numeroTelefono"));
				a.setCursoId(rs.getInt("alum.curso"));
				
				alumnos.add(a);
			}
			close();
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		
		return alumnos;
	}
	
	/**
	 * 
	 * @param idCurso
	 * @param idAlumno
	 * @return
	 */
	public Alumno findAlumnoPorCurso(int idCurso, int idAlumno) {
		Alumno alumno = null;
		try {
			connect();
			pst = conn.prepareStatement(DbQuery.SELECT_DETALLE_ALUMNO_POR_CURSO);
			pst.setInt(1, idAlumno);
			pst.setInt(2, idCurso);
			
			rs = pst.executeQuery();
			//CursosDao cursosDao = new CursosDao();
			
			while(rs.next()) {
				alumno = new Alumno();
				alumno.setId(rs.getInt("id"));
				alumno.setNombreAlumno(rs.getString("nombreAlumno"));
				alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toString());
				alumno.setEmail(rs.getString("email"));
				alumno.setNumeroTelefono(rs.getString("numeroTelefono"));
				alumno.setCursoId(rs.getInt("curso"));
			}
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		
		return alumno;
	}
}
