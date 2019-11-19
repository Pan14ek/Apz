package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.Company;

import java.util.Optional;

public interface CompanyService {

	Optional<Company> findById(long id);

}