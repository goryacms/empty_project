package ru.bellintegrator.practice.organization.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceInternalException;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
@Api(value = "OrganizationControllerAPI")
public class OrganizationController  {
    private OrganizationService orgService;



    @Autowired
    public OrganizationController(OrganizationService orgService) {
        this.orgService = orgService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrganizationView organizationById(@PathVariable(value = "id", required = true) Long id) throws Exception {
        try {
            OrganizationView orgView = orgService.loadById(id);
            return orgView;
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время поиска информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }

    }

    @ApiOperation(value = "listOrganization", nickname = "listOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<OrganizationView> organizations(@RequestBody OrganizationView orgView) throws Exception {

        try{
            List<OrganizationView> orgList = orgService.loadByParams(orgView);
            return orgList;
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время поиска информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }


    @ApiOperation(value = "addOrganization", nickname = "addOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/save", method = {POST})
    public OrganizationView addOrganization(@RequestBody OrganizationView orgView) throws Exception {
        try{
            return orgService.save(orgView);
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время сохранения информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }

    @ApiOperation(value = "updateOrganization", nickname = "updOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public OrganizationView updOrganization(@RequestBody OrganizationView orgView) throws Exception {
        try{
            return orgService.update(orgView);
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время обновления информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }


    @ApiOperation(value = "deleteOrganization", nickname = "delOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public OrganizationView delOrganization(@RequestBody OrganizationView orgView) throws Exception {
        try{
            return orgService.delete(orgView);
        }catch(ResourceInternalException e){
            throw new ResourceInternalException("Во время удаления информации произошла ошибка. Пожалуйста, обратитесь в службу поддержки");
        }
    }
}