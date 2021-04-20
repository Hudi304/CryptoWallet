export class TransactionRequest {

    public userNickname : string
    public coinID  :  string;
    public coinPrice : number;
    public transactionAmount : number;
    public currentDate : string;

    constructor(coinID : string, coinCurrentPrice : number, amount:  number, currentDate : string, userNickname : string ){
        this.coinID = coinID
        this.coinPrice = coinCurrentPrice
        this.transactionAmount = amount
        this.currentDate = currentDate
        this.userNickname = userNickname
    }

}
