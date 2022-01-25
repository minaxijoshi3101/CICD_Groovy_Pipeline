import com.poc.util.checkoutSCM;

pipeline {
    node(){
        stage("checkout SCM") 
        {
            echo 'Step to checkout the code from github'
            sh """
            cd $WORKSPACE
            """
            new checkoutSCM().call(pipelineParams)
        }       
       /* stage ('build code') {
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
        */
      }
}
