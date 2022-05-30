package bstorm.akimts.jdbc.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bstorm.akimts.jdbc.ConnectionFactory;
import bstorm.akimts.jdbc.entity.Student;
import bstorm.akimts.jdbc.exceptions.EntityNotFoundException;

public class StudentDAO {
	
	private static StudentDAO instance;
	public static StudentDAO getInstance() {
		return instance == null ? instance = new StudentDAO() : instance;
	}
	private StudentDAO(){}

	public List<Student> getAllStudent(){
		try ( 
				Connection co = ConnectionFactory.getConnection();
				Statement stmt = co.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT student_id, first_name, last_name"
						+ " FROM student");
		) { 
			List<Student> list = new ArrayList<>();
			while( rs.next() ) {
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				long id = rs.getLong("student_id");
				
				list.add(new Student(id,firstname,lastname));
			}
			return list;
		}
		catch( SQLException ex ) {
			System.out.println("Connexion echouée!");
			ex.printStackTrace();
			return null;
		}
	}
	
	public Optional<Student> getStudentById(long id) {
		try ( 
				Connection co = ConnectionFactory.getConnection();
				Statement stmt = co.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT first_name, last_name"
						+ " FROM student"
						+ " WHERE student_id = "+ id);
		) { 
			
			if( rs.next() ) {
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				
				Student s = new Student(id,firstname,lastname);
				return Optional.of(s);
			}
			// throw new EntityNotFoundException(id,Long.class, Student.class);
		}
		catch( SQLException ex ) {
			System.out.println("Connexion echouée!");
			ex.printStackTrace();
		}
		
		return Optional.empty();
	}
	
}
