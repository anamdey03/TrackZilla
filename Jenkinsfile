pipeline {

    agent any

	tools{
		maven "Local Maven"
		jdk "JDK"
	}

    stages {

        stage ('Build') {
            steps {
            withMaven(maven: "Local Maven") {
               sh 'mvn clean package'
               }
            }
        }

        stage ('Deploy') {
            steps {

                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                  credentialsId   : 'PCF_LOGIN',
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {

                    sh '/usr/local/bin/cf login -a http://api.run.pivotal.io -u $USERNAME -p $PASSWORD'
                    sh '/usr/local/bin/cf push'
                }
            }

        }

    }

}