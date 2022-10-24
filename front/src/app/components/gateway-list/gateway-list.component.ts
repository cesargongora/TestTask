import { Component, OnInit } from '@angular/core';
import { Gateway } from 'src/app/models/gateway.model';
import { GatewayService } from 'src/app/services/gateway.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-gateway-list',
  templateUrl: './gateway-list.component.html',
  styleUrls: ['./gateway-list.component.css']
})
export class GatewayListComponent implements OnInit {

  gateways?: Gateway[];
  gateway?: Gateway;
  constructor(
    private gatewayService: GatewayService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.retrieveGateways();
  }

  retrieveGateways(): void {
    this.gatewayService.getAll()
      .subscribe({
        next: (data) => {
          this.gateways = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  updateGateway(id: number) {
    this.router.navigate(['updategateway', id]);
  }

  deleteGateway(id: number) {
    this.gatewayService.delete(id).subscribe(
      userData => {
        console.log(userData);
        this.retrieveGateways();
      })
  }

}
