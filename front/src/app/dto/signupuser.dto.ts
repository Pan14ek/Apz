export class SignUpUserDto {

  private _idCompany: number;
  private _firstName: string;
  private _lastName: string;
  private _email: string;
  private _login: string;
  private _password: string;
  private _repeatPassword: string;
  private _imageLink: string;

  constructor(idCompany: number, firstName: string, lastName: string, email: string, login: string, password: string, repeatPassword: string, imageLink: string) {
    this._idCompany = idCompany;
    this._firstName = firstName;
    this._lastName = lastName;
    this._email = email;
    this._login = login;
    this._password = password;
    this._repeatPassword = repeatPassword;
    this._imageLink = imageLink;
  }

  get idCompany(): number {
    return this._idCompany;
  }

  set idCompany(value: number) {
    this._idCompany = value;
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

  get repeatPassword(): string {
    return this._repeatPassword;
  }

  set repeatPassword(value: string) {
    this._repeatPassword = value;
  }

  get imageLink(): string {
    return this._imageLink;
  }

  set imageLink(value: string) {
    this._imageLink = value;
  }

}
