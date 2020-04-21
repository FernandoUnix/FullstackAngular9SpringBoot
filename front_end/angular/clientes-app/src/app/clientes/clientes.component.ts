import { Component, OnInit } from '@angular/core';
import {Cliente} from './cliente';
import {ClienteService} from './cliente.service';
import {ModalService} from './detalle/modal.service';
import Swal from 'sweetalert2'
import {ActivatedRoute} from '@angular/router';
import {AuthService} from '../usuarios/auth.service';
import {URL_BACKEND} from '../config/config';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[];
  paginator:any;
  clienteSeleccionado : Cliente;
  urlBackend:string = URL_BACKEND;

  constructor(public authService:AuthService,
     public modalService:ModalService,
      private clienteService : ClienteService,
       private activatedRoute:ActivatedRoute) { }

  ngOnInit() {

    this.activatedRoute.paramMap.subscribe(params => {

        let page:number  = +params.get('page');

          if(!page){
            page = 0;
          }

        this.clienteService.getClientes(page).subscribe(
          response => {
            this.clientes =  response.content as Cliente[];
            this.paginator = response;
          }
        );
    });

    this.modalService.notificarUpload.subscribe(cliente =>{

      this.clientes = this.clientes.map(clienteOriginal =>{

        if(cliente.id == clienteOriginal.id){
          clienteOriginal.foto = cliente.foto;
        }

        return clienteOriginal;
      })
    })
}

  abrirModal(cliente: Cliente){
    this.clienteSeleccionado = cliente;
    this.modalService.abrirModal();
  }

  delete(cliente : Cliente): void {

          Swal.fire({
              title: 'Está seguro?',
              text: `Seguro que deseas eliminar el cliente ${cliente.nombre}`,
              icon: 'warning',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Si, eliminar!',
              cancelButtonText: 'No, cancelar!'
            }).then((result) => {
              if (result.value) {

                  this.clienteService.deleteCliente(cliente.id).subscribe(
                        response => {

                          this.clientes = this.clientes.filter(cli => cli != cliente);

                          Swal.fire(
                            'Cliente eliminado!',
                            `El cliente ${cliente.nombre} fue eliminado con sucesso`,
                            'success'
                          )
                        }
                  )
              }
            });
  }
}
