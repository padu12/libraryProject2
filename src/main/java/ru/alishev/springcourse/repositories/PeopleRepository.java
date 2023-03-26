package ru.alishev.springcourse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{
//	public Person findByBook(Book books);
}
