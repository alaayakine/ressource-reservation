import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {ReservationService} from "../../services/reservation.service";
import {Reservation} from "../../models/reservation.model";
import {CommonModule, DatePipe} from "@angular/common";

@Component({
  selector: 'app-reservation-detail',
  standalone: true,
  imports: [
    DatePipe,
    CommonModule,
  ],
  templateUrl: './reservation-detail.component.html',
  styleUrl: './reservation-detail.component.css'
})
export class ReservationDetailComponent implements OnInit{

  reservation_id!: number;
  reservation!:Reservation;

  constructor(private activeRoute : ActivatedRoute,private http:HttpClient,private reservationService: ReservationService) {

  }

  ngOnInit(): void {
    this.reservation_id=this.activeRoute.snapshot.params['id'];
    this.reservationService.getReservationById(this.reservation_id).subscribe(data=>{
      this.reservation=data;
    })
  }

}
