
pipeline {
    agent any
    
    environment 
            {
                BRANCH = env.BRANCH
                REPO = env.REPO
            }
   /* parameters {
        choice {
                name: 'BuildType',
                choices: 'SNAPSHOT\nRELEASE',
                description: 'choose the build type'
        }
    }*/
    tools{
        jdk ''
        maven ''
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
        stage ('buikd code') {
            steps {
                echo "build a java code using mvn"
                sh '''
                mvn clean install package
                '''
            }
            
        }
        stage ("deploy")
        {
            steps
            {
                echo "deploy war to tomcat app server"
                sh '''
               scp -i /etc/key.pem -r /root/.jenkins/workspace/pipeline_pocs/first_pippeline/webapp/target/*.war ec2-user@65.0.4.77:/app/apache-tomcat-9.0.56/webapps
                '''
            }
        }
      }
}
