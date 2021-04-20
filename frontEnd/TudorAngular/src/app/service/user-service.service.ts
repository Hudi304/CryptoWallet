import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


import { Observable } from 'rxjs';
import { User } from '../model/user';
import { Currencie } from '../model/currencie';
import { Coin } from '../model/coin';
import { CoinInfo } from '../model/coin-info';
import { TransactionRequest } from '../model/transaction-request';
import { CryptoTransaction } from '../model/crypto-transaction';

@Injectable()
export class UserService {

  public FIAT : string = "usd"
  public nrOfDays : number = 10
  public COIN_GEKO_GET_MARKET_CHART_URL 
  = `https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=${this.FIAT}&days=${this.nrOfDays}`

  public username : string  = ""
  public userToken : string  = ""

  public clickedCoin!  :  CoinInfo

  private usersUrl: string;
  public selectedCoin : CoinInfo

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
    this.selectedCoin =  new CoinInfo()
  }

  public findAll(): Observable<User[]> {
    console.log("find all")
    return this.http.get<User[]>(this.usersUrl);
  }

  public save(user: User) {
    console.log("save")
    return this.http.post<User>(this.usersUrl, user);
  }


  public fetchAllCurrencies(): Observable<Currencie[]> {
    console.log("fetching all Currencies")
    return this.http.get<Currencie[]>(this.usersUrl + "/currencies")
  }

  public fetchAllCoins(): Observable<Coin[]> {
    console.log("fetching all Coins")
    return this.http.get<Coin[]>(this.usersUrl + "/coins")
  }

  public fetchAllCoinInfo(): Observable<CoinInfo[]> {
    console.log("fetching all CoinsInfo")
    return this.http.get<CoinInfo[]>(this.usersUrl + "/coinInfo")
  }

  public getAllCoinData(coin : CoinInfo){
    console.log("getting all the data")
    this.selectedCoin = coin
  }


  public getCoinHistory(coinId : string,nrOfDays : number, FIAT : string ) {
    this.nrOfDays = nrOfDays
    this.FIAT = FIAT
    return this.http.get("https://api.coingecko.com/api/v3/coins/" + coinId + "/market_chart?vs_currency=" + FIAT + "&days=" + nrOfDays )
  }


  public buy(coinID : string, coinCurrentPrice : number, amount:  number){
    //! current price ar trebui sa fie la toti la fel probabil in USD
    let date  =  new Date()
    //! data trebuie sa fie in UTC
    console.log(date.toUTCString())
    let transactionRequest  =  new TransactionRequest(coinID, coinCurrentPrice, amount, date.toUTCString(), this.username) 
    return this.http.post<TransactionRequest>(this.usersUrl + "/transactions",transactionRequest);
  }

  public getTransactions(userNickname : string){
    //todo : probabil si un token 
    return this.http.post<CryptoTransaction[]>(this.usersUrl + "/tansact",userNickname)
  }

  public getCoinPrice(coinID :  string, fiat : String){
    return this.http.get("https://api.coingecko.com/api/v3/simple/price?ids=" + coinID + "&vs_currencies=" + fiat)
  }




}