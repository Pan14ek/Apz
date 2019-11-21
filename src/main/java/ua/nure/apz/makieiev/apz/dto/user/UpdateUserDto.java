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
public class UpdateUserDto {

    private long id;
    private long idCompany;
    private long idPosition;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String oldPassword;
    private String oldRepeatPassword;
    private String password;
    private String imageLink;

}