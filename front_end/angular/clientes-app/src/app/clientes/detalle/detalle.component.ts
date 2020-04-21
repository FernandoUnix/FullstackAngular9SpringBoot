import { Component, OnInit, Input } from '@angular/core';
import {Cliente} from '../cliente';
import {ClienteService} from '../cliente.service';
import {ModalService} from './modal.service';
import {ActivatedRoute} from '@angular/router';
import Swal from 'sweetalert2';
import {HttpEventType} from '@angular/common/http';
import {AuthService} from '../../usuarios/auth.service';
import {Fatura} from '../../faturas/models/fatura';
import {FaturaService} from '../../faturas/services/fatura.service';

@Component({
  selector: 'detalle-cliente',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

   @Input() cliente:Cliente;
   titulo: string ="Detalle del Cliente";
   private fotoSelecionada:File;
   progreso:number=0;

  constructor(public authService:AuthService,
     public modalService:ModalService,
    private clienteService:ClienteService,
     private activatedRoute:ActivatedRoute,
  private faturaService:FaturaService ) { }

  ngOnInit() {

    /*
    Obs: Antes de passar o cliente pelo @Input tinha que ir buscar o cliente

    this.activatedRoute.paramMap.subscribe(
        params =>{
           let id:number = +params.get('id');
           if(id){
             this.clienteService.getCliente(id).subscribe(cliente =>{
               this.cliente = cliente;
             })
           }
        }

    );*/
  }

cerrarModal(){
  this.modalService.cerrarModal();
  this.progreso = 0;
  this.fotoSelecionada  = null;
}

  deleteFatura(fatura:Fatura): void{

        Swal.fire({

            title: 'Certeza?',
            text: `Deseja eliminar a Fatura ${fatura.descricao}`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si, eliminar!',
            cancelButtonText: 'No, cancelar!'

          }).then((result) => {
            if (result.value) {

                this.faturaService.delete(fatura.id).subscribe(
                      response => {

                        this.cliente.faturas = this.cliente.faturas.filter(f => f != fatura);
                        Swal.fire(
                          'Fatura eliminada!',
                          `A Fatura ${fatura.descricao} foi eliminada com sucesso`,
                          'success'
                        )
                      }
                )
            }
          });

  }

  seleccionarFoto(event){
    this.fotoSelecionada = event.target.files[0];
    this.progreso = 0;

    if(this.fotoSelecionada.type.indexOf('image') < 0){
      Swal.fire('Error Selecionar imagem:', `El archivo debe ser del tipo imagem`,'error');
      this.fotoSelecionada  = null;
    }
  }

  subirFoto(){

    if(!this.fotoSelecionada){
      Swal.fire('Error Upload:', `Debe selecionar una foto`,'error');

    }else{

    this.clienteService.subirFoto(this.fotoSelecionada, this.cliente.id).subscribe(
      event => {

          if(event.type === HttpEventType.UploadProgress){
            this.progreso = Math.round((event.loaded/event.total) * 100);
          }
          else if(event.type === HttpEventType.Response){
            let response:any = event.body;
            this.cliente = response.cliente as Cliente;

            this.modalService.notificarUpload.emit(this.cliente);

            Swal.fire('La foto se ha subido completamente', `La foto se ha subido com exito ${response.mensaje}`,'success');
          }
      }
    );
  }
 }
}
