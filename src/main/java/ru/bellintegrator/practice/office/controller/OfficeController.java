package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.ResponseView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
@Api(value = "OfficeControllerAPI")
public class OfficeController  {
    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OfficeView officeById(@PathVariable("id") Long id) {
        return officeService.loadById(id);
    }

    /**
     * TODO: добавить парамаетры; доработать логику в Service
     */
    @ApiOperation(value = "listOffice", nickname = "listOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<OfficeView> offices(@RequestBody OfficeView offView) {
        return officeService.loadByParams(offView);
    }


    @ApiOperation(value = "addOffice", nickname = "addOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/save", method = {POST})
    public ResponseView addOffice(@RequestBody OfficeView officeView) {
        return officeService.save(officeView);
    }

    @ApiOperation(value = "updateOffice", nickname = "updOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public ResponseView updOffice(@RequestBody OfficeView officeView) {
        return officeService.update(officeView);
    }


    /**
     * TODO: выявить причину почему не работает
     */
    @ApiOperation(value = "deleteOffice", nickname = "delOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = {POST})
    public ResponseView delOffice(Long id) {
        return officeService.delete(id);
    }
}