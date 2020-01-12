import { Component, OnInit } from '@angular/core';
import { Circuit } from './circuit.model';
import { MonumentService } from '../app-monuments/monument.service';
import { SocialService } from 'ng6-social-button';





@Component({
  selector: 'app-app-circuit',
  templateUrl: './app-circuit.component.html',
  styleUrls: ['./app-circuit.component.css']
})
export class AppCircuitComponent implements OnInit {
  lat: number = 51.678418;
  lng: number = 7.809007;

  
  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};
  
  circuit : Circuit[];
  shareObj = {
    href: "FACEBOOK-SHARE-LINK",
    hashtag:"#FACEBOOK-SHARE-HASGTAG"
};

  constructor(private monumentService : MonumentService,private socialAuthService: SocialService){}
 
    signOut(){
       if(this.socialAuthService.isSocialLoggedIn()){
           this.socialAuthService.signOut().catch((err)=>{
 
           });
       }
    }

  ngOnInit() { 
    // let mapProp = {
    //   center: new google.maps.LatLng(19.2372, 72.8441),
    //   zoom: 14,
    //   mapTypeId: google.maps.MapTypeId.ROADMAP
    // };
    // let map = new google.maps.Map(document.getElementById('googleMap'), mapProp);
      this.dropdownList = [
        { item_id: 1, item_text: 'Mumbai' },
        { item_id: 2, item_text: 'Bangaluru' },
        { item_id: 3, item_text: 'Pune' },
        { item_id: 4, item_text: 'Navsari' },
        { item_id: 5, item_text: 'New Delhi' }
      ];
      this.selectedItems = [
        
      ];
      this.dropdownSettings = {
        singleSelection: false,
        idField: 'item_id',
        textField: 'item_text',
        selectAllText: 'Select All',
        unSelectAllText: 'UnSelect All',
        allowSearchFilter: true
      };
    }
    getSocialUser(socialUser){
      console.log(socialUser);
  }
    onItemSelect(item: any) {
      console.log(item);
    }
    onSelectAll(items: any) {
      console.log(items);
    }
  

  onClick(event){
    this.lat = event.cords.lat;
    this.lng = event.cords.lng;
    console.log(event);
  }
}
