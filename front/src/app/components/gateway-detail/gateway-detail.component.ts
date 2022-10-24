import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Gateway } from 'src/app/models/gateway.model';
import { GatewayService } from 'src/app/services/gateway.service';
import { PeripheralService } from 'src/app/services/peripheral.service';

@Component({
  selector: 'app-gateway-detail',
  templateUrl: './gateway-detail.component.html',
  styleUrls: ['./gateway-detail.component.css']
})
export class GatewayDetailComponent implements OnInit {

  gateway: Gateway = {
    name: '',
    ipaddr: '',
    snumber: '',
    peripheral: [],
  };
  id?: number;
  
  constructor(
    private pheripheralService: PeripheralService,
    private gatewayService: GatewayService,
    private activatedRoute: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.getGateway(this.id);
  }

  getGateway(id: any): void {
    this.gatewayService.get(id)
      .subscribe({
        next: (data) => {
          this.gateway = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  redirectGatewayList(){
    this.router.navigate(['/gateway']);
  }

  peripheralLimit():boolean{
    if(this.gateway.peripheral!.length>=10){
      return false;
    }else{
      return true;
    }
  }
  deletePheripheral(id: number) {
    this.pheripheralService.delete(id).subscribe(
      userData => {
        console.log(userData);
        this.getGateway(id);
      })
  }

}
