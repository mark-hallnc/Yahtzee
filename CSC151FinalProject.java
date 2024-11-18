import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//
// Look for comments that are prefaced by the following character sequence: //***
// These comments will be your instructions concerning the code that you need
//     to write and/or the code that has been given to you that you need to comment.
//
// ALL code that you are instructed to write MUST BE COMMENTED by you.
//
// For full credit on this assignment, your program must:
//     1) Compile
//     2) Execute without runtime errors
//     3) Produce output that matches the sample output provided to you
//     4) Be thoroughly commented
//     5) Use make proper use of whitespace and indentation
//     6) Make appropriate use of the constants provided
//        Meaning: No hard-coded values unless otherwise indicated
//

abstract class Game {

    // Constants
    public final static int SCORE_NO_VALUE = -1;
    public final long seed = (new java.util.Date()).getTime();
    public final Random generator = new Random(seed);

    // Properties
    private String borderChar = "*";
    private String turnLabel = "Turn #";
    private int displayWidth = 70;
    private final int[] scores;
    private int turnCount = 0;
    private String invalidInputMessage = "*** Invalid input ***";
    private boolean turnOver = false;
    private boolean gameExit = false;
    private boolean gameComplete = false;


    // Setters and getters
    public String getBorderChar() {
        return borderChar;
    }

    public void setBorderChar(String borderChar) {
        this.borderChar = borderChar;
    }

    public String getTurnLabel() {
        return turnLabel;
    }

