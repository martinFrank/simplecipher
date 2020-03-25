package com.github.martinfrank.simplecipher.command;

import com.github.martinfrank.cli.Command;
import com.github.martinfrank.simplecipher.Code;
import com.github.martinfrank.simplecipher.SimpleCipher;

public abstract class CodeCommand extends Command<SimpleCipher> {

    private final Code code;

    public CodeCommand(SimpleCipher application, String identifier, Code code) {
        super(application, identifier);
        this.code = code;
    }

    Code getCode(){
        return code;
    }
}
