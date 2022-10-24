import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Gateway } from 'src/app/models/gateway.model';
import { Peripheral } from 'src/app/models/peripheral.model';
import { GatewayService } from 'src/app/services/gateway.service';
import { PeripheralService } from 'src/app/services/peripheral.service';

@Component({
  selector: 'app-peripheral-add',
  templateUrl: './peripheral-add.component.html',
  styleUrls: ['./peripheral-add.component.css']
})
export class PeripheralAddComponent implements OnInit {

  peripheral: Peripheral = {
    uid: 0,
    vendor: '',
    status: '',
  };
  gateway: Gateway = {
    id:'',
    name: '',
    ipaddr: '',
    snumber: ''
  };
  id?: number;
  peripheralForm: FormGroup;
  constructor(
    private gatewayService: GatewayService,
    private activatedRoute: ActivatedRoute,
    private peripheralService: PeripheralService,
    private router: Router,
    public fb: FormBuilder) {
      this.peripheralForm=this.fb.group({
        uid: ['', [Validators.required]],
        vendor: ['', [Validators.required]],
        status: ['', [Validators.required]],
        gateway:[]
      })
     }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['gateway'];
    this.getGateway(this.id)
  }
  onSubmitForm(){
    console.log(this.peripheralForm.value);
    this.commitPeripheral();
  }
 
  commitPeripheral(){
    this.peripheralForm.get('gateway')?.setValue(this.gateway);
    this.peripheralService.create(this.peripheralForm.value).subscribe( 
      userData =>{
        console.log(userData);
        this.redirectGateway();
      },
      error => console.log(error));
  }

  getGateway(id: any){
    this.gatewayService.get(id)
      .subscribe({
        next: (data) => {
          this.gateway = data;
          console.log(this.gateway);
        },
        error: (e) => console.error(e)
      });
  }

  redirectGateway(){
    this.router.navigate(['/gateway/',this.gateway.id]);
  }
}
