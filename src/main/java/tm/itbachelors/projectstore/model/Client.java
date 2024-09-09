package tm.itbachelors.projectstore.model;

// Daan Borghs r0986005

import java.util.ArrayList;

public class Client extends Person {
    private String cardNumber;
    private int yearOfBirth;

    private int visitsPerWeek;

    private Employee contactPerson;
    private ArrayList<String> shoppingList = new ArrayList<>();

    public Client(String firstName, String surName) {
        super(firstName, surName);
        this.cardNumber = "undefined";
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        if (this.cardNumber.equals("undefined")) {
            this.cardNumber = cardNumber;
        }
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public ArrayList<String> getShoppingList() {
        return shoppingList;
    }

    public int getVisitsPerWeek() {
        return visitsPerWeek;
    }

    public void setVisitsPerWeek(int visitsPerWeek) {
        this.visitsPerWeek = visitsPerWeek;
    }

    public Employee getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Employee contactPerson) {
        this.contactPerson = contactPerson;
    }

    public boolean addToShoppingList(String productName) {
        if (shoppingList.size() < 5) {
            return shoppingList.add(productName);
        } else {
            return false;
        }
    }

    public int getNumberOnShoppingList() {
        return shoppingList.size();
    }

    public String toString() {
        return "Client " + super.toString() + " with card number " + cardNumber + "(Expected weekly visits: " + getVisitsPerWeek() + ")";
    }
}