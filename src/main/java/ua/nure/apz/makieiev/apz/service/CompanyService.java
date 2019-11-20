package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.Company;

import java.util.Optional;

public interface CompanyService {

    Company add(Company company);

    Company update(Company company);

    Optional<Company> findById(long id);

    Optional<Company> getByTitle(String title);

    boolean removeById(long id);

}