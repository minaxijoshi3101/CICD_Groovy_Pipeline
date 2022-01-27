package com.poc.util

def call(Map pipelineParams)
{
  sh '''
  docker image tag ${REPO}_image  ${REGISTRY}/${REPO}:v1.0
  docker push ${REGISTRY}/${REPO}:v1.0
  '''
}
