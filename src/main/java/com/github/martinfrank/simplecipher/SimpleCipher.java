package com.github.martinfrank.simplecipher;

import com.github.martinfrank.cli.*;
import com.github.martinfrank.simplecipher.command.*;

import java.util.HashMap;
import java.util.Map;

public class SimpleCipher implements CommandProvider, CommandInterpreterProvider {

    private final CommandInterpreter testCommandInterpreter;
    private final DefaultCommandList commandList;

    public static void main(String[] args){
        new SimpleCipher(Code.CESAR).getCommandInterpreter().start();
    }

    public SimpleCipher(Code code) {
        testCommandInterpreter = new CommandInterpreter(this);
        commandList = new DefaultCommandList();
        commandList.add(new SetCodeCommand(this, code));
        commandList.add(new ShowCodeCommand(this, code));
        commandList.add(new EncodeCommand(this, code));
        commandList.add(new DecodeCommand(this, code));
        commandList.add(new HelpCommand(this));
        commandList.add(new ExitCommand(this));
    }


    @Override
    public CommandList getCommands() {
        return commandList;
    }

    @Override
    public CommandInterpreter getCommandInterpreter() {
        return testCommandInterpreter;
    }



    private void encode(String[] code, String[] message) {
        Map<String,String> encode = createEncode(code);
        for(String word: message){
            StringBuffer result = new StringBuffer();
            word.chars().forEach(i -> result.append(encode.getOrDefault(""+(char)i,"?")+" "));
            System.out.print(result.toString()+"   ");
        }
    }

    private Map<String, String> createEncode(String[] code) {
        Map<String,String> result = new HashMap<>();
        for(int i = 0; i <  26; i++){
            System.out.println("i: "+i+"  "+code[i]+" -> "+(char)('A'+i));
            result.put( ""+(char)('A'+i),code[i] );
        }
        return result;
    }

    private void decode(String[] code, String[][] message) {
        Map<String,String> decode = createDecode(code);
        for(String[] word: message){
            String w = "";
            for (String cipher: word){
                String c = decode.getOrDefault(cipher,"?");
                w = w + c;
            }
            System.out.print(w+" ");
        }
    }

    private Map<String, String> createDecode(String[] code) {
        Map<String,String> result = new HashMap<>();
        for(int i = 0; i <  26; i++){
            System.out.println("i: "+i+"  "+code[i]+" -> "+(char)('A'+i));
            result.put(code[i], ""+(char)('A'+i) );

        }
        return result;
    }


}
