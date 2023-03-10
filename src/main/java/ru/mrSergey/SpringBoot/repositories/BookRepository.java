package ru.mrSergey.SpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrSergey.SpringBoot.models.Book;



@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
