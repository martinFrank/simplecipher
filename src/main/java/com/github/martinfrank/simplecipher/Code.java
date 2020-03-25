package com.github.martinfrank.simplecipher;

import java.util.*;
import java.util.stream.IntStream;

public class Code {

    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final String[] NUMBERS = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"};

    public static final Code PLAIN = new Code(LETTERS);
    public static final Code NUMERAL = new Code(NUMBERS);
    public static final Code ABC_REVERSE = new Code(reverse(LETTERS));
    public static final Code NUMERAL_REVERSE = new Code(reverse(NUMBERS));
    public static final Code CESAR = new Code(shift(LETTERS, 13));

    private final Map<String,String> encoding = new HashMap<>();
    private final Map<String,String> decoding = new HashMap<>();

    public Code(String[] code) {
        if(code == null || code.length != 26){
            throw new IllegalArgumentException("Code length must be exactly 26 letter!");
        }
        Set<String> set = new HashSet<>(Arrays.asList(code));
        if(set.size() != 26){
            throw new IllegalArgumentException("Code content must be unique!");
        }
        createCode(code);
    }


    public static String[] numbers() {
        return NUMBERS;
    }

    public static String[] letters() {
        return LETTERS;
    }

    public static String[] randomNumbers() {
        return random(NUMBERS);
    }

    public static String[] randomLetters() {
        return random(LETTERS);
    }

    public static String[] revertNumbers() {
        return reverse(NUMBERS);
    }

    public static String[] revertLetters() {
        return reverse(LETTERS);
    }

    public static String[] cesar(int shift) {
        return shift(LETTERS, shift);
    }

    public void createCode(String[] code) {
        encoding.clear();
        decoding.clear();
        for(int i = 0; i < 26; i ++){
            encoding.put(LETTERS[i], code[i]);
            decoding.put(code[i], LETTERS[i]);
        }
    }

    public String[] encode(String word){
        String[] code = new String[word.length()];
        for(int i = 0; i < word.length(); i ++){
            String input = (""+word.charAt(i)).toUpperCase();
            code[i] = encoding.getOrDefault(input,"?");
        }
        return code;
    }

    public String decode(String[] word){
        StringBuffer sb = new StringBuffer();
        Arrays.stream(word).forEach(c -> sb.append(decoding.getOrDefault(c.toUpperCase(),"?")));
        return sb.toString();
    }

    public void print(){
        long size = decoding.keySet().stream().mapToInt(String::length).max().orElse(1)+1;
        String format = "%"+size+"s";
        final StringBuffer encodeKeys = new StringBuffer();
        final StringBuffer encodeValues = new StringBuffer();
        decoding.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> {
            encodeKeys.append(String.format(format, entry.getKey()));
            encodeValues.append(String.format(format, entry.getValue()));
        });
        System.out.println("encode:");
        System.out.println(encodeValues.toString());
        System.out.println(encodeKeys.toString());

        final StringBuffer decodeKeys = new StringBuffer();
        final StringBuffer decodeValues = new StringBuffer();
        decoding.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            decodeKeys.append(String.format(format, entry.getKey()));
            decodeValues.append(String.format(format, entry.getValue()));
        });
        System.out.println("decode:");
        System.out.println(decodeKeys.toString());
        System.out.println(decodeValues.toString());
    }

    private static String[] reverse(String[] input){
        String[] result = new String[input.length];
        IntStream.range(0,input.length).forEach(i -> result[i] = input[input.length-i-1]);
        return result;
    }

    private static String[] shift(String[] input, int shift){
        int length = input.length;
        String[] result = new String[length];
        for(int i = 0; i < length; i ++){
            int index = (i + shift) % length;
            result[i] = input[index];
        }
        return result;
    }

    private static String[] random(String[] input){
        List<String> random = Arrays.asList(input);
        Collections.shuffle(random);
        return random.toArray(new String[0]);
    }
}
