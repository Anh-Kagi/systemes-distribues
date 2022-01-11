class Carte{
  constructor(name, rarete) {
    this.name = name;
    this.rarete = rarete;
  }
  get id(){
    return this.rarete;
  }
  set id(val) {
    this.rarete = val;
  }
  
  get name() {
    return this._name;
  }
  set name(val) {
    this._name = val;
  }
}
let exodia = new Carte("Exodia", 3);
let magician = new Carte("Magician", 2);
let garbage = new Carte("Garbage", 1);

const tabCarteId = [garbage.id, magician.id, exodia.id];
const tabCarteName = [garbage.name, magician.name, exodia.name];

function tirage(){
  x = Math.floor((Math.random() * tabCarteId.length));
  console.log( " aled x = " +  x);
    //for (i = 0; i < x ; i++){
    //if ( x != 0) {
      console.log(tabCarteId[x] + " " + tabCarteName[x]);
      var newContent = document.createTextNode("Vous venez d'obtenir : " + tabCarteName[x]);
      var newDiv = document.createElement("div");
      newDiv.appendChild(newContent);      
      var currentDiv = document.getElementById('div1');
      document.body.insertBefore(newDiv, currentDiv); 
} 

