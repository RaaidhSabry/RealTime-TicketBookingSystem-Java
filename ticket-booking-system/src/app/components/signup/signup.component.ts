import { Component } from '@angular/core';
import {MatFormField} from '@angular/material/form-field';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatDialogActions, MatDialogClose} from '@angular/material/dialog';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    MatFormField,
    MatFormFieldModule,  // Import this
    MatInputModule,      // Import this
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogClose,
    MatDialogActions,
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})

export class SignupComponent {
  fullName: string = '';  // Initialize with an empty string
  email: string = '';     // Initialize with an empty string
  password: string = '';  // Initialize with an empty string

  onSubmit() {
    // Handle signup logic here (e.g., HTTP call to register user)
    console.log('Form submitted', { fullName: this.fullName, email: this.email, password: this.password });
  }
}
