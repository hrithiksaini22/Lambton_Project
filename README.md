# To Do List App React (V1)

To Do List App React JS by WebStylePress (Add, Edit, Update, Delete, Toggle Tasks)

## How to Use the application locally

Clone or download repo
NodeJS / NPM / Yarn should be installed in your PC

Open terminal or Git for Windows (Git Bash)
Run these commands:

### Install Dependencies
yarn install OR npm install
### Run app
yarn start OR npm start

## How to run the application using jenkins
1) install jenkins on an ec2 instance using jenkins docker image
2) attach docker.sock file when launching the jenkins container and give permission 666 for the container user to access the docker.sock file. This enables the container to run docker inside itself by accessig the docker daemon installed on the host machine.
3) allow the port 8080, 22 on your Jenkins EC2 instance
