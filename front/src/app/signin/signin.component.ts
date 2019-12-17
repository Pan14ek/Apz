import {Component} from "@angular/core";
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import {SignInUserDto} from "../dto/signinuser.dto";

@Component({
  selector: 'sign-in',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class SignInComponent {

  signInUserDto: SignInUserDto = new SignInUserDto('', '');

  constructor(config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  open(content) {
    this.modalService.open(content);
  }

}
