package ru.bellintegrator.practice.guides.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.guides.model.Doc;
import ru.bellintegrator.practice.guides.service.DocService;
import ru.bellintegrator.practice.guides.view.DocView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceInternalException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@Api(value = "OfficeControllerAPI")
public class DocController {
    private DocService docService;

    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    @ApiOperation(value = "listDocs", nickname = "listDocs", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Doc.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/docs", method = RequestMethod.POST)
    public List<DocView> docs() throws Exception {
        try{
            return docService.all();
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("При поиске информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }
}
