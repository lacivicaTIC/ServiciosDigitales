import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormControl } from '@angular/forms';

@Component({
  selector: 'app-checkbox-field',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './checkbox-field.html',
  styleUrl: './checkbox-field.scss'
})
export class CheckboxFieldComponent {
  @Input() label = '';
  @Input() control: FormControl = new FormControl(false);
  @Input() errorMessage = '';
  @Input() inputId = '';
}