    public void setTurnLabel(String turnLabel) {
        this.turnLabel = turnLabel;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getScore(int index) {
        return scores[index];
    }

    public void setScore(int index, int value) {
        scores[index] = value;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public String getInvalidInputMessage() {
        return invalidInputMessage;
    }

    public void setInvalidInputMessage(String invalidInputMessage) {
        this.invalidInputMessage = invalidInputMessage;
    }

    public boolean isTurnOver() {
        return turnOver;
    }

    public void setTurnOver(boolean turnOver) {
        this.turnOver = turnOver;
    }

    public boolean isGameExit() {
        return gameExit;
    }

    public void setGameExit(boolean gameExit) {
        this.gameExit = gameExit;
    }

    public boolean isGameComplete() {
        return gameComplete;
    }

    public void setGameComplete(boolean gameComplete) {
        this.gameComplete = gameComplete;
    }

    private Random getGenerator() {
        return generator;
    }

    // Constructor
    public Game(int numberOfScores) {
        scores = new int[numberOfScores];
        Arrays.fill(scores, SCORE_NO_VALUE);
    }

    // Methods
    public final int getRandomInt() {
        return (getGenerator().nextInt(Yahtzee.MAX_NUMBER_ON_DIE) + 1);
    }

}

class Yahtzee extends Game {

    // Constants
    final static int NUMBER_OF_CATEGORIES = 14;
    final static int NUMBER_OF_CATEGORIES_TO_COMPLETE_GAME = 13;

    final static int UPPER_CATEGORY_UPPER_BOUND_INDEX = 5;
    final static int LOWER_CATEGORY_UPPER_BOUND_INDEX = 13;

    final static int NUMBER_OF_DICE = 5;

    final static int ACES_INDEX = 0;
    final static int TWOS_INDEX = 1;
    final static int THREES_INDEX = 2;
    final static int FOURS_INDEX = 3;
    final static int FIVES_INDEX = 4;
    final static int SIXES_INDEX = 5;
    final static int THREE_KIND_INDEX = 6;
    final static int FOUR_KIND_INDEX = 7;
    final static int FULL_HOUSE_INDEX = 8;
    final static int SMALL_STRAIGHT_INDEX = 9;
    final static int LARGE_STRAIGHT_INDEX = 10;
    final static int YAHTZEE_INDEX = 11;
    final static int CHANCE_INDEX = 12;
    final static int YAHTZEE_BONUS_INDEX = 13;

    final static int DIE_NUMBER_INDEX = 0;
    final static int DIE_COUNT_INDEX = 1;
    final static int DIE_COUNT_COLUMN_SIZE = 2;

    final static int FULL_HOUSE_NUMBER_IN_GROUP_1 = 2;
    final static int FULL_HOUSE_NUMBER_IN_GROUP_2 = 3;

    final static int MAX_NUMBER_ON_DIE = 6;

    final static String REROLL_MESSAGE_1 = "Enter: S for ScoreCard; D for Dice; X to Exit";
    final static String REROLL_MESSAGE_2 = "Or: A series of numbers to re-roll dice as follows:";
    final static String REROLL_MESSAGE_3 = "\t\tYou may re-roll any of the dice by entering the die #s without spaces.";
    final static String REROLL_MESSAGE_4 = "\t\tFor example, to re-roll dice #1, #3 & #4, enter 134 or enter 0 for none.";
    final static String REROLL_MESSAGE_5 = "\t\tYou have %d roll(s) left this turn.";
    final static String REROLL_MESSAGE_6 = "Which of the dice would you like to roll again? ";

    final static String CATEGORY_MESSAGE_1 = "Enter: 1-14 for category; S for ScoreCard; D for Dice; X to Exit";
    final static String CATEGORY_MESSAGE_2 = "Which category would you like to choose? ";

    final static String EXIT_RESPONSE = "X";
    final static String SCORE_CARD_RESPONSE = "S";
    final static String DISPLAY_DICE_RESPONSE = "D";
    final static String END_TURN_RESPONSE = "0";

    // Properties
    private int fullHouseScore = 25;
    private int smallStraightScore = 30;
    private int largeStraightScore = 40;
    private int yahtzeeScore = 50;
    private int yahtzeeBonusScore = 100;
    private String rollLabel = "Roll #";
    private final int[] dice = new int[NUMBER_OF_DICE];
    private int numberOfRolls = 0;
    private int maxNumberRolls = 3;
    private String welcomeMessage = "Welcome to YAHTZEE";
    private String pressEnterMessage = "Press the Enter key to continue: ";

    // Setters and getters
    public int getFullHouseScore() {
        return fullHouseScore;
    }

    public void setFullHouseScore(int fullHouseScore) {
        if (fullHouseScore >= 0)
            this.fullHouseScore = fullHouseScore;
        else
            this.fullHouseScore = 0;
    }

    public int getSmallStraightScore() {
        return smallStraightScore;
    }

    public void setSmallStraightScore(int smallStraightScore) {
        if (smallStraightScore >= 0)
            this.smallStraightScore = smallStraightScore;
        else
            this.smallStraightScore = 0;
    }

    public int getLargeStraightScore() {
        return largeStraightScore;
    }

    public void setLargeStraightScore(int largeStraightScore) {
        if (largeStraightScore >= 0)
            this.largeStraightScore = largeStraightScore;
        else
            this.largeStraightScore = 0;
    }

    public int getYahtzeeScore() {
        return yahtzeeScore;
    }

    public void setYahtzeeScore(int yahtzeeScore) {
        if (yahtzeeScore >= 0)
            this.yahtzeeScore = yahtzeeScore;
        else
            this.yahtzeeScore = 0;
    }

    public int getYahtzeeBonusScore() {
        return yahtzeeBonusScore;
    }

    public void setYahtzeeBonusScore(int yahtzeeBonusScore) {
        if (yahtzeeBonusScore >= 0)
            this.yahtzeeBonusScore = yahtzeeBonusScore;
        else
            this.yahtzeeBonusScore = 0;
    }

    public String getRollLabel() {
        return rollLabel;
    }

    public void setRollLabel(String rollLabel) {
        this.rollLabel = rollLabel;
    }

    public int getDice(int index) {
        return dice[index];
    }

    public void setDice(int index, int value) {
        dice[index] = value;
    }

    public int getNumberOfRolls() {
        return numberOfRolls;
    }

    public void setNumberOfRolls(int numberOfRolls) {
        this.numberOfRolls = numberOfRolls;
    }

    public int getMaxNumberRolls() {
        return maxNumberRolls;
    }

    public void setMaxNumberRolls(int maxNumberRolls) {
        this.maxNumberRolls = maxNumberRolls;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getPressEnterMessage() {
        return pressEnterMessage;
    }

    public void setPressEnterMessage(String pressEnterMessage) {
        this.pressEnterMessage = pressEnterMessage;
    }

    // Constructor
    public Yahtzee() {
        super(NUMBER_OF_CATEGORIES);
    }

    // Methods
    public void displayTurnNumber() {

        int labelLength;
        int centerValue;

        System.out.println(getBorderChar().repeat(getDisplayWidth()));
        labelLength = (getTurnLabel() + getTurnCount() + " " + getRollLabel() + numberOfRolls).length();
        centerValue = labelLength + ((getDisplayWidth()) - labelLength) / 2;
        System.out.printf("%" + centerValue + "s", getTurnLabel() + getTurnCount() + " " + getRollLabel() + numberOfRolls);
        System.out.println();
        System.out.println(getBorderChar().repeat(getDisplayWidth()));
    }

    public void displayDice() {

        final char UNICODE_DIE_INDEX = '\u267F';

        final String DIE_LABEL_PREFIX = "Die #";
        final String DIE_LABEL_SUFFIX = " = ";

        System.out.println();

        displayTurnNumber();

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            System.out.print(DIE_LABEL_PREFIX + (i + 1) + DIE_LABEL_SUFFIX);

            System.out.print((char) ((int) UNICODE_DIE_INDEX + dice[i]));

            System.out.println(" (" + (dice[i]) + ")");
        }

        System.out.println();
    }

    //***
    //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
    //***
    //*** Overload the displayScoreSheet method by doing the following:
    //***
    //*** 1) Modify the current displayScoreSheet method below as follows:
    //***    a) Add one parameter named outStream of datatype PrintStream.
    //***    b) In displayScoreSheet method only, replace every System.out with variable outStream.
    //***           For example, System.out.println(); becomes outStream.println();
    //*** 2) Write an overloaded public method named displayScoreSheet that
    //***        returns no value (i.e. void) and receives no parameters.
    //*** 3) Write the method body as follows:
    //***    a) Invoke the overloaded, one-parameter displayScoreSheet method from Step 1 on variable
    //***          variable "this" passing the argument of System.out
    //***    b) Do not put quotes around this or System.out
    //***    c) Step 3(a) is one line of code.
    //***
    //*** Comment your code.
    //***

    //***
    //*** Your overloaded, no-arg method for Step 2 & 3 goes here.
    //***
    public void displayScoreSheet() {  //overload displayScoreSheet method to send scores to console.
        this.displayScoreSheet(System.out);  // send the scoresheet to the console.
    }
    //***
    //*** Modify this following method (displayScoreSheet) for Step 1.
    //***

    public void displayScoreSheet(PrintStream outStream) {  //send the score sheet to a file.

        final String[] labels = new String[NUMBER_OF_CATEGORIES];

        labels[ACES_INDEX] = "Aces";
        labels[TWOS_INDEX] = "Twos";
        labels[THREES_INDEX] = "Threes";
        labels[FOURS_INDEX] = "Fours";
        labels[FIVES_INDEX] ="Fives";
        labels[SIXES_INDEX] = "Sixes";
        labels[THREE_KIND_INDEX] = "3 of a kind";
        labels[FOUR_KIND_INDEX] = "4 of a kind";
        labels[FULL_HOUSE_INDEX] = "Full House";
        labels[SMALL_STRAIGHT_INDEX] = "Sm. Straight";
        labels[LARGE_STRAIGHT_INDEX] = "Lg. Straight";
        labels[YAHTZEE_INDEX] = "YAHTZEE";
        labels[CHANCE_INDEX] = "Chance";
        labels[YAHTZEE_BONUS_INDEX] = "YAHTZEE BONUS";

        final int BONUS_THRESHOLD = 63;
        final int BONUS_SCORE = 35;

        final String UPPER_SECTION_LABEL = "UPPER SECTION";
        final String LOWER_SECTION_LABEL = "LOWER SECTION";
        final String UPPER_SECTION_SUBTOTAL_LABEL = "TOTAL SCORE";
        final String UPPER_SECTION_BONUS_LABEL = "BONUS if >= 63";
        final String UPPER_SECTION_TOTAL_LABEL = "TOTAL of Upper Section";
        final String LOWER_SECTION_TOTAL_LABEL = "TOTAL of Lower Section";
        final String GRAND_TOTAL_LABEL = "GRAND TOTAL";

        final String OPTION_SUFFIX_ONE_DIGIT = ")  ";
        final String OPTION_SUFFIX_TWO_DIGIT = ") ";

        final String EQUALS_LABEL = " = ";

        int upperScoreTotal = calculateUpperScore();
        int lowerScoreTotal = calculateLowerScore();

        outStream.println();
        outStream.println(UPPER_SECTION_LABEL);

        for (int i = 0; i <= UPPER_CATEGORY_UPPER_BOUND_INDEX; i++) {
            if (getScore(i) == SCORE_NO_VALUE)
                outStream.println((i+1) + OPTION_SUFFIX_ONE_DIGIT + labels[i]);
            else
                outStream.println((i+1) + OPTION_SUFFIX_ONE_DIGIT + labels[i] + EQUALS_LABEL + getScore(i));
        }

        if (upperScoreTotal > 0)
            outStream.println(UPPER_SECTION_SUBTOTAL_LABEL + EQUALS_LABEL + upperScoreTotal);
        else
            outStream.println(UPPER_SECTION_SUBTOTAL_LABEL);

        if (upperScoreTotal >= BONUS_THRESHOLD)
            outStream.println(UPPER_SECTION_BONUS_LABEL + EQUALS_LABEL + BONUS_SCORE);
        else
            outStream.println(UPPER_SECTION_BONUS_LABEL);

        if (upperScoreTotal > 0)
            if (upperScoreTotal >= BONUS_THRESHOLD)
                outStream.println(UPPER_SECTION_TOTAL_LABEL + EQUALS_LABEL + (upperScoreTotal + BONUS_SCORE));
            else
                outStream.println(UPPER_SECTION_TOTAL_LABEL + EQUALS_LABEL + upperScoreTotal);
        else
            outStream.println(UPPER_SECTION_TOTAL_LABEL);

        outStream.println();
        outStream.println(LOWER_SECTION_LABEL);

        for (int i = (UPPER_CATEGORY_UPPER_BOUND_INDEX + 1); i <= LOWER_CATEGORY_UPPER_BOUND_INDEX; i++) {
            if (i < 9)
                outStream.print((i+1) + OPTION_SUFFIX_ONE_DIGIT + labels[i]);
            else
                outStream.print((i+1) + OPTION_SUFFIX_TWO_DIGIT + labels[i]);

            if (i != YAHTZEE_BONUS_INDEX) {
                if (getScore(i) != SCORE_NO_VALUE)
                    outStream.println(EQUALS_LABEL + getScore(i));
                else
                    outStream.println();
            }
            else {
                if (getScore(i) != SCORE_NO_VALUE)
                    outStream.println(EQUALS_LABEL + (getScore(i) * getYahtzeeBonusScore()));
                else
                    outStream.println();
            }
        }

        if (lowerScoreTotal > 0)
            outStream.println(LOWER_SECTION_TOTAL_LABEL + EQUALS_LABEL + lowerScoreTotal);
        else
            outStream.println(LOWER_SECTION_TOTAL_LABEL);

        if (upperScoreTotal > 0)
            if (upperScoreTotal >= BONUS_THRESHOLD)
                outStream.println(UPPER_SECTION_TOTAL_LABEL + EQUALS_LABEL + (upperScoreTotal + BONUS_SCORE));
            else
                outStream.println(UPPER_SECTION_TOTAL_LABEL + EQUALS_LABEL + upperScoreTotal);
        else
            outStream.println(UPPER_SECTION_TOTAL_LABEL);

        if (upperScoreTotal + lowerScoreTotal > 0)
            if (upperScoreTotal >= BONUS_THRESHOLD)
                outStream.println(GRAND_TOTAL_LABEL + EQUALS_LABEL + (upperScoreTotal + lowerScoreTotal + BONUS_SCORE));
            else
                outStream.println(GRAND_TOTAL_LABEL + EQUALS_LABEL + (upperScoreTotal + lowerScoreTotal));
        else
            outStream.println(GRAND_TOTAL_LABEL);

        outStream.println();
    }

    public void calculateTurnScore(int scoreOption) {

        int scoreOption2Index = scoreOption - 1;

        if (scoreOption2Index <= UPPER_CATEGORY_UPPER_BOUND_INDEX)
            setScore(scoreOption2Index, calculateUpperSectionCategory(scoreOption));
        else {
            int[][] dieCount = calculateLowerSectionCategory();

            switch (scoreOption2Index) {

                case THREE_KIND_INDEX:
                    setScore(THREE_KIND_INDEX, calculateNOfKind(dieCount, 3));

                    break;

                case FOUR_KIND_INDEX:
                    setScore(FOUR_KIND_INDEX, calculateNOfKind(dieCount, 4));

                    break;

                case FULL_HOUSE_INDEX:
                    setScore(FULL_HOUSE_INDEX, calculateFullHouse(dieCount));

                    break;

                case SMALL_STRAIGHT_INDEX:
                    if (calculateNStraight(dieCount, 4))
                        setScore(SMALL_STRAIGHT_INDEX, getSmallStraightScore());

                    else
                        setScore(SMALL_STRAIGHT_INDEX, 0);

                    break;

                case LARGE_STRAIGHT_INDEX:
                    if (calculateNStraight(dieCount, 5))
                        setScore(LARGE_STRAIGHT_INDEX, getLargeStraightScore());
                    else
                        setScore(LARGE_STRAIGHT_INDEX, 0);

                    break;

                case YAHTZEE_INDEX:
                    if (dieCount.length == 1)
                        setScore(YAHTZEE_INDEX, getYahtzeeScore());
                    else
                        setScore(YAHTZEE_INDEX, 0);

                    setScore(YAHTZEE_BONUS_INDEX, 0);

                    break;

                case CHANCE_INDEX:
                    setScore(CHANCE_INDEX, calculateChance());

                    break;

                case YAHTZEE_BONUS_INDEX:
                    if (getScore(YAHTZEE_INDEX) == SCORE_NO_VALUE) {
                        setScore(YAHTZEE_INDEX, calculateYahtzee());
                        setScore(YAHTZEE_BONUS_INDEX, 0);
                    }
                    else if (getScore(YAHTZEE_INDEX) == getYahtzeeScore() && calculateYahtzee() == getYahtzeeScore())
                        setScore(YAHTZEE_BONUS_INDEX, getScore(YAHTZEE_BONUS_INDEX) + 1);

                    break;
            }
        }
    }

    public int[][] calculateLowerSectionCategory() {

        int[] tempDice = new int[NUMBER_OF_DICE];
        System.arraycopy(dice, 0, tempDice, 0, dice.length);
        Arrays.sort(tempDice);

        int[][] dieCount = new int[1][DIE_COUNT_COLUMN_SIZE];
        dieCount[dieCount.length-1][DIE_NUMBER_INDEX] = tempDice[0];
        dieCount[dieCount.length-1][DIE_COUNT_INDEX] = 1;

        for (int i = 1; i < tempDice.length; i++) {
            if (tempDice[i] == dieCount[dieCount.length-1][DIE_NUMBER_INDEX]) {
                dieCount[dieCount.length-1][DIE_COUNT_INDEX]++;
            }
            else {
                int[][] tempDieCount = new int[dieCount.length + 1][DIE_COUNT_COLUMN_SIZE];
                System.arraycopy(dieCount, 0, tempDieCount, 0, dieCount.length);

                tempDieCount[tempDieCount.length-1][DIE_NUMBER_INDEX] = tempDice[i];
                tempDieCount[tempDieCount.length-1][DIE_COUNT_INDEX] = 1;

                dieCount = tempDieCount;
            }
        }

        return dieCount;
    }

    public int calculateUpperSectionCategory(int dieNumber) {

        int score = 0;

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            if (dice[i] == dieNumber)
                score += dice[i];
        }

        return score;
    }

    public int calculateNOfKind(int[][] dieCount, int nKind) {

        int score = 0;
        boolean isNKind = false;

        for (int i = 0; i < dieCount.length; i++) {
            if (dieCount[i][DIE_COUNT_INDEX] >= nKind)
                isNKind = true;
        }

        if (isNKind)
            for (int i = 0; i < NUMBER_OF_DICE; i++)
                score += dice[i];

        return score;
    }

    public int calculateFullHouse(int[][] dieCount) {

        int score = 0;

        if (dieCount.length == 2 &&
                ((dieCount[0][DIE_COUNT_INDEX] == FULL_HOUSE_NUMBER_IN_GROUP_1 &&
                        dieCount[1][DIE_COUNT_INDEX] == FULL_HOUSE_NUMBER_IN_GROUP_2)
                        ||
                        (dieCount[1][DIE_COUNT_INDEX] == FULL_HOUSE_NUMBER_IN_GROUP_1 &&
                                dieCount[0][DIE_COUNT_INDEX] == FULL_HOUSE_NUMBER_IN_GROUP_2)))
            score = getFullHouseScore();

        return score;
    }

    public boolean calculateNStraight(int[][] dieCount, int nStraight) {

        boolean isNStraight = false;
        int n = 1;

        for (int i = 0; i < dieCount.length-1; i++) {
            if (dieCount[i][DIE_NUMBER_INDEX] == dieCount[i+1][DIE_NUMBER_INDEX]-1)
                n++;
            else
                n = 1;
        }

        if (n >= nStraight)
            isNStraight = true;

        return isNStraight;
    }

    public int calculateYahtzee() {

        int score = 0;
        boolean isYahtzee = true;

        for (int i = 0; i < NUMBER_OF_DICE - 1; i++) {
            if (dice[i] != dice[i+1]) {
                isYahtzee = false;
            }
        }

        if (isYahtzee)
            score = getYahtzeeScore();

        return score;
    }

    public int calculateChance() {

        int chance = 0;

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            chance += dice[i];
        }

        return chance;
    }

