package com.github.martinfrank.simplecipher.command;

import com.github.martinfrank.cli.Command;
import com.github.martinfrank.cli.Response;
import com.github.martinfrank.simplecipher.Code;
import com.github.martinfrank.simplecipher.SimpleCipher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DecodeCommand extends CodeCommand {

    public DecodeCommand(SimpleCipher application, Code code) {
        super(application, "decode", code);
    }

    @Override
    public Response execute(List<String> parameter) {
        List<String> clean = new ArrayList<>();
        parameter.forEach(s -> clean.addAll(Arrays.asList(s.split(","))) );
        System.out.println(clean);
        String decoded = getCode().decode(clean.toArray(new String[0]));
        System.out.println(decoded);
        return Response.success();
    }
}
