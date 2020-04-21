import {Produto} from './produto';

export class ItemFatura {
  produto:Produto;
  quantidade:number = 1;
  valorTotal: number;

    public calcularValorTotal():number{
      return this.quantidade * this.produto.preco;
    }

}
