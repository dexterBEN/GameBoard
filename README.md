# GameBoard

## Purpose 
This is short personal project to test Flutter for web.   
This web-application allow you to consult game sheet give a comment.  
You can also see informations about the studio and a video.  
You can test it there: https://gameboard-b011e.web.app  

## Stack and Architecture
The front is built with Flutter and hosted with Firebase.  
The back is built with Spring-boot and hosted on VM-linux (Maybe on App-engine later).  
The BDD is Firestore for User, Images, Comment and MySQL for GameSheet, Studio (running on docker containers for this one).  
Using Swagger for the documentation.  

Keywords: Flutter, Firebase, Firestore, Provider, Spring, Swagger, MySQL, GithubActions, Docker



There a sample of the architecture (can changed later):

![Architecture sample](![image](https://user-images.githubusercontent.com/33292824/116818444-b1563780-ab6b-11eb-90ac-d1ba579e91bd.png)
)
