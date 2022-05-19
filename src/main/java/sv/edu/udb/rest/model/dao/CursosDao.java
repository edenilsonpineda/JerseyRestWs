package sv.edu.udb.rest.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sv.edu.udb.rest.connection.DbConnection;
import sv.edu.udb.rest.constants.DbQuery;
import sv.edu.udb.rest.model.Curso;
import sv.edu.udb.rest.model.dao.interfaces.Dao;

public class CursosDao extends DbConnection implements Dao<Curso> {

	@Override
	public Curso findById(int id) {
		Curso curso = null;
		try {
			connect();
			pst = this.conn.prepareStatement(DbQuery.SELECT_CURSO_BY_ID);
			pst.setInt(1, id);
			rs = pst.executeQuery();
		
			while(rs.next()) {
				curso = new Curso();
				curso.setId(rs.getInt(1));
				curso.setNombreCurso(rs.getString(2));
				curso.setNombreProfesor(rs.getString(3));
				curso.setNumeroTelefono(rs.getString(4));
				curso.setEmail(rs.getString(5));
			}
			
			close();
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		return curso;
	}

	@Override
	public List<Curso> findAll() {
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		try {
			connect();
			st = conn.createStatement();
			rs = st.executeQuery(DbQuery.SELECT_FINDALL_CURSOS);
			while(rs.next()) {
				Curso curso = new Curso();
				curso.setId(rs.getInt("id"));
				curso.setNombreCurso(rs.getString("nombreCurso"));
				curso.setNombreProfesor(rs.getString("nombreProfesor"));
				curso.setNumeroTelefono(rs.getString("numeroTelefono"));
				curso.setEmail(rs.getString("email"));
				
				cursos.add(curso);
			}
			
			close();
			
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
		return cursos;
	}

	@Override
	public void save(Curso t) {
		try {
			connect();
			pst = conn.prepareStatement(DbQuery.INSERT_INTO_CURSOS, Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, t.getNombreCurso());
			pst.setString(2, t.getNombreProfesor());
			pst.setString(3, t.getNumeroTelefono());
			pst.setString(4, t.getEmail());
			
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
	public void update(Curso t) {
		try {
			connect();
			pst = conn.prepareStatement(DbQuery.UPDATE_CURSO);
			pst.setString(1, t.getNombreCurso());
			pst.setString(2, t.getNombreProfesor());
			pst.setString(3, t.getNumeroTelefono());
			pst.setString(4, t.getEmail());
			pst.setInt(5, t.getId());
			pst.executeUpdate();
			close();
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}

	}

	@Override
	public void delete(Curso t) {
		try {
			connect();
			pst = conn.prepareStatement(DbQuery.DELETE_CURSO);
			pst.setInt(1, t.getId());
			pst.executeUpdate();
			close();
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
	}
}
