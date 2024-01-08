package net.purocodigo.backendcursojava.repositories;

import java.util.List;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.purocodigo.backendcursojava.entities.CompanyEntity;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<CompanyEntity, String> {
    List<CompanyEntity> getByUserIdOrderByName(long userId);

    CompanyEntity findByNit(String  nit);
}
