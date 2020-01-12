import { Monument } from "../app-monuments/monument.model";

export class Circuit{
    start : Monument;
    end: Monument;
    monuments : Monument[];

    constructor(start: Monument, end: Monument, monuments: Monument[]){
        this.start = start;
        this.end = end;
        this.monuments = monuments;
    }
}