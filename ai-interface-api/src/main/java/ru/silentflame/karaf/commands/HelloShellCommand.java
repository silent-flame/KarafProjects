
package ru.silentflame.karaf.commands;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

@Command(scope = "test", name = "hello", description = "Says hello")
public class HelloShellCommand extends OsgiCommandSupport {
  @Override
  protected Object doExecute() throws Exception {
    System.out.println("Executing Hello command");
    return null;
  }
}