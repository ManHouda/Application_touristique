import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-app-signup',
  templateUrl: './app-signup.component.html',
  styleUrls: ['./app-signup.component.css']
})
export class AppSignupComponent implements OnInit {

  @ViewChild('f') signupForm : NgForm;

  user = {
    username : '',
    email : '',
    password : ''
  }

  constructor() { }

  ngOnInit() {
  }

  onRegister(f: NgForm){
    console.log(f.value.inputUsername);
  }

}
