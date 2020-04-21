import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Fatura} from '../models/fatura';
import {Produto} from '../models/produto';
import {URL_BACKEND} from '../../config/config';


@Injectable({
  providedIn: 'root'
})
export class FaturaService {

  private urlEndPoint:string = URL_BACKEND+'/api/faturas';

  constructor(private http:HttpClient ) {}

  getFatura(id:number):Observable<Fatura>{
    return this.http.get<Fatura>(`${this.urlEndPoint}/${id}`);
  }

  delete(id:number):Observable<void>{
    return this.http.delete<void>(`${this.urlEndPoint}/${id}`);
  }

  filtrarProdutos(nome:string):Observable<Produto[]>{
    return this.http.get<Produto[]>(`${this.urlEndPoint}/filtrar-prdutos/${nome}`)
  }

  create(fatura:Fatura): Observable<Fatura>{
    return this.http.post<Fatura>(`${this.urlEndPoint}`, fatura);

  }

}
