package com.poc.util

def call(Map pipelineParams)
{
  docker image tag ${REPO}_image 431078536743.dkr.ecr.ap-south-1.amazonaws.com/dockerrepo:v1.0
  docker push 431078536743.dkr.ecr.ap-south-1.amazonaws.com/dockerrepo:v1.0
}
