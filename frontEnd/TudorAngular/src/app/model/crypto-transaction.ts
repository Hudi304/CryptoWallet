export class CryptoTransaction {


        public pk : number
        public userNickname : string
        public coinName  :  string;
        public aquisitionPrice : number;
        public amount : number;
        public aquisitionGMTDate : string;
        public type : string
        public currentPrice : number  = 0
        public makingProfit : boolean = false
    
        constructor(currentPrice :  number,type : string,pk : number,coinID : string, coinCurrentPrice : number, amount:  number, currentDate : string, userNickname : string ){
            this.coinName = coinID
            this.aquisitionPrice = coinCurrentPrice
            this.amount = amount
            this.aquisitionGMTDate = currentDate
            this.userNickname = userNickname
            this.pk = pk
            this.type = type
            this.currentPrice = 0
        }
    
}
