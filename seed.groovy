def propeties = new Properties()
streamFileFromWorkspace('seed.properties').withStream {
  InputStream it -> properties.load(it)
}
job("${SEED_PROJECT}-${SEED_BRANCH}-build") {
   description "Building the ${BRANCH} branch."
   parameters {
      stringParam('COMMIT', 'HEAD', 'Commit to build')
   }
   scm {
      git {
         remote {
             url PROJECT_SCM_URL
             branch "origin/${BRANCH}"
         }
         extensions {
             wipeOutWorkspace()
             localBranch BRANCH
         }
      }
   }
   steps {
      shell "Look! I'm building ${BRANCH}!"
   }
}
