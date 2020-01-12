import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AppHomeComponent } from './components/app-home/app-home.component';
import { AppContactComponent } from './components/app-contact/app-contact.component';
import { AppSignupComponent } from "./components/auth/app-signup/app-signup.component";
import { AppMonumentsComponent } from "./components/app-monuments/app-monuments.component";
import { AppCircuitComponent } from "./components/app-circuit/app-circuit.component";
import { MonumentDetailsComponent } from "./components/app-monuments/monument-details/monument-details.component";
import { AppSigninComponent } from "./components/auth/app-signin/app-signin.component";
import { MyCircuitsComponent } from "./components/app-circuit/my-circuits/my-circuits.component";
import { AppGuideComponent } from "./components/app-guide/app-guide.component";

const appRoutes: Routes = [
    { path: 'home', component: AppHomeComponent },
    { path: 'signup', component: AppSignupComponent },
    { path: 'signin', component: AppSigninComponent },
    { path: 'monuments', component: AppMonumentsComponent},
    { path: 'monuments/details/:id', component: MonumentDetailsComponent},
    { path: 'circuit', component: AppCircuitComponent},
    { path: 'circuit/mycircuits', component: MyCircuitsComponent},
    { path: 'guide', component: AppGuideComponent},
    { path: 'contact', component: AppContactComponent },
    { path: '**', redirectTo: 'home', pathMatch: 'full' }
  ];
  
  

@NgModule({
    imports: [
        // RouterModule.forRoot(appRoutes, {useHash: true})
        RouterModule.forRoot(appRoutes)

    ],
    exports: [RouterModule]
})

export class AppRoutingModule{

}