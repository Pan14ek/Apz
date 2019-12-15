import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppComponent} from './app.component';
import {HeaderComponent} from "./header/header.component";
import {SignInComponent} from "./signin/signin.component";

@NgModule({
  declarations: [
    AppComponent, HeaderComponent, SignInComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
