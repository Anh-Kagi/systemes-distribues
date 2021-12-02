Vue.component('blog-post', {
  props: ['post'],
  template:
  "<div class='container rounded d-flex flex-row w-auto p-1 m-2 bg-secondary text-decoration-none text-white'>"+
    "<svg xmlns='http://www.w3.org/2000/svg' width='3em' height='3em' fill='white' class='bi bi-person-circle' viewBox='0 0 16 16'>"+
      "<path d='M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z' />"+
      "<path fill-rule='evenodd' d='M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z' />"+
    "</svg>"+
    "<div class='vr m-1'></div>"+
    "<div class='container d-flex flex-column'>"+
      "<span>{{post.date}}</span>"+
      "<hr />"+
      "<p>"+
        "{{post.message}}"+
      "</p>"+
    "</div>"+
  "</div>"
});
var posts =[
      { id: 1, date: new Date().toDateString(), message: 'I wonder if Exodia le maudit is really that powerful, as it is not complete yet.' + '/n' + 'Like, still 170 part of its body are missing'},
      { id: 2, date: new Date().toDateString(), message: "OF COURSE IT IS!, IF YOU'RE ASKING THE QUESTION, THEN YOU'RE A NOOB!" },
      { id: 3, date: new Date().toDateString(), message: "I don't think it is rn, but it surely will when all his parts will be available."}
    ]

var app = new Vue({
  el: '#blog-posts-demo',
  data: {
    posts : posts
  },
  methods: {
    submit: function(message){
      console.log(this.Texte);
      posts.push({id: 4, date: new Date().toDateString(), message: document.getElementById("Texte").value});
    }}
});