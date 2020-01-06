package ua.nure.apz.makieiev.apz.controller.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.nure.apz.makieiev.apz.model.entity.User;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AuthorizeUser {

	private User user;
	private String token;

}
