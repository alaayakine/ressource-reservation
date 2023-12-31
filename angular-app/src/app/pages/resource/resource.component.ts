import {Component, ElementRef, OnInit, Renderer2} from '@angular/core';
import {Resource} from "../../models/ressource.model";
import {RessourceService} from "../../services/ressource.service";
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {catchError, of} from "rxjs";
import {CommonModule} from "@angular/common";
import {Personne} from "../../models/personne.model";
import {Reservation} from "../../models/reservation.model";

@Component({
  selector: 'app-resource',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    FormsModule
  ],
  templateUrl: './resource.component.html',
  styleUrl: './resource.component.css'
})
export class ResourceComponent implements OnInit{

  resources: Resource[] = [];
  personnes: Personne[]=[];
  newResource: Resource = { id: 0, nom: '', type: '' };
  newReservation: Reservation = { id: 0, nom: '', contexte: '', date: new Date(), duree:0,
    ressource: { id: 0, nom: '', type: '' },ressource_id:0,
    personne: { id: 0, nom: '', email: '', fonction: '', reservations : []  } };
  selectedResourceId: number | null = null;
  searchResourceFormGroup!: FormGroup;
  errorMessage!: string;
  isPersonneSelected: boolean = false;
  types: string[] = [];

  constructor(private resourceService: RessourceService, private formBuilder: FormBuilder,
              private fb :FormBuilder) {

  }

  ngOnInit() {
    this.searchResourceFormGroup=this.fb.group({
      keyword : this.fb.control(null)
    })
    this.resourceService.getTypes().subscribe(
      data => {
        this.types = data;
      });
    this.fetchResources();
    this.fetchPersonnes()
  }

  fetchResources() {
    this.resourceService.getResources().subscribe(data => {
      this.resources = data;
    });
  }

  fetchPersonnes(): void {
    this.resourceService.getPersonnes().subscribe(data => {
      this.personnes = data;
    });
  }

  createReservation() {
    this.newReservation.ressource=this.newResource;
    this.newReservation.ressource_id=this.newReservation.ressource.id;
    this.resourceService.createReservation(this.newReservation).subscribe(() => {
      this.fetchResources();
      this.resetForm();
    });

  }

  createResource() {
    this.resourceService.createResource(this.newResource).subscribe(() => {
      this.fetchResources();
      this.resetForm();
    });
  }

  updateResource() {
    if (this.selectedResourceId !== null) {
      this.resourceService.updateResource(this.selectedResourceId, this.newResource).subscribe(() => {
        this.fetchResources();
        this.resetForm();
      });
    }
  }

  deleteResource(id: number) {
    this.resourceService.deleteResource(id).subscribe(() => {
      this.fetchResources();
    });
  }

  selectResource(resource: Resource) {
    this.selectedResourceId = resource.id;
    this.newResource = { ...resource };
  }

  resetForm() {
    this.selectedResourceId = null;
    this.newResource = { id: 0, nom: '', type: '' };
  }

  searchResource() {

    let keyword=this.searchResourceFormGroup.value.keyword;

    if (keyword==''){
      this.fetchResources();
    }
    this.resourceService.searchResources(keyword).subscribe({
      next :data=>{
        this.resources=data
      }
    })
  }

  onPersonneChange(selectedPersonne : Personne): void {
    this.newReservation.personne = selectedPersonne;
    this.isPersonneSelected = true;

  }



}
