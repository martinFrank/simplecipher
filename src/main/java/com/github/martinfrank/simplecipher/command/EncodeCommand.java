package com.github.martinfrank.simplecipher.command;

import com.github.martinfrank.cli.Response;
import com.github.martinfrank.simplecipher.Code;
import com.github.martinfrank.simplecipher.SimpleCipher;

import java.util.Arrays;
import java.util.List;

public class EncodeCommand extends CodeCommand {

    public EncodeCommand(SimpleCipher application, Code code) {
        super(application, "encode", code);
    }

    @Override
    public Response execute(List<String> parameter) {
        StringBuffer sb = new StringBuffer();
        for (String str: parameter){
            sb.append(Arrays.toString(getCode().encode(str))).append("   ");
        }
        System.out.println(sb.toString());
        return Response.success();
    }
}
