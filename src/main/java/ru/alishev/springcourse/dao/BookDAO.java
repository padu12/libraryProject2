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
//@Component
//public class BookDAO {
//	private final JdbcTemplate jdbcTemplate;
//
//	@Autowired
//	public BookDAO(JdbcTemplate jdbcTemplate) {
//		super();
//		this.jdbcTemplate = jdbcTemplate;
//	}

//	public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
//    }
//
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BookMapper())
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Book book) {
//        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
//    }
//
//    public void update(int id, Book updatedBook) {
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE book_id=?", updatedBook.getName(),
//                updatedBook.getAuthor(), updatedBook.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
//    }
//    
//    public void set(int id1, Person person) {
//    	jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person.getPerson_id(), id1);
//    }
//    
//    public void setFree(int id) {
//    	jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", id);
//    }
//    
//    public Person getAuthor(int id) {
//    	return jdbcTemplate.query("SELECT Person.person_id, Person.full_name, Person.birth_year FROM Book JOIN Person on Book.person_id=Person.person_id where book_id=?",
//    			new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//}
