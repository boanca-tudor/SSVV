package ssvv.example;

import org.junit.Before;
import org.junit.Test;
import ssvv.example.domain.Nota;
import ssvv.example.domain.Pair;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.repository.*;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;

public class IntegrationTest {
    private TemaXMLRepository assignmentRepository;
    private StudentXMLRepository studentRepository;
    private NotaXMLRepository gradeRepository;
    private Service service;

    @Before
    public void setUp() {
        assignmentRepository = new TemaXMLRepository(new TemaValidator(), "teme.xml");
        studentRepository = new StudentXMLRepository(new StudentValidator(), "studenti.xml");
        gradeRepository = new NotaXMLRepository(new NotaValidator(), "note.xml");
        service = new Service(studentRepository, assignmentRepository, gradeRepository);
    }

    @Test
    public void addStudentTest() {
        service.saveStudent("112", "gigel", 111);
        var students = service.findAllStudents();
        assert students.iterator().hasNext();
    }

    @Test
    public void addAssignmentTest() {
        service.saveTema("A1", "ceva", 2, 1);
        var assignments = service.findAllTeme();
        assert assignments.iterator().hasNext();
    }

    @Test
    public void addGradeTest() {
        int retval = service.saveNota("112", "A2", 9.00, 2, "Bravo");
        assert retval == -1;
    }

    @Test
    public void integrationTest() {
        service.saveStudent("112", "gigel", 111);
        var students = service.findAllStudents();
        assert students.iterator().hasNext();
        service.saveTema("A1", "ceva", 2, 1);
        var assignments = service.findAllTeme();
        assert assignments.iterator().hasNext();
        int retval = service.saveNota("112", "A1", 9.00, 2, "Bravo");
        assert retval != -1;

    }
}
