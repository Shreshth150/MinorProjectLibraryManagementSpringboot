package com.example.library.management.minor_project_library_management.Util;

import com.example.library.management.minor_project_library_management.DataAcessLayer.Book;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookValidator {

    public boolean isValid(Book book) {

        if (book.getTitle() == "" || book.getTitle() == null){
            return false ;
        }
        return true ;
    }
}
