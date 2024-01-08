package net.purocodigo.backendcursojava.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.purocodigo.backendcursojava.shared.dto.UserDto;
import net.purocodigo.backendcursojava.shared.dto.CompanyDto;

public interface UserServiceInterface extends UserDetailsService {
    public UserDto createUser(UserDto user);
    
    public UserDto getUser(String email);

    public List<CompanyDto> getUserCompany(String email);
}
