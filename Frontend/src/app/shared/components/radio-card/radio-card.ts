import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormControl } from '@angular/forms';

@Component({
  selector: 'app-radio-card',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './radio-card.html',
  styleUrl: './radio-card.scss'
})
export class RadioCardComponent {
  @Input() label = '';
  @Input() subtext = '';
  @Input() iconClass = '';
  @Input() name = '';
  @Input() value = '';
  @Input() control: FormControl = new FormControl();
  @Input() inputId = '';

  protected get isChecked(): boolean {
    return this.control.value === this.value;
  }

  protected select(): void {
    this.control.setValue(this.value);
    this.control.markAsTouched();
  }
}
