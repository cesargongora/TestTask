import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GatewayListComponent } from './components/gateway-list/gateway-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { GatewayAddComponent } from './components/gateway-add/gateway-add.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { GatewayDetailComponent } from './components/gateway-detail/gateway-detail.component';
import { PeripheralAddComponent } from './components/peripheral-add/peripheral-add.component';

@NgModule({
  declarations: [
    AppComponent,
    GatewayListComponent,
    GatewayAddComponent,
    GatewayDetailComponent,
    PeripheralAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
