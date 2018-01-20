package com.dsc.carsharing.controllers;


import com.dsc.carsharing.model.Excursion;
import com.dsc.carsharing.repositories.UserRepository;
import com.dsc.carsharing.repositories.ExcursionRepository;
import com.dsc.carsharing.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/statistics")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ExcursionRepository excursionRepository;

    @Autowired
    private GroupRepository groupRepository;
    
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String indexBis() {
    	long x = userRepository.count();
    	long y = excursionRepository.count();
    	long z = groupRepository.count();
        return "statistics/tableauDeBord";
    }
}
