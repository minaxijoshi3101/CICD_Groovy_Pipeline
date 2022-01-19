def SCM_URL
pipeline {
    agent any
    
    environment 
            {
                BRANCH = 'master'
                REPO = 'hello-world-1'
                GIT_GROUP='minaxijoshi3101'
                
            }
   /* parameters {
        choice {
                name: 'BuildType',
                choices: 'SNAPSHOT\nRELEASE',
                description: 'choose the build type'
        }
    }*/
    tools{
        jdk 'openjdk-1.11.0'
        maven 'maven'
    }
    stages {
        stage('checkout SCM') {
            steps { 
                echo 'Step to checkout the code from github'
                script {
                    SCM_URL='git@github.com:'+env.GIT_GROUP+'/'+env.REPO+'.git';
   
                     }
                echo "Code checkout from SCM Repo"+$SCM_URL
                sh ''' 
                rm -rf ${REPO}
                git clone --single-branch --branch ${BRANCH} ${SCM_URL}
                ''' 
                echo "Checkout is completed!"
                }
            }
        stage ('build code') {
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
