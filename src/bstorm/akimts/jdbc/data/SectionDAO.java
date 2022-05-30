package bstorm.akimts.jdbc.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import bstorm.akimts.jdbc.ConnectionFactory;
import bstorm.akimts.jdbc.entity.Section;
import bstorm.akimts.jdbc.entity.Student;

public class SectionDAO {
	
	private static SectionDAO instance;
	public static SectionDAO getInstance() {
		return instance == null ? instance = new SectionDAO() : instance;
	}
	private SectionDAO() {}
	
	
	public List<Section> getAll(){
		
		String query = 
				  "SELECT"
				+ "		se.section_id,"
				+ "		section_name,"
				+ "		student_id,"
				+ "		first_name,"
				+ "		last_name "
				+ "FROM section se JOIN student st"
				+ "		ON se.delegate_id = st.student_id";
		
		try ( 
				Connection co = ConnectionFactory.getConnection();
				Statement stmt = co.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		) { 
			List<Section> list = new ArrayList<>();
			while( rs.next() ) {
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				long studentId = rs.getLong("student_id");
				Student stud = new Student(studentId,firstname,lastname);
				
				long id = rs.getLong("section_id");
				String nom = rs.getString("section_name");
				Section sect = new Section(id, nom, stud);
				
				list.add(sect);
			}
			return list;
		}
		catch( SQLException ex ) {
			System.out.println("Connexion echouée!");
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean insert(Section toInsert){
		if(toInsert == null)
			return false;
		
		String query = "INSERT INTO section (section_id, section_name, delegate_id)"
				+ " VALUES (?, ?, ?)";
		
		try (
				Connection co = ConnectionFactory.getConnection();
				PreparedStatement ps = co.prepareStatement(query);
		)
		{
			ps.setLong(1, toInsert.getId());
			ps.setString(2, toInsert.getNom());
			
			if( toInsert.getDelegate() != null )
				ps.setLong(3, toInsert.getDelegate().getId());
			else 
				ps.setNull(3, Types.INTEGER);
			
			int affected = ps.executeUpdate();
			return affected > 0;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean update(Section toUpdate){
		if(toUpdate == null)
			return false;
		
		String query = "UPDATE section"
				+ " SET section_name=?, delegate_id=?"
				+ " WHERE section_id=?";
		
		try (
				Connection co = ConnectionFactory.getConnection();
				PreparedStatement ps = co.prepareStatement(query);
		)
		{
			ps.setString(1, toUpdate.getNom());
			
			if( toUpdate.getDelegate() != null )
				ps.setLong(2, toUpdate.getDelegate().getId());
			else 
				ps.setNull(2, Types.INTEGER);
			
			ps.setLong(3, toUpdate.getId());
			
			return ps.executeUpdate() > 0;	
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(long id) {
		String query = "DELETE FROM section WHERE section_id=?";
		try (
				Connection co = ConnectionFactory.getConnection();
				PreparedStatement ps = co.prepareStatement(query);
		)
		{
			ps.setLong(1, id);
			return ps.executeUpdate() > 0;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
		
	}

}
