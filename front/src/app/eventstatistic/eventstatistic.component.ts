import {EventStatistic} from "../model/EventStatistic";
import {EventStatisticService} from "../service/eventStatistic.service";
import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'statistic-info',
  templateUrl: './eventstatistic.component.html',
  styleUrls: ['./eventstatistic.component.css']
})
export class EventStatisticComponent implements OnInit {

  EventStatistics: EventStatistic[];

  constructor(private eventStatisticService: EventStatisticService) {
  }

  ngOnInit() {
    return this.eventStatisticService
      .getEventStatisticInformation()
      .subscribe(eventStatistic => this.EventStatistics = eventStatistic);
  }

}
