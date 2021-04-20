import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './service/login-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;

  message!: string;
  constructor(
    private loginService: LoginService,
    public router: Router
  ) {
    this.title = 'Spring Boot - Angular Application';
  }


  goToUserWallet() {
    this.router.navigate(['/wallet']);
  }

  goToHome() {
    this.router.navigate(['/user']);
  }



}
