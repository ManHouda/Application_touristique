export class MonumentService {
 private baseUrl:string='http://localhost:8080/rm';
 private headers= new Headers({'Content-Type':'application/json'});
 private options = new RequestOptions({headers:this.headers});
 //Injection de l'http
 constructor(private _http:Http) { }
 getMonuments(){
 // '/listMonument' est une référence au @GetMapping("/listMonument") dans le controlleur : MonumentController
 return this._http.get(this.baseUrl+'/listMonument',this.options).map((response:Response)=>response.json())
 .catch(this.errorHandle);
 }
 getMonument(id:Number){
 // '/showMonument/' est une référence au @GetMapping("/showMonument by /{id}") 

 return this._http.get(this.baseUrl+'/showmonument/'+id,this.options).map((response:Response)=>response.json())
 .catch(this.errorHandle);
 }
 
 saveMonument(monument:Monument){
 // '/add' est une référence au @GetMapping("/add") dans le controlleur : monumentController
 return this._http.post(this.baseUrl+'/add',JSON.stringify(monument),this.options).map((response:Response)=>response.json())
 .catch(this.errorHandle);
 }

 errorHandle(error:Response){
 return Observable.throw(error||"Server Error");
 }
}