import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export const passwordStrengthValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const value = control.value as string;
  if (!value) return null;

  const hasUpperCase = /[A-Z]/.test(value);
  const hasLowerCase = /[a-z]/.test(value);
  const hasNumeric = /[0-9]/.test(value);

  const errors: ValidationErrors = {};
  if (!hasUpperCase) errors['missingUppercase'] = true;
  if (!hasLowerCase) errors['missingLowercase'] = true;
  if (!hasNumeric) errors['missingNumber'] = true;

  return Object.keys(errors).length ? { passwordStrength: errors } : null;
};

export const passwordMatchValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const password = control.get('password')?.value as string;
  const confirmPassword = control.get('confirmPassword')?.value as string;

  if (!confirmPassword) return null;

  return password === confirmPassword ? null : { passwordMismatch: true };
};

export const numericMinLengthValidator = (minLength: number): ValidatorFn => {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value as string;
    if (!value) return null;

    const digitsOnly = /^[0-9]+$/.test(value);
    if (!digitsOnly) return { numeric: true };
    if (value.length < minLength) return { minlength: { requiredLength: minLength, actualLength: value.length } };

    return null;
  };
};

export const requireTrueValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  return control.value === true ? null : { required: true };
};
