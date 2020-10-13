package people;

import people.features.membership.Membership;
import tools.Input;

import java.util.ArrayList;

public class Employee extends Person {

    private int birthYear;
    private String address;
    private double grossSalary;


    private final double MIN_SALARY = 100000.00;
    private final double BONUS_LOW = 4000.00;
    private final double BONUS_MEDIUM = 6000.00;
    private final double BONUS_HIGH = 7500.00;
    private final int FIRST_AGE_FOR_BONUS = 22;
    private final int SECOND_AGE_FOR_BONUS = 30;
    private final int MONTHS = 12;
    private final double TAX_DEDUCTION = .7; // removes 30% when you times something by this.

    private Input input = Input.getInstance();

    public Employee() {
    }

    public Employee(String name, int birthYear, String address, double salary) {
        super(name);
        this.birthYear = birthYear;
        this.grossSalary = salary;
        this.address = address;

    }

    public Employee addEmployee() {
        String name = input.getInput("Name: ");
        while (name.length() < 1){
            System.out.println();
            name = input.getInput("Invalid input, name can not be empty" + input.EOL + "Name: ");
        }
        int birthYear = input.getInt("Birth year: ");
        String address = input.getInput("Address: ");
        double monthlySalary = input.getDouble("Monthly gross salary: ");
        grossSalary = monthlySalary * MONTHS;
        Employee employee = new Employee(name, birthYear, address, grossSalary);
        return employee;
    }

    public double netSalary() {
        int age = Input.CURRENT_YEAR - birthYear;
        double netSalary = 0;
        if (grossSalary < MIN_SALARY) {
            netSalary = grossSalary;

        } else {
            if (grossSalary >= MIN_SALARY) {
                netSalary = grossSalary * TAX_DEDUCTION;
            }
        }
        double bonus;
        if (age < FIRST_AGE_FOR_BONUS) {
            bonus = BONUS_LOW;
            netSalary = netSalary + bonus;
        } else if (age == FIRST_AGE_FOR_BONUS && age < SECOND_AGE_FOR_BONUS) {
            bonus = BONUS_MEDIUM;
            netSalary = netSalary + bonus;
        } else if (age > SECOND_AGE_FOR_BONUS) {
            bonus = BONUS_HIGH;
            netSalary = netSalary + bonus;
        }
        return netSalary / MONTHS;
    }

    public double getSalary() {
        return netSalary();
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return input.DIVIDER + input.EOL + "ID: " + this.getId() + input.EOL + "Name: " + this.getName() + input.EOL + "Birth year: " + this.getBirthYear()
                + input.EOL + "Address: " + this.getAddress() + input.EOL + "salary: " + this.netSalary();
    }
}


