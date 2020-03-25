package com.github.martinfrank.simplecipher.command;

import com.github.martinfrank.cli.Command;
import com.github.martinfrank.cli.Response;
import com.github.martinfrank.simplecipher.SimpleCipher;

import java.util.List;

public class ExitCommand extends Command<SimpleCipher> {

    public ExitCommand(SimpleCipher application) {
        super(application, "exit");
    }

    @Override
    public Response execute(List<String> parameter) {
        getApplication().getCommandInterpreter().stop();
        return Response.success();
    }
}
