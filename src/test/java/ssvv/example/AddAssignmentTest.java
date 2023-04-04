package ssvv.example;

import org.junit.Before;
import org.junit.Test;
import ssvv.example.domain.Tema;
import ssvv.example.repository.TemaRepository;
import ssvv.example.validation.TemaValidator;

public class AddAssignmentTest {
    private TemaRepository assignmentRepository;

    @Before
    public void initialize() {
        assignmentRepository = new TemaRepository(new TemaValidator());
    }

    @Test
    public void testIdNull() {
        assignmentRepository.save(new Tema(null, "ceva", 2, 1));
        var iterator = assignmentRepository.findAll().iterator();
        assert !iterator.hasNext();
    }

    @Test
    public void testIdEmpty() {
        assignmentRepository.save(new Tema("", "ceva", 2, 1));
        assert assignmentRepository.findOne("") == null;
    }

    @Test
    public void testIdValid() {
        assignmentRepository.save(new Tema("A1", "ceva", 2, 1));
        assert assignmentRepository.findOne("A1") != null;
    }
}
