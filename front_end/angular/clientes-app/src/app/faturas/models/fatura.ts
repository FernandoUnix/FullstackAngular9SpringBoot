import {ItemFatura} from './item-fatura';
import {Cliente} from '../../clientes/cliente';

export class Fatura {
  id:number;
  descricao:string;
  observacao:string;
  itens: Array<ItemFatura> = [];
  cliente: Cliente;
  total:number;
  dataCadastro: string;

  calcularValorTotal() : number{
      this.total = 0;

      this.itens.forEach((item:ItemFatura) => {
          this.total = this.total + item.calcularValorTotal();
      });

      return this.total;
  }
}
