import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * the scenario:
 * we're given a number of sets of product IDs, formatted like 95-115, separated by commas.
 * an ID is INVALID if there is a sequence of digits repeated twice.
 * examples: 55 is invalid (5 twice), 6969 is invalid (69 twice), but 101 IS VALID, for example.
 * <p>
 * FOR PART TWO: there is a new rule!
 * per the website:
 * Now, an ID is invalid if it is made only of some sequence of digits repeated at least twice. So, 12341234 (1234 two times), 123123123 (123 three times), 1212121212 (12 five times), and 1111111 (1 seven times) are all invalid IDs.
 * in the example input, 11-22 has two invalid IDs, 11 and 22.
 * 95-115 now has two invalid IDs: 99 and 111.
 * our job is still to find all of the invalid IDs, and add them all up.
 */

void main() {
    File inputFile = new File("src/main/resources/day_two_input.txt");
//    File inputFile = new File("src/main/resources/day_two_example_input.txt");
    ArrayList<Long> invalidIDs = new ArrayList<>();
    long sumOfInvalidIDs = 0;
    boolean addToArray;
    try {
        Scanner fileRead = new Scanner(inputFile);
        String[] idRanges = fileRead.nextLine().strip().split(","); // grab the first (and only) line of the file, strip off any whitespace, and split it at the commas.
        for (String idRange : idRanges) {
            System.out.println(idRange);
            addToArray = false;
            String[] idRangeExtremes = idRange.split("-");
            long lowerBound = Long.parseLong(idRangeExtremes[0]);
            long upperBound = Long.parseLong(idRangeExtremes[1]);
            // now, run through every number in the range, check if it contains a sequence of digits repeated twice.
            for (long l = lowerBound; l <= upperBound; l++) {
                String numAsAString = String.valueOf(l);
                if (numAsAString.length() % 2 == 0) {
                    String firstHalf = numAsAString.substring(0, numAsAString.length()/2);
                    String secondHalf = numAsAString.substring(numAsAString.length()/2);
                    if (firstHalf.equals(secondHalf)) {
                        addToArray = true;
                    } else {
                        // check if the first two are the same as the next two, and so on, but only if there's more than one grouping of two.
                        addToArray = false;
                        if (numAsAString.length() > 2) {
                            String firstGrouping = numAsAString.substring(0, 2);
                            int numberOfGroupings = numAsAString.length()/2;
                            for (int i = 1; i < numberOfGroupings; i++) {
                                String nextGrouping = numAsAString.substring(2*i, (2*i)+2);
                                if(!firstGrouping.equals(nextGrouping)) {
                                    addToArray = false;
                                    break;
                                } else {
                                    addToArray = true;
                                }
                            }
                        }
                    }
                } else if (numAsAString.length() % 3 == 0) {
                    // need to check for groupings of 3, then check for all characters
                    addToArray = false;
                    if (numAsAString.length() >= 9) {
                        // this was >3, but >6 should work too
                        String firstGrouping = numAsAString.substring(0, 3);
                        int numberOfGroupings = numAsAString.length()/3;
                        for (int i = 1; i < numberOfGroupings; i++) {
                            String nextGrouping = numAsAString.substring(3*i, (3*i)+3);
                            if(!firstGrouping.equals(nextGrouping)) {
                                addToArray = false;
                                break;
                            } else {
                                addToArray = true;
                            }
                        }
                    } else {
                        // this handles if the length IS 3 (or technically less but like hebede)
                        // this is janky and bodgy but it works
                        char[] numToCharArray = numAsAString.toCharArray();
                        if (numToCharArray[0] == numToCharArray[1] && numToCharArray[0] == numToCharArray[2]) {
                            addToArray = true;
                        }
                    }
                } else if (numAsAString.length() > 1) {
                    // handle any other cases like 1,111,111, while excluding any single-digit stuff
                    char[] numToCharArray = numAsAString.toCharArray();
                    char first = numToCharArray[0];
                    for (char c : numToCharArray) {
                        if (!(first == c)) {
                            addToArray = false;
                            break;
                        } else {
                            addToArray = true;
                        }
                    }
                }
                if (addToArray) {
                    System.out.println(l);
                    invalidIDs.add(l);
                }

            }
        }
        // now that we've iterated over everything, let's check our ArrayList:
        for (long invalidID : invalidIDs) {
//            System.out.println(invalidID);
            sumOfInvalidIDs += invalidID;
        }
        // finally, print the total:
        System.out.printf("Sum of all invalid IDs: %d%n", sumOfInvalidIDs);
    } catch (FileNotFoundException e) {
        // if we get here it's a skill issue tbh /lh
        System.err.println("i couldn't find the file. have you tried simply giving a good path? no more program privileges for you >:3");
        System.exit(1);
    }


}