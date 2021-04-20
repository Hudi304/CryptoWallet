import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Admin } from 'src/app/model/admin';
import { CryptoTransaction } from 'src/app/model/crypto-transaction';
import { Currencie } from 'src/app/model/currencie';
import { Transaction } from 'src/app/model/transaction';
import { User } from 'src/app/model/user';
import { AdminService } from 'src/app/service/admin.service';
import { UserService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-wallet-view',
  templateUrl: './wallet-view.component.html',
  styleUrls: ['./wallet-view.component.css']
})
export class WalletViewComponent implements OnInit {

 
  showFiller: boolean = false;
  addSliderOpem: boolean = false;

  transactions : CryptoTransaction[] = []

  users: User[] = [];
  oldUser: User;
  newUser: User;


  currencies: Currencie[] = [];
  oldcurrencie: Currencie;
  newcurrencie: Currencie;


  admins: Admin[] = []
  newAdmin: Admin


  tabs = ['Users', 'Currencies', 'Transaction', 'Admins'];
  selected = new FormControl(0);

  constructor(
    private userService: UserService,
    private adminService: AdminService) {
    this.oldUser = new User();
    this.newUser = new User();
    this.oldcurrencie = new Currencie();
    this.newcurrencie = new Currencie();
 
    this.newAdmin = new Admin();
  }

  onAdd() {
    this.addSliderOpem != this.addSliderOpem
    this.newUser = new User();
    this.newcurrencie = new Currencie();

  }

  ngOnInit(): void {
    this.refreshUsersTable();
    this.refreshTransactionsTable()

    //console.log(this.curerncyes)
  }

  refreshUsersTable(): void {
    this.userService.findAll()
      .subscribe(data => {
        this.users = data;
        console.log(this.users)
      })
  }

  refreshTransactionsTable(): void {
    this.userService.getTransactions('Dani')
      .subscribe(data => {
        this.transactions = data;
        console.log(this.transactions)
        this.transactions.forEach(element => {
          //console.log(element.coinName)
        });
      })
  }
 


  onUserUpdate(user: User) {
    this.oldUser = user
  }
  onCurrencieUpdate(currencie: Currencie) {
    this.oldcurrencie = currencie
  }

  saveUserAndRefresh() {
    this.userService.save(this.newUser)
      .subscribe(data => {
        this.refreshUsersTable()
      })
  }


  updateUserAndRefresh() {
  }
  updateCurrencieAndRefresh() {
  }
  updateTransactionAndRefresh() {
  }

  removeUser(nickname: string) {
    this.adminService.deleteUser(nickname)
      .subscribe(data => {
        this.refreshUsersTable()
      })
  }



}
