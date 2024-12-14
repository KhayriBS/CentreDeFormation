import { Domaine } from "../domaine/Idomaine";

export interface Formation {
    id : number;
    titre : string;
    
    annee : number;
    nb : number;
    duree : number;
    bugdet : number;
    type : string
    domaine : Domaine;
   
}