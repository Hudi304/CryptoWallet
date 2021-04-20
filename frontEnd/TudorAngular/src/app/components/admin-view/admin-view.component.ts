import { Component, OnInit } from '@angular/core';
import { Transaction } from '../../model/transaction';
import { AdminService } from '../../service/admin.service';
import { User } from '../../model/user';
import { UserService } from '../../service/user-service.service';
import { FormControl } from '@angular/forms';
import { Currencie } from 'src/app/model/currencie';
import { Admin } from '../../model/admin';


@Component({
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css']
})
export class AdminViewComponent implements OnInit {

  showFiller: boolean = false;
  addSliderOpem: boolean = false;

  users: User[] = [];
  oldUser: User;
  newUser: User;


  currencies: Currencie[] = [];
  oldcurrencie: Currencie;
  newcurrencie: Currencie;

  transactions: Transaction[] = [];
  oldTransaction: Transaction;
  newTransaction: Transaction;

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
    this.oldTransaction = new Transaction();
    this.newTransaction = new Transaction();
    this.newAdmin = new Admin();
  }

  onAdd() {
    this.addSliderOpem != this.addSliderOpem
    this.newUser = new User();
    this.newcurrencie = new Currencie();
    this.newTransaction = new Transaction();

  }

  ngOnInit(): void {
    this.refreshUsersTable();
    this.refreshCurrencysTable();
    this.refreshTransactionsTable();
    this.refreshAdminsTable();
    //console.log(this.curerncyes)
  }

  refreshUsersTable(): void {
    this.userService.findAll()
      .subscribe(data => {
        this.users = data;
      })
  }
  refreshCurrencysTable(): void {
    this.adminService.fetchAllCurrencies()
      .subscribe(data => {
        this.currencies = data
        console.log(this.currencies)
      })
  }
  refreshTransactionsTable(): void {
    this.adminService.fetchAllTransactions()
      .subscribe(data => {
        this.transactions = data
        console.log(this.transactions)
      })
  }
  refreshAdminsTable(): void {
    this.adminService.fetchAllAdmins()
      .subscribe(data => {
        this.admins = data;
      })
  }


  onUserUpdate(user: User) {
    this.oldUser = user
  }
  onCurrencieUpdate(currencie: Currencie) {
    this.oldcurrencie = currencie
  }
  onTransactionUpdate(tranaction: Transaction) {
    this.oldTransaction = tranaction
  }

  saveUserAndRefresh() {
    this.userService.save(this.newUser)
      .subscribe(data => {
        this.refreshUsersTable()
      })
  }
  saveCurrencieAndRefresh() {
    this.adminService.saveCurrencie(this.newcurrencie)
      .subscribe(data => {
        this.refreshCurrencysTable()
      })
  }
  saveTransitionAndRefresh() {
    this.adminService.saveTransaction(this.newTransaction)
      .subscribe(data => {
        this.refreshTransactionsTable()
      })
  }
  saveAdminAndRefresh() {
    this.adminService.saveAdmin(this.newAdmin)
      .subscribe(data => {
        this.refreshAdminsTable()
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
  removeCurrencie(id: number) {
    this.adminService.deleteCurrency(id)
      .subscribe(data => {
        this.refreshCurrencysTable()
      })
  }
  removeTransaction(id: number) {
    this.adminService.deleteTransaction(id)
      .subscribe(data => {
        this.refreshCurrencysTable()
      })
  }

}
