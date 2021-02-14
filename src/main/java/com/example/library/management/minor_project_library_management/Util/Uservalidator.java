package com.example.library.management.minor_project_library_management.Util;

import com.example.library.management.minor_project_library_management.DataAcessLayer.User;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Uservalidator {


    public static boolean isValidUser(User user){
        if (user.getEmail() == null || user.getEmail() == "")
            return false ;
        else
        return true ;
    }


}
