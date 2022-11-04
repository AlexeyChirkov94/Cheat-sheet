package ru.chirkov.cheat.sheet.sandBox.apache.common.lang;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.IntStream;

public class LeftPad {

    public static void main(String[] args) {

        System.out.println(ownLeftPad("  1"));
        System.out.println(ownLeftPad("1 "));
        System.out.println(ownLeftPad("01"));
        System.out.println(ownLeftPad("00"));

        System.out.println("_______");
        System.out.println(apacheCommonLeftPad("  1"));
        System.out.println(apacheCommonLeftPad("1 "));
        System.out.println(apacheCommonLeftPad("01"));
        System.out.println(apacheCommonLeftPad("00"));

    }

    public static String ownLeftPad(String value) {
        String trimmedValue = value.trim();

        if(trimmedValue.length() == value.length()) {
            return value;
        }

        StringBuilder newValue = new StringBuilder(trimmedValue);

        IntStream.rangeClosed(1, value.length() - trimmedValue.length())
                .forEach(it -> newValue.insert(0, "0"));
        return newValue.toString();
    }

    public static String apacheCommonLeftPad(String value) {
        return StringUtils.leftPad(value.trim(), value.length(), "0");
    }


}
