package com.chulos.travelagency.customer.domain.entity;

public class Customer {
    // attributes
    private int id;
    private String name;
    private String lastName;
    private int age;
    private String documentType;
    private int documentNumber;
    
    // empty constructor
    public Customer() {
    }
    
    // full constructor
    public Customer(int id, String name, String lastName, int age, String documentType, int documentNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    // constructor without id
    public Customer(String name, String lastName, int age, String documentType, int documentNumber) {
        this.id = 0;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }
    
    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }
}
