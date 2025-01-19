import { Component } from '@angular/core';
import { ConfigurationComponent } from './configuration/configuration.component';
import { NumerotationComponent } from './numerotation/numerotation.component';


@Component({
  selector: 'app-root',
  imports: [ConfigurationComponent, NumerotationComponent],
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'numérotation-front';
}
