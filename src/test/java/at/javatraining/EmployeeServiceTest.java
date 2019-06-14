package at.javatraining;

import at.technikumwien.Employee;
import at.technikumwien.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EntityManager em;
    @InjectMocks // vor jedem Test werden die Variablen gesetzt (wie ein beforeEach)
    private EmployeeService employeeService;

    private Employee emp;

    @BeforeEach
    public void setUp() {
        emp = new Employee();
    }

    @Test
    public void testMocks() {
        assertNotNull(em);
        assertNotNull(employeeService);
        assertSame(em, employeeService.getEm());
        assertNull(em.merge(null));
    }

    @Test
    public void testSaveWithCreate() {
        employeeService.save(emp);

        verify(em, times(1)).persist(emp); // die Methode persist mit Param emp ist genau 1 mal aufgerufen worden. times(1) kann weggelassen werden (ist default)
//        verify(em, times(1)).persist(any()); // die Methode persist ist mit irgendeinem Parameter genau 1 mal aufgerufen worden
        verify(em, never()).merge(emp); // ist hier redundant, aber prüft, dass merge nicht aufgerufen wurde
        verifyNoMoreInteractions(em); // abgesehen von persist ist mit "emp" nichts mehr passiert
    }

    @Test
    public void testSaveWithUpdate() {
        emp.setId(1L); // macht aus dem Integer ein Long

        when(em.merge(emp)).thenReturn(emp); // em.merge liefert ab jetzt bei jedem Aufruf "emp" zurück. Wäre ansonsten NULL
//        doReturn(emp).when(em).merge(emp); // macht das gleiche wie die Zeile darüber

        var empSaved = employeeService.save(emp);

        assertSame(emp, empSaved);
        verify(em).merge(emp);
        verifyNoMoreInteractions(em);
    }
}
