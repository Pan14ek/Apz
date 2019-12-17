export class EventStatistic {

  private _eventTitle: string;
  private _userAmount: number;
  private _taskAmount: number;
  private _percentOfDoneTasks: number;

  constructor(eventTitle: string, userAmount: number, taskAmount: number, percentOfDoneTasks: number) {
    this._eventTitle = eventTitle;
    this._userAmount = userAmount;
    this._taskAmount = taskAmount;
    this._percentOfDoneTasks = percentOfDoneTasks;
  }

  get eventTitle(): string {
    return this._eventTitle;
  }

  set eventTitle(value: string) {
    this._eventTitle = value;
  }

  get userAmount(): number {
    return this._userAmount;
  }

  set userAmount(value: number) {
    this._userAmount = value;
  }

  get taskAmount(): number {
    return this._taskAmount;
  }

  set taskAmount(value: number) {
    this._taskAmount = value;
  }

  get percentOfDoneTasks(): number {
    return this._percentOfDoneTasks;
  }

  set percentOfDoneTasks(value: number) {
    this._percentOfDoneTasks = value;
  }

}
