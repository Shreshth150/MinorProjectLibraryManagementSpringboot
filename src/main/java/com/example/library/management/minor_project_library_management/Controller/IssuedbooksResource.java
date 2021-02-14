package com.example.library.management.minor_project_library_management.Controller;

import com.example.library.management.minor_project_library_management.DataAcessLayer.IssuedBooks;
import com.example.library.management.minor_project_library_management.DataAcessLayer.IssuedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class IssuedbooksResource {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    IssuedBooksRepository repository ;

    @GetMapping("/issuedBooks")
    List<IssuedBooks> findAll(){
        return repository.findAll();
    }

    @PostMapping("/issueBook")
    @ResponseStatus(HttpStatus.CREATED)
    public IssuedBooks issuedBooks(@RequestBody IssuedBooks issuedBooks) throws Exception{
        try {
            return repository.save(issuedBooks);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    @GetMapping("/searchIssuedBooksByUser")
    public List<IssuedBooks> searchIssuedBooksByUser(@RequestParam(value = "q") String userId){
        LOGGER.info("searchIssuedBooksByUser called with userId " + userId);
        List<IssuedBooks> books = repository.findAll();
        ArrayList<IssuedBooks> list = new ArrayList<>();
        for (IssuedBooks book : books){
            int id = Integer.parseInt(userId);
            if (book.getUser_id() == id){
                list.add(book);
            }
        }
        return list ;
    }


}
