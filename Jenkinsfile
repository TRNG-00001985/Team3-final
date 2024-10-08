pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_VERSION = '1.29.2'
        GIT_REPO = 'https://github.com/TRNG-00001985/Team3-final.git'
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')
        RDS_USERNAME = credentials('RDS_USERNAME')
        DB_PASSWORD = credentials('DB_PASSWORD')
        DB_URL = credentials('DB_URL')
        SMTP_CREDENTIALS = credentials('SMTP')
        RZP_KEY = credentials('RZP_KEY')
        RZP_SECRET = credentials('RZP_SECRET')
            
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: "${GIT_REPO}"
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                     withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                        def services = sh(script: "find . -maxdepth 1 -type d -name '*-service' -not -name '*@tmp' -not -name 'prometheus' -not -name '.git' -not -name 'grafana' -not -name '.'", returnStdout: true).trim().split('\n')
                    for (service in services) {
                        dir(service.trim()) {
                            def serviceName = service.trim().replaceAll('./', '')
                            sh "docker build -t ${DOCKER_HUB_USERNAME}/${serviceName}:${BUILD_NUMBER} ."
                        }
                    }
                     }
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                        sh "echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin"
                        def services = sh(script: "find . -maxdepth 1 -type d -name '*-service' -not -name '*@tmp' -not -name 'prometheus' -not -name '.git' -not -name 'grafana' -not -name '.'", returnStdout: true).trim().split('\n')
                            
                        for (service in services) {
                            def serviceName = service.trim().replaceAll('./', '')
                            sh "docker push ${DOCKER_HUB_USERNAME}/${serviceName}:${BUILD_NUMBER}"
                        }
                    }
                }
            }
        }
         stage('Create .env file') {
            steps {
                script {

                withCredentials([usernamePassword(credentialsId: 'SMTP', usernameVariable: 'SMTP_USERNAME', passwordVariable: 'SMTP_PASSWORD')]) {

                    // Create .env file with database credentials
                    sh """
                    echo "DB_URL=${DB_URL}" > .env
                    echo "RDS_USERNAME=${RDS_USERNAME}" >> .env
                    echo "DB_PASSWORD=${DB_PASSWORD}" >> .env
                    echo "SMTP_USERNAME=${SMTP_USERNAME}" >> .env
                    echo "SMTP_PASSWORD=${SMTP_PASSWORD}" >> .env
                    echo "RZP_KEY=${RZP_KEY}" >> .env
                    echo "RZP_SECRET=${RZP_SECRET}" >> .env
                    """
                    }
                }
            }
        }


        stage('Update Docker Compose File') {
            steps {
                script {

                     
                    def composeFile = readFile 'docker-compose.yml'
                    def services = sh(script: "ls -d */ | grep -v '^prometheus/'", returnStdout: true).trim().split('\n')
                    for (service in services) {
                        def serviceName = service.trim().replaceAll('./', '')

                        composeFile = composeFile.replaceAll("(image: .*${serviceName}:).*", "\$1${BUILD_NUMBER}")
                    }
                    writeFile file: 'docker-compose.yml', text: composeFile
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh "docker-compose down || true"
                sh "docker-compose up -d"
            }
        }
    }

    post {
        always {
            sh "docker-compose logs"
        }
    }
}
