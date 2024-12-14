import { Formateur } from "../formateur/formateur";
import { Organisme } from "../organisme/organisme";

export interface Session {
    id : number;
    lieu : string;
    date_debut : Date;
    date_fin : Date;
    nb : number;
    formateur : Formateur;
    organisme : Organisme;
  
}