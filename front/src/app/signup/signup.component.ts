import {Component} from "@angular/core";
import {SignUpService} from "../service/signup.service";
import {SignUpUserDto} from "../dto/signupuser.dto";
import {User} from "../model/user.model";
import {Router} from "@angular/router";

@Component({
  selector: 'sign-up',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers: [SignUpService]
})
export class SignUpComponent {

  private receivedUser: User;
  private done: boolean = false;
  private _signUpUserDto: SignUpUserDto = new SignUpUserDto(1,
    '',
    '',
    '',
    '',
    '',
    '',
    '');

  constructor(private signUpService: SignUpService, private router: Router) {
  }

  userRegistration(signUpUserDto: SignUpUserDto) {
    this.signUpService
      .addUser(signUpUserDto)
      .subscribe(
        (data: User) => {
          this.receivedUser = data;
          if (this.receivedUser != null) {
            this.redirect();
          }
          this.done = true;
        },
        error => console.log(error));
  }

  get signUpUserDto(): SignUpUserDto {
    return this._signUpUserDto;
  }

  set signUpUserDto(value: SignUpUserDto) {
    this._signUpUserDto = value;
  }

  redirect() {
    this.router.navigate(['/home'])
  }

}
