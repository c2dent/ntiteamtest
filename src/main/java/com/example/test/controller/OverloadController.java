package com.example.test.controller;

import com.example.test.model.Overlord;
import com.example.test.repo.OverlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("overlord")
public class OverloadController {

    @Autowired
    private OverlordRepository overlordRepository;

    @PostMapping("")
    public String create(@RequestParam String name, @RequestParam int age, Model model){
        Overlord overlord = new Overlord(name, age);

        overlordRepository.save(overlord);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String formOverlord(){
        return "overlord/new";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Long id){
        model.addAttribute("overlord", overlordRepository.findById(id).orElse(null));
        return "overlord/show";
    }

    @GetMapping("/free")
    public String freeOverlords(Model model) {
        model.addAttribute("overlords", overlordRepository.findAllByPlanetsIsNull());
        System.out.println(overlordRepository.findAllByPlanetsIsNull());
        return "overlord/free";
    }

    @GetMapping("/young")
    public String youngOverlords(Model model) {
        model.addAttribute("overlords", overlordRepository.findTop10ByOrderByAgeAsc());
        return "overlord/young";
    }
}
