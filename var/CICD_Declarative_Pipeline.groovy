import com.demo.util.checkoutSCM;
pipeline {
    agent any
    environment {
    BRANCH = env.BRANCH
    REPO = 'hello-world-1'
    GIT_GROUP='minaxijoshi3101'
  }
    parameters {
        choice {
                name: 'BuildType',
                choices: 'SNAPSHOT\nRELEASE',
                description: 'choose the build type'
        }
    }
    tools{
        jdk ''
        maven ''
    }
    stages {
        stage('checkout SCM') {
            steps { 
                echo 'Step to checkout the code from github'
                env.SCM_URL="git@github.com:"+env.GIT_GROUP+"/"+env.REPO+".git"
                echo "Code checkout from SCM Repo"
                sh ''' 
                rm -rf ${REPO}
                git clone --single-branch --branch ${BRANCH} ${SCM_URL}
                ''' 
                echo "Checkout is completed!"
                }
            }
        }
    }
}
