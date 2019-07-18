/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 5
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class morseTranslator {
    // two dimensional array to hold morse to english conversion
    public static final String morseMap[][] = {
            {"A", ".-"},
            {"B", "-..."},
            {"C", "-.-."},
            {"D", "-.."},
            {"E", "."},
            {"F", "..-."},
            {"G", "--."},
            {"H", "...."},
            {"I", ".."},
            {"J", ".---"},
            {"K", "-.-"},
            {"L", ".-.."},
            {"M", "--"},
            {"N", "-."},
            {"O", "---"},
            {"P", ".--."},
            {"Q", "--.-"},
            {"R", ".-."},
            {"S", "..."},
            {"T", "-"},
            {"U", "..-"},
            {"V", "...-"},
            {"W", ".--"},
            {"X", "-..-"},
            {"Y", "-.--"},
            {"Z", "--.."},
            {"1", ".----"},
            {"2", "..---"},
            {"3", "...--"},
            {"4", "....-"},
            {"5", "....."},
            {"6", "-...."},
            {"7", "--..."},
            {"8", "---.."},
            {"9", "----."},
            {"0", "-----"},
    };

    public static void main(String args[]) throws IOException {

        System.out.println("What kind of translation do you want? Morse or English? Type 'M' for Morse or 'E' for English.");
        String mode = obtainInput();

        if (mode.equals("M")) {
            System.out.println("Input morse code");
            String morsePipeDelimited = obtainInput();

            // get an upper bound for the StringBuffer space
            int bufferLength = morsePipeDelimited.split(" ").length;

            // split morse code input into array of morse delimited by the pipe
            String[] morseWords = morsePipeDelimited.split("\\|");

            // create a buffer for our final english translation
            StringBuffer morseTranslated = new StringBuffer(bufferLength);

            for (String word: morseWords) {
                String[] symbols = word.split(" ");

                for (String symbol : symbols) {
                    for (String[] translation : morseMap) {
                        if (translation[1].equals(symbol)) {
                            morseTranslated.append(translation[0]);
                        }
                    }
                }

                morseTranslated.append(" ");
            }

            System.out.println(morseTranslated);
        } else {
            // handle the english to morse conversion
            System.out.println("Input English sentence");
            String sentenceSpaceDelimited = obtainInput();
            String[] englishWords = sentenceSpaceDelimited.toUpperCase().split(" ");

            // get upper bound buffer size from number of letters
            StringBuffer englishTranslatedToMorse = new StringBuffer(sentenceSpaceDelimited.length() * 5);

            for (String word : englishWords) {
                String[] charArray = word.split("");
                for (String letter : charArray) {
                    for (String[] translation : morseMap) {
                        if (translation[0].equals(letter)) {
                            englishTranslatedToMorse.append(translation[1]);
                        }
                    }

                    // once we're done converting a letter to morse, we need to append a space
                    englishTranslatedToMorse.append(" ");
                }

                englishTranslatedToMorse.append(" | ");
            }

            // get rid of last pipe character
            englishTranslatedToMorse.deleteCharAt(englishTranslatedToMorse.lastIndexOf("|"));
            System.out.println(englishTranslatedToMorse);
        };
    }


    // handles all input from user
    public static String obtainInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input.trim().toUpperCase();
    }
}
