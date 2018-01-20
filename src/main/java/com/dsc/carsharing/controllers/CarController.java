package com.dsc.carsharing.controllers;

import com.dsc.carsharing.model.Car;
import com.dsc.carsharing.model.Parent;
import com.dsc.carsharing.repositories.CarRepository;
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
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ParentRepository parentRepository;

    @GetMapping
    public String list(Model model, Principal principal) {
        List<Car> cars = carRepository.findByParentUsername(principal.getName());
        model.addAttribute("cars", cars);
        return "cars/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Car car = carRepository.findOne(id);
        model.addAttribute("car", car);
        return "cars/form";
    }

    @GetMapping("new")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("create", true);
        return "cars/form";
    }

    @PostMapping("save")
    public String save(Car car, Principal principal) {
        Parent parent = parentRepository.findByUsername(principal.getName());
        car.setParent(parent);
        carRepository.save(car);
        return "redirect:/cars";
    }

}
