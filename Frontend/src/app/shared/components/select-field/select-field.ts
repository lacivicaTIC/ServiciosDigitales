import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormControl } from '@angular/forms';

export interface SelectOption {
  value: string;
  label: string;
}

@Component({
  selector: 'app-select-field',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './select-field.html',
  styleUrl: './select-field.scss'
})
export class SelectFieldComponent {
  @Input() label = '';
  @Input() placeholder = 'Selecciona';
  @Input() iconClass = '';
  @Input() inputId = '';
  @Input() control: FormControl = new FormControl();
  @Input() errorMessages: Record<string, string> = {};
  @Input() options: SelectOption[] = [];
  @Input() required = false;

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
