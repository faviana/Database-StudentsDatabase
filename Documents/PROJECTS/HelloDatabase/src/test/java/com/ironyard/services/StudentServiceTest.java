package com.ironyard.services;

import com.ironyard.data.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by favianalopez on 9/26/16.
 */
public class StudentServiceTest {
    @org.junit.BeforeClass
    public static void setUp() throws Exception {
        Database myDb = new Database();
        myDb.init();

    }

    @org.junit.After
    public  void clearStudents() throws Exception {
        Database myDb = new Database();
        myDb.clear();

    }

    @org.junit.Test
    public void createStudent() throws Exception {
        StudentService myServ = new StudentService();
        myServ.createStudent(new Student("jason", 40));
        // Lets see if its there

        Student found = myServ.getStudentByName("jason");
        assertEquals(found.getName(), "jason");
        assertEquals(found.getAge(), 40);
        assertNotNull(found.getId());
    }

    @org.junit.Test
    public void updateStudent() throws Exception{

        StudentService myServ = new StudentService();
        // create a student
        myServ.createStudent(new Student("jason", 40));

        // call getStudentByName
        Student found = myServ.getStudentByName("jason");

        // change some field of student
        found.setName("HELLO");
        found.setAge(100);

        // call update method
        myServ.updateStudent(found);

        // get student again
        found = myServ.getStudentByName("HELLO");


        // assertEquals() test that field values updated
        assertEquals(found.getName(), "HELLO");
        assertEquals(found.getAge(), 100);
        assertNotNull(found.getId());
    }

    @org.junit.Test
    public void deleteStudent() throws Exception{

        StudentService myServ = new StudentService();
        //create Student
        myServ.createStudent(new Student("jason", 40));

        //call getStudentByName
        Student found = myServ.getStudentByName("jason");

        //delete method
        myServ.deleteStudent(found);

        //call getStudentByName
        found = myServ.getStudentByName("jason");

        //make sure it's null
        assertNull(found);
    }

}