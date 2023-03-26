//package ru.alishev.springcourse.dao;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import ru.alishev.springcourse.models.Book;
//import ru.alishev.springcourse.models.Person;
//
///**
// * @author Neil Alishev
// */
//@Component
//public class PersonDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//    
//    
//    public List<Book> getTakenBooks(int id) {
//        return jdbcTemplate.query("SELECT Book.book_id, Book.person_id, Book.name, Book.author, Book.year "
//        		+ "FROM Book JOIN Person ON Book.person_id=Person.person_id where Book.person_id=?", new Object[]{id},
//        		new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(full_name, birth_year) VALUES(?, ?)", person.getFull_name(), person.getBirth_year());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET full_name=?, birth_year=? WHERE person_id=?", updatedPerson.getFull_name(),
//                updatedPerson.getBirth_year(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
//    }
//    
////    public List<Book> getTakenBooks1(int id) {
////    	return jdbcTemplate.query("SELECT Book.book_id, Book.person_id, Book.name, Book.author, Book.year "
////    			+ "FROM Book JOIN Person ON Book.person_id=Person.person_id where Book.person_id=?",
////    			new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
////    }
//}
