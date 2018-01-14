package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Excursion;
import com.dsc.carsharing.model.Parent;
import com.dsc.carsharing.model.Proposal;
import com.dsc.carsharing.repositories.ExcursionRepository;
import com.dsc.carsharing.repositories.ParentRepository;
import com.dsc.carsharing.repositories.ProposalRepository;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private ExcursionRepository excursionRepository;
    @Autowired
    private ParentRepository parentRepository;

    @GetMapping
    public String list(Model model) {
        List<Proposal> proposals = proposalRepository.findAll();
        model.addAttribute("proposals", proposals);
        return "proposals/list";
    }

    @GetMapping("new")
    public String create(Model model, Model m) {
        List<Excursion> excursions= excursionRepository.findAll();
        model.addAttribute("proposal", new Proposal());
        m.addAttribute("excursions", excursions);
        return "proposals/form";
    }

    @GetMapping("newCar")
    public String createCar(Model model) {
        model.addAttribute("proposal", new Proposal());
        return "redirect:/cars";
    }
    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Proposal proposal = proposalRepository.findOne(id);
        model.addAttribute("proposal", proposal);
        return "proposals/form";
    }

    @PostMapping("save")
    public String save(Proposal proposal, Principal principal) {
        //System.out.println(proposal.getExcursion().getDestination());
        Excursion excursion = excursionRepository.findByDestination(proposal.getExcursion().getDestination());
        Parent parent = parentRepository.findByUsername(principal.getName());
        proposal.setExcursion(excursion);
        /*if (parent.getCar() == null){
            return "redirect:/cars";
        }
        */
        proposal.setParent(parent);
        proposalRepository.save(proposal);
        return "redirect:/proposals";
    }



}
