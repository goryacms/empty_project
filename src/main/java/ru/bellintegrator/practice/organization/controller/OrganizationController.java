package ru.bellintegrator.practice.organization.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.service.OrganizationValidService;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotValidException;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
@Api(value = "OrganizationControllerAPI")
public class OrganizationController  {
    private OrganizationService orgService;

    private OrganizationValidService orgValidService;

    @Autowired
    public OrganizationController(OrganizationService orgService, OrganizationValidService orgValidService) {
        this.orgService = orgService;
        this.orgValidService = orgValidService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrganizationView organizationById(@PathVariable(value = "id", required = true) Long id) throws ResourceNotFoundException {
            OrganizationView orgView = orgService.loadById(id);

            if (orgView == null ) {
                throw new ResourceNotFoundException("Организация с идентификатором = " + id + " не найдена");
            }else{
                return orgView;
            }
    }

    @ApiOperation(value = "listOrganization", nickname = "listOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<OrganizationView> organizations(@RequestBody OrganizationView orgView) throws ResourceNotFoundException, ResourceNotValidException {
        if(!orgValidService.checkList(orgView))
            throw new ResourceNotValidException("Полученные данные некорректны");

        List<OrganizationView> orgList = orgService.loadByParams(orgView);
        if(orgList.size() == 0) {
            throw new ResourceNotFoundException("Информация по заданным условиям не найдена");
        }
        
        return orgList;

    }


    @ApiOperation(value = "addOrganization", nickname = "addOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/save", method = {POST})
    public OrganizationView addOrganization(@RequestBody OrganizationView orgView) throws ResourceNotValidException {
        if(!orgValidService.checkSave(orgView))
            throw new ResourceNotValidException("Полученные данные некорректны");

        return orgService.save(orgView);
    }

    @ApiOperation(value = "updateOrganization", nickname = "updOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public OrganizationView updOrganization(@RequestBody OrganizationView orgView) throws ResourceNotValidException {
        if(!orgValidService.checkUpdate(orgView))
            throw new ResourceNotValidException("Полученные данные некорректны");

        return orgService.update(orgView);
    }


    @ApiOperation(value = "deleteOrganization", nickname = "delOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public OrganizationView delOrganization(@RequestBody OrganizationView orgView) {
        return orgService.delete(orgView);
    }
}