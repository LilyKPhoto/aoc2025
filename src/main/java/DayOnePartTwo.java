/**
 * Situation: We need to find the password to the North Pole's Secret Entrance
 * There is a safe with a dial whose numbers range from 0 to 99.
 * We have two directions: Left and Right, where Left subtracts from the dial, and Right adds.
 * Going left from 0 underflows to 99, and going right from 99 overflows to 0.
 * We start at 50.
 * Given input: a document containing a sequence of rotations (L12, R34, etc.)
 * The password is the number of times the dial lands on zero.
 */

// okay so apparently in the newest java spec, if your file only contains main(), then you can just do this? you don't need to declare the class? weird.
void main() {
    // set up some variables:
    File inputFile = new File("src/main/resources/day_one_input_testing.txt"); // grab our input file
    int dialPosition = 50; // our dial position starts at 50.
    int numZeroLandings = 0; // how many times have we landed on zero? this is our final password
    try {
        Scanner fileRead = new Scanner(inputFile);
        while (fileRead.hasNextLine()) {
            String line = fileRead.nextLine().strip();
            // only do anything if the line isn't empty
            if (!line.isEmpty()) {
                char direction = line.charAt(0);
                int numClicks = Integer.parseInt(line.substring(1));
                // either add or subtract the number of clicks depending on the direction
                if (direction == 'L') {
                    // if the direction is Left, we need to subtract.
                    for (int i = 0; i < numClicks; i++) {
                        if(dialPosition == 0) {
                            numZeroLandings += 1;
                            dialPosition += 100; // prevent underflow
                        }
                        dialPosition -= 1;
                    }
                } else {
                    // if the direction is Right, we need to add.
                    for (int i = 0; i < numClicks; i++) {
                        if(dialPosition == 100) {
                            numZeroLandings += 1;
                            dialPosition = 0; // prevent overflow
                        }
                        dialPosition += 1;
                    }
                }



//                System.out.printf("The dial is rotated %s to point at %d.%n", line, dialPosition);
            }
        }
        System.out.printf("The dial crossed or landed on zero %d times.%n", numZeroLandings);
    } catch (FileNotFoundException e) {
        System.err.println("funny story, the file can't be found lmao");
        System.exit(1);
    }
}
