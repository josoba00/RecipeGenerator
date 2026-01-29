import {Component, signal, inject, OnDestroy} from '@angular/core';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MediaMatcher} from '@angular/cdk/layout';
import {RouterLink, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'app-sidenav',
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatSidenavModule, MatListModule, RouterLink, RouterLinkActive],
  templateUrl: './sidenav.html',
  styleUrl: './sidenav.css',
})
export class Sidenav {
  /*
  protected readonly isMobile = signal(true);
  private readonly _mobilQuery: MediaQueryList;
  private readonly _mobileQueryListener: () => void;


  constructor() {
    const media = inject(MediaMatcher);

    this._mobilQuery = media.matchMedia('(max-width: 600px)');
    this.isMobile.set(this._mobilQuery.matches);
    this._mobileQueryListener = () => this.isMobile.set(this._mobilQuery.matches);
    this._mobilQuery.addEventListener('change', this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this._mobilQuery.removeEventListener('change', this._mobileQueryListener);
  }
  */
}
