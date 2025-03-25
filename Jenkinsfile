
def ifbuild(a) {
    IFBUILD = ''
    if( a.startsWith('feature') || a.startsWith('release') || a.startsWith('develop' )){
        IFBUILD = true
    } else{
        IFBUILD = false
    }
    return IFBUILD
}
pipeline {
    environment {
        IFBUILD = ifbuild(BRANCH_NAME)
    }

    options{
      timeout(time:1,unit: 'HOURS')
      skipStagesAfterUnstable()
      disableConcurrentBuilds()
      buildDiscarder(logRotator(numToKeepStr: '7'))
      timestamps()
    }
    agent any
    
    stages {
        stage('Pull Source code') {
            steps {
                script {
                    checkout scm
                }
            }
        }
        stage('say Hello') {
          steps {
              script {
                  sh "echo '当前分支名称为: ${env.BRANCH_NAME}'"
                  sh "printenv"
              }
              
          }
      }  
        stage('Deploy') {
             when { environment name: 'IFBUILD', value: 'true' }
            parallel {
              stage('Deploy ruoyi-auth-service') {
                 when { changeset "ruoyi-auth/**" }
                    steps {
                        build job: "ruoyi-auth-deploy", parameters: [string(name: 'BRANCH', value: env.BRANCH_NAME)]
                    }
                }

                stage('Deploy ruoyi-api-service') {
                    when { changeset "ruoyi-api/**" }
                    steps {
                        build job: "ruoyi-api-deploy", parameters: [string(name: 'BRANCH', value: env.BRANCH_NAME)]
                    }
                }
            }
            
        }
    }
}

