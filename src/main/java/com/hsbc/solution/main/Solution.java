package com.hsbc.solution.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by osereda on 2017-06-13.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.hsbc.solution"})
public class Solution {
    public static void main(String[] args) {
        SpringApplication.run(Solution.class, args);
    }
}
