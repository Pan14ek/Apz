import {Component} from "@angular/core";
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import {SignInUserDto} from "../dto/signinuser.dto";
import {AuthorizeService} from "../service/authorize.service";
import {User} from "../model/user.model";
import {Router} from "@angular/router";

@Component({
  selector: 'sign-in',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class SignInComponent {

  private _signInUserDto: SignInUserDto = new SignInUserDto('', '');
  private receivedUser: User;
  private done: boolean = false;

  constructor(config: NgbModalConfig, private modalService: NgbModal, private authorizeService: AuthorizeService, private router: Router) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  open(content) {
    this.modalService.open(content);
  }

  signIn(signInUserDto: SignInUserDto) {
    console.log(signInUserDto);
    this.authorizeService
      .signIn(signInUserDto)
      .subscribe((data: User) => {
          this.receivedUser = data;
          if (this.receivedUser != null) {
            this.redirect();
          }
          this.done = true;
        },
        error => console.log(error));
  }

  redirect() {
    this.router.navigate(['/home'])
  }


  get signInUserDto(): SignInUserDto {
    return this._signInUserDto;
  }

  set signInUserDto(value: SignInUserDto) {
    this._signInUserDto = value;
  }

}
