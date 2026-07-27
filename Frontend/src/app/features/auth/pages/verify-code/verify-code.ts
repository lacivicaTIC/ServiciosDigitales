import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthLayoutComponent } from '../../../../shared/components/auth-layout/auth-layout';
import { AuthButtonComponent } from '../../../../shared/components/auth-button/auth-button';
import { CodeInputComponent } from '../../../../shared/components/code-input/code-input';
import { AuthFeature } from '../../../../shared/components/auth-layout/auth-layout';

@Component({
  selector: 'app-verify-code',
  standalone: true,
  imports: [CommonModule, AuthLayoutComponent, AuthButtonComponent, CodeInputComponent],
  templateUrl: './verify-code.html',
  styleUrl: './verify-code.scss'
})
export class VerifyCodePage {
  protected readonly code = signal('');
  protected readonly error = signal<string | null>(null);

  protected readonly features: AuthFeature[] = [
    { icon: 'icon-[mdi--email-outline]', text: 'Revisa también tu bandeja de spam' },
    { icon: 'icon-[mdi--clock-outline]', text: 'El código expira en 10 minutos' }
  ];

  protected onCodeChange(value: string): void {
    this.code.set(value);
    if (value.length === 6) {
      this.error.set(null);
    }
  }

  protected onVerify(): void {
    const value = this.code();
    if (!/^\d{6}$/.test(value)) {
      this.error.set('El código debe tener exactamente 6 dígitos numéricos.');
      return;
    }

    // TODO: connect with backend when required
    console.log('Verify code:', value);
  }
}
