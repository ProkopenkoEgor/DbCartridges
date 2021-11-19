package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.*;
import com.example.springbootsbyt.service.impl.HistoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
    public class MainframeController {
    private final HistoryServiceImpl historyServiceImpl;

    public MainframeController(HistoryServiceImpl historyServiceImpl) {
        this.historyServiceImpl = historyServiceImpl;
    }

    @GetMapping("/main")
    public String mainFrame() {
        return "main-frame";
    }
//    @GetMapping("/")
//    public String greeting() {
//        return "home";
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
}
