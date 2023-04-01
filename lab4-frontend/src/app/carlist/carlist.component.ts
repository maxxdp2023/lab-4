import { Component, OnInit } from '@angular/core';
import { Car } from '../interfaces/car';
import { Service1Service } from '../services/service1.service';

@Component({
  selector: 'app-carlist',
  templateUrl: './carlist.component.html',
  styleUrls: ['./carlist.component.scss'],
})
export class CarlistComponent implements OnInit {
  title: string = 'List of cars';
  carList: Car[] = [];
  showAddForm: boolean = false;
  selectedCar?: Car;

  constructor(private service: Service1Service) {}

  ngOnInit(): void {
    this.updateCars();
    this.service.list.subscribe((list: Car[]) => {
      this.carList = list;
    });
  }

  updateCars() {
    this.service.getCars().subscribe((cars) => {
      this.service.setList(cars);
    });
  }

  addCar(car: Car) {
    this.service.postCar(car).subscribe((cars) => {
      this.updateCars();
    });
    this.refreshPage();
  }

  onSelect(car: Car) {
    if (this.selectedCar && car.id == this.selectedCar.id) {
      this.selectedCar = undefined;
    } else {
      this.selectedCar = car;
    }
  }

  deleteCar(car: Car) {
    this.service.deleteCar(car).subscribe(() => {
      this.updateCars();
    });
    this.refreshPage();
  }

  refreshPage(): void {
    window.location.reload();
  }
}
