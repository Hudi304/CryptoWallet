import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from '../model/admin';
import { Currencie } from '../model/currencie';
import { Transaction } from '../model/transaction';

@Injectable()
export class AdminService {


  private adminsUrl: string;

  constructor(private http: HttpClient) {
    this.adminsUrl = 'http://localhost:8080/admin';
  }

  // ** aici ar trebui sa ai SAVE UPDATE FETCH si DELETE pentru toate entitatile din aplicatie


  //** FETCHING
  //tested
  public fetchAllCurrencies(): Observable<Currencie[]> {
    console.log("fetching all Currencies")
    return this.http.get<Currencie[]>(this.adminsUrl + "/currencies")
  }
  //tested
  public fetchAllTransactions(): Observable<Transaction[]> {
    console.log("fetching all transactions")
    return this.http.get<Transaction[]>(this.adminsUrl + "/transactions")
  }
  public fetchAllAdmins(): Observable<Admin[]> {
    console.log("fetching all Admins")
    return this.http.get<Admin[]>(this.adminsUrl + "/admins")
  }

  //**SAVING */
  public saveTransaction(transaction: Transaction) {
    return this.http.post(this.adminsUrl + "/transactions/save", transaction)
  }

  public saveCurrencie(currencie: Currencie) {
    return this.http.post(this.adminsUrl + "/currencies/save", currencie)
  }
  public saveAdmin(admin: Admin){
    return this.http.post(this.adminsUrl + "/admin/save", admin)
  }


  //**DELETE */
  //tested
  public deleteUser(nickname: string) {
    console.log("removed " + nickname)
    return this.http.post("http://localhost:8080/" + "admin/users/remove", nickname)
  }

  public deleteCurrency(id: number) {
    console.log("removed " + id)
    return this.http.post("http://localhost:8080/" + "admin/currencies/remove", id)
  }

  public deleteTransaction(id: number) {
    console.log("removed " + id)
    return this.http.post("http://localhost:8080/admin/transactions", id)
  }

  // *TODO: implement UPDATE 

}
