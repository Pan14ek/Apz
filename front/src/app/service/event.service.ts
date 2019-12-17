import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Event} from "../model/event.model";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  getAllEventsApiUrl = "http://localhost:8080/event/get/all";

  constructor(private http: HttpClient) {
  }

  getAllEvents() {
    return this.http.get<Event[]>(this.getAllEventsApiUrl);
  }

}
