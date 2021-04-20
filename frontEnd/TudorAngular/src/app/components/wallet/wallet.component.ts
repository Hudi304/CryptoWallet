import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CryptoTransaction } from 'src/app/model/crypto-transaction';
import { UserService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.css']
})
export class WalletComponent implements OnInit {

  panelOpenState = false;
  isLoading :  boolean = true
  currentPrices = new Map()

  totalTransactions : any[] = []
  //? made it any to be able to add fields
  transactions : any[] = []
  
  constructor(
    private userService : UserService,
    private router: Router){

    }

  async ngOnInit(): Promise<void> {
    this.refreshTransactionHistory("Dani")
    
  }

  //todo asta nu ar trebui sa poata fi facuta de cat de usrul curent 
  //autentificare prit token
  refreshTransactionHistory(user: string) {
    this.userService.getTransactions(user).subscribe(async data => {
      this.transactions = data
      await this.refreshCoinPrice()
      console.log(this.transactions)
      this.computeAvrage()
      this.isLoading = false

    })
  }

  async refreshCoinPrice() : Promise<any> {
    //? Get price for every currency in transaction List
    let ret = new Promise<void>( (resolve, reject) => {
      this.transactions
      .forEach(transaction =>{
          this.userService.getCoinPrice(transaction.coinName,"usd")
          .subscribe(response =>{
            let str = JSON.stringify(response)
            transaction.currentPrice = this.coinPriceJSONToNumber(str)
            console.log( transaction.currentPrice)
            if(transaction.currentPrice - transaction.aquisitionPrice >= 0){
              transaction.isMakingProfit = true
            }else{
              transaction.isMakingProfit = false
            }
          }) 
      })
      resolve()
    })
    return ret
  }

  coinPriceJSONToNumber( priceJSON : string){
    let rez = JSON.stringify(priceJSON).split(":")
    rez[2] = rez[2].slice(0, -2)
    return parseInt(rez[2])
  }


  setCurrentPrice(coinName : string, currentPrice : number){
    this.transactions.forEach(tran =>{
      if(tran.coinName = coinName){
        tran.currentPrice = currentPrice
      }
      if(tran.currentPrice < tran.aquisitionPrice){
        tran.makingProfit = true
      }
    })
  }

  computeAvrage(){
    this.transactions.forEach(x => {
      let sameNameTrans : CryptoTransaction[] =[]
      this.transactions.forEach(y => {
        if(x.coinName == y.coinName){
          sameNameTrans.push(y)
        }
      })
      let avarageAqPrice : number = 0
      let totalAmount : number = 0
      let currentPrice : number = 0
      let coinID : string = ""
      let cnt : number = 0
      sameNameTrans.forEach(tran =>{
        totalAmount += tran.amount
        avarageAqPrice += tran.aquisitionPrice
        currentPrice = tran.currentPrice
        coinID = tran.coinName
        cnt ++
      })
      avarageAqPrice = avarageAqPrice / cnt
      let totalTransaction = new CryptoTransaction(currentPrice,"NaN",12,coinID,avarageAqPrice,totalAmount,"now","doesn't matter")
      this.totalTransactions.push(totalTransaction)
     
      //console.log(avarageAqPrice)

    })
  }

  goToHome() {
    this.router.navigate(["/user"])
  }

}
