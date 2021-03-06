package ru.bellintegrator.practice.guides.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.guides.model.Citizenship;
import ru.bellintegrator.practice.guides.service.CitizenshipService;
import ru.bellintegrator.practice.guides.view.CitizenshipView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceInternalException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@Api(value = "OfficeControllerAPI")
public class CitizenshipConrtroller {
    private CitizenshipService citizenService;

    @Autowired
    public CitizenshipConrtroller(CitizenshipService citizenService) {
        this.citizenService = citizenService;
    }

    @ApiOperation(value = "listDocs", nickname = "listDocs", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Citizenship.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/countries", method = RequestMethod.POST)
    public List<CitizenshipView> citizen() {
            return citizenService.all();
    }
}
