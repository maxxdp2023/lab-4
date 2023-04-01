import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Car } from '../interfaces/car';
import { Service1Service } from '../services/service1.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss'],
})
export class UpdateComponent implements OnInit {
  @Input() car?: Car;
  @Output() updated: EventEmitter<null> = new EventEmitter();
  constructor(private service: Service1Service) {}

  ngOnInit(): void {}

  updateCar() {
    if (this.car) {
      this.service.putCar(this.car).subscribe(() => {
        this.updated.emit();
      });
    }
    this.refreshPage();
  }

  refreshPage(): void {
    window.location.reload();
  }
}
