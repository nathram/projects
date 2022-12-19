/**
 * Creates the Game of Nim
 *
 * Nathra
 * 2/15/22
 */
import java.util.*;
public class Nim
{
    public int[] stones = {3, 5, 8};
    public ArrayList<int[]> losingPosition = new ArrayList<int[]>();
    public String statement = "";
    public static int first, max, pile = 0, removed = 0;
    public static boolean gameOver = false, badmove = true;
    Scanner thing = new Scanner(System.in);
    /**
     * Default constructor: defaults to {3, 5, 8} as the starting
     * @param int to indicate who goes first
     */
    public Nim(int firstplayer)
    {
        first = firstplayer;
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
     * @param int who goes first, int for # of stones in each pile
     */
    public Nim(int firstplayer, int pile1, int pile2, int pile3)
    {
        first = firstplayer;
        stones[0] = pile1;
        stones[1] = pile2;
        stones[2] = pile3;
        //iterates while one or more entries are invalid
        while (first != 0 && first != 1 && first != 2 || stones[0] < 1 || stones[1] < 1 || stones[2] < 1)
        {
            System.out.println("One or more invalid entries. Try again.");
            first = thing.nextInt();
            stones[0] = thing.nextInt();
            stones[1] = thing.nextInt();
            stones[2] = thing.nextInt();
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
        if (pile < 4 && pile > 0 && removed <= stones[pile-1] && removed > 0)
        {
            stones[pile-1] -= removed;
            badmove = false;
            statement = "Removed " + removed + " stone(s) from Pile " + pile + "\n" + "Board: ";
        }
        else
            statement = "Invalid entry. Please try again.";
        return statement;
    }
    /**
     * Populates the list of losing positions
     */
    public void computerStrat()
    {
        //adds the first losing position: {0,0,1}
        int[] firstArr = {0, 0, 1};
        losingPosition.add(firstArr);
        //iterates through every possible position
        for (int a = 0; a < stones[0]+1; a++)
            for (int b = 0; b < stones[1]+1; b++)
                for (int c = 0; c < stones[2]+1; c++)
                {
                    int[] temp = {a, b, c};
                    int addtolist = 0;
                    //if a certain position can reach a losing position in one move, it is a winning position, and is not
                    //added to the list. if it cannot reach a losing position in one move, it must be a losing position and is
                    //added to the list.
                    for (int[] k: losingPosition)
                    {
                        if (temp[0] == 0 && temp[1] == 0 && temp[2] == 0)
                            addtolist += 1;
                        else if (temp[0] == k[0] && temp[1] == k[1] && temp[2] > k[2] || temp[0] == k[0] && temp[2] == k[2] &&
                        temp[1] > k[1] || temp[1] == k[1] && temp[2] == k[2] && temp[0] > k[0])
                            addtolist += 1;
                    }
                    //adds it if it could not reach any losing position (making it a losing position)
                    if (addtolist == 0)
                        losingPosition.add(temp);
                    //if the beginning position is losing, the computer wants to go 2nd, and vice versa
                    if (stones[0] == a && stones[1] == b && stones[2] == c && addtolist == 0 && first == 2)
                        first = 0;
                    else if (stones[0] == a && stones[1] == b && stones[2] == c && addtolist != 0 && first == 2)
                        first = 1;
                }
    }
    /**
     * Calculates the best move (which pile + how much to remove)
     */
    public void computerCalcs()
    {
        //iterates through each pile, and for removing every possible amount
        for (int i = 0; i < 3; i++)
            for (int j = 1; j < stones[i]+1; j++)
            {
                int[] checker = new int[3];
                checker[0] = stones[0];
                checker[1] = stones[1];
                checker[2] = stones[2];
                checker[i] -= j;
                //if removing some j from pile i leads to one of the losing positions in the list, that move should be made.
                for (int[] k: losingPosition)
                {
                    Arrays.sort(k);
                    int[] sortedchecker = new int[3];
                    sortedchecker[0] = checker[0];
                    sortedchecker[1] = checker[1];
                    sortedchecker[2] = checker[2];
                    Arrays.sort(sortedchecker);
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
            pile = 1;
            removed = 1;
            if (stones[1] > stones[0])
                pile = 2;
            if (stones[2] > stones[1] && stones[2] > stones[0])
                pile = 3;
        }
    }
    /**
     * Checks if the game is over + displays the winner
     * @param int movecounter to check whose move it is -> who won
     * @returns true if game is over, false if the game is not over
     */
    public boolean gameOver(int winner)
    {
        if(stones[0] == 0 && stones[1] == 0 && stones[2] == 0)
        {
            gameOver = true;
            displayNim();
            if (winner % 2 == first)
                System.out.println("Computer has won the game.");
            else
                System.out.println("Player has won the game.");
            System.out.println("Thanks for playing!");
        }
        return gameOver;
    }
}