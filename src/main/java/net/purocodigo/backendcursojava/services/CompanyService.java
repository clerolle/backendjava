package net.purocodigo.backendcursojava.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.purocodigo.backendcursojava.entities.CompanyEntity;
import net.purocodigo.backendcursojava.entities.UserEntity;
import net.purocodigo.backendcursojava.repositories.CompanyRepository;
import net.purocodigo.backendcursojava.repositories.UserRepository;
import net.purocodigo.backendcursojava.shared.dto.CompanyCreationDto;
import net.purocodigo.backendcursojava.shared.dto.CompanyDto;

@Service
public class CompanyService implements CompanyServiceInterface {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public CompanyDto createCompany(CompanyCreationDto creationCompanyDto) {

        UserEntity userEntity = userRepository.findByEmail(creationCompanyDto.getUserEmail());

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setUser(userEntity);
        companyEntity.setNit(creationCompanyDto.getNit());
        companyEntity.setName(creationCompanyDto.getName());
        companyEntity.setAddress(creationCompanyDto.getAddress());
        companyEntity.setPhone(creationCompanyDto.getPhone());
        
        CompanyEntity createCompany = companyRepository.save(companyEntity);

        // ModelMapper mapper = new ModelMapper();

        CompanyDto companyToReturn = mapper.map(createCompany, CompanyDto.class);

        return companyToReturn;
    }

    @Override
    public CompanyDto getCompany(String nit) {

        CompanyEntity companyEntity = companyRepository.findByNit(nit);

        CompanyDto companyDto = mapper.map(companyEntity, CompanyDto.class);
        return companyDto;   
    }

    @Override
    public void deleteCompany(String nit, long userId) {
        CompanyEntity companyEntity = companyRepository.findByNit(nit);

        companyRepository.delete(companyEntity);
    }

    
}
