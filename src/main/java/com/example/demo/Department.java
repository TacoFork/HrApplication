package com.example.demo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class Department {

    @NotEmpty
    @NotNull
    private String name;

    private long id;
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(Employee employee){
        this.employeeList.add(employee);
    }

    public void updateEmployee(Employee employee){
        for (int index = 0; index < this.employeeList.size(); index++){
            if (this.employeeList.get(index).getId() == employee.getId()){
                this.employeeList.set(index, employee);
            }
        }
    }
}
