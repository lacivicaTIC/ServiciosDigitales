import { Component, input, output, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.scss'
})
export class SidebarComponent {
  readonly mobileOpen = input(false);
  readonly closeSidebar = output<void>();

  readonly citasOpen = signal(false);

  toggleCitas(): void {
    this.citasOpen.update(open => !open);
  }

  onBackdropClick(): void {
    this.closeSidebar.emit();
  }
}
