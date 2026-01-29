import {Component, EventEmitter, Output} from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {RouterOutlet} from '@angular/router';
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from '@angular/material/sidenav';
import {MatNavList} from '@angular/material/list';
import {Sidenav} from '../sidenav/sidenav';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatNavList,
    RouterOutlet,
    MatSidenavContainer,
    MatSidenavContent,
    MatSidenav,
    Sidenav,
  ],
  templateUrl: './header.html',
  styleUrls: ['./header.css']
})
export class Header {}
