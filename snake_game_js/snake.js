var stop = false;
var timer;
var x = 0;
var y = 250;
var currentDirection = "RIGHT";
//var canRestart = false;

function start(){
    if(!stop){
        timer = setInterval(run,500);
        document.getElementById("start").value = "stop";
        stop = true;
    }else{
        clearInterval(timer);
        document.getElementById("start").value = "start";
        stop = false;
    }

    // if(canRestart){
    //     x = 0;
    //     y = 250;
    //     var context = document.getElementById("canvasId").getContext("2d");
    //     context.clearRect(0, 0, 1000, 600);
    //     timer = setInterval(run,500);
    //     document.getElementById("start").value = "stop";
    //     stop = true;
    // }
} 

function turnRight(){
    if(currentDirection == "UP"){
        currentDirection = "RIGHT";
    }else if(currentDirection=="RIGHT"){
        currentDirection="DOWN";
    }else if(currentDirection=="DOWN"){
        currentDirection = "LEFT";
    }else if(currentDirection=="LEFT"){
        currentDirection = "UP";
    }
} 
function turnLeft(){
    if(currentDirection == "UP"){
        currentDirection = "LEFT";
    }else if(currentDirection=="RIGHT"){
        currentDirection="UP";
    }else if(currentDirection=="DOWN"){
        currentDirection = "RIGHT";
    }else if(currentDirection=="LEFT"){
        currentDirection = "DOWN";
    }

}

function run(){

    
    if(x>1000 || x<0 || y>600 || y<0){
        alert("You died");
        clearInterval(timer);
        document.getElementById("start").value="Refresh page to restart";
        stop = false;
        //canRestart = true;
    }else{
        draw(currentDirection);
    }

}


function draw(direction){
    var context = document.getElementById("canvasId").getContext("2d");
    if(context.getImageData(x,y,10,10).data.some(channel => channel!==0)){
        alert("You have eat yourself");
        clearInterval(timer);
        document.getElementById("start").value="Refresh page to restart";
        stop = false;
        //canRestart = true;
        return;
    }
    context.fillStyle = "#ffc821";
    context.rect(x,y,10,10);       
    //fill the rectangle
    context.fill();
    //x = x + 10;
    if(currentDirection=="UP"){
        y = y - 10;
    }else if(currentDirection=="DOWN"){
        y = y+10;
    }else if(currentDirection=="RIGHT"){
        x = x  + 10;
    }else if(currentDirection=="LEFT"){
        x = x -10;
    }

}

