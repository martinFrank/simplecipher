package com.github.martinfrank.simplecipher.command;

import com.github.martinfrank.cli.Response;
import com.github.martinfrank.simplecipher.Code;
import com.github.martinfrank.simplecipher.SimpleCipher;

import java.util.List;

public class ShowCodeCommand extends CodeCommand{

    public ShowCodeCommand(SimpleCipher application, Code code) {
        super(application, "showcode", code);
    }

    @Override
    public Response execute(List<String> parameter) {
        getCode().print();
        return Response.success();
    }
}
