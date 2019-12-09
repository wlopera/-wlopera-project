package org.wlopera.project.command;

import org.springframework.ui.Model;
import org.wlopera.project.web.model.RegistryDTO;

public interface RegistryCommand {

	boolean execute(final RegistryDTO registry, final Model model);
}
