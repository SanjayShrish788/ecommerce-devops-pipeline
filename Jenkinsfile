pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }
    stages {
        stage('1 - Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/SanjayShrish788/ecommerce-devops-pipeline.git'
            }
        }
        stage('2 - Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('3 - Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('4 - Docker Build') {
            steps {
                sh 'docker build -t ecommerce-app:${BUILD_NUMBER} .'
            }
        }
        stage('5 - Deploy') {
            steps {
                sh 'docker-compose down && docker-compose up -d --build'
            }
        }
    }
    post {
        success {
            echo '✅ Deployment successful'
        }
        failure {
            echo '❌ Pipeline failed'
        }
    }
}
