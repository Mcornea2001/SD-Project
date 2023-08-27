package com.example.Assignment3.model;

import jakarta.persistence.*;

@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(name = "cuid")
    private Integer cuid;
    private String name;
    private String email;
    private String phone;
    private int age;
    private String username;
    private String password;

    public Customer() {
    }

    public Customer(Integer cuid,
                    String name,
                    String email,
                    String phone,
                    int age,
                    String username,
                    String password) {
        this.cuid = cuid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public Customer(String name,
                    String email,
                    String phone,
                    int age,
                    String username,
                    String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer id) {
        this.cuid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
