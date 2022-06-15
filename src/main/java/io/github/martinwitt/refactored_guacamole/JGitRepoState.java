package io.github.martinwitt.refactored_guacamole;

import java.nio.file.Path;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class JGitRepoState implements RepoState {

  @Override
  public boolean isModified(Path path) {
    try (Repository repository = new FileRepositoryBuilder().setWorkTree(path.toFile())
        .findGitDir().readEnvironment().build();
        Git git = new Git(repository)) {
      return git.status().call().isClean();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  
}
