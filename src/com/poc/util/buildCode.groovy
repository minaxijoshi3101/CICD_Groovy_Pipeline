package com.poc.util;
def call(Map pipelineParams)
{
    sh """
    cd ${REPO}
    export MAVEN_HOME="/root/apache-maven-3.0.5/bin/mvn"
    mvn clean install package
    docker image build -t dockerrepo .
    """    
}
