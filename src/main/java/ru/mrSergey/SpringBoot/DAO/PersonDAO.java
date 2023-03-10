package ru.mrSergey.SpringBoot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mrSergey.SpringBoot.models.Book;
import ru.mrSergey.SpringBoot.models.Person;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBookByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_person=?",
                new BeanPropertyRowMapper<>(Book.class), id);
    }
}
