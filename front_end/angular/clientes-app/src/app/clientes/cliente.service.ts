import { Injectable } from '@angular/core';
//import {CLIENTES} from './clientes.json';
import {Cliente} from './cliente';
import {of,Observable, throwError} from 'rxjs';
import {HttpClient, HttpRequest, HttpEvent} from '@angular/common/http';
import {map, catchError, tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {formatDate, DatePipe} from '@angular/common';
import {Region} from './region';
import {URL_BACKEND} from '../config/config';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
private urlEndPoint:string = URL_BACKEND + '/api/clientes';

  constructor(private http : HttpClient, private router : Router) { }

  getClientes(page : number) : Observable<any>{
    return this.http.get(this.urlEndPoint +"/page/" + page).pipe(
      tap(response => {
            //let clientes =  response as Cliente[];
      }),
        map((response:any) => {

          var cli =  (response.content as Cliente[]).map(x => {
                  let datePipe = new DatePipe('pt-BR');
                  //  x.createAt = formatDate(x.createAt,'dd-MM-yyyy','en-US');
                  //x.createAt = datePipe.transform(x.createAt,'dd/MM/yyyy');
                  x.createAt = datePipe.transform(x.createAt,'EEEE dd MMMM yyyy');

                  return x;
              });

                console.log(response);

            return response;
        })
    );
  }

  create(cliente : Cliente): Observable<Cliente>{
    return this.http.post(this.urlEndPoint, cliente).pipe(
      map((response:any) => response.cliente as Cliente),
      catchError(e => {

        if(e.status == 400 ){
          return throwError(e);
        }

        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }


        return throwError(e);
      })
    );
  }

  getRegiones(): Observable<Region[]>{
      return this.http.get<Region[]>(this.urlEndPoint + '/regiones');
  }

  getCliente(id): Observable<Cliente>{
      return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
        catchError(e => {

          if(e.status != 401 && e.error.mensaje){
            this.router.navigate(['/clientes']);
            console.error(e.error.mensaje);
          }
          return throwError(e);
        })
      );
  }

  updateCliente(cliente : Cliente) : Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${cliente.id}`, cliente)
    .pipe(
      catchError(e => {

        if(e.status == 400 ){
          return throwError(e);
        }

        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    );
  }

  deleteCliente(id) : Observable<Cliente>{
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {

        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }

        return throwError(e);
      })
    );
  }

  subirFoto(archivo: File, id): Observable<HttpEvent<{}>>{
    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);

    const req = new HttpRequest('POST',`${this.urlEndPoint}/upload`, formData, {
      reportProgress: true,
    });

    return this.http.request(req);

    /*.pipe(
      map((response:any) => response.cliente as Cliente),
      catchError(e => {
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );*/
  }
}
