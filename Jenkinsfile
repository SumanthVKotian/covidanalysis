pipeline{
	agent any
	
	stages{
		stage('Compile stage'){
			steps{
				withMaven(maven : 'maven_3_9_5') {
					sh 'mvn clean compile'
					}
			}
		}
		stage('Testing stage'){
			steps{
				withMaven(maven : 'maven_3_9_5') {
					sh 'mvn test'
					}
			}
		}
		stage('Deploy stage'){
			steps{
				withMaven(maven : 'maven_3_9_5') {
					sh 'mvn deploy'
					}
			}
		}
			
		
	}
}
