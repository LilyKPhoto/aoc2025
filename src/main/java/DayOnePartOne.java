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
    File inputFile = new File("src/main/resources/day_one_input.txt"); // grab our input file
    int dialPosition = 50; // our dial position starts at 50.
    int numZeroLandings = 0; // how many times have we landed on zero? this is our final password
    try {
        Scanner fileRead = new Scanner(inputFile);
        // this for-loop is temporary, and just for testing. it should be replaced with while (fileRead.hasNextLine()).
        while (fileRead.hasNextLine()) {
            String line = fileRead.nextLine().strip();
            // only do anything if the line isn't empty
            if (!line.isEmpty()) {
//                    System.out.printf("Line: %s. Current Position: %d. ", line, dialPosition);
                char direction = line.charAt(0);
                int numClicks = Integer.parseInt(line.substring(1));
                // either add or subtract the number of clicks depending on the direction
                if (direction == 'L') {
                    // if the direction is Left, we need to subtract.
//                        System.out.printf("Turning Left by (subtracting) %d clicks. ", numClicks);
                    dialPosition -= numClicks;
                } else {
                    // if the direction is Right, we need to add.
//                        System.out.printf("Turning Right by (adding) %d clicks. ", numClicks);
                    dialPosition += numClicks;
                }
//                    System.out.printf("Resulting position: %d. ", dialPosition);
                // since the range is [0, 99], we need to check if our value is out of the range, and bring it back in.

                // if the new dial position is less than zero, then keep adding 100 until we're under 100.
                // if the new dial position >= 100, then keep subtracting 100 until we're under 100.
                if (dialPosition < 0) {
                    // if we're in the negatives, keep adding 100 until we're back in the positives,
                    // example: if dialPosition equals -40, then add 100,
                    // resulting in dialPosition equaling 60.
                    while (dialPosition < 0) {
                        dialPosition += 100;
                    }
                } else if (dialPosition >= 100) {
                    // if we're greater than or equal to 100, keep subtracting 100 until we're back in range,
                    // example: if dialPosition equals 140, then subtract 100,
                    // resulting in dialPosition equaling 40.
                    while (dialPosition >= 100) {
                        dialPosition -= 100;
                    }
                }
                // if the new dial position equals zero, then we need to increment numZeroLandings.
                if (dialPosition == 0) {
                    numZeroLandings++;
                }
            }
//                System.out.printf("Number of zero landings: %d.%n", numZeroLandings);
        }
    } catch (FileNotFoundException e) {
        System.err.println("funny story, the file can't be found lmao");
        System.exit(1);
    }

    System.out.printf("Final Position = %d. Password = number of zero crossings = %d.%n", dialPosition, numZeroLandings);
}
