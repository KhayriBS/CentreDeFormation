import { Organisme } from "../organisme/Iorganisme";

export interface Formateur {
    id : number;
    nom : string;
    prenom : string;
    email : string;
    tel : number;
    type : string
    organisme : Organisme;
  
}