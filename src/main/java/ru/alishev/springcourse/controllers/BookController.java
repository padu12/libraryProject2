package ru.alishev.springcourse.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.services.BookService;
import ru.alishev.springcourse.services.PeopleService;

@Controller
@RequestMapping("/library")
public class BookController {
	private final BookService bookService;
	private final PeopleService peopleService;

	@Autowired
	public BookController(BookService bookService, PeopleService personService) {
		super();
		this.bookService = bookService;
		this.peopleService = personService;
	}
	
	
	@GetMapping()
	public String index(Model model, Optional<Integer> page, Optional<Integer> books_per_page,
			Optional<Boolean> sort_by_year) {
		int page1 = page.orElse(0);
		int books_per_page1 = books_per_page.orElse(0);
		boolean sort_by_year1 = sort_by_year.orElse(false);
		
		if (books_per_page1>0 && sort_by_year1 == true) {		
			model.addAttribute("library", bookService.sortAndPages(page1, books_per_page1));
		}else if(books_per_page1>0){
			model.addAttribute("library", bookService.pages(page1, books_per_page1));
		}else if(sort_by_year1 == true){
			model.addAttribute("library", bookService.sort());			
		}else {
			model.addAttribute("library", bookService.findAll());
		}
		return "library/index";
	}

	@GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "library/new";
    }
	
	@PostMapping()
	public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "library/new";
		
		bookService.save(book);
		return "redirect:/library";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		model.addAttribute("book", bookService.findOne(id));
		model.addAttribute("people", peopleService.findAll());
//		model.addAttribute("person1", bookService.getAuthor(id));
		return "library/show";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("book", bookService.findOne(id));
		return "library/edit";
	}
	
	@PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "library/edit";

        bookService.update(id, book);
        return "redirect:/library";
    }
	
	@DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/library";
    }
    
	@PatchMapping("/{id}/set")
	public String set(@PathVariable("id") int id, @ModelAttribute("person") Person person,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors())
			return "library/show";
		
		bookService.set(id, person);
		return "redirect:/library";
	}
	
	@PatchMapping("/{id}/set-free")
	public String setFree(@PathVariable("id") int id, Model model) {
		bookService.setFree(id);
		return "redirect:/library/" + id;
	}
	
	@GetMapping("/search")
	public String search(Optional<String> request, Model model) {
		String trueRequest = request.orElse("");
		if(trueRequest == "") {
			model.addAttribute("library", new ArrayList<>());
		}else {
			model.addAttribute("library", bookService.findByRequest(trueRequest));
		}
		model.addAttribute("request", trueRequest);
		System.out.println(bookService.findByRequest(trueRequest));
		return "library/search";
	}
	
}
