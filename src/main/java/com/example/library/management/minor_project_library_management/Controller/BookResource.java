package com.example.library.management.minor_project_library_management.Controller;


import com.example.library.management.minor_project_library_management.DataAcessLayer.Book;
import com.example.library.management.minor_project_library_management.DataAcessLayer.BookRepository;
import com.example.library.management.minor_project_library_management.DataAcessLayer.IssuedBooksRepository;
import com.example.library.management.minor_project_library_management.DataAcessLayer.UserRepository;
import com.example.library.management.minor_project_library_management.Util.BookValidator;
import com.example.library.management.minor_project_library_management.Util.Uservalidator;
import com.example.library.management.minor_project_library_management.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookResource {
    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IssuedBooksRepository rentRepository;

    @Autowired
    private BookRepository repository;

    @Autowired
    BookValidator validator ;

    @Autowired
    Uservalidator uservalidator ;

    @GetMapping("/hi")
    public String sayHello(){
        return "Hello Client";
    }

    @GetMapping("/books")
    List<Book> findAll(){
        LOGGER.info("findAll Called");
        List<Book> list = new ArrayList<Book>();
        try {
            list = repository.findAll();
            if (list.size() == 0){
                LOGGER.severe("No Book Found");
                throw new BookNotFoundException(5);
            }
        }catch (BookNotFoundException exc){
            LOGGER.severe(exc.toString());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Book Found" , exc);
        }
        return list ;
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@RequestBody Book newbook){
        if (validator.isValid(newbook)){
            return repository.save(newbook);
        }else {
            LOGGER.severe("newBook is not valid");
            return null;
        }
    }

    @GetMapping(value = "/searchBooksByAuthor")
    public List<Book> searchBookByAuthor(@RequestParam(value = "q") String author){
        List<Book> books = bookRepository.findAll();

        ArrayList<Book> list = new ArrayList<Book>();
        for (Book book : books){
            if (book.getAuthor().equals(author))
                list.add(book);
        }
        return list ;
    }

    @GetMapping(value = "/searchBooksBySubject")
    public List<Book> searchBooksBySubject(@RequestParam(value = "q") String subject){
        List<Book> books = bookRepository.findAll();
        ArrayList<Book> list = new ArrayList<>();
        for (Book book : books){
            if (book.getSubject().equals(subject))
                list.add(book);
        }
        return list ;
    }

    

}
