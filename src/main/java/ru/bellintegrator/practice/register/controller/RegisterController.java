package ru.bellintegrator.practice.register.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.register.model.Register;
import ru.bellintegrator.practice.register.service.RegisterService;
import ru.bellintegrator.practice.register.service.RegisterValidService;
import ru.bellintegrator.practice.register.view.RegisterView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotValidException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@Api(value = "RegisterControllerAPI")
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @ApiOperation(value = "loginRegister", nickname = "loginRegister", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Register.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/login", method = {POST})
    public RegisterView loginRegister(@RequestBody RegisterView regView) throws ResourceNotFoundException {
        RegisterView view = registerService.loadByParams(regView);

        if(view == null)
            throw new ResourceNotFoundException("Данные пользователя не найдены");

        return view;
    }


    @ApiOperation(value = "addRegister", nickname = "addRegister", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Register.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/register", method = {POST})
    public void addRegister(@RequestBody RegisterView regView) throws ResourceNotValidException {
        registerService.save(regView);
    }

    @RequestMapping(value = "/activation", method = {GET})
    public void checkCode(@RequestParam(name = "code", required = true) String activeCode) throws ResourceNotValidException {
        registerService.update(activeCode);
    }

}
