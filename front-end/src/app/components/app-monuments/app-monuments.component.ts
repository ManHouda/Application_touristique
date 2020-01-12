import { Component, OnInit } from '@angular/core';
import { Monument } from './monument.model';
import { MonumentService } from './monument.service';

@Component({
  selector: 'app-app-monuments',
  templateUrl: './app-monuments.component.html',
  styleUrls: ['./app-monuments.component.css']
})
export class AppMonumentsComponent implements OnInit {

  

  constructor(private monumentService: MonumentService) { }

  ngOnInit() {
    
  }

}
