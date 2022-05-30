package bstorm.akimts.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bstorm.akimts.jdbc.data.SectionDAO;
import bstorm.akimts.jdbc.data.StudentDAO;
import bstorm.akimts.jdbc.entity.Section;
import bstorm.akimts.jdbc.entity.Student;
import bstorm.akimts.jdbc.exceptions.EntityNotFoundException;

public class Program {
	
	public static void main(String[] args) {
		
		StudentDAO dao = StudentDAO.getInstance();
		List<Student> students = dao.getAllStudent();
		
		for (Student student : students) {
			System.out.println( student );
		}
		
		Optional<Student> optStud = dao.getStudentById(11);
		Student student = optStud.orElseThrow( () -> {
			return new EntityNotFoundException(11, Long.class, Student.class);
		});
		
		/*if( optStud.isPresent() ) {
			student = optStud.get();
		}
		else {
			throw new EntityNotFoundException(111, Long.class, Student.class);
		}*/
		System.out.println(student);
		
		System.out.println(" --- DEBUT DE L'INSERTION --- ");
		
		Section s = new Section(9999, "NRB Java", student);
		
		SectionDAO sectionDAO = SectionDAO.getInstance();
		if( sectionDAO.insert(s) ) {
			System.out.println("INSERTION REUSSIE");
		}
		else {
			System.out.println("ECHEC D'INSERTION");
		}
		
		
	}
	
	
	

}
