import { User } from "./user";
import { Currencie } from "./currencie";

export class Transaction {

    public id!: number;
    public currencie!: Currencie
    public amount!: number
    public from!: User
    public to!: User

    public constructor() { }


}
