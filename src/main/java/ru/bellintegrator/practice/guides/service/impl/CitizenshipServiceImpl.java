package ru.bellintegrator.practice.guides.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.guides.dao.CitizenshipDAO;
import ru.bellintegrator.practice.guides.model.Citizenship;
import ru.bellintegrator.practice.guides.service.CitizenshipService;
import ru.bellintegrator.practice.guides.view.CitizenshipView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CitizenshipServiceImpl implements CitizenshipService {
    public CitizenshipServiceImpl(CitizenshipDAO dao) {
        this.dao = dao;
    }

    private final CitizenshipDAO dao;

    @Override
    public List<CitizenshipView> all() {
        List<Citizenship> all = dao.all();

        if(all.size() == 0) {
            throw new ResourceNotFoundException("Информация не найдена");
        }

        Function<Citizenship, CitizenshipView> mapCit = p -> {
            CitizenshipView view = new CitizenshipView();
            view.code = p.getCode();
            view.name = p.getName();

            return view;
        };

        return all.stream()
                .map(mapCit)
                .collect(Collectors.toList());
    }
}
