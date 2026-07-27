import { Component, ElementRef, QueryList, ViewChildren, output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-code-input',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './code-input.html',
  styleUrl: './code-input.scss'
})
export class CodeInputComponent {
  @ViewChildren('digitInput') digitInputs!: QueryList<ElementRef<HTMLInputElement>>;

  readonly codeChange = output<string>();

  protected readonly digitIndexes = Array.from({ length: 6 }, (_, i) => i);

  protected onInput(event: Event, index: number): void {
    const input = event.target as HTMLInputElement;
    const cleaned = input.value.replace(/\D/g, '').slice(0, 1);
    input.value = cleaned;
    this.emitCode();

    if (cleaned && index < 5) {
      this.focus(index + 1);
    }
  }

  protected onKeydown(event: KeyboardEvent, index: number): void {
    const input = event.target as HTMLInputElement;

    if (event.key === 'Backspace') {
      if (!input.value && index > 0) {
        event.preventDefault();
        this.clear(index - 1);
        this.focus(index - 1);
        this.emitCode();
      }
      return;
    }

    if (event.key === 'ArrowLeft' && index > 0) {
      event.preventDefault();
      this.focus(index - 1);
      return;
    }

    if (event.key === 'ArrowRight' && index < 5) {
      event.preventDefault();
      this.focus(index + 1);
      return;
    }
  }

  protected onPaste(event: ClipboardEvent, index: number): void {
    event.preventDefault();
    const raw = event.clipboardData?.getData('text') ?? '';
    const cleaned = raw.replace(/\D/g, '').slice(0, 6);
    if (!cleaned) return;

    const inputs = this.digitInputs.toArray();
    for (let i = 0; i < 6; i++) {
      const value = cleaned[i] ?? '';
      if (inputs[i]) {
        inputs[i].nativeElement.value = value;
      }
    }

    const focusIndex = Math.min(index + cleaned.length, 5);
    this.focus(focusIndex);
    this.emitCode();
  }

  private clear(index: number): void {
    const input = this.digitInputs.get(index)?.nativeElement;
    if (input) {
      input.value = '';
    }
  }

  private focus(index: number): void {
    const input = this.digitInputs.get(index)?.nativeElement;
    if (input) {
      input.focus();
      input.select();
    }
  }

  private emitCode(): void {
    const code = this.digitInputs.toArray().map(ref => ref.nativeElement.value).join('');
    this.codeChange.emit(code);
  }
}
