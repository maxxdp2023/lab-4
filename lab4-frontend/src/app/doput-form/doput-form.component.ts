import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Service1Service } from '../services/service1.service';

@Component({
  selector: 'app-doput-form',
  templateUrl: './doput-form.component.html',
  styleUrls: ['./doput-form.component.scss'],
})
export class DoPutFormComponent {
  constructor(private service: Service1Service) {}

  img: string = '';
  name: string = '';
  price: number = 0;

  getImg(val: string) {
    console.warn(val);
    this.img = val;
  }
  getName(val: string) {
    console.warn(val);
    this.name = val;
  }
  getPrice(p: NgForm) {
    this.price = parseInt(p.value.price);
  }

  OnClick(): void {
    if (this.img != '' && this.name != '' && this.price > 0) {
      console.log(this.img);
      console.log(this.name);
      console.log(this.price);
      alert("Success! Click the button in 'Our Cars' section to check this.");
      this.img = '';
      this.name = '';
      this.price = 0;
    } else {
      alert('Please, fill in all the fields to proceed!');
    }
  }
}
