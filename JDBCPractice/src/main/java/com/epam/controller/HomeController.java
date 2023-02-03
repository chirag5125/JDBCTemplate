package com.epam.controller;

import com.epam.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value="/home", method=GET)
    @ResponseBody
    public Integer goHome(){
        System.out.println("In home controller");
        usersRepository.count();

       return 200;
    }
}
