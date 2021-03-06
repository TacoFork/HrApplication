package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Entity
//@Table(name = "employee_table")
public class Employee {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @Column(name = "email")
    @NotEmpty
    @NotNull
    private String email;

//    @Column(name = "firstName")
    @NotEmpty
    @NotNull
    private String firstName;

//    @Column(name = "lastName")
    @NotEmpty
    @NotNull
    private String lastName;

//    @Column(name = "department")
    @NotNull
    private long departmentId;

    public Employee() {
    }

    public Employee(@NotEmpty @NotNull String email, @NotEmpty @NotNull String firstName, @NotEmpty @NotNull String lastName, @NotNull long departmentId) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}