    public int calculateUpperScore() {

        int score = 0;

        for (int i = 0; i <= UPPER_CATEGORY_UPPER_BOUND_INDEX; i++)
            if (getScore(i) != SCORE_NO_VALUE)
                score += getScore(i);

        return score;
    }

    public int calculateLowerScore() {

        int score = 0;

        for (int i = UPPER_CATEGORY_UPPER_BOUND_INDEX + 1; i <= LOWER_CATEGORY_UPPER_BOUND_INDEX; i++)
            if (i != YAHTZEE_BONUS_INDEX) {
                if (getScore(i) != SCORE_NO_VALUE)
                    score += getScore(i);
            }
            else {
                if (getScore(i) != SCORE_NO_VALUE)
                    score += (getScore(i) * getYahtzeeBonusScore());
            }

        return score;
    }

    public boolean isCategoryUsed(int scoreOption) {

        boolean used = false;

        if ((scoreOption - 1) != YAHTZEE_BONUS_INDEX) {
            if (getScore(scoreOption - 1) != SCORE_NO_VALUE)
                used = true;
        }
        else {
            if (getScore(YAHTZEE_INDEX) == getYahtzeeScore() && calculateYahtzee() != getYahtzeeScore())
                used = true;
        }

        return used;
    }

    public boolean isGameOver() {

        boolean gameOver = true;

        for (int i = 0; i < NUMBER_OF_CATEGORIES_TO_COMPLETE_GAME; i++)
            if (getScore(i) == SCORE_NO_VALUE)
                gameOver = false;

        return gameOver;
    }

