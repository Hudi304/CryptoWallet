import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../DTO/login-request';
import { LoginResponse } from '../DTO/login-response';
import { UserService } from './user-service.service';


@Injectable()
export class LoginService {

  private userUrl: string;
  private loginResp: LoginResponse;
  public userName: string | undefined
  public password: string | undefined

  constructor(private http: HttpClient,
    private userService : UserService) {
    this.userUrl = 'http://localhost:8080/login';
    this.loginResp = new LoginResponse();
  }

  public requestLogin(loginReq: LoginRequest): Observable<LoginResponse> {
    this.userName = loginReq.nickname
    this.password = loginReq.password
    this.userService.username = this.userName
    //TODO : implement the token 
    return this.http.post<LoginResponse>(this.userUrl, loginReq);
  }


}


