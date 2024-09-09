package tm.itbachelors.projectstore;

import tm.itbachelors.projectstore.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientTests {
    /**
     * Test of constructor en getters
     */
    @Test
    public void testConstructorEnGetters() {

        Client client = new Client("Donald", "Duck");
        assertEquals("Donald", client.getFirstName());
        assertEquals("Duck", client.getSurName());
        assertEquals("undefined", client.getCardNumber());
        assertEquals(0, client.getYearOfBirth());
    }

    /**
     * Test of setFirstName method, of class Client.
     */
    @Test
    public void testSetFirstName() {
        Client client = new Client("Donald", "Duck");
        client.setFirstName("Mickey");
        assertEquals("Mickey", client.getFirstName());
    }

    /**
     * Test of setSurName method, of class Client.
     */
    @Test
    public void testSetSurName() {
        Client client = new Client("Donald", "Duck");
        client.setSurName("Mouse");
        assertEquals("Mouse", client.getSurName());
    }

    /**
     * Test of setCardNumber method, of class Client.
     */
    @Test
    public void testSetCardNumber() {
        Client client = new Client("Donald", "Duck");
        client.setCardNumber("undefined");
        assertEquals("undefined", client.getCardNumber());
    }

    /**
     * Test of setYearOfBirth of class Client.
     */
    @Test
    public void testSetYearOfBirth() {
        Client client = new Client("Donald", "Duck");
        client.setYearOfBirth(2015);
        assertEquals(2015, client.getYearOfBirth());
    }

    /**
     * check basic functionality of Client: constructor, setters/getters and
     * adding product to shoppinglist
     */
    @Test
    public void testShoppingListBasics() {
        Client client = new Client("Donald", "Duck");
        assertEquals(0, client.getNumberOnShoppingList());
        assertTrue(client.addToShoppingList("Butter"));
        assertEquals(1, client.getNumberOnShoppingList());
    }

    /**
     * Visitor can add maximum 5 products names to shoppinglist
     */
    @Test
    public void testShoppingListMax5() {
        Client client = new Client("Donald", "Duck");
        assertEquals(0, client.getNumberOnShoppingList());
        assertTrue(client.addToShoppingList("Butter"));
        assertEquals(1, client.getNumberOnShoppingList());
        assertTrue(client.addToShoppingList("Cheese"));
        assertEquals(2, client.getNumberOnShoppingList());
        assertTrue(client.addToShoppingList("Fish"));
        assertEquals(3, client.getNumberOnShoppingList());
        assertTrue(client.addToShoppingList("Bread"));
        assertEquals(4, client.getNumberOnShoppingList());
        assertTrue(client.addToShoppingList("Apple"));
        assertEquals(5, client.getNumberOnShoppingList());
        assertFalse(client.addToShoppingList("Banana"));
        assertEquals(5, client.getNumberOnShoppingList());
    }

    /**
     * Test of toString method, of class Client.
     */
    @Test
    public void testToString() {
        Client donald = new Client("Donald", "Duck");
        donald.setCardNumber("undefined");
        assertEquals("Client DUCK Donald with card number undefined", donald.toString());
    }
}
