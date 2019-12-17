import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppComponent} from './app.component';
import {HeaderComponent} from "./header/header.component";
import {SignInComponent} from "./signin/signin.component";
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {MainCarousel} from "./maincarousel/maincarousel.component";
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {EventStatisticComponent} from "./eventstatistic/eventstatistic.component";
import {AppRoutingModule} from "./app-routing.module";
import {EventStatisticRoutingModule} from "./eventstatistic/eventstatistic-routing.module";
import {SignUpComponent} from "./signup/signup.component";
import {MainComponent} from "./main/main.component";
import {EventsComponent} from "./events/events.component";
import {MainModule} from "./main/main-routing.module";
import {EventsModule} from "./events/events-routing.module";
import {SignUpModule} from "./signup/signup-routing.module";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignInComponent,
    MainCarousel,
    EventStatisticComponent,
    SignUpComponent,
    MainComponent,
    EventsComponent
  ],
  entryComponents: [
    SignInComponent,
    SignUpComponent
  ],
  imports: [
    BrowserModule,
    SignUpModule,
    AppRoutingModule,
    FormsModule,
    EventStatisticRoutingModule,
    MainModule,
    EventsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [],
  exports: [
    SignInComponent,
    SignUpComponent
  ],
  bootstrap: [
    AppComponent,
    SignInComponent,
    HeaderComponent,
    EventStatisticComponent,
    SignUpComponent
  ]
})
export class AppModule {
}

export function HttpLoaderFactory(httpClient: HttpClient) {
  return new TranslateHttpLoader(httpClient);
}
