import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

export interface AuthFeature {
  icon: string;
  text: string;
}

@Component({
  selector: 'app-auth-layout',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './auth-layout.html',
  styleUrl: './auth-layout.scss'
})
export class AuthLayoutComponent {
  @Input() headline = 'Agenda tus trámites sin filas.';
  @Input() highlightedWord = 'filas';
  @Input() subheadline = 'Agenda citas, solicita turnos y realiza pagos en línea desde un solo lugar.';
  @Input() features: AuthFeature[] = [
    { icon: 'icon-[mdi--calendar-clock]', text: 'Agenda citas con disponibilidad en tiempo real' },
    { icon: 'icon-[mdi--timer-outline]', text: 'Consulta el estado de tus trámites' },
    { icon: 'icon-[mdi--shield-check]', text: 'Realiza pagos en línea de forma segura' },
    { icon: 'icon-[mdi--information-variant-circle-outline]', text: 'Información protegida por la Secretaría de Tránsito' }
  ];

  @Input() pageTitle = '';
  @Input() pageSubtitle = '';
  @Input() footerText = '';
  @Input() footerLinkText = '';
  @Input() footerLink = '';
  @Input() wideForm = false;

  protected get headlineParts(): { before: string; word: string; after: string } {
    const word = this.highlightedWord?.trim();
    if (!word || !this.headline) {
      return { before: this.headline || '', word: '', after: '' };
    }

    const index = this.headline.indexOf(word);
    if (index === -1) {
      return { before: this.headline, word: '', after: '' };
    }

    return {
      before: this.headline.slice(0, index),
      word: this.headline.slice(index, index + word.length),
      after: this.headline.slice(index + word.length)
    };
  }
}
