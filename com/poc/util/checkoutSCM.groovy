def call(Map pipelineParams) {

      SCM_URL='git@github.com:'+env.GIT_GROUP+'/'+env.REPO+'.git';
      echo "Code checkout from SCM Repo ${SCM_URL}"
      sh """
      rm -rf ${REPO}
      git clone --single-branch --branch ${BRANCH} ${SCM_URL}
      """ 
      echo "Checkout is completed!"

}
