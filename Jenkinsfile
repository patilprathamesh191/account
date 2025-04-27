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
    stages {
        stage('Checkout Code') {
            steps {
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/master']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/patilprathamesh191/account']]
                ])
            }
        }

        stage('Build Maven Project') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")
                    dockerImage.tag("${IMAGE_NAME}:latest") // Tagging the image as 'latest' also
                    echo "Docker image built successfully: ${dockerImage.id}"
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKERHUB_CREDENTIALS}") {
                        dockerImage.push("${BUILD_NUMBER}")   // Push the build number tag
                        dockerImage.push('latest')            // Push the latest tag
                    }
                }
            }
        }

        stage('Deploy using Docker Compose') {
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
