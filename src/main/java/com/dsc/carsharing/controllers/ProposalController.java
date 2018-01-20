package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Children;
import com.dsc.carsharing.model.Excursion;
import com.dsc.carsharing.model.Parent;
import com.dsc.carsharing.model.Proposal;
import com.dsc.carsharing.repositories.ExcursionRepository;
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
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ExcursionRepository excursionRepository;

    @Autowired
    private ProposalService proposalService;

    @GetMapping
    public String list(Model model, Principal principal) {
        List<Excursion> excursions = proposalService.findAvailableExcursionsForChildren(principal.getName());
        model.addAttribute("excursions", excursions);
        return "proposals/list";
    }

    @PostMapping("remove/{id}")
    public String edit(@PathVariable Long id) {
        proposalRepository.delete(id);
        return "redirect:/proposals";
    }

    @GetMapping("create/{id}")
    public String create(@PathVariable Long id, Model model, Principal principal) {
        Parent parent = parentRepository.findByUsername(principal.getName());
        Excursion excursion = excursionRepository.findOne(id);
        List<Children> children = proposalService.findChildrenForExcursion(principal.getName(), id);
        Proposal proposal = createBaseProposal(parent, children, excursion);
        model.addAttribute("proposal", proposal);
        model.addAttribute("children", children);
        model.addAttribute("cars", parent.getCars());
        model.addAttribute("create", true);
        return "proposals/form";
    }

    @GetMapping("select/{id}")
    public String selectProposal(@PathVariable Long id, Model model, Principal principal) {
        Proposal proposal = proposalRepository.findOne(id);
        Parent parent = parentRepository.findByUsername(principal.getName());
        model.addAttribute("proposal", proposal);
        model.addAttribute("children", parent.getChildren());
        model.addAttribute("cars", Collections.singletonList(proposal.getCar()));
        model.addAttribute("create", false);
        return "proposals/form";
    }

    @PostMapping("save")
    public String save(Proposal proposal) {
        proposal.setParent(parentRepository.findOne(proposal.getParent().getId()));
        proposalRepository.save(proposal);
        return "redirect:/proposals";
    }

    private Proposal createBaseProposal(Parent parent, List<Children> children, Excursion excursion) {
        Proposal proposal = new Proposal();
        proposal.setParent(parent);
        proposal.setExcursion(excursion);
        proposal.getChildren().addAll(children);
        return proposal;
    }

}
