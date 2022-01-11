function nbCard(){
  return document.getElementById('dropzone').childElementCount;
}

function addButton (name){
  var btn = document.createElement("BUTTON");
  var t = document.createTextNode(name);
  btn.appendChild(t);
  document.body.appendChild(btn);
}

function deckVerification (){
  if (nbCard()==20){
    JavaScript:window.close();
  }
}
