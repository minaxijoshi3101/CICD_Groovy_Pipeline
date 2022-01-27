import com.poc.util.checkoutSCM;
package com.poc.util.buildCode;
def call(Map pipelineParams)
{
    env.BRANCH = pipelineParams.BRANCH
    env.REPO = pipelineParams.REPO
    env.REGISTRY = pipeline.REGISTRY 
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
           stage ('build code and create docker image') 
            {    
                    echo "build a java code using mvn"
                    new buildCompileApp().call(pipelineParams)  
            }
            
            stage ("deploy")
            {
                    echo "deploy war to tomcat app server"
                    sh """
                      scp -i /etc/key.pem -r /root/.jenkins/workspace/pipeline_pocs/first_pippeline/webapp/target/*.war ec2-user@65.0.4.77:/app/apache-tomcat-9.0.56/webapps
                    """

            }

          }
    }
}
