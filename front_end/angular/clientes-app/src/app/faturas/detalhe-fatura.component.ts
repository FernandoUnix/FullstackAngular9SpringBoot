import { Component, OnInit } from '@angular/core';
import {FaturaService} from './services/fatura.service';
import {Fatura} from './models/fatura';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-detalhe-fatura',
  templateUrl: './detalhe-fatura.component.html',
})
export class DetalheFaturaComponent implements OnInit {

  fatura:Fatura;
  titulo:string = 'Fatura';

  constructor(private faturaService : FaturaService,
     private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
        this.activatedRoute.paramMap.subscribe(params => {
              let id = +params.get('id');
               this.faturaService.getFatura(id).subscribe(fatura => {
                  this.fatura = fatura;
               })
        })
  }

}
