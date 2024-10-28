pipeline {
    agent any

    tools{
        maven "M2_HOME"
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials-id')
        MYSQL_CREDENTIALS = credentials('mysql-credentials')
        

    }
 stages {
       stage('GIT') {
            steps {
                git branch: "MohamedMrabet-5ARCTIC4-G2",
                url: "https://github.com/Achreef01/5ARCTIC4-G2-Kaddem.git"
            }
        }
        stage('Scan') {
            dir('kaddem') {
            steps {
                withSonarQubeEnv(installationName: 'sq1'){
                                 
                sh 'mvn sonar:sonar'
                                 }
            }
            }
        }
    



        stage('Build Backend Docker Image') {
            steps {
                dir('kaddem'){
                sh 'docker build -t kaddem-backend .'
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker tag kaddem-backend hammamrabet/mohamed_mrabet_5arctic4_g2:$BUILD_NUMBER'
                sh 'docker push hammamrabet/mohamed_mrabet_5arctic4_g2:$BUILD_NUMBER'
                }
            }
        }

        
        stage('Deploy Application') {
            steps {
                // Create Docker network
                sh 'docker network create my-network || true'

                // Stop and remove old containers
                sh 'docker stop mysql_db || true'
                sh 'docker rm mysql_db || true'
                sh 'docker stop backend_ctr || true'
                sh 'docker rm backend_ctr || true'
                

                // Start MySQL container
                sh 'docker run -d --network my-network -p 3306:3306 --name mysql_db -e MYSQL_ROOT_PASSWORD=$MYSQL_CREDENTIALS_PSW mysql:latest'

                // Start Backend container
                sh 'docker run -d --network my-network -p 8089:8089 --name backend_ctr hammamrabet/mohamed_mrabet_5arctic4_g2:$BUILD_NUMBER'

                // Start Frontend container

               






            }
        }

    

        
        
       


    }
}