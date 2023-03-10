package ru.mrSergey.SpringBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
    public Person(){}
}