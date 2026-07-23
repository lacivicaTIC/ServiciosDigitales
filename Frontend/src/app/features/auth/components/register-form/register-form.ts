import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';

import { InputFieldComponent } from '../../../../shared/components/input-field/input-field';
import { SelectFieldComponent } from '../../../../shared/components/select-field/select-field';
import { AuthButtonComponent } from '../../../../shared/components/auth-button/auth-button';
import { numericMinLengthValidator, requireTrueValidator } from '../../../../core/utils/validators';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    InputFieldComponent,
    SelectFieldComponent,
    AuthButtonComponent
  ],
  templateUrl: './register-form.html',
  styleUrl: './register-form.scss'
})
export class RegisterFormComponent {
  private readonly fb = inject(FormBuilder);

  protected readonly documentTypeOptions = [
    { value: 'CC', label: 'Cédula de ciudadanía' },
    { value: 'CE', label: 'Cédula de extranjería' },
    { value: 'PA', label: 'Pasaporte' },
    { value: 'NIT', label: 'NIT' }
  ];

  protected readonly registerForm = this.fb.group({
    firstName: ['', [Validators.required, Validators.minLength(2)]],
    lastName: ['', [Validators.required, Validators.minLength(2)]],
    documentType: ['', [Validators.required]],
    documentNumber: ['', [Validators.required, numericMinLengthValidator(5)]],
    phone: ['', [Validators.required, numericMinLengthValidator(10)]],
    address: ['', [Validators.required, Validators.minLength(5)]],
    email: ['', [Validators.required, Validators.email]],
    terms: [false, [requireTrueValidator]]
  });

  protected readonly firstNameErrors: Record<string, string> = {
    required: 'El nombre es obligatorio.',
    minlength: 'El nombre debe tener al menos 2 caracteres.'
  };

  protected readonly lastNameErrors: Record<string, string> = {
    required: 'Los apellidos son obligatorios.',
    minlength: 'Los apellidos deben tener al menos 2 caracteres.'
  };

  protected readonly documentTypeErrors: Record<string, string> = {
    required: 'El tipo de documento es obligatorio.'
  };

  protected readonly documentNumberErrors: Record<string, string> = {
    required: 'El número de documento es obligatorio.',
    numeric: 'Solo se permiten números.',
    minlength: 'El documento debe tener al menos 5 dígitos.'
  };

  protected readonly phoneErrors: Record<string, string> = {
    required: 'El teléfono es obligatorio.',
    numeric: 'Solo se permiten números.',
    minlength: 'El teléfono debe tener al menos 10 dígitos.'
  };

  protected readonly addressErrors: Record<string, string> = {
    required: 'La dirección es obligatoria.',
    minlength: 'La dirección debe tener al menos 5 caracteres.'
  };

  protected readonly emailErrors: Record<string, string> = {
    required: 'El correo electrónico es obligatorio.',
    email: 'Ingresa un correo electrónico válido.'
  };

  protected readonly termsError = 'Debes aceptar los términos y condiciones.';

  protected onSubmit(): void {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    // TODO: connect with backend when required
    console.log('Register payload:', this.registerForm.value);
  }
}
