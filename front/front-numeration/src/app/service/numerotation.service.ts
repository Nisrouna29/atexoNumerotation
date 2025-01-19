import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInfo } from '../model/userInfo';

@Injectable({
  providedIn: 'root'
})
export class NumerotationService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }
  generate(userInfo: UserInfo): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/generate`, userInfo, { responseType: 'text' as 'json' });
  }
}
