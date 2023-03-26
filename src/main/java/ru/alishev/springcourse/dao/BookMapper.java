//package ru.alishev.springcourse.dao;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import ru.alishev.springcourse.models.Book;
//
//public class BookMapper implements RowMapper<Book>{
//	@Override
//	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
//		Book book = new Book();
//		
//		book.setAuthor(rs.getString("author"));
//		book.setBook_id(rs.getInt("book_id"));
//		book.setName(rs.getString("name"));
//		book.setYear(rs.getInt("year"));
//		
//		return book;
//	}
//}
