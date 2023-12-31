import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Reservation} from "../models/reservation.model";
import {Resource} from "../models/ressource.model";
import {Personne} from "../models/personne.model";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiUrl = 'http://localhost:8888/RESERVATION-SERVICE/reservations';
  private personneUrl = 'http://localhost:8888/RESERVATION-SERVICE/personnes';
  private ressourceUrl = 'http://localhost:8888/RESSOURCE-SERVICE/ressources';

  reservations!: Reservation[];

  constructor(private http: HttpClient) {
    this.getReservations().subscribe({
      next: data=>{
        this.reservations = data;
      }
    });
  }

  getResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(this.ressourceUrl);
  }

  getPersonnes(): Observable<Personne[]> {
    return this.http.get<Personne[]>(this.personneUrl);
  }

  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.apiUrl);
  }

  getReservationById(id: number): Observable<Reservation> {
    return this.http.get<Reservation>(`${this.apiUrl}/${id}`);
  }

  createReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(this.apiUrl, reservation);
  }

  updateReservation(id: number, reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(`${this.apiUrl}/${id}`, reservation);
  }

  deleteReservation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  searchReservations(keyword: string) {
    let reservationList =this.reservations.filter(r=>`${r.nom}`.includes(keyword));
    return of(reservationList);
  }
}
