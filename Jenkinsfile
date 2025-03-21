pipeline {
    agent any

    environment {
        SONAR_URL = 'http://80.233.35.117:9000'
        SONAR_SCANNER = 'sonar-scanner'
        DOCKERHUB_USERNAME = 'michaelroddy04'
        SONAR_PROJECT_KEY = 'sonar-project-local'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/michaeljosephroddy/user-management-service.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Static Code Analysis') {
            steps {
                sh "${SONAR_SCANNER} -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.sources=src -Dsonar.host.url=${SONAR_URL}"
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Create Docker Image') {
            steps {
                sh 'docker build -t user-management-service .'
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker tag user-management-service ${DOCKERHUB_USERNAME}/user-management-service'
                    sh 'docker push ${DOCKERHUB_USERNAME}/user-management-service'
                }
            }
        }

        // stage('Deploy to AWS') {
        //     steps {
        //         sh 'ansible-playbook -i inventory deploy.yml'
        //     }
        // }
    }
}
