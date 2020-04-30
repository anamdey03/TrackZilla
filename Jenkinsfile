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
        
        stage('Upload Jar to Nexus') {
        	steps {
        		bat 'mvn clean deploy'
        	}
        }

        stage ('Deploy') {
            steps {

                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                  credentialsId   : 'PCF',
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {
                                  
                    bat 'C:/Users/anamdey/AppData/Roaming/CloudFoundry/cf login -a http://api.run.pivotal.io -u ' + USERNAME + ' -p ' +PASSWORD
                    bat 'C:/Users/anamdey/AppData/Roaming/CloudFoundry/cf push'
                }
            }

        }

    }

}