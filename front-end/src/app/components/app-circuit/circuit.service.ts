import { Circuit } from "./circuit.model";
import { Monument } from "../app-monuments/monument.model";

export class CircuitService{

    circuit: Circuit;

    constructor(){

    }

    getCircuit(){
        return this.circuit;
    }

    addMonumentsToCircuit(monuments: Monument[]){
        this.circuit.monuments = monuments;
    }


    
}