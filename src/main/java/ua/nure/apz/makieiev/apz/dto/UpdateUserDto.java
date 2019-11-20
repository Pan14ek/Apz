package ua.nure.apz.makieiev.apz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {

    private long id;
    private long idCompany;
    private long idPosition;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String imageLink;

}