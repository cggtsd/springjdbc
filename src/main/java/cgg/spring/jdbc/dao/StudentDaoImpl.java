package cgg.spring.jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import cgg.spring.jdbc.entities.Student;

@Component("studentdao")
public class StudentDaoImpl  implements StudentDao{
	
	
//	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//	public JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}
//
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	

	@Override
	public int insert(Student student) {
		//insert query...
//		String query = "insert into student values(?,?,?)";
		String query = "insert into student values(:id,:name,:city)";
		SqlParameterSource namedParameters= new MapSqlParameterSource("id",student.getId()).
				addValue("name",student.getName())
			   .addValue("city",student.getCity());

       int r= this.namedParameterJdbcTemplate.update(query, namedParameters);
		
	   return r;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int change(Student student) {
		//updating data...
		String query="update student set name=:name,city=:city where id=:id";
		SqlParameterSource namedParameters= new MapSqlParameterSource("id",student.getId()).
				addValue("name",student.getName())
			   .addValue("city",student.getCity());
		int r = this.namedParameterJdbcTemplate.
				update(query,namedParameters);
		return r;
	}

	@Override
	public int delete(int studentId) {
		//delete operation
		
		String query="delete from student where id=:id";
		SqlParameterSource namedParameters= new MapSqlParameterSource("id",studentId);
		int r = this.namedParameterJdbcTemplate.update(query,namedParameters);
		return r;
	}

	@Override
	public Student getStudent(int studentId) {
		//select single student data
		String query = "select * from student where id=:id";
		SqlParameterSource namedParameters= new MapSqlParameterSource("id",studentId);
		RowMapper<Student> rowMapper = new RowMapperImpl();
		Student student = this.namedParameterJdbcTemplate
				.queryForObject(query, namedParameters, rowMapper);
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		//selecting multiple students
		String query = "select * from student";
		List<Student> students = this.namedParameterJdbcTemplate
				.query(query, new RowMapperImpl());
		return students;
	}
	
	

}
