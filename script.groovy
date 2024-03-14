def buildImage() {
    echo "building the docker image and pushing it to our repository"
    def imageTag = 'hrtithik9876789/app:1.0'  // Define the image tag

    // Check if the image already exists in the Docker Hub repository
    def imageExists = sh(script: "docker pull $imageTag", returnStatus: true) == 0

    // If the image exists, skip the build process
    if (imageExists) {
        echo "Image $imageTag already exists in the repository. Skipping build."
        return
    }

    // If the image does not exist, proceed with the build process
    withCredentials([usernamePassword(credentialsId: 'Dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t hrtithik9876789/app:1.0 .'
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
        sh 'docker push hrtithik9876789/app:1.0'
    }
}


def deployApp() {
    echo 'deploying the application...'
    sshagent(['3.85.172.81']) {
        // SSH into the EC2 instance
        sh 'ssh -o StrictHostKeyChecking=no ubuntu@ip-172-31-57-210 << EOF'

        // Start Minikube
        minikube start --driver=docker

        // Apply deployment YAML
        kubectl apply -f deployment.yaml

        // Port forward
        nohup kubectl port-forward svc/todolistapp-service 3000:3000 --address 0.0.0.0 >/dev/null 2>&1 &
        
        EOF
    }
}
return this
