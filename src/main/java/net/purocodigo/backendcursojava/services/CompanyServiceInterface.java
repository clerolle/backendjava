package net.purocodigo.backendcursojava.services;

import net.purocodigo.backendcursojava.shared.dto.CompanyCreationDto;
import net.purocodigo.backendcursojava.shared.dto.CompanyDto;

public interface CompanyServiceInterface {
    public CompanyDto createCompany(CompanyCreationDto creationCompanyDto);

    public CompanyDto getCompany(String nit);

    public void deleteCompany(String nit, long userId);
}
