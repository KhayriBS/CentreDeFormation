import { Organisme } from "../organisme/organisme";

export interface Formateur {
    id : number;
    nom : string;
    prenom : string;
    email : string;
    tel : number;
    type : string
    organisme : Organisme;
  
}