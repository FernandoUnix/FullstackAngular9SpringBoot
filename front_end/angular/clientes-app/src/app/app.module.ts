import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { ClientesComponent } from './clientes/clientes.component'
import { ClienteService } from './clientes/cliente.service'
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormComponent } from './clientes/form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';
import {LOCALE_ID} from '@angular/core';
import { PaginatorComponent } from './paginator/paginator.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDatepickerModule} from "@angular/material";
import {MatMomentDateModule} from "@angular/material-moment-adapter";
import { DetalleComponent } from './clientes/detalle/detalle.component';
import { LoginComponent } from './usuarios/login.component';
import {AuthGuard} from './usuarios/guards/auth.guard';
import {RoleGuard} from './usuarios/guards/role.guard';
import {TokenInterceptor} from './usuarios/interceptors/token.interceptor';
import {AuthInterceptor} from './usuarios/interceptors/auth.interceptor';
import { DetalheFaturaComponent } from './faturas/detalhe-fatura.component';
import { FaturasComponent } from './faturas/faturas.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';

registerLocaleData(localePt);

const routes : Routes = [
  {path:'',   redirectTo :'/clientes' , pathMatch : 'full'},
  {path:'directivas',component:DirectivaComponent },
  {path:'clientes',component:ClientesComponent },
  {path:'clientes/page/:page',component:ClientesComponent },
  {path:'clientes/form',component:FormComponent , canActivate : [AuthGuard,RoleGuard], data : {role :'ROLE_ADMIN'}},
  {path:'clientes/form/:id',component:FormComponent  , canActivate : [AuthGuard, RoleGuard], data : {role :'ROLE_ADMIN'}},
  {path:'login',component:LoginComponent },
  { path: 'faturas/:id', component: DetalheFaturaComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ROLE_USER' } },
  {path:'faturas/form/:clienteId',component:FaturasComponent , canActivate: [AuthGuard, RoleGuard], data: { role: 'ROLE_ADMIN' }}


  //{path:'clientes/ver/:id',component:DetalleComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    ClientesComponent,
    FormComponent,
    PaginatorComponent,
    DetalleComponent,
    LoginComponent,
    DetalheFaturaComponent,
    FaturasComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatMomentDateModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatInputModule,
    MatFormFieldModule
  ],
  providers: [
    ClienteService,
    { provide: LOCALE_ID, useValue: 'pt-BR' } ,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true } ,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
