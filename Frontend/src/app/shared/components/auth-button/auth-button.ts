import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-auth-button',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './auth-button.html',
  styleUrl: './auth-button.scss'
})
export class AuthButtonComponent {
  @Input() label = '';
  @Input() iconClass = '';
  @Input() type: 'button' | 'submit' | 'reset' = 'submit';
  @Input() disabled = false;
  @Input() loading = false;
}
