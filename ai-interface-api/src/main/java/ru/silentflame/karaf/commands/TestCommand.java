package ru.silentflame.karaf.commands;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;


//import org.apache.karaf.shell.console.OsgiCommandSupport;

@Command(
  scope = "silentflame",
  name = "say",
  description = "Just test command")
public class TestCommand extends OsgiCommandSupport {

  public TestCommand() {
  }

  @Override
  protected Object doExecute() throws Exception {
    System.out.println("Good night");
    return null;
  }
}

