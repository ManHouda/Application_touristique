import { Monument } from "./monument.model";
import { Injectable, EventEmitter } from "@angular/core";
import { CircuitService } from "../app-circuit/circuit.service";

@Injectable()
export class MonumentService{

    text = `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt  
    ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip 
    ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla 
    pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum`;

    private monuments : Monument[] = [
        
        new Monument(1,'monument1', this.text,
        'http://www.amush.org/wp-content/uploads/2011/04/_images_tngcngr_tng.jpg',20,'Tetouan','Tanger-Tetouan-Al hoceima'),
         
        new Monument(2,'monument2', this.text, 'https://i.imgur.com/Y95CAoZ.jpg', 20,'Al Hoceima','Tanger-Tetouan-Al hoceima'),
        
        new Monument(3,'monument3', this.text, 
         'https://media-cdn.tripadvisor.com/media/photo-s/0d/39/5f/41/img-20161006-115243-largejpg.jpg',
         20,'Tetouan','Tanger-Tetouan-Al hoceima'),
         
        new Monument(4,'monument4', this.text,
         'http://www.nadorcity.com/photo/art/default/3745419-5568796.jpg?v=1327930308',
         20,'Tetouan','Tanger-Tetouan-Al hoceima'),
        
        new Monument(5,'monument5', this.text,
         'http://www.lot-46.com/wp001/wp-content/uploads/2014/08/052-004-camboulit-bru-eglise-saint-martin-post.jpg',
         20,'Al Hoceima','Tanger-Tetouan-Al hoceima'),
         
        new Monument(6,'monument6', this.text,
         'http://www.amush.org/wp-content/uploads/2011/04/_images_tngcngr_tng.jpg',
         20,'Al Hoceima','Tanger-Tetouan-Al hoceima'),
         
        new Monument(7,'monument7', this.text,
         'http://www.lot-46.com/wp001/wp-content/uploads/2014/08/052-004-camboulit-bru-eglise-saint-martin-post.jpg',
         20,'Tanger','Tanger-Tetouan-Al hoceima'),
         
        new Monument(8,'monument8', this.text,
         'http://www.amush.org/wp-content/uploads/2011/04/_images_tngcngr_tng.jpg',
         20,'Tetouan','Tanger-Tetouan-Al hoceima'),
    ];

    constructor(private circuitService: CircuitService){
        
    }

    getMonuments(){
        return this.monuments;
    }

    getMonument(id: number){
        return this.monuments.find(m => m.id==id);
    }
   
}