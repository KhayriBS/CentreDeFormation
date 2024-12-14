import { Formateur } from "../formateur/Iformateur";
import { Organisme } from "../organisme/Iorganisme";

export interface Session {
    id : number;
    lieu : string;
    date_debut : Date;
    date_fin : Date;
    nb : number;
    formateur : Formateur;
    organisme : Organisme;
  
}