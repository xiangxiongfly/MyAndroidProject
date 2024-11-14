package com.example.java_design_pattern.prototype;

public class Person implements Cloneable {
    private String name;
    private int age;
    private Address address = new Address();

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String province, String city) {
        address.setProvince(province);
        address.setCity(city);
    }

    @Override
    protected Person clone() {
        Person person = null;
        try {
            person = (Person) super.clone();
            person.address = this.address.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void show() {
        System.out.println(name + " - " + age + " - " + address);
    }
}

class Address implements Cloneable {
    private String province;
    private String city;

    public Address() {
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    protected Address clone() {
        Address address = null;
        try {
            address = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}