import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormGroup, FormBuilder, FormControl, NgForm } from '@angular/forms';
@Component({
  selector: 'app-app-signin',
  templateUrl: './app-signin.component.html',
  styleUrls: ['./app-signin.component.css']
})
export class AppSigninComponent implements OnInit {
  
    form: FormGroup;
    username: string;
    password: string;

    constructor(private router: Router,private fb: FormBuilder,
      private dialogRef: MatDialogRef<AppSigninComponent>,
      @Inject(MAT_DIALOG_DATA) data) {
        //this.username = data.username;
        //this.password = data.password;
    }
  
    ngOnInit() {
      this.form = this.fb.group({
          username: [this.username, []],
          password: [this.password, []],
      });
  }

    nagivateToSignup(){
      console.log(document.querySelector('#signup'));
      document.querySelector('#signup').scrollIntoView()
    }

    onLogin(){
      
        this.dialogRef.close(this.form.value);
        console.log(this.form.value);
        console.log(this.form);
    }
}
