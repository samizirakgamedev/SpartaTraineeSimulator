package com.purerangers;

import java.util.*;

public class InputManager {
    // Initialises the 'Scanner'.
    private static Scanner scanner = new Scanner(System.in);
    // Global variable to store the desired length of the simulation.
    private static int monthsOfSimulation = 0;
    // List of stings that are allowed within the input we are converting.
    final static List<String> textualAllowedStrings = Arrays.asList("and", "zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
            "ninety", "hundred", "thousand", "million", "billion", "trillion");
    final static List<String> boolAllowedStrings = Arrays.asList("yes", "true", "okay", "ok", "sure","please", "okie dokie");
    final static List<String> boolFalseStrings = Arrays.asList("no", "false", "no thank you");

    // Getter for getting a String from the user.
    public static String getSimulationDuration()
    {
        int simulationDuration = 0;
        String simString = "0";
        try{
            simString = convertTextualNumbersInString(scanner.nextLine());
        }catch (Exception e){
            handleInputExceptions(e);
            var i = scanner.nextLine();
            simString = convertTextualNumbersInString(i);
            getSimulationDuration();
        }
      return simString;
    }

    public static String convertTextualNumbersInString(String inputText) {
        // Splits text into words and deals with hyphenated numbers.
        // Used linked list due to manipulation during processing.
        List<String> words = new LinkedList<>(cleanAndTokenizeText(inputText.toLowerCase()));

        // Calls method to replace all the textual numbers with actual integers.
        words = replaceTextualNumbers(words);

        // Calls method to convert input into months if it detects a string such as "years" after the number.
        try
        {
            monthsOfSimulation = convertInputToMonths(words);
        }
        catch (Exception e)
        {
            handleInputExceptions(e);
        }

        // Calls method that put spaces back in and returns the string.
        // This should be the same as input text except from textual numbers that should now be ints.
        return wordListToString(words);
    }
    // Getter to retrieve the length of the simulation from the class.
    public static int getMonthsOfSimulation() {
        return monthsOfSimulation;
    }
    // Removes any symbols and spaces their may be in the string ready for processing.
    // E.g. twenty-two -> twenty two
    private static List<String> cleanAndTokenizeText(String sentence) {
        List<String> words = new LinkedList<String>(Arrays.asList(sentence.split(" ")));
        // Removes hyphenated textual numbers
        for (int i = 0; i < words.size(); i++) {
            String str = words.get(i);
            if (str.contains("-")) {
                List<String> splitWords = Arrays.asList(str.split("-"));
                // Just checks the first word is a textual number. Caters for
                // "twenty-five," without having to strip the comma
                if (splitWords.size() > 1 && textualAllowedStrings.contains(splitWords.get(0))) {
                    words.remove(i);
                    words.addAll(i, splitWords);
                }
            }
        }
        return words;
    }
    // Method that is called to process each word in the string by grouping them and replace the textual numbers with their respective number/s.
    // This list of words is then returned following processing.
    private static List<String> replaceTextualNumbers(List<String> words) {

        // List that will hold each group of textual numbers being processed together. e.g.
        // "one" or "five hundred and two"
        List<String> processingList = new LinkedList<>();
        // This loop will execute so long as there are still words or the processing list isn't empty.
        int i = 0;
        while (i < words.size() || !processingList.isEmpty()) {

            // Checks for sentences only containing one word (that is a number)
            String word = "";
            if (i < words.size()) {
                word = words.get(i);
            }

            // Striping the word of all punctuation using a regular expression to make it easier to process.
            // This includes normalising the word and making it all lowercase.
            String wordStripped = word.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();

            // Conditional statement that checks for and removes "and" words by themselves or "and"s at start of number.
            if (textualAllowedStrings.contains(wordStripped) && !(processingList.size() == 0 && wordStripped.equals("and"))) {
                words.remove(i); // remove from main list, will process later
                processingList.add(word);
            } else if (processingList.size() > 0) { // If there are no more textual words to process.

                // If "and" is the last word, add it back in to original list.
                String lastProcessedWord = processingList.get(processingList.size() - 1);
                if (lastProcessedWord.equals("and")) {
                    words.add(i, "and");
                    processingList.remove(processingList.size() - 1);
                }
                // Calls the method that now takes processing list of textual words and replaces them with numbers.
                // The long that the method returns is then converted back to a String.
                String wordAsDigits = String.valueOf(convertWordsToNum(processingList));
                // Calls the method that takes the processing list and our string number and adds any punctuation from the original string back to the number.
                wordAsDigits = retainPunctuation(processingList, wordAsDigits);
                words.add(i, String.valueOf(wordAsDigits));
                // Clears the processing list at the end of the loop.
                processingList.clear();
                i += 2;
            } else {
                i++;
            }
        }
        return words;
    }
    // Takes the list containing the non-textual number and checks (based on the second string in the list) if the number needs converting to months.
    public static int convertInputToMonths(List<String> words) {
        int totalMonths;
        if (words.size() <= 1) {
            totalMonths = Integer.parseInt(words.get(0));
            words.add("months");
            return totalMonths;
        }
        if (words.get(1).contains("days")){
            totalMonths = Integer.parseInt(words.get(0)) / 30;
            words.set(0, String.valueOf(totalMonths));
        } else if (words.get(1).contains("weeks")){
            double weeks = Double.parseDouble(words.get(0)) / 4.34524;
            totalMonths = Integer.parseInt(String.valueOf(Math.round(weeks)));
            words.set(0, String.valueOf(totalMonths));
        } else if (words.get(1).contains("years")){
            totalMonths = Integer.parseInt(words.get(0)) * 12;
            words.set(0, String.valueOf(totalMonths));
        } else{
                totalMonths = Integer.parseInt(words.get(0));
        }
        words.set(1, "months");
        return totalMonths;
    }
    // Method that is called to build a string back together from a list of strings. This includes spaces.
    private static String wordListToString(List<String> list) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (i == 0 && str != null) {
                result.append(list.get(i));
            } else if (str != null) {
                result.append(" ").append(list.get(i));
            }
        }
        return result.toString();
    }
    // Method for adding back in any punctuation to the string that has now been converted into a numerical string.
    private static String retainPunctuation(List<String> processingList, String wordAsDigits) {
        // Checks last word first for punctuation.
        String lastWord = processingList.get(processingList.size() - 1);
        char lastChar = lastWord.trim().charAt(lastWord.length() - 1);
        if (!Character.isLetter(lastChar)) {
            wordAsDigits += lastChar;
        }
        // Checks the first word for any punctuation.
        String firstWord = processingList.get(0);
        char firstChar = firstWord.trim().charAt(0);
        if (!Character.isLetter(firstChar)) {
            wordAsDigits = firstChar + wordAsDigits;
        }
        return wordAsDigits;
    }
    // Method for taking a textual number string and converting it into a number.
    // E.g. twenty five -> 25
    private static long convertWordsToNum(List<String> words) {
        long finalResult = 0;
        long intermediateResult = 0;
        for (String str : words) {
            // clean up string for easier processing
            str = str.toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
            if (str.equalsIgnoreCase("zero")) {
                intermediateResult += 0;
            } else if (str.equalsIgnoreCase("one")) {
                intermediateResult += 1;
            } else if (str.equalsIgnoreCase("two")) {
                intermediateResult += 2;
            } else if (str.equalsIgnoreCase("three")) {
                intermediateResult += 3;
            } else if (str.equalsIgnoreCase("four")) {
                intermediateResult += 4;
            } else if (str.equalsIgnoreCase("five")) {
                intermediateResult += 5;
            } else if (str.equalsIgnoreCase("six")) {
                intermediateResult += 6;
            } else if (str.equalsIgnoreCase("seven")) {
                intermediateResult += 7;
            } else if (str.equalsIgnoreCase("eight")) {
                intermediateResult += 8;
            } else if (str.equalsIgnoreCase("nine")) {
                intermediateResult += 9;
            } else if (str.equalsIgnoreCase("ten")) {
                intermediateResult += 10;
            } else if (str.equalsIgnoreCase("eleven")) {
                intermediateResult += 11;
            } else if (str.equalsIgnoreCase("twelve")) {
                intermediateResult += 12;
            } else if (str.equalsIgnoreCase("thirteen")) {
                intermediateResult += 13;
            } else if (str.equalsIgnoreCase("fourteen")) {
                intermediateResult += 14;
            } else if (str.equalsIgnoreCase("fifteen")) {
                intermediateResult += 15;
            } else if (str.equalsIgnoreCase("sixteen")) {
                intermediateResult += 16;
            } else if (str.equalsIgnoreCase("seventeen")) {
                intermediateResult += 17;
            } else if (str.equalsIgnoreCase("eighteen")) {
                intermediateResult += 18;
            } else if (str.equalsIgnoreCase("nineteen")) {
                intermediateResult += 19;
            } else if (str.equalsIgnoreCase("twenty")) {
                intermediateResult += 20;
            } else if (str.equalsIgnoreCase("thirty")) {
                intermediateResult += 30;
            } else if (str.equalsIgnoreCase("forty")) {
                intermediateResult += 40;
            } else if (str.equalsIgnoreCase("fifty")) {
                intermediateResult += 50;
            } else if (str.equalsIgnoreCase("sixty")) {
                intermediateResult += 60;
            } else if (str.equalsIgnoreCase("seventy")) {
                intermediateResult += 70;
            } else if (str.equalsIgnoreCase("eighty")) {
                intermediateResult += 80;
            } else if (str.equalsIgnoreCase("ninety")) {
                intermediateResult += 90;
            } else if (str.equalsIgnoreCase("hundred")) {
                intermediateResult *= 100;
            } else if (str.equalsIgnoreCase("thousand")) {
                intermediateResult *= 1000;
                finalResult += intermediateResult;
                intermediateResult = 0;
            } else if (str.equalsIgnoreCase("million")) {
                intermediateResult *= 1000000;
                finalResult += intermediateResult;
                intermediateResult = 0;
            } else if (str.equalsIgnoreCase("billion")) {
                intermediateResult *= 1000000000;
                finalResult += intermediateResult;
                intermediateResult = 0;
            } else if (str.equalsIgnoreCase("trillion")) {
                intermediateResult *= 1000000000000L;
                finalResult += intermediateResult;
                intermediateResult = 0;
            }
        }
        finalResult += intermediateResult;
        intermediateResult = 0;
        return finalResult;
    }
    // Method to be called to  handle input exceptions.
    public static void handleInputExceptions(Exception e){
        String message;
        switch (e.toString()){
            case "java.util.InputMismatchException":
                message =  "Please enter your input in the desired format.";
                break;
            case "java.lang.NullPointerException":
                message = "Please ensure you have entered a value before pressing enter.";
                break;
            default:
                message = "Your input was invalid, please review it and try again.";
        };
        DisplayManager.displayMessage(message);
    }

}
