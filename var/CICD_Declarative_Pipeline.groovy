import com.demo.util.checkoutSCM;
pipeline {
    agent any
    environment {
    BRANCH = env.BRANCH
    REPO = env.REPO
  }

    stages {
        stage('checkout SCM') {
            steps { 
                echo 'Step to checkout the code from github'
                env.SCM_URL="git@github.com:"+pipelineParams.GIT_GROUP+"/"+pipelineParams.REPO+".git"
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
