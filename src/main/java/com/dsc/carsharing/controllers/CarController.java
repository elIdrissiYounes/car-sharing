package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Car;
import com.dsc.carsharing.model.Excursion;
import com.dsc.carsharing.model.Parent;
import com.dsc.carsharing.model.Proposal;
import com.dsc.carsharing.repositories.CarRepository;
import com.dsc.carsharing.repositories.GroupRepository;
import com.dsc.carsharing.repositories.ParentRepository;
import com.dsc.carsharing.repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ParentRepository parentRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("car", new Car());
        return "cars/form";
    }
    @PostMapping("save")
    public String save(Car car, Principal principal) {
        Parent parent = parentRepository.findByUsername(principal.getName());
        car.setParent(parent);
        carRepository.save(car);
        return "redirect:/proposals";
    }

}
