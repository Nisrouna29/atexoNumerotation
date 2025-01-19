import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Config } from '../model/config';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private apiUrl = 'http://localhost:8080/configs';

  constructor(private http: HttpClient) {
  }
  createConfigs(configs: Config[]): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}`, configs, { responseType: 'text' as 'json' });
  }
}
