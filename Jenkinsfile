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
                    // Build the image with the current build number
                    dockerImage = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}")

                    // Tag the image as 'latest' too
                    dockerImage.tag("${IMAGE_NAME}:latest")

                    // Output the image ID for verification
                    echo "Docker image built successfully: ${dockerImage.id}"
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    // Push the image to DockerHub with both the build number and 'latest' tags
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKERHUB_CREDENTIALS}") {
                        // Push the image with the build number tag
                        dockerImage.push("${BUILD_NUMBER}")

                        // Push the image with the 'latest' tag
                        dockerImage.push('latest')

                        echo "Docker image pushed to DockerHub with tags ${BUILD_NUMBER} and 'latest'."
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
