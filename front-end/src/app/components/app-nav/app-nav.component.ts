import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatDialog, MatDialogConfig } from "@angular/material";
import { AppSigninComponent } from '../auth/app-signin/app-signin.component';

@Component({
  selector: 'app-app-nav',
  templateUrl: './app-nav.component.html',
  styleUrls: ['./app-nav.component.css']
})
export class AppNavComponent implements OnInit {

  
  private fragment: string;

  constructor(private route: ActivatedRoute, private dialog : MatDialog) {}

  ngOnInit() {
    this.route.fragment.subscribe(fragment => { this.fragment = fragment; });
  }

  ngAfterViewInit(): void {
    try {
     
    } catch (e) {
      console.log(e);
    }
  }

  nagivateToSignup(){
    console.log(document.querySelector('#signup'));
    document.querySelector('#signup').scrollIntoView()
  }

  onSignin(){

    const dialogConfig = new MatDialogConfig();
   
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    this.dialog.open(AppSigninComponent, dialogConfig);
   
    
    const dialogRef = this.dialog.open(AppSigninComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
        data => console.log("Dialog output:", data)
    );    
    
  }

}
