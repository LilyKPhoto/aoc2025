/**
 * the scenario:
 * we're given a number of sets of product IDs, formatted like 95-115, separated by commas.
 * an ID is INVALID if there is a sequence of digits repeated twice.
 * examples: 55 is invalid (5 twice), 6969 is invalid (69 twice), but 101 IS VALID, for example.
 * our job is to find all of the invalid IDs, and add them all up.
 * in the example input, 11-22 has two invalid IDs, 11 and 22.
 * 95-115 has one invalid ID: 99.
 */


import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

void main() {
    // looking at the examples, i'm immediately noticing that all invalid IDs have an even length.
    // that does NOT mean that all IDs with even length are automatically invalid,
    // but all invalid IDs seem to have an even length.
    File inputFile = new File("src/main/resources/day_two_input.txt");
    ArrayList<Long> invalidIDs = new ArrayList<>();
    long sumOfInvalidIDs = 0;
    try {
        Scanner fileRead = new Scanner(inputFile);
        String[] idRanges = fileRead.nextLine().strip().split(","); // grab the first (and only) line of the file, strip off any whitespace, and split it at the commas.
        for (String idRange : idRanges) {
            String[] idRangeExtremes = idRange.split("-");
            long lowerBound = Long.parseLong(idRangeExtremes[0]);
            long upperBound = Long.parseLong(idRangeExtremes[1]);
            // now, run through every number in the range, check if it contains a sequence of digits repeated twice.
            for (long l = lowerBound; l <= upperBound; l++) {
                String numAsAString = String.valueOf(l);
                // check if the length of the number as a string is even. if not, then don't worry about it
                if (numAsAString.length() % 2 == 0) {
                    String firstHalf = numAsAString.substring(0,numAsAString.length()/2);
                    String secondHalf = numAsAString.substring(numAsAString.length()/2);
                    if (firstHalf.equals(secondHalf)) {
                        invalidIDs.add(l);
                    }
                }
            }

        }
        // now that we've iterated over everything, let's check our ArrayList:
        for (long invalidID : invalidIDs) {
            System.out.println(invalidID);
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