package ssvv.example;

import org.junit.Before;
import org.junit.Test;
import ssvv.example.domain.Student;
import ssvv.example.repository.StudentRepository;
import ssvv.example.validation.StudentValidator;

import java.util.Iterator;

public class AddStudentTest {
    StudentRepository repository;

    @Before
    public void initialize() {
        repository = new StudentRepository(new StudentValidator());
    }

    @Test
    public void testGroupTrue() {
        repository.save(new Student("112", "gigel", 111));
        assert repository.findOne("112") != null;

    }

    @Test
    public void testGroupFalse() {
        repository.save(new Student("113", "gigel", 110));
        assert repository.findOne("113") == null;
    }

    @Test
    public void testIdTrue() {
        repository.save(new Student("112", "gigel", 111));
        assert repository.findOne("112") != null;
    }

    @Test
    public void testIdFalse() {
        repository.save(new Student("", "gigel", 112));
        assert repository.findOne("") == null;
    }

    @Test
    public void testIdNull() {
        repository.save(new Student(null, "gigel", 113));
        Iterator<Student> iterator = repository.findAll().iterator();
        assert !iterator.hasNext();
    }

    @Test
    public void testNumeTrue() {
        repository.save(new Student("112", "gigel", 111));
        assert repository.findOne("112") != null;
    }

    @Test
    public void testNumeFalse() {
        repository.save(new Student("112", "", 112));
        assert repository.findOne("112") == null;
    }

    @Test
    public void testNumeNull() {
        repository.save(new Student("112", null, 113));
        assert repository.findOne("112") == null;
    }
}
