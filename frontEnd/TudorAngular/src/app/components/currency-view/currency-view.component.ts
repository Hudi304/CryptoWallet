import { Component, OnInit } from '@angular/core';
import { CoinInfo } from 'src/app/model/coin-info';
import { ChartDataset, ChartOptions } from 'chart.js';
import { UserService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-currency-view',
  templateUrl: './currency-view.component.html',
  styleUrls: ['./currency-view.component.css']
})


export class CurrencyViewComponent implements OnInit {

  title = 'Angular Charts';
  view: [number,number]= [0,0];
  legend: boolean = false;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Price in USD';
  yAxisLabel: string = '';
  timeline: boolean = true;
  amount :  number = 0;
  colorScheme = {
    domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };

  isLoading :  boolean = true

 
  public pricesData: any[] =[]  
  public dates : Date[] =[]
  public prices :  number[] =[]
  
  public coin !:  CoinInfo

  tableData = [
    {
      "name":  this.userService.clickedCoin.name,
      "series": [
        {
          "name": "",
          "value": 0
        }
      ]
    }
  ]

  constructor(private userService : UserService) { }

  ngOnInit(): void {
    this.coin = this.userService.clickedCoin
    this.refreshTable(this.coin.id,10,"usd")
    //this.isLoading = false
  }

  refreshTable(coinID : string, nrOfDays : number, fiat : string){
    this.userService.getCoinHistory(coinID,10,"usd").subscribe((data: any) => {
      this.pricesData = data.prices
      this.pricesData.forEach(element => {
        if( element[0]! && element[1]!){
          let date =  new Date( element[0])
          this.tableData[0].series.push(  {
            "name": date.toString(),  //date.getDate() + "." + date.getMonth(),
            "value": element[1]!
          })
        }
        this.tableData[0].series[0] = this.tableData[0].series[1]        
      });
      //! fara reaasignmetul asta tabelul nu afiseaza nimic
      console.log("GOT IT")
      this.tableData = [...this.tableData]
      this.isLoading = false
    })
  }

  addPoint(){
    console.log(this.tableData[0])
    this.tableData[0].series.push(  {  "name": "321321","value": 63321})
    this.tableData = [...this.tableData]
    this.updateChart()
  }

  updateChart(){

  }


  buy(){
    console.log("Processing trnsaction", this.amount)
    this.userService.buy(this.coin.id, this.coin.current_price,this.amount).subscribe(resposne => {
      console.log(resposne)
    })
  }

 

  onSelect(data: any): void {
    //console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    //console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    //console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }


}
