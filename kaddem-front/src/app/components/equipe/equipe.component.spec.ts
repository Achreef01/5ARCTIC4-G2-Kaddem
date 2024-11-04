import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtudiantComponent } from './equipe.component';

describe('EtudiantComponent', () => {
  let component: EtudiantComponent;
  let fixture: ComponentFixture<EtudiantComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EtudiantComponent]
    });
    fixture = TestBed.createComponent(EtudiantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
