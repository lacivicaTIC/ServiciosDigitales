import { Component, Input, output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormControl } from '@angular/forms';
import { signal } from '@angular/core';

@Component({
  selector: 'app-input-field',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './input-field.html',
  styleUrl: './input-field.scss'
})
export class InputFieldComponent {
  @Input() label = '';
  @Input() type = 'text';
  @Input() placeholder = '';
  @Input() iconClass = '';
  @Input() control: FormControl = new FormControl();
  @Input() errorMessages: Record<string, string> = {};
  @Input() showTogglePassword = false;
  @Input() inputId = '';
  @Input() autocomplete = '';
  @Input() required = false;
  @Input() minlength: number | null = null;
  @Input() pattern: string | null = null;

  protected showPassword = signal(false);
  protected togglePassword = output<boolean>();

  protected get inputType(): string {
    if (this.type !== 'password' || this.showPassword()) {
      return this.type === 'password' ? 'text' : this.type;
    }
    return 'password';
  }

  protected onTogglePassword(): void {
    this.showPassword.update(value => !value);
    this.togglePassword.emit(this.showPassword());
  }

  protected get firstErrorKey(): string | null {
    const errors = this.control.errors;
    if (!errors) return null;
    return Object.keys(errors)[0] ?? null;
  }

  protected get errorMessage(): string | null {
    const key = this.firstErrorKey;
    return key ? (this.errorMessages[key] ?? `Error: ${key}`) : null;
  }
}
