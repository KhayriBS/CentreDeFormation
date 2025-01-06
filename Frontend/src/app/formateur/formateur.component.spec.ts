import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormateurComponent } from './formateur.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

describe('FormateurComponent', () => {
  let component: FormateurComponent;
  let fixture: ComponentFixture<FormateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormateurComponent ],
      imports:[HttpClientModule,FormsModule]
      
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
