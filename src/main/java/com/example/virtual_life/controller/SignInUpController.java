package com.example.virtual_life.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SignInUpController {
    // TODO
    // anyone can see this
    @GetMapping("sign-in")
    public String signInPage() {
        return "<h1>Sign in form<h1>";
    }
    
    // TODO
    // anyone can see this
    @GetMapping("sign-up")
    public String signUpPage() {
        return "<h1>Sign up form<h1>";
    }
}
