package cgg.spring.jdbc.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cgg.spring.jdbc.entities.Student;

public class StudentDaoImpl  implements StudentDao{
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(Student student) {
		//insert query...
		String query = "insert into student values(?,?,?)";
		int r = this.jdbcTemplate.update(query,student.getId(),student.getName(),student.getCity());
		
		return r;
	}

	@Override
	public int change(Student student) {
		//updating data...
		String query="update student set name=?,city=? where id=?";
		int r = this.jdbcTemplate.
				update(query,student.getName(),student.getCity(),student.getId());
		return r;
	}

	@Override
	public int delete(int studentId) {
		//delete operation
		
		String query="delete from student where id=?";
		int r = this.jdbcTemplate.update(query,studentId);
		return r;
	}

	@Override
	public Student getStudent(int studentId) {
		//select single student data
		String query = "select * from student where id=?";
		RowMapper<Student> rowMapper = new RowMapperImpl();
		Student student = this.jdbcTemplate
				.queryForObject(query,rowMapper ,studentId);
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		//selecting multiple students
		String query = "select * from student";
		List<Student> students = this.jdbcTemplate.query(query, new RowMapperImpl());
		return students;
	}
	
	

}
