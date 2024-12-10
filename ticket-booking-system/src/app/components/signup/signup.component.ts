import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  standalone: true,
  imports: [FormsModule],
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  name: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  role: string = '';

  constructor(
    private dialogRef: MatDialogRef<SignupComponent>,
    private authService: AuthService
  ) {}

  onSignup() {
    if (!this.name || !this.email || !this.password || !this.role) {
      alert('Please fill in all fields.');
      return;
    }

    if (!this.email.includes('@')) {
      alert('Invalid email. Please enter a valid email address.');
      return;
    }

    if (this.password !== this.confirmPassword) {
      alert('Passwords do not match!');
      return;
    }

    const user = {
      name: this.name,
      email: this.email,
      password: this.password,
      role: this.role,
    };

    this.authService.signup(user).subscribe(
      () => {
        alert('Signup successful!');
        this.dialogRef.close();
      },
      (err) => {
        console.error('Signup error:', err);

        // Handle errors based on status codes
        if (err.status === 400 && err.error === 'Email already registered') {
          alert('Email already registered. Please use a different email.');
        } else if (err.status === 400) {
          alert('Invalid inputs. Please check your details and try again.');
        } else if (err.status === 403) {
          alert('Access is forbidden. Check your permissions.');
        } else if (err.status === 404) {
          alert('Signup endpoint not found. Contact support.');
        } else if (err.status === 500) {
          alert('Server error. Please try again later.');
        } else {
          alert(`Unexpected error: ${err.message}`);
        }
      }
    );
  }

  onCancel() {
    this.dialogRef.close();
  }
}
