package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Parent;
import com.dsc.carsharing.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/parents")
public class ParentController {

    @Autowired
    private ParentRepository parentRepository;

    @GetMapping
    public String list(Model model) {
        List<Parent> parents = parentRepository.findAll();
        model.addAttribute("parents", parents);
        return "parents/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Parent parent = parentRepository.findOne(id);
        model.addAttribute("parent", parent);
        return "parents/form";
    }

    @GetMapping("new")
    public String create(Model model) {
        model.addAttribute("parent", new Parent());
        model.addAttribute("create", true);
        return "parents/form";
    }

    @PostMapping("save")
    public String save(Parent parent) {
        parentRepository.save(parent);
        return "redirect:/parents";
    }

}
