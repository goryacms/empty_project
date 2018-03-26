package ru.bellintegrator.practice.guides.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.guides.dao.DocDAO;
import ru.bellintegrator.practice.guides.model.Doc;
import ru.bellintegrator.practice.guides.service.DocService;
import ru.bellintegrator.practice.guides.view.DocView;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DocServiceImpl implements DocService{
    private final DocDAO dao;

    public DocServiceImpl(DocDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<DocView> all() {
        List<Doc> all = dao.all();
        Function<Doc, DocView> mapDoc = p -> {
            DocView view = new DocView();
            view.code = p.getId();
            view.name = p.getName();

            return view;
        };

        return all.stream()
                .map(mapDoc)
                .collect(Collectors.toList());
    }
}
