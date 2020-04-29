pipeline {

    agent any

	tools{
		maven "Maven-3.6.3"
		jdk "JDK"
	}

    stages {

        stage ('Build') {
            steps {
            withMaven(maven: 'Maven-3.6.3') {
               bat 'mvn clean package'
               }
            }
        }

        stage ('Deploy') {
            steps {

                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                  credentialsId   : 'PCF_LOGIN',
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {

                    bat 'C:/Users/anamdey/AppData/Roaming/CloudFoundry/cf login -a http://api.run.pivotal.io -u anamitradey.ece@gmail.com -p Ana@1117'
                    bat 'C:/Users/anamdey/AppData/Roaming/CloudFoundry/cf push'
                }
            }

        }

    }

}