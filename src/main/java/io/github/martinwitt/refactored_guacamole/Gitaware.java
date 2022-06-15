package io.github.martinwitt.refactored_guacamole;

import java.nio.file.Path;

public interface Gitaware {
  

  default Path getRepo() {
    return Path.of("./refactored_guacamole/");
  }
}
