import { Component, OnInit, Input } from '@angular/core';
import { Monument } from '../monument.model';
import { MonumentService } from '../monument.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-monument-details',
  templateUrl: './monument-details.component.html',
  styleUrls: ['./monument-details.component.css']
})
export class MonumentDetailsComponent implements OnInit {

  monument : Monument;
  id : number;

  constructor(private monumentService: MonumentService, private activatedroute:ActivatedRoute,
    private router:Router) { }

    sub;
 
    ngOnInit() {
       this.sub=this.activatedroute.paramMap.subscribe(params => { 
          console.log(params);
           this.id = parseInt(params.get('id')); 
           let monuments=this.monumentService.getMonuments();
           this.monument=monuments.find(m => m.id==this.id);    
       });
    }

    ngOnDestroy() {
      this.sub.unsubscribe();
    }
    
    onBack(): void {
       this.router.navigate(['monuments']);
    }
}
