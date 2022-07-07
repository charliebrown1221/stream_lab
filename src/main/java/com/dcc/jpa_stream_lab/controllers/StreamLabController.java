package com.dcc.jpa_stream_lab.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dcc.jpa_stream_lab.service.StreamLabService;
import com.dcc.jpa_stream_lab.models.Product;
import com.dcc.jpa_stream_lab.models.Role;
import com.dcc.jpa_stream_lab.models.ShoppingcartItem;
import com.dcc.jpa_stream_lab.models.User;

@RestController
public class StreamLabController {

	@Autowired
	public StreamLabService service;

    @GetMapping("/problemOne")
    public long ProblemOne() {    	
    	return service.ProblemOne();
    }
	
    @GetMapping("/problemTwo")
    public List<User> ProblemTwo() {
    	return service.ProblemTwo();
    }
    
    @GetMapping("/problemThree")
    public List<Product> ProblemThree() {
    	return service.ProblemThree();
    }
    
    @GetMapping("/problemFour")
    public List<Product> ProblemFour() {
    	return service.ProblemFour();
    }
    
    @GetMapping("/problemFive")
    public List<User> ProblemFive() throws ParseException {
    	return service.ProblemFive();
    }
    
    @GetMapping("/problemSix")
    public List<User> ProblemSix() throws ParseException {
    	return service.ProblemSix();
    }
    
    @GetMapping("/problemSeven")
    public List<User> ProblemSeven() {
    	return service.ProblemSeven();
    }
    
    @GetMapping("/problemEight")
    public List<Product> ProblemEight() {
    	return service.ProblemEight();
    }
    
    @GetMapping("/problemNine")
    public int ProblemNine() {
    	return service.ProblemNine();
    }
    
    @GetMapping("/problemTen")
    public List<Product> ProblemTen() {
    	return service.ProblemTen();
    }
    
    @PostMapping("/problemEleven")
    public User ProblemEleven() {
    	return service.ProblemEleven();
    }
    
    @PostMapping("/problemTwelve")
    public Product ProblemTwelve() {
    	return service.ProblemTwelve();
    }
    
    @PostMapping("/problemThirteen")
    public List<Role> ProblemThirteen() {
    	return service.ProblemThirteen();
    }
    
    @PostMapping("/problemFourteen")
    public ShoppingcartItem ProblemFourteen() {
    	return service.ProblemFourteen();
    }
    
    @PutMapping("/problemFifteen")
    public User ProblemFifteen() {
    	return service.ProblemFifteen();
    }
    
    @PutMapping("/problemSixteen")
    public Product ProblemSixteen() {
    	return service.ProblemSixteen();
    }
    
    @PutMapping("/problemSeventeen")
    public User ProblemSeventeen() {
    	return service.ProblemSeventeen();
    }
    

    @DeleteMapping("/ProblemEighteen")
    public List<Role> ProblemEighteen(){return service.ProblemEighteen();}

    @DeleteMapping("/ProblemNineteen")
    public int ProblemNineteen(){return service.ProblemNineteen();}


    @DeleteMapping("/ProblemTwenty")
    public User ProblemTwenty(){return service.ProblemTwenty();}

}
