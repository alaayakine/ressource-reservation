import {Reservation} from "./reservation.model";

export interface Personne{

  id: number;
  nom: string;
  email: string;
  fonction: string;
  reservations : Reservation[];
}
