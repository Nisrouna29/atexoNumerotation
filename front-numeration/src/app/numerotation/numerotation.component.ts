import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { provideMomentDateAdapter } from '@angular/material-moment-adapter';
import 'moment/locale/fr';
import { SnackBarService } from '../service/snack.bar.service';
import { NumerotationService } from '../service/numerotation.service';
import { catchError, finalize, tap, throwError } from 'rxjs';

@Component({
  selector: 'app-numeretation',
  imports: [ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    MatDatepickerModule
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'fr-FR' },
    provideMomentDateAdapter(),
  ],
  standalone: true,
  templateUrl: './numerotation.component.html',
  styleUrl: './numerotation.component.css'
})
export class NumerotationComponent implements OnInit {
  userForm!: FormGroup;
  generatedValue!: string;
  loading!: boolean;

  constructor(private fb: FormBuilder, private snackBarService: SnackBarService, private numerotationService: NumerotationService) { }
  ngOnInit(): void {
    this.userForm = this.fb.group({
      firstname: ['', [Validators.required, Validators.min(1), Validators.max(4)]],
      lastname: ['', [Validators.required]],
      birthdate: [new Date(), [Validators.required]],
    });
  }

  onSubmit() {
    this.loading = true;
    this.numerotationService.generate(this.userForm.value).pipe(tap(() => {
      this.snackBarService.showMessage('Numérotation générée avec succès.');
    }), catchError((error) => {
      this.snackBarService.showMessage('Erreur lors de la génération de la numérotation.');
      return throwError(() => new Error(error.error));
    }), finalize(() => { this.loading = false })).subscribe((value: string) => {
      this.generatedValue = value;
    })
  }


}
