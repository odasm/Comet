var launcher = require('./launcher.js');

// Configure web sockets
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

io.on('connection', function(socket){
    socket.on('message', function(msg) {
        console.log("Message received: " + msg);
    });
});

http.listen(3000, function(){
    console.log('listening on *:3000');
});