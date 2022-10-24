import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Peripheral } from '../models/peripheral.model';
import { HttpClient } from '@angular/common/http';

const baseUrl = 'http://localhost:8080/api/peripheral';

@Injectable({
  providedIn: 'root'
})
export class PeripheralService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Peripheral[]> {
    return this.http.get<Peripheral[]>(`${baseUrl}`);
  }
  get(id: number): Observable<Peripheral> {
    return this.http.get<Peripheral>(`${baseUrl}/${id}`);
  }

  create(data: Peripheral): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: number, data: Peripheral): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

}