    public void displayErrorMessage() {

        System.out.println();

        System.out.println(getBorderChar().repeat(getInvalidInputMessage().length()));
        System.out.println(getInvalidInputMessage());
        System.out.println(getBorderChar().repeat(getInvalidInputMessage().length()));
    }

}

public class CSC151FinalProject {

    public static void main(String[] args) {

        //***
        //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
        //***
        //*** 1) Declare a variable named GameOfYahtzee.
        //*** 2) Create a new instance (using the new operator) of class Yahtzee and assign the instance to
        //***        variable GameOfYahtzee.
        //***
        //*** This is one line of code.
        //***
        //*** Comment your code.
        //***
        Yahtzee GameOfYahtzee = new Yahtzee(); //create a new instance of object Yahtzee called GameOfYahtzee.
        //***
        //*** Your code goes here.
        //***

        final int ASCII_DICE_INDEX = 49;
        final String OUTPUT_FILE_NAME = "output.txt";
        final String OUTPUT_FILE_ERROR_MESSAGE = "Error opening file: ";

        String dice2Reroll;
        String scoreOptionInput;
        int scoreOption = 0;

        Scanner input = new Scanner(System.in);

        System.out.println();

        //***
        //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
        //***
        //*** 1) Invoke method getWelcomeMessage on variable GameOfYahtzee passing no arguments
        //***        sending the return value to the console.
        //***
        //*** This is one line of code.
        //***
        //*** Comment your code.
        //***

        //***
        //*** Remove this code and replace with your code.
        System.out.println(GameOfYahtzee.getWelcomeMessage());  //Output the welcome message to the console.
        //***

        //***
        //*** Your replacement code goes here.
        //***

        do {
            System.out.println();

            //***
            //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
            //***
            //*** 1) Invoke method getPressEnterMessage on variable GameOfYahtzee passing no arguments
            //***        sending the return value to the console.
            //***
            //*** This is one line of code.
            //***
            //*** Comment your code.
            //***

            //***
            //*** Remove this code and replace with your code.
            System.out.println(GameOfYahtzee.getPressEnterMessage()); //Output the press enter message to the console.
            //***

            //***
            //*** Your replacement code goes here.
            //***

            input.nextLine();

            //***
            //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
            //***
            //*** 1) Add 1 to the value of property turnCount by invoking the getter and setter of property turnCount
            //***        on variable GameOfYahtzee.
            //***
            //*** This can be completed in as little as one line of code. If you prefer, it can be completed in more
            //***     than one line of code.
            //***
            //*** Comment your code.
            //***

            //***
            //*** Remove this code and replace with your code.
            GameOfYahtzee.setTurnCount(GameOfYahtzee.getTurnCount() + 1); //get the turncount, add 1 and send back
                                                                         // to GameOfYahtzee


            //***

            //***
            //*** Your replacement code goes here.
            //***

            for (int i = 0; i < Yahtzee.NUMBER_OF_DICE; i++) {

                //***
                //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
                //***
                //*** 1) Invoke method getRandomInt on variable GameOfYahtzee passing no arguments.
                //*** 2) Invoke method setDice on variable GameOfYahtzee.
                //***    a) The first argument being the for-loop control variable i.
                //***    b) The second argument being the return value of Step 1.
                //***
                //*** This can be completed in as little as one line of code. If you prefer, it can be completed in more
                //***     than one line of code.
                //***
                //*** Comment your code.
                //***

                //***
                //*** Remove this code and replace with your code.
                GameOfYahtzee.getRandomInt();  //call getRandomInt method on GameOfYahtzee.
                GameOfYahtzee.setDice(i, GameOfYahtzee.getRandomInt()); //roll a random number for each die by calling
                                                                        //getRandomInt, and control variable i from loop
                                                                        //that loops through each die.
                //***

                //***
                //*** Your replacement code goes here.
                //***
            }


            //***
            //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
            //***
            //*** 1) Invoke method setNumberOfRolls on variable GameOfYahtzee passing the argument of 1.
            //*** 2) Invoke method displayDice on variable GameOfYahtzee passing no arguments.
            //*** 3) Invoke method setTurnOver on variable GameOfYahtzee passing the argument of false.
            //***
            //*** This is three lines of code.
            //***
            //*** Comment your code.
            //***

            //***
            //*** Remove this code and replace with your code.
            GameOfYahtzee.setNumberOfRolls(1); //tell NumberOfRolls this is a turn by passing it the arg of 1.
            GameOfYahtzee.displayDice(); //call displayDice to show results of roll and dice icons.
            GameOfYahtzee.setTurnOver(false); //pass false to the var turnover.
            //***

            //***
            //*** Your replacement code goes here.
            //***

            do {
                System.out.println();
                System.out.println(Yahtzee.REROLL_MESSAGE_1);
                System.out.println();

                System.out.println(Yahtzee.REROLL_MESSAGE_2);
                System.out.println(Yahtzee.REROLL_MESSAGE_3);
                System.out.println(Yahtzee.REROLL_MESSAGE_4);

                System.out.println();
                System.out.printf(Yahtzee.REROLL_MESSAGE_5, (GameOfYahtzee.getMaxNumberRolls() - GameOfYahtzee.getNumberOfRolls()));
                System.out.println();

                System.out.println();
                System.out.print(Yahtzee.REROLL_MESSAGE_6);

                dice2Reroll = input.nextLine();
                dice2Reroll = dice2Reroll.trim();

                switch (dice2Reroll.toUpperCase()) {

                    //***
                    //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
                    //***
                    //*** 1) Modify the first five (5) case statements as follows:
                    //***    a) For the first four (4) case statements, use the appropriate class variable to
                    //***           reference each CONSTANT (i.e. EXIT_RESPONSE, SCORE_CARD_RESPONSE,
                    //***           DISPLAY_DICE_RESPONSE, END_TURN_RESPONSE)
                    //***    b) For the code in case EXIT_RESPONSE (first case), invoke the appropriate setters on
                    //***           variable GameOfYahtzee to assign the values to properties turnOver and gameExit.
                    //***    c) For the code in case SCORE_CARD_RESPONSE (second case), invoke method displayScoreSheet
                    //***           on variable GameOfYahtzee passing no arguments.
                    //***    d) For the code in case DISPLAY_DICE_RESPONSE (third case), invoke method displayDice
                    //***           on variable GameOfYahtzee.
                    //***    e) For the code in case END_TURN_RESPONSE (fourth case), invoke the appropriate setter on
                    //***           variable GameOfYahtzee to assign the value to property turnOver.
                    //***    f) For the code in case "" (fifth case), invoke method displayErrorMessage on variable
                    //***           GameOfYahtzee.
                    //***
                    //*** Comment your code.
                    //***

                    //***
                    //*** Remove this code and replace with your code.
                    case Yahtzee.EXIT_RESPONSE: // if choice is "x" to exit...
                        GameOfYahtzee.setTurnOver(true); // the turn is over.
                        GameOfYahtzee.setGameExit(true); // exit the game.
                        break;

                    case Yahtzee.SCORE_CARD_RESPONSE: // if choice is "s" to view scorecard...
                        GameOfYahtzee.displayScoreSheet(); // invoke displayScoreSheet method to display scoresheet.
                        break;

                    case Yahtzee.DISPLAY_DICE_RESPONSE: // if choice is "D" to display current roll...
                        GameOfYahtzee.displayDice(); // invoke displayDice method to display dice.
                        break;

                    case Yahtzee.END_TURN_RESPONSE: // if choice is "0" to end turn...
                        GameOfYahtzee.setTurnOver(true);  // then var turnover is true and turn is over.
                        break;

                    case "":
                        GameOfYahtzee.displayErrorMessage(); // if no choice is entered, error message is displayed.
                        break;
                    //***

                    //***
                    //*** Your replacement code goes here.
                    //***

                    default:
                        dice2Reroll = dice2Reroll.replace(" ", "");
                        String checkDice2Reroll = dice2Reroll;

                        for (int i = 1; i <= Yahtzee.NUMBER_OF_DICE; i++) {
                            checkDice2Reroll = checkDice2Reroll.replaceFirst(String.valueOf(i), " ");
                        }

                        if (checkDice2Reroll.isBlank()) {
                            for (int i = 0; i < dice2Reroll.length(); i++) {
                                GameOfYahtzee.setDice(((int)dice2Reroll.charAt(i)) - ASCII_DICE_INDEX, GameOfYahtzee.getRandomInt());
                            }

                            //***
                            //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
                            //***
                            //*** 1) Add 1 to the value of property numberOfRolls by invoking the getter and setter of
                            //***        property numberOfRolls on variable GameOfYahtzee.
                            //*** 2) Invoke method displayDice on variable GameOfYahtzee passing no arguments.
                            //***
                            //*** Step 1 can be completed in as little as one line of code. If you prefer, it can be
                            //***     completed in more than one line of code.
                            //*** Step 2 is one line of code.
                            //***
                            //*** Comment your code.
                            //***

                            //***
                            //*** Remove this code and replace with your code.
                            GameOfYahtzee.setNumberOfRolls(GameOfYahtzee.getNumberOfRolls() + 1); // fetch number of rolls,
                            // add 1 and send back to numberOfRolls. In other words increment numberOfRolls by 1 but since
                            // its out of scope of this method we need to use setters and getters to get access, change
                            // value and send back.
                            GameOfYahtzee.displayDice(); // invoke displayDice method to display dice.
                            //***

                            //***
                            //*** Your replacement code goes here.
                            //***

                            //***
                            //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
                            //***
                            //*** 1) Using the instance variable GameOfYahtzee and appropriate getters, write an
                            //***        if-statement that checks the value of property numberOfRolls for "equality to"
                            //***         the value of property maxNumberRolls.
                            //*** 2) If the condition in step 1 evaluates to true, then invoke the appropriate setter
                            //***        on variable GameOfYahtzee to assign the value to property turnOver.
                            //***
                            //*** Comment your code.
                            //***

                            //***
                            //*** Remove this code and replace with your code.
                            if (GameOfYahtzee.getNumberOfRolls() == GameOfYahtzee.getMaxNumberRolls())
                                // Check if the current number of rolls is equal to the max(3). If it is, the turn is over.
                                GameOfYahtzee.setTurnOver(true);


                            //***

                            //***
                            //*** Your replacement code goes here.
                            //***
                        }
                        else {
                            GameOfYahtzee.displayErrorMessage();
                        }

                } // This is the closing curly brace for the switch statement.

            } while (!GameOfYahtzee.isTurnOver());

            if (!GameOfYahtzee.isGameExit()) {

                //***
                //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
                //***
                //*** 1) Invoke method displayScoreSheet on variable GameOfYahtzee passing no arguments.
                //***
                //*** This is one line of code.
                //***
                //*** Comment your code.
                //***

                //***
                //*** Remove this code and replace with your code.
                GameOfYahtzee.displayScoreSheet(); // invoke displayScoreSheet method to display the score sheet.
                //***

                //***
                //*** Your replacement code goes here.
                //***

                boolean isValidEntry;
                boolean categoryPicked;
                boolean continuePrompting;

                do {

                    isValidEntry = true;
                    categoryPicked = false;

                    System.out.println();
                    System.out.println(Yahtzee.CATEGORY_MESSAGE_1);
                    System.out.println();

                    System.out.print(Yahtzee.CATEGORY_MESSAGE_2);

                    scoreOptionInput = input.nextLine();
                    scoreOptionInput = scoreOptionInput.trim();

                    scoreOption = 0;

                    switch (scoreOptionInput.toUpperCase()) {

                        //***
                        //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
                        //***
                        //*** 1) Modify the first four (4) case statements as follows:
                        //***    a) For the first three (3) case statements, use the appropriate class variable to
                        //***           reference each CONSTANT (i.e. EXIT_RESPONSE, SCORE_CARD_RESPONSE,
                        //***           DISPLAY_DICE_RESPONSE)
                        //***    b) For the code in case EXIT_RESPONSE (first case), invoke the appropriate setter on
                        //***           variable GameOfYahtzee to assign the value to property gameExit.
                        //***    c) For the code in case SCORE_CARD_RESPONSE (second case), invoke method
                        //***           displayScoreSheet on variable GameOfYahtzee passing no arguments.
                        //***    d) For the code in case DISPLAY_DICE_RESPONSE (third case), invoke method displayDice
                        //***           on variable GameOfYahtzee.
                        //***    e) For the code in case "" (fourth case), invoke method displayErrorMessage on
                        //***           variable GameOfYahtzee.
                        //***
                        //*** Comment your code.
                        //***

                        //***
                        //*** Remove this code and replace with your code.
                        case Yahtzee.EXIT_RESPONSE: // if the user chooses "x" to exit...
                            GameOfYahtzee.setTurnOver(true); // the turn is over.
                            GameOfYahtzee.setGameExit(true); // exit the game.
                            break;

                        case Yahtzee.SCORE_CARD_RESPONSE: // if the user chooses "s" to view the score sheet...
                            GameOfYahtzee.displayScoreSheet(); // display the score sheet.
                            break;

                        case Yahtzee.DISPLAY_DICE_RESPONSE: // if the user chooses "d" to see the dice...
                            GameOfYahtzee.displayDice(); // display the dice by invoking displayDice method.
                            break;

                        case "":                                    // if user does not enter anything...
                            GameOfYahtzee.displayErrorMessage(); // show the error message.
                            break;
                        //***

                        //***
                        //*** Your replacement code goes here.
                        //***

                        default:
                            for (int x = 0; x < scoreOptionInput.length(); x++)
                                if (!(Character.isDigit(scoreOptionInput.charAt(x)))) {
                                    isValidEntry = false;
                                    GameOfYahtzee.displayErrorMessage();
                                }

                            if (isValidEntry) {
                                scoreOption = Integer.parseInt(scoreOptionInput);

                                if (scoreOption < 1 || scoreOption > Yahtzee.NUMBER_OF_CATEGORIES) {
                                    isValidEntry = false;
                                    GameOfYahtzee.displayErrorMessage();
                                }
                            }

                            if (isValidEntry && GameOfYahtzee.isCategoryUsed(scoreOption)) {
                                isValidEntry = false;
                                GameOfYahtzee.displayErrorMessage();
                            }

                            if (isValidEntry) {
                                categoryPicked = true;
                                GameOfYahtzee.calculateTurnScore(scoreOption);
                                GameOfYahtzee.displayScoreSheet();
                                GameOfYahtzee.setGameComplete(GameOfYahtzee.isGameOver());
                            }
                    } // This is the closing curly brace for the switch statement.

                    continuePrompting = !GameOfYahtzee.isGameExit() && !categoryPicked;

                } while (continuePrompting);

            } // This is the closing curly brace for the if-statement.

        } while (!GameOfYahtzee.isGameExit() && !GameOfYahtzee.isGameComplete());

        //***
        //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
        //***
        //*** 1) Invoke method displayScoreSheet on variable GameOfYahtzee passing no arguments.
        //***
        //*** This is one line of code.
        //***
        //*** Comment your code.
        //***

        //***
        //*** Remove this code and replace with your code.
        GameOfYahtzee.displayScoreSheet(); // invoke method to display the score sheet.
        //***

        //***
        //*** Your replacement code goes here.
        //***

        //***
        //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
        //***
        //*** 1) Declare a variable named outputFile of datatype File.
        //*** 2) Create a new instance (using the new operator) of class File.
        //***    a) Pass the CONSTANT OUTPUT_FILE_NAME to the File constructor.
        //***    b) Assign the instance to variable outputFile.
        //***
        //*** This can be completed in as little as one line of code. If you prefer, it can be
        //***     completed in more than one line of code.
        //***
        //*** Comment your code.
        //*** Use the appropriate CONSTANT.
        //***
              java.io.File outputFile = new java.io.File(OUTPUT_FILE_NAME); // create a new file called outputPutFile.
        //***
        //*** Your code goes here.
        //***

        //***
        //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
        //***
        //*** Write a try-catch block by doing the following:
        //***
        //*** 1) Code for try block:
        //***    a) Declare a variable named outputStream of datatype PrintStream.
        //***    b) Create a new instance (using the new operator) of class PrintStream.
        //***    c) Pass the variable outputFile to the PrintStream constructor.
        //***    d) Assign the instance to variable outputStream.
        //***    e) Invoke method displayScoreSheet on variable GameOfYahtzee passing the variable
        //***           outputStream as an argument.
        //*** 2) For the catch block header, use class Exception for the exception type and a variable named ex.
        //*** 3) Code for catch block:
        //***    a) Display an error message to the console by using the CONSTANT OUTPUT_FILE_ERROR_MESSAGE
        //***           concatenated to the return value of invoking the method getName on variable outputFile.
        //***
        //*** Step 1(a-d) can be completed in as little as one line of code. If you prefer, it can be
        //***     completed in more than one line of code.
        //*** Step 1(e) is one line of code.
        //*** Step 3(a) is one line of code.
        //***
        //*** Comment your code.
        //*** Use the appropriate CONSTANT.
        //***
              try {
                  // create a new output that can be used to send the score sheet to the file we created above.
                java.io.PrintStream outputStream = new java.io.PrintStream(outputFile);
                GameOfYahtzee.displayScoreSheet(outputStream);
              }
              // if there is an issue with doing the above action, display an error message.

              catch (Exception ex) {
                  System.out.println(OUTPUT_FILE_ERROR_MESSAGE + outputFile.getName());
              }
        //***
        //*** Your code goes here.
        //***
    }
}