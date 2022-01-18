pipeline {
    agent any
    environment {
    
  }

    stages {
        stage('checkout SCM') {
            steps { 
                echo 'Step to checkout the code from github'
                
            }
        }
    }
}
