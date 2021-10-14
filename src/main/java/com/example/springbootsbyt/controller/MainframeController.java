package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.Cartrs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
    public class MainframeController {
    @GetMapping("/main")
    public String MainFrame() {
        return "main-frame";

    }
}
