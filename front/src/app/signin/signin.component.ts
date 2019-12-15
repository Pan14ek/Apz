import {Component} from "@angular/core";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'sign-in',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SignInComponent {

  signInForm: FormGroup;

  ngOnInit() {
    this.signInForm = new FormGroup({
      login: new FormControl(),
      password: new FormControl()
    });
  }

}
