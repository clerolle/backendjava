package net.purocodigo.backendcursojava.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.purocodigo.backendcursojava.models.requests.CompanyCreateRequestModel;
import net.purocodigo.backendcursojava.models.responses.CompanyRest;
import net.purocodigo.backendcursojava.services.CompanyServiceInterface;
import net.purocodigo.backendcursojava.services.UserService;
import net.purocodigo.backendcursojava.shared.dto.CompanyCreationDto;
import net.purocodigo.backendcursojava.shared.dto.CompanyDto;
import net.purocodigo.backendcursojava.shared.dto.UserDto;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyServiceInterface companyServiceInterface;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper mapper;
    
    @PostMapping
    public CompanyRest createCompany(@RequestBody CompanyCreateRequestModel companyCreateRequestModel) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getPrincipal().toString();

        // ModelMapper mapper = new ModelMapper();

        CompanyCreationDto companyCreationDto = mapper.map(companyCreateRequestModel, CompanyCreationDto.class);

        companyCreationDto.setUserEmail(email);

        CompanyDto companyDto = companyServiceInterface.createCompany(companyCreationDto);

        CompanyRest companyToReturn = mapper.map(companyDto, CompanyRest.class);

        return companyToReturn;
    }

    @GetMapping(path = "/{id}")
    public CompanyRest getCompany(@PathVariable String id) {

        CompanyDto companyDto =  companyServiceInterface.getCompany(id);

        CompanyRest companyRest = mapper.map(companyDto, CompanyRest.class);

        return companyRest;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCompany(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDto user = userService.getUser(authentication.getPrincipal().toString());

        companyServiceInterface.deleteCompany(id, user.getId());
    }
}
