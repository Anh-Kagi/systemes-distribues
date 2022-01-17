"use strict";

Vue.component('deck-card', {
  props: ['card'],
  template: "<button type="
  button " class="
  btn btn - primary btn - lg m - 2 " onClick="
  ">{{card.name}}</button>"
});

function nbCard() {
  return document.getElementById('dropzone').childElementCount;
}

function addButton(name) {
  var btn = document.createElement("BUTTON");
  var t = document.createTextNode(name);
  btn.appendChild(t);
  document.body.appendChild(btn);
}

function deckVerification() {
  if (nbCard() == 20) {
    JavaScript: window.close();
  }
}
