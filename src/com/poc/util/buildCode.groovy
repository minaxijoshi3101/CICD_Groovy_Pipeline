package com.poc.util;
def call(Map pipelineParams)
{
    sh """
    cd ${REPO}
    mvn clean install package
    """    
}
