class Carte {
  constructor(name, rarete) {
    this.name = name;
    this.rarete = rarete;
  }
  get id() {
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
let magician = new Carte("Magicien", 2);
let garbage = new Carte("Péon", 1);

const tabCarteId = [garbage.id, magician.id, exodia.id];
const tabCarteName = [garbage.name, magician.name, exodia.name];

function tirage() {
  for (let i = 0; i < 5; i++) {
    let x = Math.floor((Math.random() * tabCarteId.length));
    var newContent = document.createTextNode("Vous venez d'obtenir : " + tabCarteName[x]);
    var newDiv = document.createElement("div");
    newDiv.appendChild(newContent);
    var currentDiv = document.getElementById('div1');
    currentDiv.appendChild(newDiv);
  }
}
