import {Component} from '@angular/core';
import {NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'main-carousel',
  templateUrl: './maincarousel.component.html',
  styleUrls: ['./maincarousel.component.css'],
  providers: [NgbCarouselConfig]
})
export class MainCarousel {

  images = [700, 533, 807, 124]
    .map((n) => `https://picsum.photos/id/${n}/900/500`);

  constructor(config: NgbCarouselConfig) {
    config.interval = 10000;
    config.wrap = false;
    config.keyboard = false;
    config.pauseOnHover = false;
  }

}
