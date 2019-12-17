export class Company {

  private _id: number;
  private _address: string;
  private _email: string;
  private _title: string;

  constructor(id: number, address: string, email: string, title: string) {
    this._id = id;
    this._address = address;
    this._email = email;
    this._title = title;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get address(): string {
    return this._address;
  }

  set address(value: string) {
    this._address = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

}
