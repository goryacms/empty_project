package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceInternalException;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;


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
    public OfficeView officeById(@PathVariable("id") Long id) throws Exception {
        try{
            OfficeView officeView = officeService.loadById(id);
            return officeView;
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время поиска информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }


    @ApiOperation(value = "listOffice", nickname = "listOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<OfficeView> offices(@RequestBody OfficeView offView) throws Exception {
        try{
            List<OfficeView> officeList  = officeService.loadByParams(offView);
            return officeList;
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время поиска информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }


    @ApiOperation(value = "addOffice", nickname = "addOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/save", method = {POST})
    public OfficeView addOffice(@RequestBody OfficeView officeView) throws Exception {
         try{
            return officeService.save(officeView);
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время сохранения информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }

    @ApiOperation(value = "updateOffice", nickname = "updOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public OfficeView updOffice(@RequestBody OfficeView officeView) throws Exception {
        try{
            return officeService.update(officeView);
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время обновления информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }

    @ApiOperation(value = "deleteOffice", nickname = "delOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Office.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public OfficeView delOffice(@RequestBody OfficeView officeView) throws Exception {
        try{
            return officeService.delete(officeView);
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время удаления информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }
}