import {Component, OnInit} from '@angular/core';
import {Personne} from "../../models/personne.model";
import {CommonModule, DatePipe} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {PersonneService} from "../../services/personne.service";

@Component({
  selector: 'app-personne-detail',
  standalone: true,
  imports: [
    DatePipe,
    CommonModule,
  ],
  templateUrl: './personne-detail.component.html',
  styleUrl: './personne-detail.component.css'
})
export class PersonneDetailComponent implements OnInit{

  constructor(private activeRoute : ActivatedRoute,private http:HttpClient,private personneService : PersonneService) {
  }

  personne_id!:number;
  personne!:Personne;

  ngOnInit(): void {
    this.personne_id=this.activeRoute.snapshot.params['id'];
    this.personneService.getPersonneById(this.personne_id).subscribe(data=>{
      this.personne=data;
    })
  }

}
