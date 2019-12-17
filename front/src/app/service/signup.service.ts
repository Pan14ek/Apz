import {Injectable} from "@angular/core";
import {SignUpUserDto} from "../dto/signupuser.dto";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  addUserApiPostUrl = "http://localhost:8080/user/add";

  constructor(private http: HttpClient) {
  }

  addUser(signUpUserDto: SignUpUserDto) {
    const body = {
      idCompany: signUpUserDto.idCompany,
      firstName: signUpUserDto.firstName,
      lastName: signUpUserDto.lastName,
      email: signUpUserDto.email,
      login: signUpUserDto.login,
      password: signUpUserDto.password,
      repeatPassword: signUpUserDto.repeatPassword,
      imageLink: signUpUserDto.imageLink
    }
    return this.http.post(this.addUserApiPostUrl, body);
  }

}
