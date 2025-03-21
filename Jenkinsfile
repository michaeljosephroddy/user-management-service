pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/michaeljosephroddy/user-management-service.git'
            }
        }

        stage('Build with Maven and Static Code Analysis') {
            steps{
                withSonarQubeEnv('SonarQube') {
                    sh "mvn clean verify sonar:sonar -Dsonar.projectKey=sonar-project-local -Dsonar.projectName='sonar-project-local'"
                }
            }
        }

        stage('Create Docker Image and Push Docker Image to DockerHub') {
            steps {
                sh 'docker build -t user-management-service .'
                 withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker tag user-management-service michaelroddy04/user-management-service'
                    sh 'docker push michaelroddy04/user-management-service'
                 }
            }
        }


    }
}
