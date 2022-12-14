import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GatewayAddComponent } from './gateway-add.component';

describe('GatewayAddComponent', () => {
  let component: GatewayAddComponent;
  let fixture: ComponentFixture<GatewayAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GatewayAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GatewayAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
