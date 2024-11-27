import { Component } from '@angular/core';
import {MatFormField} from '@angular/material/form-field';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialogModule } from '@angular/material/dialog';
import {MatDialogActions, MatDialogContent} from '@angular/material/dialog';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatFormField,
    MatFormFieldModule,  // Import this
    MatInputModule,      // Import this
    MatButtonModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogActions,
    MatDialogContent,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
  username = '';
  password = '';

  constructor() {}

  closeLogin() {
    console.log('Login dialog submitted!');
  }
}
