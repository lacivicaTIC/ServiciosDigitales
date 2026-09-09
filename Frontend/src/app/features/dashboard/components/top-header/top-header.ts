import { Component, output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-top-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './top-header.html',
  styleUrl: './top-header.scss'
})
export class TopHeaderComponent {
  readonly openSidebar = output<void>();

  onMenuClick(): void {
    this.openSidebar.emit();
  }
}
