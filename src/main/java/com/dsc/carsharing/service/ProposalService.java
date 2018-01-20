package com.dsc.carsharing.service;

import com.dsc.carsharing.model.Children;
import com.dsc.carsharing.model.Excursion;
import com.dsc.carsharing.model.Group;
import com.dsc.carsharing.model.Parent;
import com.dsc.carsharing.repositories.ExcursionRepository;
import com.dsc.carsharing.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposalService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ExcursionRepository excursionRepository;

    public List<Excursion> findAvailableExcursionsForChildren(String parentUsername) {
        Parent parent = parentRepository.findByUsername(parentUsername);
        List<Group> childrenGroups = new ArrayList<>();
        parent.getChildren().forEach(children -> childrenGroups.add(children.getGroup()));
        return excursionRepository.findByGroupsIn(childrenGroups);
    }

    public List<Children> findChildrenForExcursion(String parentUsername, Long excursionId) {
        Parent parent = parentRepository.findByUsername(parentUsername);
        Excursion excursion = excursionRepository.findOne(excursionId);
        return parent.getChildren().stream()
                .filter(child -> excursion.getGroups().contains(child.getGroup()))
                .collect(Collectors.toList());
    }

}
