import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
// @ts-ignore
import defaultLanguage from "src/assets/i18n/en.json";

@Component({
  selector: 'main-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  projectName: "Game4you";

  constructor(private translate: TranslateService) {
    translate.setTranslation('en', defaultLanguage);
    translate.setDefaultLang('en');
  }

  useLanguage(language: string) {
    this.translate.use(language);
  }

}
