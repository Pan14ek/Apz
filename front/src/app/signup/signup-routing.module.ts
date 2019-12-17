import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {SignUpComponent} from "./signup.component";

const routes: Routes = [
  {path: 'signup', component: SignUpComponent}
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SignUpModule {
}
