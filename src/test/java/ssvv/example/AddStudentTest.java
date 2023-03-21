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
    public void testGroup() {
        repository.save(new Student("112", "gigel", 111));
        assert repository.findOne("112") != null;
        repository.save(new Student("113", "gigel", 110));
        assert repository.findOne("113") == null;
    }

    @Test
    public void testId() {
        repository.save(new Student("112", "gigel", 111));
        assert repository.findOne("112") != null;
        repository.save(new Student("", "gigel", 112));
        assert repository.findOne("") == null;
        repository.save(new Student(null, "gigel", 113));
        Iterator<Student> iterator = repository.findAll().iterator();
        iterator.next();
        assert !iterator.hasNext();
    }
}
