package com.whut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
  Created by IntelliJ IDEA.
  User: ccc
  Date: 2019/10/16
  Time: 21:09
  To change this template use File | Settings | File Templates.
*/
@Controller
public class Index {
    @GetMapping("/")
    public String all(Model model) {
        return "index.html";
    }
}
