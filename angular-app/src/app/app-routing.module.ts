import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ResourceComponent} from "./pages/resource/resource.component";
import {ReservationComponent} from "./pages/reservation/reservation.component";
import {PersonneComponent} from "./pages/personne/personne.component";
import {ReservationDetailComponent} from "./pages/reservation-detail/reservation-detail.component";
import {PersonneDetailComponent} from "./pages/personne-detail/personne-detail.component";
import {AuthGuard} from "./guards/auth.guard";
import {WelcomeComponent} from "./pages/welcome/welcome.component";

const routes: Routes = [
  {path : "", component : WelcomeComponent},
  {path : "ressources" , component : ResourceComponent,canActivate:[AuthGuard], data : { roles:['USER'] }},
  {path : "reservations" , component : ReservationComponent},
  {path : "personnes" , component : PersonneComponent},
  {path : "reservation-detail/:id" , component : ReservationDetailComponent},
  {path : "personne-detail/:id" , component : PersonneDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
