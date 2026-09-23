//import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { Header } from '../util/headers';
import { LoginResponse } from '../models/LoginResponse';
import { SdUsuario } from '../models/SdUsuario';
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  header: Header = new Header();

  private currentUserSubject: BehaviorSubject<SdUsuario>;
  public currentUser: Observable<SdUsuario>;

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {

    let user: SdUsuario | any = {};

    if (isPlatformBrowser(this.platformId)) {

      const currentUser =
        localStorage.getItem('currentUser');

      user = currentUser
        ? JSON.parse(currentUser)
        : {};

    }

    this.currentUserSubject =
      new BehaviorSubject<SdUsuario>(user);

    this.currentUser =
      this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): any {
    return this.currentUserSubject.value;
  }

  login(body: LoginResponse): Observable<any> {

    return this.http
      .post<any>(
        this.header.url.concat('login/ingresar'),
        body,
        { withCredentials: true }
      )
      .pipe(
        map((user) => {

          if (user) {

            if (isPlatformBrowser(this.platformId)) {

              localStorage.setItem(
                'currentUser',
                JSON.stringify(user)
              );

              localStorage.setItem(
                'permisos',
                JSON.stringify(user.permisos ?? [])
              );

              localStorage.setItem(
                'horaIngreso',
                JSON.stringify(user.horaInicio ?? [])
              );

            }

            this.currentUserSubject.next(user);

          }

          return user;
        })
      );
  }
}