import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminViewComponent } from './components/admin-view/admin-view.component';
import { CurrencyViewComponent } from './components/currency-view/currency-view.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserViewComponent } from './components/user-view/user-view.component';
import { WalletViewComponent } from './components/wallet-view/wallet-view.component';
import { WalletComponent } from './components/wallet/wallet.component';


const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminViewComponent },
  { path: 'user', component: UserViewComponent },
  { path: 'wallet', component: WalletComponent },
  { path: 'currency', component: CurrencyViewComponent },
  { path: 'walletView', component: WalletViewComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
