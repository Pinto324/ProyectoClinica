import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComponentCommunicationService {
  private closeSecondComponentSubject = new Subject<void>();

  closeSecondComponent$ = this.closeSecondComponentSubject.asObservable();

  closeSecondComponent() {
    this.closeSecondComponentSubject.next();
  }
}