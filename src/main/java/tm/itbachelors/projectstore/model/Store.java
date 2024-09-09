package tm.itbachelors.projectstore.model;

// Daan Borghs r0986005

import java.util.ArrayList;

public class Store {
    private String name;
    private int numberCustomers;
    private ArrayList<Section> sectionList = new ArrayList<>();

    public Store(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberCustomers() {
        return numberCustomers;
    }

    public ArrayList<Section> getSectionList() {
        return sectionList;
    }

    public int getNumberOfSections() {
        return sectionList.size();
    }

    public void addSection(Section section) {
        sectionList.add(section);
    }

    public Section searchSectionByName(String name) {
        for (Section section : sectionList) {
            if (section.getName().equals(name)) {
                return section;
            }
        }
        return null;
    }

    public void registerCustomer(Client client) {
        numberCustomers++;
        String cardNumber = name.substring(0, 2) + numberCustomers;
        client.setCardNumber(cardNumber);
    }
}