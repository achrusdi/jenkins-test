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
}
