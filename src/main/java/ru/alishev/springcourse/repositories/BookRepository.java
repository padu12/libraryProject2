package ru.alishev.springcourse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.alishev.springcourse.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	public List<Book> findByNameStartingWith (String request);
}
