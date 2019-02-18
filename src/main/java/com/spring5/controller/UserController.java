package com.spring5.controller;

import com.spring5.dao.UserCrudRepository;
import com.spring5.dao.UserPagingRepositary;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring5.model.User;
import com.spring5.service.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.data.domain.PageRequest;

// http://learningprogramming.net/java/spring-mvc/pagination-with-spring-data-jpa-in-spring-mvc/
// https://examples.javacodegeeks.com/enterprise-java/spring/mvc/spring-mvc-pagination-example/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserPagingRepositary userPagingRepo;
//
//    @Autowired
//    private UserCrudRepository userCrudRepo;
    @GetMapping("/")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("users", userService.list());
        return "editUsers";
    }

    @ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("users", userService.list());
            return "editUsers";
        }

        userService.save(user);
        return "redirect:/";
    }
//
//    @GetMapping("/listUsers")
//    public String listUsers(Locale locale, Model model) {
//        long count = userPagingRepo.count();
//        int pageSize = 5;
//        long pages = count / pageSize;
//        for (int i = 0; i < pages; i++) {
//            System.out.printf("page num: %s%n", i);
//            List<User> list = userPagingRepo.findPageAll(PageRequest.of(i, pageSize));
//            list.forEach(System.out::println);
//        }
//
//        model.addAttribute("users", userService.listUsers());
//        return "redirect:/listUsers";
//    }
//
//    private List<User> createUsers() {
//
//        List<User> returnValue = new ArrayList();
//        User user = null;
//        Random rand = new Random();
//        for (int i = 0; i < 100; i++) {
//            user = new User();
//            user.setName(F_N_LIST.get(rand.nextInt(F_N_LIST.size()))
//                    + " " + L_N_List.get(rand.nextInt(L_N_List.size())));
//            user.setEmail(E_LIST.get(rand.nextInt(E_LIST.size())));
//
//            returnValue.add(user);
//        }
//
//        userPagingRepo.saveAll(returnValue);
//        //userCrudRepo.saveAll(returnValue);
//        return returnValue;
//    }

    static final String[] FIRST_NAMES = {"Alex", "Arby", "Allen", "Abbey", "Ashley", "Ben", "Bill", "Carol", "Dan", "Don", "Doug", "Ernie", "Gary", "Jax", "Jon", "Jeff", "Jessica", "Kevin", "Shannon"};
    static final String[] LAST_NAMES = {"Alton", "Aleon", "Atux", "Lee", "Swift", "Liu", "Alexon", "Alatian", "Smith", "Smita", "Will", "Wall", "Zina"};
    static final String[] EMAILS = {"aaa@ciminc.com", "a12@ciminc.com", "a2a@ciminc.com", "alibaba@ciminc.com", "alab@ciminc.com", "alex@ciminc.com",
        "arby@ciminc.com", "Allen@ciminc.com", "Abbey@ciminc.com", "Ashley@ciminc.com", "Ben@ciminc.com", "Bill@ciminc.com", "Carol@ciminc.com",
        "Dan@ciminc.com", "Don@ciminc.com", "Doug@ciminc.com", "Ernie@ciminc.com", "Gary@ciminc.com", "Jax@ciminc.com", "Jon@ciminc.com",
        "Jeff@ciminc.com", "Jessica@ciminc.com", "Kevin@ciminc.com", "Shannon@ciminc.com"};
    static final List<String> F_N_LIST = Arrays.asList(FIRST_NAMES);
    static final List<String> L_N_List = Arrays.asList(LAST_NAMES);
    static final List<String> E_LIST = Arrays.asList(EMAILS);
}
