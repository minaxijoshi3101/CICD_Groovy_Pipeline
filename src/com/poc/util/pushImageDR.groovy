package com.poc.util

def call(Map pipelineParams)
{
  sh '''
  docker image tag ${REPO}_image  env.REGISTRY = pipelineParams.REGISTRY:v1.0
  docker push pipelineParams.REGISTRY:v1.0
  '''
}
