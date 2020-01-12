import { Component, OnInit, Input } from '@angular/core';
import { Monument } from '../../monument.model';
import { MonumentService } from '../../monument.service';

@Component({
  selector: 'app-monument-item',
  templateUrl: './monument-item.component.html',
  styleUrls: ['./monument-item.component.css']
})
export class MonumentItemComponent implements OnInit {

  @Input() monument : Monument;
  
  constructor(private monumentService : MonumentService) { }

  ngOnInit() {
  }

  

}
