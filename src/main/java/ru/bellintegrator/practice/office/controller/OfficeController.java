package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.service.OfficeValidService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotValidException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
@Api(value = "OfficeControllerAPI")
public class OfficeController  {
    private OfficeService officeService;

    private OfficeValidService officeValidService;

    @Autowired
    public OfficeController(OfficeService officeService, OfficeValidService officeValidService) {
        this.officeService = officeService;
        this.officeValidService = officeValidService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OfficeView officeById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        OfficeView officeView = officeService.loadById(id);

        if(officeView == null)
            throw new ResourceNotFoundException("Офис с идентификатором = " + id + " не найден");

        return officeView;
    }


    @ApiOperation(value = "listOffice", nickname = "listOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<OfficeView> offices(@RequestBody OfficeView offView) throws ResourceNotValidException, ResourceNotFoundException {
        if(!officeValidService.checkList(offView))
            throw new ResourceNotValidException("Полученные данные некорректны");

        List<OfficeView> officeList  = officeService.loadByParams(offView);

        if(officeList.size() == 0) {
            throw new ResourceNotFoundException("Информация по заданным условиям не найдена");
        }

        return officeList;
    }


    @ApiOperation(value = "addOffice", nickname = "addOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/save", method = {POST})
    public OfficeView addOffice(@RequestBody OfficeView officeView) throws ResourceNotValidException {
        if(!officeValidService.checkSave(officeView))
            throw new ResourceNotValidException("Полученные данные некорректны");

        return officeService.save(officeView);
    }

    @ApiOperation(value = "updateOffice", nickname = "updOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public OfficeView updOffice(@RequestBody OfficeView officeView) throws ResourceNotValidException {
        if(!officeValidService.checkUpdate(officeView))
            throw new ResourceNotValidException("Полученные данные некорректны");

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
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public OfficeView delOffice(@RequestBody OfficeView officeView) {
        return officeService.delete(officeView);
    }
}