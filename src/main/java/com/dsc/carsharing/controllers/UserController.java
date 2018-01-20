package com.dsc.carsharing.controllers;


import com.dsc.carsharing.repositories.ExcursionRepository;
import com.dsc.carsharing.repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class UserController {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ExcursionRepository excursionRepository;

    @GetMapping
    public String index(Model model) {
        long proposals = proposalRepository.count();
        long excursions = excursionRepository.count();
        model.addAttribute("proposals", proposals);
        model.addAttribute("excursions", excursions);
        return "statistics/list";
    }
}
