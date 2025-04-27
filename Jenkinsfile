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
                    userRemoteConfigs: [[url: 'https://github.com/patilprathamesh191/account']]])
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
                    // Build Docker image with the unique build number tag
                    dockerImage = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")

                    // Tag the image as 'latest'
                    dockerImage.tag("${IMAGE_NAME}:latest")

                    echo "Docker image built with build number: ${BUILD_NUMBER} and 'latest' tag."
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    // Authenticate with DockerHub and push the image
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKERHUB_CREDENTIALS}") {
                        // Ensure that both build number and 'latest' tags are pushed
                        echo "Pushing Docker image with build number: ${BUILD_NUMBER}"
                        dockerImage.push("${BUILD_NUMBER}")

                        echo "Pushing Docker image with 'latest' tag."
                        dockerImage.push('latest')
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
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
