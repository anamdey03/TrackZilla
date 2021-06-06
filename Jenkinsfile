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

    }

}
