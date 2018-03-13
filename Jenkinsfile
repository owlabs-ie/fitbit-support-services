node {

  def buildError = false

  //Update the project first
  stage('Checkout') {
      echo "Checking out project..."
      checkout scm
  }

  try{

    switch (env.BRANCH_NAME){

      case "develop":

        //Build Project
        stage('Build') {
            sh "./mvnw clean package -DskipTests -U"
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
