package tm.itbachelors.projectstore.controller;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tm.itbachelors.projectstore.model.Client;
import tm.itbachelors.projectstore.model.Employee;
import tm.itbachelors.projectstore.model.Section;
import tm.itbachelors.projectstore.model.Store;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class MainController{

    private ArrayList<Employee> employeeArrayList;
    private ArrayList<Client> clientArrayList;
    private ArrayList<Store> storeArrayList;

    @PostConstruct
    private void fillData() {
        employeeArrayList = fillEmployees();
        clientArrayList = fillClients();
        storeArrayList = fillStores();
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/client-details")
    public String clientDetails(HttpServletRequest request, Model model) {
        int clientId = Integer.parseInt(request.getParameter("clientId"));

        Client client = clientArrayList.get(clientId);

        model.addAttribute("client", client);

        return "0_exam";
    }

    @RequestMapping("/new-client")
    public String newClient(Model model) {
        model.addAttribute("employees", employeeArrayList);
        model.addAttribute("stores", storeArrayList);
        return "1_newClient";
    }

    @RequestMapping("/create-client")
    public String createClient(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surName");
        String yearOfBirthStr = request.getParameter("yearOfBirth");
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        int weeklyVisits = Integer.parseInt(request.getParameter("weeklyVisits"));
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        if (employeeId < 0) {
            model.addAttribute("errorMessage", "You didn't choose a contact person!");
            return "error";
        }

        int yearOfBirth = Integer.parseInt(yearOfBirthStr);

        Client client = new Client(firstName, surName);
        client.setYearOfBirth(yearOfBirth);
        client.setVisitsPerWeek(weeklyVisits);

        Employee employee = employeeArrayList.get(employeeId);

        client.setContactPerson(employee);

        Store selectedStore = storeArrayList.get(storeId);
        selectedStore.registerCustomer(client);

        clientArrayList.add(client);

        model.addAttribute("clientDetails", client.toString());
        model.addAttribute("contactPerson", client.getContactPerson());

        return "2_clientDetails";
    }

    @RequestMapping("/new-employee")
    public String newEmployee() {
        return "3_newEmployee";
    }

    @RequestMapping("/create-employee")
    public String createEmployee(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surName");
        String startDateStr = request.getParameter("startDate");
        boolean jobStudent = request.getParameter("jobStudent") != null;

        Employee employee = new Employee(firstName, surName);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, dtf);
        employee.setStartDate(startDate);
        employee.setJobStudent(jobStudent);

        employeeArrayList.add(employee);

        model.addAttribute("employeeDetails", employee.toString());

        return "4_employeeDetails";
    }

    @RequestMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeArrayList);
        return "5_listEmployees";
    }

    @RequestMapping("/clients")
    public String listClients(Model model) {
        model.addAttribute("clients", clientArrayList);
        return "6_listClients";
    }

    @RequestMapping("/new-store")
    public String newStore() {
        return "7_newStore";
    }

    @RequestMapping("/create-store")
    public String createStore(HttpServletRequest request, Model model) {
        String storeName = request.getParameter("storeName");

        Store newStore = new Store(storeName);
        storeArrayList.add(newStore);

        model.addAttribute("stores", storeArrayList);
        return "8_listStores";
    }

    @RequestMapping("/stores")
    public String listStores(Model model) {
        model.addAttribute("stores", storeArrayList);
        return "8_listStores";
    }

    @RequestMapping("/new-section")
    public String newSection(Model model) {
        model.addAttribute("employees", employeeArrayList);
        model.addAttribute("stores", storeArrayList);
        return "9_newSection";
    }

    @RequestMapping("/create-section")
    public String createSection(HttpServletRequest request, Model model) {
        String sectionName = request.getParameter("sectionName");
        String picture = "/img/" + request.getParameter("picture");
        boolean cooled = request.getParameter("cooled") != null;
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        if (storeId < 0 && employeeId < 0) {
            model.addAttribute("errorMessage", "You didn't choose a store and employee!");
            return "error";
        }

        else if (storeId < 0) {
            model.addAttribute("errorMessage", "You didn't choose a store!");
            return "error";
        }

        else if (employeeId < 0) {
            model.addAttribute("errorMessage", "You didn't choose an employee!");
            return "error";
        }

        Section newSection = new Section(sectionName);
        newSection.setPicture(picture);
        newSection.setCooled(cooled);

        Store store = storeArrayList.get(storeId);

        store.addSection(newSection);

        Employee employee = employeeArrayList.get(employeeId);

        newSection.setResponsible(employee);

        model.addAttribute("storeName", store.getName());
        model.addAttribute("sections", store.getSectionList());
        return "10_sectionDetails";
    }

    @RequestMapping("/sectionDetails")
    public String sectionDetails(HttpServletRequest request,Model model) {
        int storeId = Integer.parseInt(request.getParameter("storeId"));

        Store store = storeArrayList.get(storeId);
        model.addAttribute("storeName", store.getName());
        model.addAttribute("sections", store.getSectionList());
        return "10_sectionDetails";
    }

    @RequestMapping("/search-section")
    public String searchSection(HttpServletRequest request, Model model) {
        String sectionName = request.getParameter("sectionName");

        Section foundSection = null;
        Store foundStore = null;

        for (Store store : storeArrayList) {
            Section section = store.searchSectionByName(sectionName);
            if (section != null) {
                foundStore = store;
                foundSection = section;
                break;
            }
        }

        if (foundSection != null) {
            model.addAttribute("storeName", foundStore.getName());
            model.addAttribute("section", foundSection);
            return "11_sectionSearch";
        } else {
            model.addAttribute("errorMessage", "There is no section with the name '" + sectionName + "'");
            return "error";
        }
    }

   private ArrayList<Employee> fillEmployees() {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        Employee employee1 = new Employee("Johan", "Bertels");
        employee1.setStartDate(LocalDate.of(2002, 5, 1));
        Employee employee2 = new Employee("An", "Van Herck");
        employee2.setStartDate(LocalDate.of(2019, 3, 15));
        employee2.setJobStudent(true);
        Employee employee3 = new Employee("Bruno", "Coenen");
        employee3.setStartDate(LocalDate.of(1995,1,1));
        Employee employee4 = new Employee("Wout", "Dayaert");
        employee4.setStartDate(LocalDate.of(2002, 12, 15));
        Employee employee5 = new Employee("Louis", "Petit");
        employee5.setStartDate(LocalDate.of(2020, 8, 1));
        employee5.setJobStudent(true);
        Employee employee6 = new Employee("Jean", "Pinot");
        employee6.setStartDate(LocalDate.of(1999,4,1));
        Employee employee7 = new Employee("Ahmad", "Bezeri");
        employee7.setStartDate(LocalDate.of(2009, 5, 1));
        Employee employee8 = new Employee("Hans", "Volzky");
        employee8.setStartDate(LocalDate.of(2015, 6, 10));
        employee8.setJobStudent(true);
        Employee employee9 = new Employee("Joachim", "Henau");
        employee9.setStartDate(LocalDate.of(2007,9,18));
        employeeArrayList.add(employee1);
        employeeArrayList.add(employee2);
        employeeArrayList.add(employee3);
        employeeArrayList.add(employee4);
        employeeArrayList.add(employee5);
        employeeArrayList.add(employee6);
        employeeArrayList.add(employee7);
        employeeArrayList.add(employee8);
        employeeArrayList.add(employee9);
        return employeeArrayList;
    }

    private ArrayList<Client> fillClients() {
        ArrayList<Client> clientArrayList = new ArrayList<>();
        Client client1 = new Client("Dominik", "Mioens");
        client1.setYearOfBirth(2001);
        client1.setVisitsPerWeek(2);
        client1.setContactPerson(employeeArrayList.get(3));
        Client client2 = new Client("Zion", "Noops");
        client2.setYearOfBirth(1996);
        client2.setVisitsPerWeek(5);
        client2.setContactPerson(employeeArrayList.get(4));
        Client client3 = new Client("Maria", "Bonetta");
        client3.setYearOfBirth(1998);
        client3.setVisitsPerWeek(7);
        client3.setContactPerson(employeeArrayList.get(2));
        Client client4 = new Client("Daan", "Borghs");
        client4.setYearOfBirth(2004);
        client4.setVisitsPerWeek(45);
        client4.setContactPerson(employeeArrayList.get(5));
        clientArrayList.add(client1);
        clientArrayList.add(client2);
        clientArrayList.add(client3);
        clientArrayList.add(client4);
        clientArrayList.get(0).addToShoppingList("Butter");
        clientArrayList.get(0).addToShoppingList("Bread");
        clientArrayList.get(1).addToShoppingList("Apple");
        clientArrayList.get(1).addToShoppingList("Banana");
        clientArrayList.get(1).addToShoppingList("Grapes");
        clientArrayList.get(1).addToShoppingList("Oranges");
        clientArrayList.get(2).addToShoppingList("Fish");
        clientArrayList.get(3).addToShoppingList("Milk");
        clientArrayList.get(3).addToShoppingList("Fish");
        clientArrayList.get(3).addToShoppingList("Bread");
        return clientArrayList;
    }

    private ArrayList<Store> fillStores() {
        ArrayList<Store> storeArrayList = new ArrayList<>();
        Store store1 = new Store("Delhaize");
        Store store2 = new Store("Colruyt");
        Store store3 = new Store("Albert Heyn");
        Section section1 = new Section("Fruit");
        Section section2 = new Section("Bread");
        Section section3 = new Section("Vegetables");
        section1.setPicture("/img/fruit.jpg");
        section2.setPicture("/img/bread.jpg");
        section3.setPicture("/img/vegetables.jpg");
        section1.setResponsible(employeeArrayList.get(0));
        section2.setResponsible(employeeArrayList.get(1));
        section3.setResponsible(employeeArrayList.get(2));
        section1.setCooled(true);
        section2.setCooled(true);
        store1.addSection(section1);
        store1.addSection(section2);
        store1.addSection(section3);
        store2.addSection(section1);
        store2.addSection(section2);
        store3.addSection(section1);
        store3.addSection(section3);
        storeArrayList.add(store1);
        storeArrayList.add(store2);
        storeArrayList.add(store3);
        return storeArrayList;
    }
}
