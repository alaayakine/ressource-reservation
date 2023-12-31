import {Resource} from "./ressource.model";
import {Personne} from "./personne.model";

export interface Reservation {
  id: number;
  nom: string;
  contexte: string;
  date: Date;
  duree: number;
  ressource : Resource;
  ressource_id: number;
  personne : Personne;
}
