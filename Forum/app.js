"use strict";

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
            "<span>{{ post.author }}</span><span>{{ post.date }}</span>"+
            "<hr />"+
            "<p>"+
                "{{ post.content }}"+
            "</p>"+
        "</div>"+
    "</div>",
});

Vue.component("thread-tile", {
    props: ['threadid', 'thread'],
    template:
    "<a class='container rounded d-flex flex-row w-auto p-1 m-2 bg-secondary text-decoration-none text-white' href='./thread.html' @click='localStorage[\"threadid\"] = threadid'>"+
        "<!-- author icon's -->"+
        "<svg xmlns='http://www.w3.org/2000/svg' width='3em' height='3em' fill='white' class='bi bi-person-circle' viewBox='0 0 16 16' title='Author Name'>"+
            "<path d='M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z'/>"+
            "<path fill-rule='evenodd' d='M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z'/>"+
        "</svg>"+
        "<div class='vr m-1'></div>"+
        "<span style='font-size: 2em;'>{{ thread.name }}</span>"+
        "<div class='vr m-1 ms-auto'></div>"+
        "<svg xmlns='http://www.w3.org/2000/svg' width='3em' height='3em' fill='white' class='bi bi-chevron-right' viewBox='0 0 16 16'>"+
            "<path fill-rule='evenodd' d='M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z'/>"+
        "</svg>"+
  "</a>",
});

// TODO: switch from local db to server's api
let threads;
let threadid
if (!localStorage["threads"] || !localStorage["threadid"])
    resetCache();
else {
    threads = JSON.parse(localStorage["threads"]);
    threadid = JSON.parse(localStorage["threadid"]);
}
function resetCache() {
    threads = [
        {
            name: "What is the best card?",
            author: "Luke",
            content: "The question is in the title.",
            date: new Date().toLocaleString(),
            posts: [
                {
                    author: "Erik",
                    date: new Date().toLocaleString(),
                    content: "Only noobs ask questions like these"
                },
            ]
        },{
            name: "Help - Game Mechanics",
            author: "",
            content: "I don't understand how the game works",
            date: new Date().toLocaleString(),
            posts: [
                {
                    author: "Henry",
                    date: new Date().toLocaleString(),
                    content: "It simple: use your cards to win."
                },
            ],
        }
    ];
    threadid = 0;
}

addEventListener("beforeunload", () => {
    localStorage["threads"] = JSON.stringify(threads);
});

function newThread(name, date, content) {
    let thread = {
        name: name,
        date: date,
        content: content,
        posts: []
    };
    threads.push(thread);
    return thread;
}

function getThread(id) {
    return threads[id];
}

function addPost(thread, author, date, content) {
    if (!threads[thread]) return false;
    
    let post = {
        author: author,
        date: date,
        content: content,
    };

    threads[thread].posts.push(post);

    return post;
}
