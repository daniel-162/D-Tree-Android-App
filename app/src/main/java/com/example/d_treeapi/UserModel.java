package com.example.d_treeapi;

public class UserModel {

     private String id;
     private String name;
     private String surname;
     private int age;
     private String city;

    public UserModel(String id, String name, String surname, int age, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
    }

    public UserModel() {
    }

    @Override
    public String toString() {
        return  name + ' '  + surname + '\n' +
                "Age:" + age + '\n' +
                "City:" + city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
