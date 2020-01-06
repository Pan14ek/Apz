import {Injectable} from "@angular/core";
import {SignInUserDto} from "../dto/signinuser.dto";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthorizeService {

  authorizeApiUrl = "http://localhost:8080/signIn";

  constructor(private http: HttpClient) {
  }

  signIn(signInUserDto: SignInUserDto) {
    const body = {
      login: signInUserDto.login,
      password: signInUserDto.password
    }
    return this.http.post(this.authorizeApiUrl, body);
  }

}
