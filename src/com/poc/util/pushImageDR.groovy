package com.poc.util

def call(Map pipelineParams)
{
  sh '''
  docker logout public.ecr.aws
  docker image tag dockerrepo ${REGISTRY}:v1.0
  LOGIN=$(aws ecr get-login --no-include-email --region ap-south-1)
  $LOGIN
  docker push ${REGISTRY}:v1.0
  '''
}
