import { EventEmitter, Output } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginRequest } from '../../DTO/login-request';
import { LoginResponse } from '../../DTO/login-response';
import { LoginService } from '../../service/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginRequest: LoginRequest;
  loginResponse: LoginResponse;

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  message: string = "Hola Mundo!"
  @Output() messageEvent = new EventEmitter<string>();

  sendMessage() {
    this.messageEvent.emit(this.message)
  }

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService,
    private _snackBar: MatSnackBar
  ) {
    this.loginRequest = new LoginRequest();
    this.loginResponse = new LoginResponse();
  }

  onLogin() {
    console.log(this.loginRequest)
    this.loginService.requestLogin(this.loginRequest)
      .subscribe(data => {
        this.loginResponse = data;
        if (this.loginResponse.foundUser) {
          if (this.loginResponse.isAdmin) {
            this.goToAdminScreen()
            this.openSnackBar("Welcome Admin", 3000, "green-snackbar")
          } else {
            this.goToUserScreen()
            this.openSnackBar("Welcome to your Virual Wallet", 3000, "blue-snackbar")
          }
        } else {
          this.openSnackBar("UserName or password is incorrect !", 10000, "red-snackbar")
        }
        this.sendMessage()
      })
  }


  goToAdminScreen() {
    this.router.navigate(['/admin']);
  }
  goToUserScreen() {
    this.router.navigate(['/user']);
  }

  openSnackBar(mesaage: string, duration: number, color: string) {
    this._snackBar.open(mesaage, 'X', {
      duration: duration,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
      panelClass: [color]
    });
  }

}
