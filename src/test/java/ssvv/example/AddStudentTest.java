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

    @Test
    public void testIdValidBVA() {
        repository.save(new Student("112", "gigel", 113));
        assert repository.findOne("112") != null;
        repository.save(new Student("1", "mirel", 113));
        assert repository.findOne("1") != null;
        repository.save(new Student("abc", "vasile", 113));
        assert repository.findOne("abc") != null;
    }

    @Test
    public void testIdInvalidBVA() {
        repository.save(new Student("", "gigel", 113));
        assert repository.findOne("") == null;
        repository.save(new Student(null, "mirel", 113));
        Iterator<Student> iterator = repository.findAll().iterator();
        assert !iterator.hasNext();
    }

    @Test
    public void testNameValidBVA() {
        repository.save(new Student("112", "gigel", 113));
        assert repository.findOne("112") != null;
        repository.save(new Student("113", "a", 113));
        assert repository.findOne("113") != null;
        repository.save(new Student("114", "i0n", 113));
        assert repository.findOne("114") != null;
    }

    @Test
    public void testNameInvalidBVA() {
        repository.save(new Student("113", null, 113));
        assert repository.findOne("113") == null;
        repository.save(new Student("114", "", 113));
        assert repository.findOne("114") == null;
    }

    @Test
    public void testGroupLowerBound() {
        repository.save(new Student("113", "gigel", 110));
        assert repository.findOne("113") == null;
        repository.save(new Student("114", "mirel", 111));
        assert repository.findOne("114") != null;
        repository.save(new Student("115", "vasile", 112));
        assert repository.findOne("115") != null;
    }

    @Test
    public void testGroupUpperBound() {
        repository.save(new Student("113", "gigel", 936));
        assert repository.findOne("113") != null;
        repository.save(new Student("114", "mirel", 937));
        assert repository.findOne("114") != null;
        repository.save(new Student("115", "vasile", 938));
        assert repository.findOne("115") == null;
    }
}
