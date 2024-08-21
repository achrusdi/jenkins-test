pipeline {
    // agent any
    agent{
        node {
            label "linux && java17"
        }
    }

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
}
