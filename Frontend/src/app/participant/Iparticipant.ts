import { Organisme } from "../organisme/Iorganisme";
import { Pays } from "../pays/Ipays";
import { Profil } from "../profil/Iprofil";
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