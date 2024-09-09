package tm.itbachelors.projectstore;

import tm.itbachelors.projectstore.model.Section;
import tm.itbachelors.projectstore.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SectionTests {
    /**
     * Test of constructor en getters
     */
    @Test
    public void testConstructorEnGetters() {
        Section section = new Section("Fruit");
        assertEquals("Fruit", section.getName());
        Section section1 = new Section();
        assertEquals(null, section1.getName());
        Section section2 = new Section("Meat");
        assertEquals("Meat", section2.getName());
    }

    /**
     * Test of setName method, of class Section.
     */
    @Test
    public void testSetNaam() {
        Section section = new Section();
        section.setName("Fruit");
        assertEquals("Fruit", section.getName());
    }


    /**
     * Test of setPhoto method, of class Section.
     */
    @Test
    public void testSetPhoto() {
        Section section = new Section("Fruit");
        section.setPicture("testphoto.jpg");
        assertEquals("testphoto.jpg", section.getPicture());
    }

    /**
     * Test of setResponsible method, of class Section.
     */
    @Test
    public void testResponsible() {
        Section section = new Section("Fruit");
        Employee Mickey = new Employee("Mickey", "Mouse");
        section.setResponsible(Mickey);
        assertEquals(Mickey, section.getResponsible());
    }
}
