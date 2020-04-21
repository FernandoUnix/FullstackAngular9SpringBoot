import { Component, OnInit } from '@angular/core';
import {Fatura} from './models/fatura';
import {ClienteService} from '../clientes/cliente.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, flatMap} from 'rxjs/operators';
import {FaturaService} from './services/fatura.service';
import {Produto} from './models/produto';
import {ItemFatura} from './models/item-fatura';
import {MatAutocompleteSelectedEvent} from '@angular/material';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-faturas',
  templateUrl: './faturas.component.html',
})

export class FaturasComponent implements OnInit {

  titulo:string = 'Nova Fatura';
  fatura:Fatura = new Fatura();

  autoCompleteControl = new FormControl();
    filteredProdutos: Observable<Produto[]>;

  constructor(private clienteService : ClienteService,
     private activatedRoute: ActivatedRoute, private faturaService :FaturaService,
     private router : Router)
     {

     }

  ngOnInit() {

    this.activatedRoute.paramMap.subscribe(params => {

      let clienteId = +params.get('clienteId');
      this.clienteService.getCliente(clienteId).subscribe(cliente => this.fatura.cliente = cliente);
    })

///////
this.filteredProdutos = this.autoCompleteControl.valueChanges.pipe(
      map(value => typeof value === 'string' ? value : value.nome),
      flatMap(value =>  value ? this._filter(value) : [])
    );
  }


  /////////////////////


  private _filter(value: string): Observable<Produto[]> {
     const filterValue = value.toLowerCase();
     return this.faturaService.filtrarProdutos(filterValue);
   }

   mostrarNome(produto?: Produto): string | undefined{
     console.log(produto)
     return produto ? produto.nome : undefined;
   }

   selecionarProduto(event:MatAutocompleteSelectedEvent): void{
     let produto = event.option.value as Produto;

     if(this.existeItem(produto.id)){
        this.incrementaQuantidade(produto.id);
     }else{
       let novoItem = new ItemFatura();
       novoItem.produto = produto;

       this.fatura.itens.push(novoItem);
       this.autoCompleteControl.setValue('');
       event.option.focus();
       event.option.deselect();
     }
   }

  eliminarItemFatura(id:number):void{
    this.fatura.itens = this.fatura.itens.filter((item:ItemFatura) => item.produto.id != id);
  }

    atualizarQuantidade(id:number,event:any):void{
      let quantidade = event.target.value;

      this.fatura.itens.map((item:ItemFatura) => {
        if(id == item.produto.id){
          item.quantidade = quantidade;
        }

        return item;
      });
    }

    existeItem(id:number):boolean{
        let existe = false;

        this.fatura.itens.forEach((item:ItemFatura) => {
            if(item.produto.id == id){
              existe = true;
            }

        });

        return existe;
    }

    incrementaQuantidade(id:number):void{
      this.fatura.itens.map((item:ItemFatura) => {
        if(id == item.produto.id){
          ++item.quantidade;
        }

        return item;
      });
    }

  create():void{
      this.faturaService.create(this.fatura).subscribe(fatura => {
        Swal.fire(this.titulo, `Fatura ${fatura.descricao} criada com sucesso`,'success');
        this.router.navigate(['/clientes']);
      });
  }
}
