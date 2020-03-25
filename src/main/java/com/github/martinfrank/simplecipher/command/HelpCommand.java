package com.github.martinfrank.simplecipher.command;

import com.github.martinfrank.cli.Command;
import com.github.martinfrank.cli.Response;
import com.github.martinfrank.simplecipher.SimpleCipher;

import java.util.List;

public class HelpCommand extends Command<SimpleCipher> {

    public HelpCommand(SimpleCipher application) {
        super(application, "help");
    }

    @Override
    public Response execute(List<String> parameter) {
        System.out.println("these commands are available");
        getApplication().getCommands().asList().forEach(
                c -> System.out.println("command: "+c.getIdentifier()));
        return Response.success();
    }
}
