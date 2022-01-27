package com.poc.util

def call(Map pipelineParams) {
    if(pipelineParams.APP_TYPE == "JAVA")
    {
        sh '''
            cd $REPO
            #mvn deploy copy the package to remote repo, docker registry
            mvn deploy -P docker -Ddocker.host=${DOCKER_HOST} -Ddocker.registry.name=${DOCKER_REGISTRY} -Dmaven.test.skip=true
        '''
    }
}
