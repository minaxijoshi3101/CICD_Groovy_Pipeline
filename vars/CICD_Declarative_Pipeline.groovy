import com.poc.util.*;
pipeline {
    
    agent any
    
    /* environment 
            {
                BRANCH = 'master'
                REPO = 'hello-world-1'
                GIT_GROUP='minaxijoshi3101'
                SCM_URL=''
            } */
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
                script{
                echo 'Step to checkout the code from github'
                sh """
                cd $WORKSPACE
                echo "PWD $PWD"
                cd ..
                echo "PWD $PWD"
                """
                def example = load "/com/poc/util/checkoutSCM.groovy"
                example.call()
                }
                 }
            }
        stage ('build code') {
            steps {
                echo "build a java code using mvn"
                sh """
                cd ${REPO}
                mvn clean install package
                """
            }
            
        }
        stage ("deploy")
        {
            steps
            {
                echo "deploy war to tomcat app server"
                sh """
                  scp -i /etc/key.pem -r /root/.jenkins/workspace/pipeline_pocs/first_pippeline/webapp/target/*.war ec2-user@65.0.4.77:/app/apache-tomcat-9.0.56/webapps
                """
            }
        }
      }
}
