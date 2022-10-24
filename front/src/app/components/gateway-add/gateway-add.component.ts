import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GatewayService } from 'src/app/services/gateway.service';

@Component({
  selector: 'app-gateway-add',
  templateUrl: './gateway-add.component.html',
  styleUrls: ['./gateway-add.component.css']
})
export class GatewayAddComponent implements OnInit {

 gatewayForm: FormGroup;

  constructor(private gatewayService: GatewayService,
    private router: Router,
    public fb: FormBuilder
    ) {
      this.gatewayForm=this.fb.group({
        name: ['', [Validators.required]],
        snumber: ['', [Validators.required]],
        ipaddr: ['', [Validators.pattern('(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)')]],
        peripheral: this.fb.array([])
      })
     }

  ngOnInit(): void {

  }
  redirectGatewayList(){
    this.router.navigate(['/gateway']);
  }
  onSubmitForm(){
    console.log(this.gatewayForm.value);
    this.commitGateway();
  }

  commitGateway(){
    this.gatewayService.create(this.gatewayForm.value).subscribe( 
      userData =>{
        console.log(userData);
        this.redirectGateway();
      },
      error => console.log(error));
  }

  redirectGateway(){
    this.router.navigate(['/gateway']);
  }
}
