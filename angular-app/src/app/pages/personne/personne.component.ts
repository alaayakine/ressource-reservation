import {Component, OnInit} from '@angular/core';
import {Personne} from "../../models/personne.model";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PersonneService} from "../../services/personne.service";
import {Router} from "@angular/router";
import {Reservation} from "../../models/reservation.model";
import {Resource} from "../../models/ressource.model";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-personne',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './personne.component.html',
  styleUrl: './personne.component.css'
})
export class PersonneComponent implements OnInit{

  personnes: Personne[] = [];
  reservationList: Reservation[] = [];
  ressources: Resource[]=[];
  newPersonne: Personne = { id: 0, nom: '', email: '', fonction: '', reservations: [] };
  selectedPersonneId: number | null = null;
  searchPersonneFormGroup!: FormGroup;
  errorMessage!: string;
  isReservationSelected: boolean = false;

  constructor(private personneService: PersonneService, private formBuilder: FormBuilder, private router: Router
  ) {}

  ngOnInit(): void {
    this.searchPersonneFormGroup = this.formBuilder.group({
      keyword: this.formBuilder.control(null)
    });
    this.fetchPersonnes();
    this.fetchReservations();
    this.fetchRessouces();
  }

  fetchPersonnes(): void {
    this.personneService.getPersonnes().subscribe(data => {
      this.personnes = data;
    });
  }

  fetchReservations(): void {
    this.personneService.getReservations().subscribe(data => {
      this.reservationList = data;
    });
  }

  fetchRessouces(): void {
    this.personneService.getResources().subscribe(data => {
      this.ressources = data;
    });
  }

  createPersonne() {
    this.personneService.createPersonne(this.newPersonne).subscribe(() => {
      this.fetchPersonnes();
      this.resetForm();
    });
  }

  updatePersonne() {
    if (this.selectedPersonneId !== null) {
      this.personneService.updatePersonne(this.selectedPersonneId, this.newPersonne).subscribe(() => {
        this.fetchPersonnes();
        this.resetForm();
      });
    }
  }

  deletePersonne(id: number): void {
    this.personneService.deletePersonne(id).subscribe(() => {
      this.fetchPersonnes();
    });
  }

  selectPersonne(personne: Personne) {
    this.selectedPersonneId = personne.id;
    this.newPersonne = { ...personne };
  }

  resetForm(): void {
    this.selectedPersonneId = null;
    this.newPersonne = { id: 0, nom: '', email: '', fonction: '', reservations: [] };
  }

  searchPersonne() {
    let keyword = this.searchPersonneFormGroup.value.keyword;
    if (keyword === '') {
      this.fetchPersonnes();
    }
    this.personneService.searchPersonnes(keyword).subscribe({
      next :data=>{
        this.personnes=data
      }
    })
  }

  getDetail(personne : Personne) {
    this.router.navigateByUrl("/personne-detail/"+personne.id)
  }

  onReservationChange(selectedReservations : Reservation[]): void {
    this.newPersonne.reservations = selectedReservations;
    this.isReservationSelected = true;
  }

}
