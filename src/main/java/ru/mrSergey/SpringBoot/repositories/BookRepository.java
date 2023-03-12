package ru.mrSergey.SpringBoot.repositories;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrSergey.SpringBoot.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
List<Book> findBookByNameStartingWith(String query);
}
