package pl.gornik.szynal.management;

import pl.gornik.szynal.tasks.AssemblyLineEmployee;

import java.util.*;

public class EmployeeTasks {
    private List<AssemblyLineEmployee> employees = new ArrayList<>();


    public void addEmployee(AssemblyLineEmployee employee) {
        employees.add(employee);
    }


    public void checkEmployeesStatus() {
        Random rand = new Random();
        for (AssemblyLineEmployee employee : employees) {

            String status = rand.nextBoolean() ? "Zakończono montaż" : "W trakcie montażu";
            System.out.println("Pracownik " + employee.getName() + ": " + status);
        }
    }
}
