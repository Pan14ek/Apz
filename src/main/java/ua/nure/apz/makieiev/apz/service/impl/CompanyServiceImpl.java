package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.model.Company;
import ua.nure.apz.makieiev.apz.service.CompanyService;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Override
	public Optional<Company> findById(long id) {
		return Optional.empty();
	}

}