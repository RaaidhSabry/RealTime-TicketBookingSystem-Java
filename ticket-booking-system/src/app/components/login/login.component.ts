import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [FormsModule],
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  role: string = '';

  constructor(
    private dialogRef: MatDialogRef<LoginComponent>,
    private authService: AuthService
  ) {}

  onLogin() {
    if (!this.email || !this.password || !this.role) {
      alert('Please fill in all fields before logging in.');
      return;
    }

    const user = { email: this.email, password: this.password, role: this.role };

    this.authService.login(user).subscribe(
      () => {
        alert('Login successful!');
        this.dialogRef.close({ role: this.role }); // Pass role for further actions
      },
      (err) => {
        console.error('Login error:', err);

        // Handle errors based on status codes
        if (err.status === 400) {
          alert('Invalid email, password, or role. Please try again.');
        } else if (err.status === 500) {
          alert('Server error. Please try again later.');
        } else {
          alert('Unexpected error occurred. Please contact support.');
        }
      }
    );
  }


  onCancel() {
    this.dialogRef.close();
  }
}
