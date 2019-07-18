/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 7
*/

import java.io.IOException;

class Employee {
    // these can be private since they are set by the Employee class' constructor and are obtained using getters.
    private Name name;
    private String employeeNumber;
    private Date hireDate;
    private Address address;

    // need a base constructor for the subclasses unlike assignment 6 where I chose to set these using setters.
    Employee(String name, String employeeNumber, String street, String city, String state, String zip, int hireMonth, int hireDay, int hireYear) {
        this.address = new Address(street, city, state, zip);
        this.name = new Name(name);
        this.employeeNumber = employeeNumber;
        this.hireDate = new Date(hireMonth, hireDay, hireYear);
    }

    // since this method returns a Name object, we need to define a toString() method in the Name class. This will get used
    // when printing out the list of employees at the end of this program.
    // Can also be protected since it's only going to be used by the subclasses in their toString method.
    protected String getName() {
        return this.name.getName();
    }
    // again, Address will need a toString() method.
    protected Address getAddress() {
        return this.address;
    }

    protected Date getHireDate() {
        return this.hireDate;
    }

    protected String getEmployeeNumber() {
        return this.employeeNumber;
    }
}

// an HourlyEmployee 'is a' Employee, so it inherits from Employee
class HourlyEmployee extends Employee {
    // we will expose this via a getter and they are set via the constructor
    private double hoursWorked;
    private double earnings;
    private double hourlyRate;

    HourlyEmployee(String name, String employeeNumber, String street, String city, String state, String zip, int hireMonth, int hireDay, int hireYear, double hoursWorked, double hourlyRate) {
        // leverage the base class' constructor
        super(name, employeeNumber, street, city, state, zip, hireMonth, hireDay, hireYear);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.earnings = calculateEarnings();
    }

    public double calculateEarnings() {
        if (hoursWorked > 40) {
            double overtime = hoursWorked - 40;
            return (40 * hoursWorked) + (overtime * hoursWorked * 1.5);
        }

        return hoursWorked * hourlyRate;
    }

    public double getEarnings() {
        return this.earnings;
    }

    public double getHoursWorked() {
        return this.hoursWorked;
    }
    public double getHourlyRate() {
        return this.hourlyRate;
    }
}


// a SalariedEmployee 'is a' Employee, so it inherits from Employee
class SalariedEmployee extends Employee {
    private int annualSalary;

    SalariedEmployee(String name, String employeeNumber, String street, String city, String state, String zip, int hireMonth, int hireDay, int hireYear, int annualSalary) {
        // leverage the base class' constructor
        super(name, employeeNumber, street, city, state, zip, hireMonth, hireDay, hireYear);
        this.annualSalary = annualSalary;
    }

    public String toString() {
        return (this.getName());
//                + this.getAddress() +
//        this.getHireDate() +
//        this.getEmployeeNumber() +
//        this.annualSalary);
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
    private String name;

    Name(String name) {
        this.name = name;
    }

    public String getName() {
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

public class EmployeeWageCalculator {
    public static void main(String args[]) throws IOException {
        SalariedEmployee se = new SalariedEmployee("first hourly employee", "24601", "133 north st", "NYC", "NY", "10001", 06, 30, 2000, 100000);;
        HourlyEmployee he1 = new HourlyEmployee("first hourly employee", "24601", "133 north st", "NYC", "NY", "10001", 06, 30, 2000, 2000, 50.50);
        HourlyEmployee he2 = new HourlyEmployee("second hourly employee", "24601", "134 north st", "NYC", "NY", "10002", 06, 29, 2001, 2500, 50);

        Employee employees[] = {se, he1, he2};

        for (Employee employee : employees) {
            outputEmployeeData(employee);
        }

    }

    public static void outputEmployeeData(Employee employee) {
        System.out.println(employee);
    }
}
