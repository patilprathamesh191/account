pipeline {
    agent any

    tools {
        maven 'maven_3.9.9'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout([
                    $class: 'GitSCM',
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
                    bat 'docker build -t microservice/account .'
                    echo "Docker image built with tag 'latest'."
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'docker_jenkin_access_token_2', variable: 'docker_jenkin_access_token_2')]) {
                        bat 'docker login -u 27871810 -p ${docker_jenkin_access_token_2}'
                    }
                    bat 'docker push microservice/account'
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
