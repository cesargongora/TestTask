import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PeripheralAddComponent } from './peripheral-add.component';

describe('PeripheralAddComponent', () => {
  let component: PeripheralAddComponent;
  let fixture: ComponentFixture<PeripheralAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PeripheralAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PeripheralAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
