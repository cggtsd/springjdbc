package cgg.spring.jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cgg.spring.jdbc.dao.StudentDao;
import cgg.spring.jdbc.entities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "My Program started....." );
        //spring jdbc -> JdbcTemplate
        
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao = context.getBean("studentdao",StudentDao.class);
        
       //INSERT
		
		  Student student = new Student(); 
		  student.setId(9990);
		  student.setName("xyz");
		  student.setCity("hyd");
		
		  int r = studentDao.insert(student); 
		  System.out.println("student added "+r);
		 
        
        //UPDATE
		/*
		 * Student student = new Student(); student.setId(246);
		 * student.setName("Fathima"); student.setCity("hyd");
		 * 
		 * int r = studentDao.change(student); System.out.println("data changed "+r);
		 */
        
        //DELETE
		/*
		 * int r = studentDao.delete(666); System.out.println("Deleted "+r);
		 */
        List<Student> students = studentDao.getAllStudents();
        for (Student s : students) {
        	System.out.println(s);
		}
   
        
        
    }
}
