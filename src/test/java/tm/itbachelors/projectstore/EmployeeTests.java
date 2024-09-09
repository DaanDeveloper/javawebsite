package tm.itbachelors.projectstore;

import tm.itbachelors.projectstore.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class EmployeeTests {

    /**
     * Test of constructor en getters
     */
    @Test
    public void testConstructorEnGetters() {
        Employee employee = new Employee("Juul", "Kabas");
        assertEquals("Juul", employee.getFirstName());
        assertEquals("Kabas", employee.getSurName());
        assertEquals(false, employee.isJobStudent());
        assertEquals(LocalDate.now(), employee.getStartDate());
    }


    /**
     * Test1 of toString method, of class Employee.
     */
    @Test
    public void testToString() {
        Employee donald = new Employee("Donald", "Duck");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        assertEquals("Employee DUCK Donald is employed since " + LocalDate.now().format(dtf) , donald.toString());
        Employee minnie = new Employee("Minnie", "Mouse");
        minnie.setJobStudent(true);
        assertEquals("Employee MOUSE Minnie (job student) is employed since " + LocalDate.now().format(dtf) , minnie.toString());
    }

    /**
     * Test2 of toString method, of class Employee.
     */
    @Test
    public void testToString2() {
        Employee donald = new Employee("Donald", "Duck");
        donald.setStartDate(LocalDate.of(1999,2,25));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        assertEquals("Employee DUCK Donald is employed since " + donald.getStartDate().format(dtf) , donald.toString());
    }

}
