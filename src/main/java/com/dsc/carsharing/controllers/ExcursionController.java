package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Excursion;
import com.dsc.carsharing.repositories.ExcursionRepository;
import com.dsc.carsharing.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/excursions")
public class ExcursionController {

    @Autowired
    private ExcursionRepository excursionRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public String list(Model model) {
        List<Excursion> excursions = excursionRepository.findAll();
        model.addAttribute("excursions", excursions);
        return "excursions/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Excursion excursion = excursionRepository.findOne(id);
        model.addAttribute("excursion", excursion);
        model.addAttribute("groups", groupRepository.findAll());
        return "excursions/form";
    }

    @GetMapping("new")
    public String create(Model model) {
        model.addAttribute("excursion", new Excursion());
        model.addAttribute("groups", groupRepository.findAll());
        return "excursions/form";
    }

    @PostMapping("save")
    public String save(Excursion excursion) {
        excursionRepository.save(excursion);
        return "redirect:/excursions";
    }

}
