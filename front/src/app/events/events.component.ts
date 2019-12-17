import {Component, OnInit} from "@angular/core";
import {Event} from "../model/event.model";
import {EventService} from "../service/event.service";

@Component({
  selector: 'events-show',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  events: Event[];

  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    return this.eventService.getAllEvents()
      .subscribe(eventInformation => this.events = eventInformation);
  }

}
