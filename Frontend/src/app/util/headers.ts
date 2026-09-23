import { HttpHeaders } from '@angular/common/http';

export class Header  {
 // url = "http://10.10.20.107:5050/v1/rest/api/"
 /*  url = "http://10.10.20.106:5050/v1/rest/api/" */
  url = "http://localhost:5050/v1/rest/api/"

  parametro = new HttpHeaders({ 'content-type': 'application/json' });
}