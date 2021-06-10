package com.example.test.controller;

import com.example.test.model.Overlord;
import com.example.test.model.Planet;
import com.example.test.repo.OverlordRepository;
import com.example.test.repo.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("planet")
public class PlanetController {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private OverlordRepository overlordRepository;

    @PostMapping()
    public String create(@RequestParam String name, @RequestParam(required = false) Long overlord_id, Model model){
        Planet planet = null;
        if (overlord_id != null && overlord_id > 0 && overlordRepository.existsById(overlord_id)) {
            planet = new Planet(name, overlordRepository.findById(overlord_id).get());
        } else {
            planet = new Planet(name, null);
        }
        planetRepository.save(planet);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("overlords", overlordRepository.findAll());
        return "planet/new";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Long id) {
        model.addAttribute("planet", planetRepository.findById(id).get());
        return "planet/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        if(planetRepository.existsById(id)) {
            planetRepository.deleteById(id);
        }
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("planet", planetRepository.findById(id).get());
        model.addAttribute("overlords", overlordRepository.findAll());
        return "planet/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestParam("overlord_id") Long overlord_id){
        Planet planet = planetRepository.findById(id).get();
        planet.setOverlord(overlordRepository.findById(overlord_id).isPresent() ? overlordRepository.findById(overlord_id).get() : null);
        planetRepository.save(planet);
        return "redirect:/";
    }
}
