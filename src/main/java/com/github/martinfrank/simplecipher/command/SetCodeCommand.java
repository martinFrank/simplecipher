package com.github.martinfrank.simplecipher.command;

import com.github.martinfrank.cli.Command;
import com.github.martinfrank.cli.Response;
import com.github.martinfrank.simplecipher.Code;
import com.github.martinfrank.simplecipher.SimpleCipher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetCodeCommand extends CodeCommand{

    public SetCodeCommand(SimpleCipher application, Code code) {
        super(application, "setcode", code);
    }

    @Override
    public Response execute(List<String> parameter) {
        String[] newCode = null;
        List<String> code = new ArrayList<>();
        parameter.forEach(s -> code.addAll(Arrays.asList(s.split(","))) );
        if(code.size() == 26){
            newCode = code.toArray(new String[0]);
        }else {
            if (parameter.size() == 1) {
                if ("numbers".equalsIgnoreCase(parameter.get(0))) {
                    newCode = Code.numbers();
                }
                if ("letters".equalsIgnoreCase(parameter.get(0))) {
                    newCode = Code.letters();
                }
            }

            if (parameter.size() == 2) {
                if ("random".equalsIgnoreCase(parameter.get(0))) {
                    if ("numbers".equalsIgnoreCase(parameter.get(1))) {
                        newCode = Code.randomNumbers();
                    }
                    if ("letters".equalsIgnoreCase(parameter.get(1))) {
                        newCode = Code.randomLetters();

                    }
                }
                if ("revert".equalsIgnoreCase(parameter.get(0))) {
                    if ("numbers".equalsIgnoreCase(parameter.get(1))) {
                        newCode = Code.revertNumbers();
                    }
                    if ("letters".equalsIgnoreCase(parameter.get(1))) {
                        newCode = Code.revertLetters();
                    }
                }
                if ("cesar".equalsIgnoreCase(parameter.get(0))) {
                    try {
                        int shift = Integer.parseInt(parameter.get(1));
                        newCode = Code.cesar(shift);
                    } catch (NumberFormatException e) {
                        return Response.fail("" + e.toString() + "could not set code " + parameter);
                    }
                }
            }
        }

        try {
            getCode().createCode(newCode);
            System.out.println("code successfully set "+Arrays.asList(newCode));
            return Response.success();
        } catch (Exception e) {
            return Response.fail(""+e.toString()+"could not set code "+parameter);
        }

    }

}
