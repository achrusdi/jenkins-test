pipeline {
    // agent any
    agent {
        node {
            label 'linux && java21'
        }
    }

    stages {
        // stage('Checkout') {
        //     steps {
        //         git branch: 'main', url: 'https://github.com/achrusdi/jenkins-test.git'
        //     }
        // }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh './mvnw clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Hello Test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Hello Deploy'
            }
        }
    }

    post {
        always {
            echo 'This will always run'
        }

        success {
            echo 'This will run only if successful'
        }

        failure {
            echo 'This will run only if failed'
        }

        cleanup {
            echo 'This will always run cleanup'
        }
    }
}
