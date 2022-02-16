import com.poc.util.checkoutSCM;
import com.poc.util.pushImageDR;
import com.poc.util.buildCode;

def call(Map pipelineParams)
{
    env.BRANCH = pipelineParams.BRANCH
    env.REPO = pipelineParams.REPO
    env.REGISTRY = pipelineParams.REGISTRY
    pipeline {
        node("master"){
            stage("checkout SCM") 
            {
                echo 'Step to checkout the code from github'
                sh """
                cd $WORKSPACE
                """
                new checkoutSCM().call(pipelineParams)
            }    
            stage("static code anlysis")
            {
               echo 'step to analyse the code'
               withSonarQubeEnv('sonarqube')
                {
                sh'''
                echo "pwd is"$PWD
                cd $REPO
                echo "pwd is after repo "$PWD
                mvn sonar:sonar
                '''
                }
            }
           stage ('build code and create docker image') 
            {    
                    echo "build a java code using mvn"
                    new buildCode().call(pipelineParams)  
            }
            stage ('Push docker image to docker-registry')
            {
                echo "push docker image to ECR-docker registry"
                new pushImageDR().call(pipelineParams)
            }
        }
        node("Dev")
        {
            stage ("deploy")
            {
                    echo "deploy war to tomcat app server"
                    sh '''
                    #agents are installed in different env, we pull images there, profile will be configured in machine provide arn and arn role ko milega and 
                    #scp -i /etc/key.pem -r /root/.jenkins/workspace/pipeline_pocs/first_pippeline/webapp/target/*.war ec2-user@65.0.4.77:/app/apache-tomcat-9.0.56/webapps
                    LOGIN=$(aws ecr get-login --no-include-email --region ap-south-1)
                    $LOGIN
                    docker pull ${REGISTRY}:v1.0
                    docker rm -f hello-world-container | error=true
                    docker run -d -p 5000:8080 --name hello-world-container ${REGISTRY}:v1.0 
                    '''
            }

          }
    }
}
