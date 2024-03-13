def buildImage() {
    echo "building the docker image and pushing it to our repository"
    withCredentials([usernamePassword(credentialsId: 'Dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t hrtithik9876789/app:1.0 .'
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
        sh 'docker push hrtithik9876789/app:1.1'
    }
} 

def deployApp() {
    echo 'deploying the application...'
    sshagent(['3.85.172.81']) {
                    sh 'ssh -o StrictHostKeyChecking=no ubuntu@ip-172-31-57-210'
                     // Start Minikube
                    sh 'minikube start --driver=docker'

        // Apply deployment YAML
                    sh 'kubectl apply -f deployment.yaml'

        // Port forward
                    sh 'nohup kubectl port-forward svc/todolistapp-service 3000:3000 --address 0.0.0.0 >/dev/null 2>&1 &'
                
                        }
} 

return this
