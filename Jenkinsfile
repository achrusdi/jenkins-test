pipeline {
    // agent any
    agent{
        node {
            label "linux && java21"
        }
    }

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }

    post{
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
