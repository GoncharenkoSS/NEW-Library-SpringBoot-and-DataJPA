package ru.mrSergey.SpringBoot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mrSergey.SpringBoot.models.Book;
import ru.mrSergey.SpringBoot.models.Person;
import ru.mrSergey.SpringBoot.repositories.BookRepository;
import ru.mrSergey.SpringBoot.services.BookService;
import ru.mrSergey.SpringBoot.services.PeopleService;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.peopleService = peopleService;
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public String index(Model model,
          @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
          @RequestParam(name = "quantityString", required = false, defaultValue = "100") int quantityString){
          model.addAttribute("book", bookService.findAll(pageNumber, quantityString));
        return  "book/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookService.findOne(id));
        ////////////////////////////////////////////////////////////////////////////////////////////////
        Optional<Person> bookOwner = bookService.getBookOwner(id);
        if(bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", peopleService.findAll());
        ///////////////////////////////////////////////////////////////////////////////////////////////
        return  "book/show";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "book/new";
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookService.findOne(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "book/edit";
        bookService.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/book;";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookService.release(id);
        return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person select) {
        bookService.assign(id, select);
        return "redirect:/book/" + id;
    }

    @GetMapping("/search")
    public String searchPage(){
        return "book/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query){
        model.addAttribute("books", bookService.search(query));
        return "book/search";
    }
}