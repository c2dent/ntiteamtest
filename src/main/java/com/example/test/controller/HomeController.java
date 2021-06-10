package com.example.test.controller;

import com.example.test.repo.OverlordRepository;
import com.example.test.repo.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private OverlordRepository overlordRepository;

    @Autowired
    private PlanetRepository planetRepository;

    @GetMapping()
    public String listPlanet(Model model){
        model.addAttribute("planets", planetRepository.findAll());
        model.addAttribute("overlords", overlordRepository.findAll());
        return "home";
    }
}
