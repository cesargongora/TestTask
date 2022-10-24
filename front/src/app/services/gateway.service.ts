import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Gateway } from '../models/gateway.model';

const baseUrl = 'http://localhost:8080/api/gateway';

@Injectable({
  providedIn: 'root'
})
export class GatewayService {


  constructor(private http: HttpClient) { }

  getAll(): Observable<Gateway[]> {
    return this.http.get<Gateway[]>(`${baseUrl}`);
  }
  get(id: number): Observable<Gateway> {
    return this.http.get<Gateway>(`${baseUrl}/${id}`);
  }

  create(data: Gateway): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: number, data: Gateway): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
  
}
