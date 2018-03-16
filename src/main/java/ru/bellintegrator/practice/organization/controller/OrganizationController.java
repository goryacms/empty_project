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

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
@Api(value = "OrganizationControllerAPI")
public class OrganizationController /*implements ErrorController*/ {
    private OrganizationService orgService;

    /*
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
    */

    @Autowired
    public OrganizationController(OrganizationService orgService) {
        this.orgService = orgService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrganizationView organizationById(@PathVariable("id") long id) {
        return orgService.loadById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Organization> organizations() {
        return orgService.all();
    }


    @ApiOperation(value = "addOrganization", nickname = "addOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Organization.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    //@RequestMapping(value = "/save", method = {POST})
    @PostMapping("/save")
    public void addOrganization(@RequestBody OrganizationView orgView) {
        orgService.save(orgView);
    }
}
