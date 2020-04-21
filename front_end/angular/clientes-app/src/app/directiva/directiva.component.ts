import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directiva',
  templateUrl: './directiva.component.html',
})
export class DirectivaComponent {

  listaCurso: string[] = ['TypeString', 'Java', 'C#', 'Node'];

 habilitar : boolean = true;

  constructor() { }

  setHabilitar() : void{
  this.habilitar =  !this.habilitar;
  }
}
