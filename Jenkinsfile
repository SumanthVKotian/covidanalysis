pipeline{
	agent any
	
	stages{
		stage('compile stage'){
			steps{
				withMaven(maven : 'maven_3_5_0') {
					sh 'mvn clean compile'
					}
			}
			
			
			stage ('Testing stage') {
				withMaven(maven : 'maven_3_5_0'){
					sh 'mvn test'
					}
			}
			
			
			stage ('Deploy stage') {
				withMaven(maven : 'maven_3_5_0'){
					sh 'mvn deploy'
					}
			}
		}
	}
}
