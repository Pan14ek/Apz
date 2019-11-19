package ua.nure.apz.makieiev.apz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {

	private long idCompany;
	private String firstName;
	private String lastName;
	private String email;
	private String login;
	private String password;
	private String imageLink;

}