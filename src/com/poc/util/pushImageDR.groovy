package com.poc.util

def call(Map pipelineParams)
{
  sh '''

  docker image tag dockerrepo ${REGISTRY}:v1.0
  docker push ${REGISTRY}:v1.0
  '''
}
