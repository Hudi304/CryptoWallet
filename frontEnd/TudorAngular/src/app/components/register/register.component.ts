import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterRequest } from '../../DTO/register-request';
import { RegisterResponse } from '../../DTO/register-response';
import { RegisterService } from '../../service/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})


export class RegisterComponent {

  registerRespRecieved: boolean = false;
  registerSuccessful: boolean = false;
  registerRequest: RegisterRequest;
  registerResponse: RegisterResponse;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private regService: RegisterService) {
    this.registerRequest = new RegisterRequest();
    this.registerResponse = new RegisterResponse();
  }

  onRegister() {
    this.regService.requestRegister(this.registerRequest)
      .subscribe(data => {
        this.registerResponse = data
        this.registerRespRecieved = true

        if (this.registerResponse.emailAlreadyUsed == false &&
            this.registerResponse.emailLooksWeird == false &&
            this.registerResponse.nicknameAlreadyUsed == false)
             {
          this.registerSuccessful = true;
          this.router.navigate(['/login'])
        }

        console.log(this.registerResponse)
      }
      )

    //console.log(this.registerResponse)

    if (this.registerResponse.emailAlreadyUsed == false &&
      this.registerResponse.nicknameAlreadyUsed == false &&
      this.registerResponse.nicknameAlreadyUsed == false) {
      console.log("user Registred sucessfully")
    }
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

}
