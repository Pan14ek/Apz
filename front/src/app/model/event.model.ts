export class Event {

  private _id: number;
  private _title: string;
  private _startDate: Date;
  private _finishDate: Date;
  private _description: string;

  constructor(id: number, title: string, startDate: Date, finishDate: Date, description: string) {
    this._id = id;
    this._title = title;
    this._startDate = startDate;
    this._finishDate = finishDate;
    this._description = description;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get startDate(): Date {
    return this._startDate;
  }

  set startDate(value: Date) {
    this._startDate = value;
  }

  get finishDate(): Date {
    return this._finishDate;
  }

  set finishDate(value: Date) {
    this._finishDate = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

}
