import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Personne} from "../models/personne.model";
import {Reservation} from "../models/reservation.model";
import {Resource} from "../models/ressource.model";

@Injectable({
  providedIn: 'root'
})
export class PersonneService {

  private apiUrl = 'http://localhost:8888/RESERVATION-SERVICE/personnes';
  private reservationUrl = 'http://localhost:8888/RESERVATION-SERVICE/reservations';
  private ressourceUrl = 'http://localhost:8888/RESSOURCE-SERVICE/ressources';
  private personnes!: Personne[];

  constructor(private http: HttpClient) {
    this.getPersonnes().subscribe({
      next: data=>{
        this.personnes = data;
      }
    });
  }

  getPersonnes(): Observable<Personne[]> {
    return this.http.get<Personne[]>(this.apiUrl);
  }

  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.reservationUrl);
  }

  getResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(this.ressourceUrl);
  }

  getPersonneById(id: number): Observable<Personne> {
    return this.http.get<Personne>(`${this.apiUrl}/${id}`);
  }

  createPersonne(personne: Personne): Observable<Personne> {
    return this.http.post<Personne>(this.apiUrl, personne);
  }

  updatePersonne(id: number, personne: Personne): Observable<Personne> {
    return this.http.put<Personne>(`${this.apiUrl}/${id}`, personne);
  }

  deletePersonne(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchPersonnes(keyword: string) {
    let personneList =this.personnes.filter(p=>`${p.nom}`.includes(keyword));
    return of(personneList);
  }
}
