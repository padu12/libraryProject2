package ru.alishev.springcourse.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BookRepository;

@Service
@Transactional(readOnly = true)
public class BookService {
	private BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}
	
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public Book findOne(int id) {
		Optional<Book> foundBook = bookRepository.findById(id);
		return foundBook.orElse(null);
	}
	
	@Transactional
	public void save(Book book) {
		book.setTook_at(Calendar.getInstance());
		bookRepository.save(book);
	}
	
	@Transactional
	public void update(int id, Book newBook) {
		newBook.setBook_id(id);
		bookRepository.save(newBook);
	}
	
	@Transactional
	public void delete(int id) {
		bookRepository.deleteById(id);
	}
	
	@Transactional
	public void set(int id, Person person) {
		Optional<Book> book = bookRepository.findById(id);
		book.orElse(null).setOwner(person);
	}
	
	@Transactional
	public void setFree(int id) {
		Optional<Book> book = bookRepository.findById(id);
		book.orElse(null).setOwner(null);
	}
	
	public List<Book> pages(int page, int numOfItems) {
		return bookRepository.findAll(PageRequest.of(page, numOfItems)).getContent();
	}
	
	public List<Book> sort() {
		return bookRepository.findAll(Sort.by("year"));
	}
	
	public List<Book> sortAndPages(int page, int numOfItems) {
		return bookRepository.findAll(PageRequest.of(page, numOfItems, Sort.by("year"))).getContent();
	}
	
	public List<Book> findByRequest(String request) {
		return bookRepository.findByNameStartingWith(request);
	}
}
