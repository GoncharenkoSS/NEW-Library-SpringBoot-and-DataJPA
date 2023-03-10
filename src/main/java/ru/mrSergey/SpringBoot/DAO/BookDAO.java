package ru.mrSergey.SpringBoot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mrSergey.SpringBoot.models.Book;
import ru.mrSergey.SpringBoot.models.Person;


import java.util.List;
import java.util.Optional;

 @Component
    public class BookDAO {
        private final JdbcTemplate jdbcTemplate;
        @Autowired
        public BookDAO(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        public Optional<Person> getBookOwner(int id){
            return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.id_person=Person.id " +
                    "WHERE Book.id=?", new BeanPropertyRowMapper<>(Person.class),  new Object[]{id}).stream().findAny();
        }
        public void release(int id){
            jdbcTemplate.update("UPDATE Book SET id_person=NULL WHERE id=?", id);
        }

        public void assign(int id, Person select){
            jdbcTemplate.update("UPDATE Book SET id_person=? WHERE id=?", select.getId(), id);
        }
    }
