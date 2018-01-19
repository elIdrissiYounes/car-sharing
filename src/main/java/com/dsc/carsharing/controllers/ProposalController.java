package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Excursion;
import com.dsc.carsharing.model.Parent;
import com.dsc.carsharing.model.Proposal;
import com.dsc.carsharing.repositories.ExcursionRepository;
import com.dsc.carsharing.repositories.ParentRepository;
import com.dsc.carsharing.repositories.ProposalRepository;
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

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Proposal proposal = proposalRepository.findOne(id);
        model.addAttribute("proposal", proposal);
        return "proposals/form";
    }

    @GetMapping("create")
    public String create(Model model, Principal principal) {
        List<Excursion> excursions = excursionRepository.findAll();
        Parent parent = parentRepository.findByUsername(principal.getName());
        // TODO: Show just his/her children groups excursions
        // TODO: Indicate what children are already in the car, his at least
        model.addAttribute("excursions", excursions);
        model.addAttribute("cars", parent.getCars());
        model.addAttribute("proposal", new Proposal());
        model.addAttribute("create", true);
        return "proposals/form";
    }

    @PostMapping("save")
    public String save(Proposal proposal, Principal principal) {
        Parent parent = parentRepository.findByUsername(principal.getName());
        proposal.setParent(parent);
        proposalRepository.save(proposal);
        return "redirect:/proposals";
    }

    @GetMapping("proposals")
    public String showProposals(Model model) {
        // TODO: Excursions of his children
        List<Excursion> excursions = excursionRepository.findAll();
        model.addAttribute("excursions", excursions);
        return "proposals/proposals";
    }

    @GetMapping("select/{id}")
    public String selectProposal(@PathVariable Long id, Model model, Principal principal) {
        Proposal proposal = proposalRepository.findOne(id);
        Parent parent = parentRepository.findByUsername(principal.getName());
        model.addAttribute("proposal", proposal);
        // TODO: Children that can be in the excursion
        model.addAttribute("children", parent.getChildren());
        return "proposals/select";
    }

    @PostMapping("select/save")
    public String selectProposal(Proposal proposal, Principal principal) {
        Proposal modified = proposalRepository.findOne(proposal.getId());
        Parent parent = parentRepository.findByUsername(principal.getName());
        modified.getChildren().removeAll(parent.getChildren());
        modified.getChildren().addAll(proposal.getChildren());
        proposalRepository.save(modified);
        return "redirect:/proposals/proposals";
    }

}
