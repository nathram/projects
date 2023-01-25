/**
 * Creates the Game of Nim for any number of piles
 *
 * Nathra
 * 2/15/22
 */
import java.util.*;
public class NimCustom
{
    public int[] stones;
    public ArrayList<int[]> losingPosition = new ArrayList<int[]>();
    public String statement;
    public static int first, max, pile = 0, removed = 0, gameOverNum, whosfirst, badinitial, totalpiles, allbutoneequals, onegreater, allzeros, addtolist;
    public static boolean gameOver = false, badmove = true;
    Scanner thing = new Scanner(System.in);
    /**
     * Default constructor: defaults to {3, 5, 8} as the starting
     * @param int to indicate who goes first
     */
    public NimCustom(int firstplayer)
    {
        first = firstplayer;
        totalpiles = 3;
        stones = new int[3];
        //iterates while the entry is invalid
        while (first != 0 && first != 1 && first!= 2)
        {
            System.out.println("Invalid entry. Try again.");
            first = thing.nextInt();
        }
        System.out.print("Game has started." + "\n");
    }
    /**
     * Copy constructor
     * @param int who goes first, int for # of piles
     */
    public NimCustom(int firstplayer, int piles)
    {
        first = firstplayer;
        totalpiles = piles;
        stones = new int[totalpiles];
        System.out.println("Please enter the number of stones in each of the " + totalpiles + " piles.");
        //player indicates # of stones in each pile
        for (int i = 0; i < totalpiles; i++)
        {
            stones[i] = thing.nextInt();
            if (stones[i] < 0)
                badinitial += 1;
        }
        //iterates while one or more entries are invalid
        while (first != 0 && first != 1 && first != 2 || badinitial != 0)
        {
            System.out.println("One or more invalid entries. Try again.");
            first = thing.nextInt();
            badinitial = 0;
            for (int i = 0; i < totalpiles; i++)
            {
                stones[i] = thing.nextInt();
                if (stones[i] < 1)
                    badinitial += 1;
            }
        }
        System.out.print("Game has started." + "\n");
    }
    /**
     * Displays the board
     */
    public void displayNim()
    {
        for (int i: stones)
        {
            for(int j=0;j<i;j++)
                System.out.print("O");
            System.out.print(" (" + i + ")");
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    /**
     * Completes a move
     * @param int which pile, int how much to be removed
     * @return either how much was removed + the pile, or to try again because the input was invalid
     */
    public String move(int pile, int removed)
    {
        if (pile < totalpiles+1 && pile > 0 && removed <= stones[pile-1] && removed > 0)
        {
            stones[pile-1] -= removed;
            badmove = false;
            statement = "Removed " + removed + " stone(s) from Pile " + pile + "." + "\n" + "Board: ";
        }
        else
            statement = "Invalid entry. Please try again.";
        return statement;
    }
    /**
     * Adds the first array to losingPositions and calls the recursion method to complete it
     */
    public void computerStratBase()
    {
        //adds the first losing position, all 0's except the last value, which should be 1
        int[] firstArr = new int[totalpiles];
        for (int i = 0; i < totalpiles - 1; i++)
        {
            firstArr[i] = 0;
        }
        firstArr[totalpiles-1] = 1;
        losingPosition.add(firstArr);
        //creates a temporary array with size = # of piles to pass through the recursion method along with the total # of piles
        int[] temp3 = new int[totalpiles];
        computerStratRecursion(temp3, totalpiles);
    }
    /**
     * Completes a recursion to get an undefined number of for loops based on the # of piles, and populate losingPositions
     * @param int[] the array being checked so that it can keep the old values and only change the ones it needs to, int
     * the level to check how many recursions are left to be completed
     */
    public void computerStratRecursion(int[] temp2, int level)
    {
        //breaks out of the method if the level = 0, in other words, when we've gone through enough recursions
        if (level == 0)
            return;
        //sets a temporary array equal to the one passed
        int[] temp = temp2;
        //iterates from 0 to the number of stones in each pile
        for (int i = 0; i < stones[totalpiles-level]+1; i++)
        {   
            //for every for loop, sets the respective temp value, which will fill the temp array
            temp[totalpiles-level] = i;
            //only if level=1, or in other words inside the inner-most for loop, should the inside code be executed
            if (level == 1)
            {
                addtolist = 0;
                //checks temp against the previous losingPositions
                for (int[] k: losingPosition)
                {
                    allbutoneequals = 0;
                    onegreater = 0;
                    allzeros = 0;
                    //makes sure the array with all 0s is not added to losingPositions
                    for (int j: temp)
                        if (j == 0)
                            allzeros += 1;
                    if (allzeros == totalpiles)
                        addtolist += 1;
                    //if a certain position can reach a losing position in one move, it is a winning position, and is not
                    //added to the list. if it cannot reach a losing position in one move, it must be a losing position and is
                    //added to the list.
                    for (int m = 0; m < totalpiles; m++)
                        if (temp[m] == k[m])
                            allbutoneequals += 1;
                        else if (temp[m] > k[m])
                            onegreater += 1;
                    if (allbutoneequals == totalpiles - 1 && onegreater == 1)
                        addtolist += 1;
                }
                //to avoid the passing by reference issue, creates string with temp values, then puts it back into a copy array
                String tempstring = "";
                for (int x: temp)
                    tempstring += x + " ";
                String[] parts = tempstring.split(" ");
                int[] copy = new int[totalpiles];
                for (int w = 0; w < totalpiles; w++)
                    copy[w] = Integer.parseInt(parts[w]);
                //adds the copy array if it could not reach any losing position (making it a losing position)
                if (addtolist == 0)
                    losingPosition.add(copy);
                //if the beginning position is losing, the computer wants to go 2nd, and vice versa
                whosfirst = 0;
                for (int f = 0; f < totalpiles; f++)
                    if (stones[f] == copy[f])
                        whosfirst += 1;
                if (whosfirst == totalpiles && addtolist == 0 && first == 2)
                    first = 0;
                else if (whosfirst == totalpiles && addtolist != 0 && first == 2)
                    first = 1;
            }
            //passes temp so because it doesn't go back through and redefine all the temp[i], it keeps the old values and only
            //change some of the values, the ones that needed to be changed in order to keep increasing
            //passes level-1 to indicate one less recursion since one has been completed
            computerStratRecursion(temp, level-1);
        }
    }
    /**
     * Calculates the best move (which pile + how much to remove)
     */
    public void computerCalcs()
    {
        //iterates through each pile, and for removing every possible amount
        for (int i = 0; i < totalpiles; i++)
            for (int j = 1; j < stones[i]+1; j++)
            {
                int[] checker = new int[totalpiles];
                for (int n = 0; n < totalpiles; n++)
                    checker[n] = stones[n];
                checker[i] -= j;
                //checks this possible "move" of removing j stones from pile i against all the losingPositions
                for (int[] k: losingPosition)
                {
                    Arrays.sort(k);
                    int[] sortedchecker = new int[totalpiles];
                    for (int w = 0; w < totalpiles; w++)
                        sortedchecker[w] = checker[w];
                    Arrays.sort(sortedchecker);
                    //if removing j from pile i leads to one of the losing positions in the list, that move should be made.
                    if (Arrays.equals(sortedchecker, k))
                    {
                        pile = i+1;
                        removed = j;
                    }
                }
                //resets the array to continue checking all "j" removed possibilities
                checker[i] += j;
            }
        //if no losing position can be reached in one move, the computer is in a losing position and can only stall as long as
        //possible to allow for the other player to make a mistake eventually.
        if (pile == 0)
        {
            removed = 1;
            max = 0;
            for (int z = 0; z < totalpiles; z++)
                if (stones[z] > max)
                {
                    max = stones[z];
                    pile = z+1;
                }
        }
    }
    /**
     * Checks if the game is over + displays the winner
     * @param int movecounter to check whose move it is -> who won
     * @returns true if game is over, false if the game is not over
     */
    public boolean gameOver(int winner)
    {
        gameOverNum = 0;
        //checks if all the piles have 0 stones
        for (int i = 0; i < totalpiles; i++)
            if (stones[i] == 0)
                gameOverNum += 1;
        //ends the game
        if (gameOverNum == totalpiles)
        {
            gameOver = true;
            displayNim();
            if (winner % 2 == first)
                System.out.println("Computer has won the game.");
            else
                System.out.println("Player has won the game.");
            System.out.println("lmao you suck!");
        }
        return gameOver;
    }
}