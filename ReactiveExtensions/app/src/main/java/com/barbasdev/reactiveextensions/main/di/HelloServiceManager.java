package com.barbasdev.reactiveextensions.main.di;

public class HelloServiceManager implements HelloService {

    @Override
    public String greet(String userName) {
        return "Hello " + userName + "!";
    }
}