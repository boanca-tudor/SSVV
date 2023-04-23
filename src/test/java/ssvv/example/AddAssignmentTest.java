package ssvv.example;

import org.junit.Before;
import org.junit.Test;
import ssvv.example.domain.Tema;
import ssvv.example.repository.TemaRepository;
import ssvv.example.validation.TemaValidator;

import java.util.Iterator;

public class AddAssignmentTest {
    private TemaRepository repository;

    @Before
    public void initialize() {
        repository = new TemaRepository(new TemaValidator());
    }

    @Test
    public void testIdNull() {
        repository.save(new Tema(null, "ceva", 2, 1));
        Iterator<Tema> iterator = repository.findAll().iterator();
        assert !iterator.hasNext();
    }

    @Test
    public void testDescriptionEmpty() {
        repository.save(new Tema("A1", "", 2, 1));
        assert repository.findOne("A1") == null;
    }

    @Test
    public void testDescriptionNull() {
        repository.save(new Tema("A1", null, 2, 1));
        assert repository.findOne("A1") == null;
    }

    @Test
    public void testDeadlineBeforeInterval() {
        repository.save(new Tema("A1", "ceva", 0, 1));
        assert repository.findOne("A1") == null;
    }

    @Test
    public void testDeadlineAfterInterval() {
        repository.save(new Tema("A1", "ceva", 15, 1));
        assert repository.findOne("A1") == null;
    }

    @Test
    public void testDeadlineBeforeStartline() {
        repository.save(new Tema("A1", "ceva", 1, 2));
        assert repository.findOne("A1") == null;
    }

    @Test
    public void testStartlineBeforeInterval() {
        repository.save(new Tema("A1", "ceva", 2, 0));
        assert repository.findOne("A1") == null;
    }

    @Test
    public void testStartlineAfterInterval() {
        repository.save(new Tema("A1", "ceva", 2, 15));
        assert repository.findOne("A1") == null;
    }

    @Test
    public void testStartlineAfterDeadline() {
        repository.save(new Tema("A1", "ceva", 2, 3));
        assert repository.findOne("A1") == null;
    }
}
