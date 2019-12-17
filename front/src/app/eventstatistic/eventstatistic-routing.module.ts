import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {EventStatisticComponent} from "./eventstatistic.component";

const routes: Routes = [
  {path: 'rating/events', component: EventStatisticComponent}
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventStatisticRoutingModule {
}
