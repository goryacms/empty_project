package ru.bellintegrator.practice.organization.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.ResponseView;

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
    public OrganizationView organizationById(@PathVariable("id") Long id) {
        return orgService.loadById(id);
    }

    /**
     * TODO: добавить парамаетры; доработать логику в Service
     */
    @ApiOperation(value = "listOrganization", nickname = "listOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<OrganizationView> organizations() {
        return orgService.all();
    }


    @ApiOperation(value = "addOrganization", nickname = "addOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/save", method = {POST})
    public ResponseView addOrganization(@RequestBody OrganizationView orgView) {
        return orgService.save(orgView);
    }

    @ApiOperation(value = "updateOrganization", nickname = "updOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/update", method = {POST})
    public ResponseView updOrganization(@RequestBody OrganizationView orgView) {
        return orgService.update(orgView);
    }


    /**
     * TODO: выявить причину почему не работает
     */
    @ApiOperation(value = "deleteOrganization", nickname = "delOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/delete", method = {POST})
    public ResponseView delOrganization(Long id) {
        return orgService.delete(id);
    }
}