import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GatewayListComponent } from './components/gateway-list/gateway-list.component'
import { GatewayAddComponent } from './components/gateway-add/gateway-add.component';
import { GatewayDetailComponent } from './components/gateway-detail/gateway-detail.component';
import { PeripheralAddComponent } from './components/peripheral-add/peripheral-add.component';

const routes: Routes = [
  { path: '', redirectTo: 'gateway', pathMatch: 'full' },
  { path: 'gateway/:id', component: GatewayDetailComponent },
  { path: 'gateway', component: GatewayListComponent },
  { path: 'add', component: GatewayAddComponent },
  { path: 'peripheral/add/:gateway', component: PeripheralAddComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
