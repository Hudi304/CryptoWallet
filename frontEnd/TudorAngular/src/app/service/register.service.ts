import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pipe, PipeTransform } from '@angular/core';
import { RegisterResponse } from '../DTO/register-response';
import { RegisterRequest } from '../DTO/register-request';

@Injectable()
export class RegisterService {



  private userUrl: string;
  public registerResponse: RegisterResponse;

  constructor(private http: HttpClient) {
    this.userUrl = 'http://localhost:8080/register';
    this.registerResponse = new RegisterResponse();
  }



  public requestRegister(registerReq: RegisterRequest): Observable<RegisterResponse> {
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      observe: 'response' as 'response'
    };

    return this.http.post<RegisterResponse>(this.userUrl, registerReq)

  }

}
