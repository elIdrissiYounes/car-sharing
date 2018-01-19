package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Children;
import com.dsc.carsharing.model.Excursion;
import com.dsc.carsharing.model.Parent;
import com.dsc.carsharing.model.Proposal;
import com.dsc.carsharing.repositories.ParentRepository;
import com.dsc.carsharing.repositories.ProposalRepository;
import com.dsc.carsharing.service.ProposalService;
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
    private ParentRepository parentRepository;

    @Autowired
    private ProposalService proposalService;

    @GetMapping
    public String list(Model model) {
        List<Proposal> proposals = proposalRepository.findAll();
        model.addAttribute("proposals", proposals);
        return "proposals/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model, Principal principal) {
        Proposal proposal = proposalRepository.findOne(id);
        Parent parent = parentRepository.findByUsername(principal.getName());
        List<Excursion> excursions = proposalService.findAvailableExcursionsForChildren(principal.getName());
        model.addAttribute("proposal", proposal);
        model.addAttribute("excursions", excursions);
        model.addAttribute("cars", parent.getCars());
        return "proposals/form";
    }

    @GetMapping("create")
    public String create(Model model, Principal principal) {
        Parent parent = parentRepository.findByUsername(principal.getName());
        List<Excursion> excursions = proposalService.findAvailableExcursionsForChildren(principal.getName());
//        List<Children> children = proposalService.findChildrenForExcursion(parent.getId());
        // TODO: Indicate what children are already in the car, his at least
        model.addAttribute("proposal", new Proposal());
        model.addAttribute("excursions", excursions);
        model.addAttribute("cars", parent.getCars());
        model.addAttribute("create", true);
        return "proposals/form";
    }

    @PostMapping("save")
    public String save(Proposal proposal, Principal principal) {
        // TODO: Show children can join the excursion
        Parent parent = parentRepository.findByUsername(principal.getName());
        proposal.setParent(parent);
        proposalRepository.save(proposal);
        return "redirect:/proposals";
    }

    @GetMapping("proposals")
    public String showProposals(Model model, Principal principal) {
        List<Excursion> excursions = proposalService.findAvailableExcursionsForChildren(principal.getName());
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
