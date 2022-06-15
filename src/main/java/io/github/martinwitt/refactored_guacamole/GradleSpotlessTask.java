package io.github.martinwitt.refactored_guacamole;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ResultHandler;

public class GradleSpotlessTask implements SpotlessTask {

  @Override
  public void runSpotless() {
    GradleConnector connector = GradleConnector.newConnector();
    connector.forProjectDirectory(getRepo().toFile());
    var gradle = connector.connect();
    BuildLauncher build = gradle.newBuild();
    build.forTasks("spotlessApply").run(new ResultHandler<Void>() {
      @Override
      public void onComplete(Void arg0) {
        System.out.println("Spotless applied");
      }
      @Override
      public void onFailure(GradleConnectionException arg0) {
        System.out.println("Spotless failed");
        arg0.printStackTrace();
      }
    });
  }
  
}
