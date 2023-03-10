package ru.mrSergey.SpringBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mrSergey.SpringBoot.models.Book;
import ru.mrSergey.SpringBoot.models.Person;
import ru.mrSergey.SpringBoot.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findOne(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id,Book upBook){
        upBook.setId(id);
        bookRepository.save(upBook);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

}
