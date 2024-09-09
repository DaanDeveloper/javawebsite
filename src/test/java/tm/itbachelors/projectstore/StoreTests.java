package tm.itbachelors.projectstore;

import tm.itbachelors.projectstore.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class StoreTests {
    /**
     * Test of constructor en getters
     */
    @Test
    public void testConstructorEnGetters() {
        Store store = new Store("Colruyt");
        assertEquals("Colruyt", store.getName());
    }

    /**
     * Test of setName method, of class Store.
     */
    @Test
    public void testSetName() {
        Store store = new Store("Colruyt");
        store.setName("Delhaize");
        assertEquals("Delhaize", store.getName());
    }

    /**
     * check constructor of Store + adding of Section check if you can
     * find an Section by name
     */
    @Test
    public void checkAddSectionsAndGetNumber() {
        Store store1 = new Store("Colruyt");
        assertEquals(0, store1.getNumberOfSections());
        Section section = new Section("Fruit");
        store1.addSection(section);
        assertEquals(1, store1.getNumberOfSections());
        Section section1 = new Section("Vegetables");
        store1.addSection(section1);
        assertEquals(2, store1.getNumberOfSections());
        Section[] array = new Section[2];
        array[0] = section;
        array[1] = section1;
        assertArrayEquals(array, store1.getSectionList().toArray());
    }

    /**
     * Test of search by Name
     */
    @Test
    public void testSearchSectionbyName() {
        Store store2 = new Store("Delhaize");
        assertEquals(0, store2.getNumberOfSections());
        Section vegetables = new Section("Vegetables");
        store2.addSection(vegetables);
        assertEquals(1, store2.getNumberOfSections());
        Section fruit = new Section("Fruit");
        store2.addSection(fruit);
        assertEquals(2, store2.getNumberOfSections());
        assertNotNull(store2.searchSectionByName("Fruit"));
        assertEquals(fruit.getName(), store2.searchSectionByName("Fruit").getName());
        assertNull(store2.searchSectionByName("blabla"));
    }

    /**
     * before a Client can make his shopping, he has to register to the
     * Store as result of this registration he will receive a card number
     */
    @Test
    public void testRegisterCustomer() {
        Client client = new Client("Donald", "Duck");
        Store colruyt = new Store("Colruyt");
        colruyt.registerCustomer(client);
        assertEquals("Co1", client.getCardNumber());
        Client client1 = new Client("Mickey", "Mouse");
        colruyt.registerCustomer(client1);
        assertEquals("Co2", client1.getCardNumber());
        assertEquals(2, colruyt.getNumberCustomers());
    }
}
