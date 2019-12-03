package ua.nure.apz.makieiev.apz.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUserDto {

	private long idCompany;
	private String firstName;
	private String lastName;
	private String email;
	private String login;
	private String password;
	private String repeatPassword;
	private String imageLink;

}