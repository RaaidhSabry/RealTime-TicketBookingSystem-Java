import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketPoolComponent } from './ticket-pool.component';

describe('TicketPoolComponent', () => {
  let component: TicketPoolComponent;
  let fixture: ComponentFixture<TicketPoolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketPoolComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketPoolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
