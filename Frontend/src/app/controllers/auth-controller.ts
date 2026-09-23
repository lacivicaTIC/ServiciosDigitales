import { Injectable } from '@angular/core';
import { Validators } from '@angular/forms';

import { AuthService } from '../services/auth.services';
import { LoginFormComponent } from '../features/auth/components/login-form/login-form';

import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AuthController {

  constructor(
    private _serviceAuth: AuthService
  ) {}

  /**
   * Inicializa el formulario de login
   */
  init(view: LoginFormComponent): void {

    view.authForm = view.formBuilder.group({

      email: [
        '',
        [
          Validators.required,
          Validators.email
        ]
      ],

      password: [
        '',
        [
          Validators.required,
          Validators.minLength(6)
        ]
      ],

      firstLogin: [
        false
      ],

      rememberMe: [
        false
      ]

    });
  }


  /**
   * Procesar login
   */
  onSubmit(view: LoginFormComponent): void {

    view.submitted = true;

    /**
     * Validar formulario
     */
    if (view.authForm.invalid) {

      view.authForm.markAllAsTouched();

      view.loading = false;

      return;
    }


    view.loading = true;


    /**
     * Obtener valores
     */
    const email = view.f['email'].value;
    const password = view.f['password'].value;


    /**
     * Construir objeto que espera el backend
     *
     * Backend:
     * {
     *   "username": "...",
     *   "password": "..."
     * }
     */
    const body = {

      username: String(email).trim().toLowerCase(),

      password: String(password)

    };


    console.log('================================');
    console.log('ENVIANDO LOGIN');
    console.log('================================');
    console.log('Usuario:', body.username);
    console.log('Password:', '********');


    /**
     * SweetAlert de carga
     */
    Swal.fire({

      title: 'Iniciando sesión',

      text: 'Validando credenciales...',

      allowOutsideClick: false,

      allowEscapeKey: false,

      didOpen: () => {

        Swal.showLoading();

      }

    });


    /**
     * Llamar al servicio
     */
    this._serviceAuth.login(body).subscribe({

      next: (user) => {

        this.handleLoginSuccess(user, view);

      },

      error: (error) => {

        this.handleError(view, error);

      }

    });
  }


  /**
   * Login exitoso
   */
  private handleLoginSuccess(
    user: any,
    view: LoginFormComponent
  ): void {

    console.log('================================');
    console.log('RESPUESTA DEL LOGIN');
    console.log('================================');

    console.log(user);


    /**
     * Validar respuesta del backend
     */
    if (user && user.codigo === 'ok') {

      /**
       * Cerrar loading
       */
      Swal.close();


      /**
       * Mostrar mensaje de éxito
       */
      Swal.fire({

        position: 'center',

        icon: 'success',

        title: user.mensaje || 'Login exitoso',

        text: `Bienvenido, ${user.nombre || ''}`,

        showConfirmButton: false,

        timer: 2000

      });


      /**
       * Actualizar estado
       */
      view.loading = false;

      view.submitted = false;


      /**
       * Navegar después del mensaje
       */
      setTimeout(() => {

        view.router.navigate(['/dashboard']);

      }, 2000);


    } else {

      /**
       * Respuesta inesperada o login rechazado
       */
      Swal.close();

      Swal.fire({

        position: 'center',

        icon: user?.codigo === 'error'
          ? 'error'
          : 'warning',

        title: user?.codigo || 'Error',

        text: user?.mensaje || 'Acceso denegado',

        showConfirmButton: true

      });


      this.handleInvalidLogin(view);
    }
  }


  /**
   * Login inválido
   */
  private handleInvalidLogin(
    view: LoginFormComponent
  ): void {

    view.submitted = false;

    view.loading = false;
  }


  /**
   * Error HTTP
   */
  private handleError(
    view: LoginFormComponent,
    error: any
  ): void {

    view.submitted = false;

    view.loading = false;


    console.error('================================');
    console.error('ERROR EN LOGIN');
    console.error('================================');

    console.error(error);


    /**
     * Cerrar loading
     */
    Swal.close();


    /**
     * Error de conexión
     */
    if (
      error?.status === 0 ||
      !navigator.onLine
    ) {

      Swal.fire({

        position: 'center',

        icon: 'error',

        title: 'Error de conexión',

        text:
          'No se pudo establecer conexión con el servidor. ' +
          'Verifica tu conexión e inténtalo nuevamente.',

        showConfirmButton: true

      });

      return;
    }


    /**
     * Error interno del servidor
     */
    if (error?.status === 500) {

      Swal.fire({

        position: 'center',

        icon: 'error',

        title: 'Error en el servidor',

        text:
          error?.error?.mensaje ||
          'Ocurrió un error interno en el servidor.',

        showConfirmButton: true

      });

      return;
    }


    /**
     * Obtener mensaje enviado por backend
     */
    const backendMessage =
      error?.error?.mensaje ||
      'Usuario o contraseña incorrecta';


    /**
     * Correo no validado
     */
    if (
      error?.status === 401 &&
      backendMessage === 'Correo no validado'
    ) {

      Swal.fire({

        position: 'center',

        icon: 'warning',

        title: 'Correo no validado',

        text: backendMessage,

        showConfirmButton: true

      });

      return;
    }


    /**
     * Error de autenticación
     */
    Swal.fire({

      position: 'center',

      icon: 'error',

      title: 'Error al ingresar',

      text: backendMessage,

      showConfirmButton: true

    });
  }


  /**
   * Cerrar sesión
   */
  cerrarSession(): void {

    console.log('Cerrar sesión');

  }
}