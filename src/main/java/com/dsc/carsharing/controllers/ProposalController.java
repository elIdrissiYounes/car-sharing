package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Proposal;
import com.dsc.carsharing.repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    private ProposalRepository proposalRepository;

    @GetMapping
    public String list(Model model) {
        List<Proposal> proposals = proposalRepository.findAll();
        model.addAttribute("proposals", proposals);
        return "proposals/list";
    }

    @GetMapping("new")
    public String create(Model model) {
        model.addAttribute("proposal", new Proposal());
        return "proposals/form";
    }

    @GetMapping("newCar")
    public String createCar(Model model) {
        model.addAttribute("proposal", new Proposal());
        return "redirect:/cars";
    }



}
