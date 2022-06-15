package io.github.martinwitt.refactored_guacamole;

import java.nio.file.Path;

public interface RepoState {

  public boolean isModified(Path path); 
}
