package com.example.applicationform;

public class Person {
    private final String name;
    private final String fatherName;
    private final String cnic;
    private final String date;
    private final String gender;
    private final String city;

    public Person(String name, String fatherName, String cnic, String date, String gender, String city) {
        this.name = name;
        this.fatherName = fatherName;
        this.cnic = cnic;
        this.date = date;
        this.gender = gender;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | " +"Father Name: "+fatherName + " | " +"CNIC: "+ cnic
                + " | " +"Date: "+ date + " | " +"Gender: " +gender + " | " +"City: "+ city;
    }
}