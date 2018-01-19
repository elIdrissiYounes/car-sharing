package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Children;
import com.dsc.carsharing.model.Group;
import com.dsc.carsharing.model.Parent;
import com.dsc.carsharing.repositories.ChildrenRepository;
import com.dsc.carsharing.repositories.GroupRepository;
import com.dsc.carsharing.repositories.ParentRepository;
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
@RequestMapping("/children")
public class ChildrenController {

    @Autowired
    private ChildrenRepository childrenRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public String list(Model model) {
        List<Children> children = childrenRepository.findAll();
        model.addAttribute("children", children);
        return "children/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Children children = childrenRepository.findOne(id);
        List<Group> groups = groupRepository.findAll();
        model.addAttribute("children", children);
        model.addAttribute("groups", groups);
        return "children/form";
    }

    @GetMapping("new")
    public String create(Model model) {
        List<Group> groups = groupRepository.findAll();
        model.addAttribute("children", new Children());
        model.addAttribute("groups", groups);
        model.addAttribute("create", true);
        return "children/form";
    }

    @PostMapping("save")
    public String save(Children children, Principal principal) {
        Parent parent = parentRepository.findByUsername(principal.getName());
        parent.getChildren().add(children);
        parentRepository.save(parent);
        return "redirect:/children";
    }

}
