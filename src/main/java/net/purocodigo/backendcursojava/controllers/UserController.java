package net.purocodigo.backendcursojava.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.purocodigo.backendcursojava.models.requests.UserDetailRequestModel;
import net.purocodigo.backendcursojava.models.responses.CompanyRest;
import net.purocodigo.backendcursojava.models.responses.UserRest;
import net.purocodigo.backendcursojava.services.UserServiceInterface;
import net.purocodigo.backendcursojava.shared.dto.CompanyDto;
import net.purocodigo.backendcursojava.shared.dto.UserDto;

@RestController
@RequestMapping("/users") // localhost:8080/users
public class UserController {

    @Autowired
    UserServiceInterface userService;

    @Autowired
    ModelMapper mapper;
    
    @GetMapping
    public UserRest getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getPrincipal().toString();

        UserDto userDto = userService.getUser(email);

        // ModelMapper mapper = new ModelMapper();

        UserRest userToReturn = mapper.map(userDto, UserRest.class);

        return userToReturn;
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailRequestModel userDetails ){
        
        UserRest userToReturn = new UserRest();

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, userToReturn );

        return userToReturn;
    }

    @GetMapping(path = "/company") // localhost:8080/users/company
    public List<CompanyRest> getCompany() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getPrincipal().toString();

        List<CompanyDto> company = userService.getUserCompany(email);

        List<CompanyRest> companyRests = new ArrayList<>();

        for (CompanyDto companyDto : company) {
            CompanyRest companyRest = mapper.map(companyDto, CompanyRest.class);
            companyRests.add(companyRest);
        }
        return companyRests;
    }

}
