package ru.mrSergey.SpringBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    private  String name;

    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private  int year;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id")
    private Person owner;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }
    public Book(){}
}
