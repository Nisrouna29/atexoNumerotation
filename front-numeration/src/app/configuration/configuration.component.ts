import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Config } from '../model/config';
import { ConfigService } from '../service/config.service';
import { catchError, finalize, tap, throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { SnackBarService } from '../service/snack.bar.service';

@Component({
  selector: 'app-configuration',
  imports: [ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule
  ],
  standalone: true,
  templateUrl: './configuration.component.html',
  styleUrl: './configuration.component.css'
})
export class ConfigurationComponent {
  configForm!: FormGroup;
  configs!: Config[];
  loading!: boolean;

  constructor(private fb: FormBuilder, private snackBarService: SnackBarService, private configService: ConfigService) { }

  ngOnInit() {
    this.configForm = this.fb.group({
      firstName: this.fb.group({
        orderIndex: [0, [Validators.required, Validators.min(1), Validators.max(4)]],
        length: [0, [Validators.required]],
        prefix: [''],
        suffix: ['']
      }),
      name: this.fb.group({
        orderIndex: [0, [Validators.required, Validators.min(1), Validators.max(4)]],
        length: [0, [Validators.required]],
        prefix: [''],
        suffix: ['']
      }),
      birthdate: this.fb.group({
        orderIndex: [0, [Validators.required, Validators.min(1), Validators.max(4)]],
        dateFormat: ['', Validators.required],
        prefix: [''],
        suffix: ['']
      }),
      counter: this.fb.group({
        orderIndex: [0, [Validators.required, Validators.min(1), Validators.max(4)]],
        length: [0, [Validators.required]],
        prefix: [''],
        suffix: ['']
      })
    });
  }

  onSubmit() {
    if (this.configForm.valid) {
      const formValue = this.configForm.value;
      this.configs = [{
        type: 'FirstNameConfig',
        criterionType: "FIRSTNAME",
        ...formValue.firstName
      }, {
        type: 'NameConfig',
        criterionType: "NAME",
        ...formValue.name
      }, {
        type: 'BirthdateConfig',
        criterionType: "BIRTHDATE",
        ...formValue.birthdate
      }, {
        type: 'CounterConfig',
        criterionType: "COUNTER",
        ...formValue.counter
      }];
      this.loading = true;
      this.configService.createConfigs(this.configs).pipe(
        tap(() => {
          this.snackBarService.showMessage('Configuration mise à jour avec succès');
        }),
        catchError((error: HttpErrorResponse) => {
          this.snackBarService.showMessage('Erreur lors de la Mise à jour de la configuration ' + error.error);
          return throwError(() => new Error(error.error));
        }),
        finalize(() => { this.loading = false; })
      ).subscribe();
    }
  }
}
