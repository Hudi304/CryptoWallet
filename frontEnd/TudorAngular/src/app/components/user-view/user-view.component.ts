import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Coin } from 'src/app/model/coin';
import { CoinInfo } from 'src/app/model/coin-info';
import { Currencie } from 'src/app/model/currencie';
import { UserService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {


  currencies: Currencie[] = [];
  coins: Coin[] = [];
  coinsInfo : CoinInfo[] =[]
  

  constructor(
    private userService: UserService,
    public router: Router
  ) { }

  ngOnInit(): void {
    //this.refreshCurrencysTable();
    //this.refreshCoinsTable()
    this.refreshCoinsInfoTable()
    this.currencies.forEach((item) => {
      item.buyPressed = false
    })
  }


  refreshCurrencysTable(): void {
    this.userService.fetchAllCurrencies()
      .subscribe(data => {
        this.currencies = data
        console.log(this.currencies)
      })
  }

  refreshCoinsTable(): void {
    this.userService.fetchAllCoins()
      .subscribe(data => {
        this.coins = data
        //console.log(this.coins)
      })
  }

  refreshCoinsInfoTable(): void {
    this.userService.fetchAllCoinInfo()
      .subscribe(data => {
        this.coinsInfo = data
        //console.log(this.coinsInfo)
      })
  }

  onBuy(currencie: Currencie) {
    currencie.buyPressed = true;
  }

  goToCurrencyView(coin : CoinInfo){
    console.log("going to currenny view...")
    //console.log(coin)
    this.userService.clickedCoin = coin
    this.router.navigate(['/currency']);
  }

}
