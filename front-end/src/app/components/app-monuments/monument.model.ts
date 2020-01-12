export class Monument{
    public id: number;
    public name: string;
    public description: string;
    public imagePath: string;
    public altitude: number;
    public longitude: number;
    public tempsVisite: number;
    public ville: string;
    public region : string;


    public constructor(id: number,name: string, desc: string, img: string, tmp: number, ville: string, region: string){
        this.id = id;
        this.name = name;
        this.description = desc;
        this.imagePath = img;
        this.tempsVisite = tmp;
        this.ville = ville;
        this.region = region;
    }

    
}