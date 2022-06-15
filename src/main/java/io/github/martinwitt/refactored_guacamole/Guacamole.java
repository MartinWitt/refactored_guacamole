package io.github.martinwitt.refactored_guacamole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHEventPayload;
import io.quarkiverse.githubapp.event.PullRequest;

public class Guacamole implements Gitaware {

  public void runSpotless(@PullRequest.Synchronize GHEventPayload.PullRequest data) {
    
    GHCommit commit = null;
    try {
      commit = data.getPullRequest().getHead().getCommit();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (commit == null) {
      return;
    }
    try {
     Git git = Git.cloneRepository().setURI(commit.getUrl().toString()).setDirectory(getRepo().toFile()).call();
     git.checkout().setName(commit.getSHA1()).call();
     GradleSpotlessTask task = new GradleSpotlessTask();
     task.runSpotless();
      List.copyOf(new ArrayList<>());
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
    System.out.println("Spotless applied");
  }
}
