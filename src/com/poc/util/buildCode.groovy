package com.poc.util;
def call(Map pipelineParams)
{
    sh """
    cd ${REPO}
    mvn clean install package
    docker image build -t ${REPO}_image .
    """    
}
