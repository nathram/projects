/**
 * Plays the Game of Nim for any number of piles
 *
 * Nathra
 * 2/15/22
 */
import java.util.*;
public class GameOfNimCustom
{
    public static void main(String[] args)
    {
        Scanner thing = new Scanner(System.in);
        //counts whose move it is
        int movecounter = 1;
        System.out.println("Enter 0 to go first, 1 to go second, or 2 to let the Computer to choose. Then enter the total number of piles.");
        NimCustom game = new NimCustom(thing.nextInt(), thing.nextInt());
        //calls computerStratBase to populate the list of losing positions based on the stones in each pile
        game.computerStratBase();
        //iterates while the game is not over (at least one pile has a stone)
        while(game.gameOver(movecounter) == false)
        {
            //displays the board at the start of each turn
            game.displayNim();
            //iterates as long as the move is valid. if not valid, in the move method it makes you input values again
            while(game.badmove == true)
            {
                //checks whose turn it is
                if (movecounter % 2 == game.first)
                {
                    System.out.println("Computer's move.");
                    //calculates the best move and makes it
                    game.computerCalcs();
                    System.out.println(game.move(game.pile, game.removed));
                }
                else
                {
                    System.out.println("Player's move. Please input the pile number and number of stones.");
                    //makes the move based on player's input
                    System.out.println(game.move(thing.nextInt(), thing.nextInt()));
                }
            }
            //resets the variable + increases the move counter
            game.badmove = true;
            movecounter++;
        }
    }
}