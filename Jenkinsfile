pipeline {
    agent any
    tools {
            maven "maven_3.9.9"
        }
    environment {
            DOCKERHUB_CREDENTIALS = 'docker_jenkin_access_token_2'  // Jenkins Credentials ID
            DOCKERHUB_USERNAME = '27871810'
            IMAGE_NAME = '27871810/springboot-app'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/patilprathamesh191/account']]])
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    dockerImage = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")
                    echo "Docker image built: ${dockerImage.id}"
                }
            }
        }
        stage('Push Docker Image') {
                    steps {
                        script {
                            docker.withRegistry('https://index.docker.io/v1/', "${DOCKERHUB_CREDENTIALS}") {
                                dockerImage.push()
                                dockerImage.push('latest')  // Optional: Tag as latest
                            }
                        }
                    }
        }
        stage('Deploy') {
            steps {
                bat '''
                    docker-compose down
                    docker-compose pull
                    docker-compose up -d
                '''
            }
        }
    }
}