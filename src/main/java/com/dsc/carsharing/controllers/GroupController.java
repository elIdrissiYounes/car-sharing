package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Group;
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
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public String list(Model model) {
        List<Group> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        return "groups/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Group group = groupRepository.findOne(id);
        model.addAttribute("group", group);
        return "groups/form";
    }

    @GetMapping("new")
    public String create(Model model) {
        model.addAttribute("group", new Group());
        return "groups/form";
    }

    @PostMapping("save")
    public String save(Group group) {
        groupRepository.save(group);
        return "redirect:/groups";
    }

}
