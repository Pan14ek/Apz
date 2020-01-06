package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.service.TokenService;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class TokenServiceImpl implements TokenService {

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	private static final Base64.Encoder BASE_64_ENCODER = Base64.getUrlEncoder();

	@Override
	public String generateToken() {
		byte[] randomBytes = new byte[24];
		SECURE_RANDOM.nextBytes(randomBytes);
		return BASE_64_ENCODER.encodeToString(randomBytes);
	}

}
