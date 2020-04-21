import { Component, OnInit } from '@angular/core';
import {Cliente} from './cliente';
import {ClienteService} from './cliente.service';
import {Router, ActivatedRoute} from '@angular/router';
import Swal from 'sweetalert2';
import {Region} from './region';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
})
export class FormComponent implements OnInit {

  public cliente : Cliente = new Cliente();
  public titulo :string = "Crear Cliente";
  public errrores: string[];
  regiones:Region[];

  constructor(private clienteService: ClienteService,
  private router : Router,
  private activateRoute: ActivatedRoute) {

  }

  ngOnInit() {
    this.cargarCliente();
    this.clienteService.getRegiones().subscribe(regiones => this.regiones = regiones);
  }

  cargarCliente():void {

    this.activateRoute.params.subscribe(
      param => {
        let id = param['id'];
        if(id){
          this.clienteService.getCliente(id).subscribe((cliente) => this.cliente = cliente);
        }
      }
    );
  }

  public create():void{
    this.clienteService.create(this.cliente).subscribe(
      cliente => {
        this.router.navigate(['/clientes']);
        Swal.fire('Nuevo cliente', `Cliente ${cliente.nombre} creado con exito`,'success');
      },
      err => {
          this.errrores = err.error.errors as string[];
          console.error("Codigo del error "+err.status);
          console.error(err.error.errors);
      }
    );
  }

  public update() : void{

    this.cliente.faturas = null;

    this.clienteService.updateCliente(this.cliente).subscribe(
      response => {
        this.router.navigate(['/clientes']);
          Swal.fire('Cliente Actualizado', `${response.mensaje} : ${response.cliente.nombre}`,'success');
        },
        err => {
            this.errrores = err.error.errors as string[];
            console.error("Codigo del error "+err.status);
            console.error(err.error.errors);
        }
    );
  }

  compararRegion(o1:Region, o2:Region) : boolean{

    if(o1 === undefined && o2 === undefined){
      return true;
    }

    return o1 == null || o2 == null ? false : o1.id === o2.id;
  }

}
