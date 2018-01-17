package com.example.student.a2018011701;

import com.example.student.a2018011701.data.StudentSourceDAO;
import com.example.student.a2018011701.data.student;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void test_add_data1() throws  Exception{
        StudentSourceDAO dao = new StudentSourceDAO();
        dao.add(new student(1, "Bob", 95));
        dao.add(new student(2, "Mary", 90));
        assertEquals(2, dao.getList().size());
    }

    @Test
    public void test_add_data2() throws  Exception{
        StudentSourceDAO dao = new StudentSourceDAO();
        dao.add(new student(1, "Bob", 95));
        dao.add(new student(2, "Mary", 90));
        assertEquals(90, dao.getList().get(1).score);
    }
    @Test
    public void test_add_data3() throws  Exception{
        StudentSourceDAO dao = new StudentSourceDAO();
        dao.add(new student(1, "Bob", 95));
        dao.add(new student(2, "Mary", 90));
        assertEquals(90, dao.getStudent(2).score);
    }
    @Test
    public void test_add_data4() throws  Exception{
        StudentSourceDAO dao = new StudentSourceDAO();
        dao.add(new student(1, "Bob", 95));
        dao.add(new student(2, "Mary", 90));
        assertEquals(null, dao.getStudent(3));
    }
    @Test
    public void test_add_data5() throws  Exception{
        StudentSourceDAO dao = new StudentSourceDAO();
        dao.add(new student(1, "Bob", 95));
        dao.add(new student(2, "Mary", 90));
        dao.update(new student(2,"SDSD",5));
        assertEquals(5, dao.getStudent(2).score);
    }
    @Test
    public void test_add_data6() throws  Exception{
        StudentSourceDAO dao = new StudentSourceDAO();
        dao.add(new student(1, "Bob", 95));
        dao.delete(2);//引數並非像查詢有student,所以不用new他拉
        assertEquals(null, dao.getStudent(1));
    }
}