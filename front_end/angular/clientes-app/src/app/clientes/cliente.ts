import {Region} from './region';
import {Fatura} from '../faturas/models/fatura';

export class Cliente {
  id:number;
  nombre:string;
  apellido:string;
  createAt:string;
  email:string;
  foto:string;
  region:Region;

  faturas:Fatura[] = [];
}
