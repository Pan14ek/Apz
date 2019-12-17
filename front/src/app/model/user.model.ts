import {Position} from "./position.model";
import {Company} from "./company.model";

export class User {

  private _id: number;
  private _firstName: string;
  private _lastName: string;
  private _email: string;
  private _login: string;
  private _password: string;
  private _imageLink: string;
  private _position: Position;
  private _company: Company;

  constructor(id: number, firstName: string, lastName: string, email: string, login: string, password: string, imageLink: string, position: Position, company: Company) {
    this._id = id;
    this._firstName = firstName;
    this._lastName = lastName;
    this._email = email;
    this._login = login;
    this._password = password;
    this._imageLink = imageLink;
    this._position = position;
    this._company = company;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get firstName(): string {
    return this._firstName;
  }

  set firstName(value: string) {
    this._firstName = value;
  }

  get lastName(): string {
    return this._lastName;
  }

  set lastName(value: string) {
    this._lastName = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get login(): string {
    return this._login;
  }

  set login(value: string) {
    this._login = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get imageLink(): string {
    return this._imageLink;
  }

  set imageLink(value: string) {
    this._imageLink = value;
  }

  get position(): Position {
    return this._position;
  }

  set position(value: Position) {
    this._position = value;
  }

  get company(): Company {
    return this._company;
  }

  set company(value: Company) {
    this._company = value;
  }
}
