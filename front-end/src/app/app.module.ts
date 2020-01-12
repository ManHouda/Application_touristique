import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { FlxUiDatatableModule, FlxUiDataTable } from 'flx-ui-datatable'
import { AgmCoreModule } from '@agm/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTableModule} from '@angular/material/table';
 
import { Angular2FontawesomeModule } from 'angular2-fontawesome/angular2-fontawesome'
import { AppComponent } from './app.component';

import { AppNavComponent } from './components/app-nav/app-nav.component';
import { AppHomeComponent } from './components/app-home/app-home.component';
import { AppContactComponent } from './components/app-contact/app-contact.component';
import { AppFooterComponent } from './components/app-footer/app-footer.component';
import { AppSlideComponent } from './components/app-slide/app-slide.component';
import { AppSignupComponent } from './components/auth/app-signup/app-signup.component';
import { AppSigninComponent } from './components/auth/app-signin/app-signin.component';
import { AppRoutingModule } from './app-routing.module';
import { AppMonumentsComponent } from './components/app-monuments/app-monuments.component';
import { MonumentDetailsComponent } from './components/app-monuments/monument-details/monument-details.component';
import { MonumentListComponent } from './components/app-monuments/monument-list/monument-list.component';
import { MonumentItemComponent } from './components/app-monuments/monument-list/monument-item/monument-item.component';
import { MonumentService } from './components/app-monuments/monument.service';
import { AppCircuitComponent } from './components/app-circuit/app-circuit.component';
import { MyCircuitsComponent } from './components/app-circuit/my-circuits/my-circuits.component';
import { CircuitService } from './components/app-circuit/circuit.service';
import { MatButtonModule, MatFormFieldModule, MatInputModule, MatRippleModule,MatCardModule, MatIconModule } from '@angular/material';
import { AppGuideComponent } from './components/app-guide/app-guide.component';
import { HttpModule } from '@angular/http';



import {
  Ng6SocialButtonModule,
  SocialServiceConfig
} from "ng6-social-button";

const modules = [
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatRippleModule,
  MatCardModule,
  MatIconModule
];

@NgModule({
  declarations: [
    AppComponent,
    AppNavComponent,
    AppHomeComponent,
    AppContactComponent,
    AppFooterComponent,
    AppSlideComponent,
    AppSignupComponent,
    AppSigninComponent,
    AppMonumentsComponent,
    MonumentDetailsComponent,
    MonumentListComponent,
    MonumentItemComponent,
    AppCircuitComponent,
    MyCircuitsComponent,
    AppGuideComponent,
  ],
  imports: [
    ...modules,
    BrowserModule,
    Angular2FontawesomeModule,
    FlxUiDatatableModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
     MatDialogModule,
     MatTableModule,
     HttpModule,
    AppRoutingModule,
    Ng6SocialButtonModule,
    
    NgbModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAV6OxMoTIeZNIkXeigAQIm4FJGzo3XRIY'
    }),
    NgMultiSelectDropDownModule.forRoot()
  ],
  exports:[...modules],
  providers: [MonumentService,CircuitService,FlxUiDataTable,
    {
      provide: SocialServiceConfig,
      useFactory: getAuthServiceConfigs
  }],
  bootstrap: [AppComponent],
  entryComponents: [AppSigninComponent]
})
export class AppModule { }
export function getAuthServiceConfigs() {
  let config = new SocialServiceConfig()
  .addFacebook("Your-Facebook-app-id");
  return config;
    }
   