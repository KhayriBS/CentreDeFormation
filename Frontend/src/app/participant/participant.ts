import { Organisme } from "../organisme/organisme";
import { Pays } from "../pays/pays";
import { Profil } from "../profil/profiil";
export interface Participant {
    id : number;
    nom : string;
    prenom : string;
    email : string;
    tel : number;
    type : string
    organisme : Organisme;
    pays : Pays;
    profil : Profil;
}