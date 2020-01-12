import { Component, OnInit } from '@angular/core';
import { Monument } from '../monument.model';
import { MonumentService } from '../monument.service';

@Component({
  selector: 'app-monument-list',
  templateUrl: './monument-list.component.html',
  styleUrls: ['./monument-list.component.css']
})
export class MonumentListComponent implements OnInit {

  monuments : Monument[];

  constructor(private monumentService: MonumentService) { }

  ngOnInit() {
    this.monuments = this.monumentService.getMonuments();
  }

}
