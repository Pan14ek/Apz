import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {EventStatistic} from "../model/EventStatistic";

@Injectable({
  providedIn: 'root'
})
export class EventStatisticService {

  eventStatisticApiGetUrl = "http://localhost:8080/statistic/get/event/statistic/all";

  constructor(private http: HttpClient) {
  }

  getEventStatisticInformation() {
    return this.http.get<EventStatistic[]>(this.eventStatisticApiGetUrl);
  }

}
