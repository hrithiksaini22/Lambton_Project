def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'Dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t hrtithik9876789/app:1.0 .'
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
        sh 'docker push hrtithik9876789/app:1.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
    sshagent(['3.85.172.81']) {
                    sh 'ssh -o StrictHostKeyChecking=no ubuntu@44.202.116.136 "cd /home/ubuntu && touch yay"'
                
                        }
} 

return this
