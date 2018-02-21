node {

  def buildError = false

  //Update the project first
  stage('Checkout') {
      echo "Checking out project..."
      checkout scm
  }

  try{

    //Build Project
    stage('Build') {
        sh "./mvnw clean package -U"
    }

    switch (env.BRANCH_NAME){

      case "master":

        stage('Updating and running') {
            // sh "docker-compose up -d"
        }

        break;

    }

  } catch (Exception ex) {
    buildError = true;
    throw ex
  } finally {
    //TODO Slack msgs
  }

}
