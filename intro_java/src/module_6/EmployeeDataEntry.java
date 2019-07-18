/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 6
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class Employee {
    // these can be private since they are set by the Employee class' setters.
    private Name name;
    private String employeeNumber;
    private Date hireDate;
    private Address address;


    // every setter has an equivalent getter.
    public void setName(String name) {
        this.name = new Name(name);
    }

    // since this method returns a Name object, we need to define a toString() method in the Name class. This will get used
    // when printing out the list of employees at the end of this program.
    public Name getName() {
        return this.name;
    }

    public Address getAddress() {
        return this.address;
    }

    public Date getHireDate() {
        return this.hireDate;
    }

    public String getEmployeeNumber() {
        return this.employeeNumber;
    }

    public void setEmployeeNumber(String number) {
        this.employeeNumber = number;
    }

    public void setHireDate(Date date) {
        this.hireDate = date;
    }

    public void setAddress(String street, String city, String state, String zip) {
        this.address = new Address(street, city, state, zip);
    }
}

class Date {
    private int month;
    private int day;
    private int year;

    Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }
}

class Name {
    String name;

    Name(String name) {
        this.name = name;
    }

    // this will be called during string concatention.
    public String toString() {
        return this.name;
    }

}

class Address {
    // these don't need to be public. We're encapsulating the data by using getters and setters.
    private String city;
    private String state;
    private String zip;
    private String street;

    Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String toString() {
        return (street + " / " + city + ", " + state + " / " + zip);
    }
}

public class EmployeeDataEntry {
    public static void main(String args[]) throws IOException {
        Employee[] employees = new Employee[100];
        System.out.println("Welcome to the Employee Data Entry System. You can enter up to 100 employees at at time.");

        String command;
        command = issueBeginningInstruction();

        int employeeIndex = 0;
        while (!command.equals("p")) {
            // this loop will create an employee object and set various properties using setters and add the employee to an array of employees.
            Employee employee = new Employee();

            System.out.println("Enter Employee Name");
            employee.setName(obtainInput());

            System.out.println("Enter Employee Number");
            employee.setEmployeeNumber(obtainInput());

            setHireDate(employee);

            setAddress(employee);

            // add the employee to the employees list.
            employees[employeeIndex] = employee;
            employeeIndex++;

            command = issueBeginningInstruction();
        }

        // print out all the employees and their data. The check is to prevent null point exception.

        printAllEmployees(employees);
    }

    public static void printAllEmployees(Employee[] employees) {
        for(Employee employee : employees) {
            if (employee != null) {
                System.out.println("Employee Employee Number: " + employee.getEmployeeNumber());
                System.out.println("Employee Name: " + employee.getName());
                System.out.println("Employee Address: " + employee.getAddress());
                System.out.println("Employee Hire Date: " + employee.getHireDate());
                System.out.println("---------------------");
            }
        }
    }

    public static String obtainInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input.trim().toLowerCase();
    }

    public static int obtainIntInput() {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        return input;
    }

    public static void setHireDate(Employee employee) {
        int monthInput = 0;
        boolean monthValid = false;
        while (monthValid == false) {
            System.out.println("Enter Employee hire date. First enter the month (number between 1-12)");
            monthInput = obtainIntInput();
            if (monthInput >= 1 && monthInput <= 12) {
                monthValid = true;
            } else {
                issueError("month");
            }
        }

        int dayInput = 0;
        boolean dayValid = false;
        while (dayValid == false) {
            System.out.println("Next Enter the hire day (number between 1-31)");
            dayInput = obtainIntInput();
            if (dayInput >= 1 && dayInput <= 31) {
                dayValid = true;
            } else {
                issueError("Day");
            }
        }

        int yearInput = 0;
        boolean yearValid = false;
        while (yearValid == false) {
            System.out.println("Next Enter the hire year (number between 1900-2020)");
            yearInput = obtainIntInput();
            if (yearInput >= 1900 && yearInput <= 2020) {
                yearValid = true;
            } else {
                issueError("year");
            }
        }

        // using the setter for hire date instead of setting it directly. Using the employee API for setting the hire date.
        employee.setHireDate(new Date(monthInput, dayInput, yearInput));
    }

    public static void setAddress(Employee employee) throws IOException {
        // get employee address
        String addressStreet = null;
        System.out.println("Enter Employee Address. Start with the street.");
        addressStreet = obtainInput();

        String addressCity = null;
        System.out.println("Enter Employee city.");
        addressCity = obtainInput();

        String addressState = null;
        boolean stateValid = false;
        while (stateValid == false) {
            System.out.println("Enter employee state.");
            addressState = obtainInput();

            if (addressState.length() == 2) {
                stateValid = true;
            } else {
                issueError("state");
            }
        }

        String addressZip = null;
        boolean zipValid = false;
        while (zipValid == false) {
            System.out.println("Enter employee zip.");
            addressZip = obtainInput();

            if (addressZip.length() == 5) {
                zipValid = true;
            } else {
                issueError("zip");
            }
        }

        employee.setAddress(addressStreet, addressCity, addressState, addressZip);
    }

    public static void issueError(String invalid) {
        System.out.println(invalid + " is invalid");
    }

    public static String issueBeginningInstruction() throws IOException {
        System.out.println("Enter 'p' to print out employees or 'e' to enter employee data");
        return obtainInput().toLowerCase();
    }
}

