import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonneDetailComponent } from './personne-detail.component';

describe('PersonneDetailComponent', () => {
  let component: PersonneDetailComponent;
  let fixture: ComponentFixture<PersonneDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonneDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PersonneDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
