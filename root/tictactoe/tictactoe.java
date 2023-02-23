package root.tictactoe;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.HashSet;


public class tictactoe {

    static Scanner scan = new Scanner(System.in);

    public static class Game {
    
        public String board = "\n+---+---+---+\n| 1 | 2 | 3 |\n+---+---+---+\n| 4 | 5 | 6 |\n+---+---+---+\n| 7 | 8 | 9 |\n+---+---+---+\n";
        boolean gameDone = false;
        Set<Integer> positionsToWin1 = Set.of(1,2,3);
        Set<Integer> positionsToWin2 = Set.of(4,5,6);
        Set<Integer> positionsToWin3 = Set.of(7,8,9);
        Set<Integer> positionsToWin4 = Set.of(1,4,7);
        Set<Integer> positionsToWin5 = Set.of(2,5,8);
        Set<Integer> positionsToWin6 = Set.of(3,6,9);
        Set<Integer> positionsToWin7 = Set.of(3,5,7);
        Set<Integer> positionsToWin8 = Set.of(1,5,9);
        Set <Set<Integer>> allPositionsToWin = Set.of(positionsToWin1,positionsToWin2,positionsToWin4,positionsToWin5,positionsToWin6,positionsToWin7,positionsToWin8);

        Game(String board, Set <Set<Integer>> allPositionsToWin){
            this.board = board;
            this.allPositionsToWin = allPositionsToWin;

        }
        
        public boolean checkIfGameDone 
    
    }



    public static class Player {
        
        String symbol; /*"X" or "O"*/
        Integer id;
        Set<Integer> plays = new HashSet<Integer>(); //positions on the board

        Player(String symbol, Integer id, Set<Integer> plays) {
            this.symbol = symbol;
            this.id = id;
            this.plays = plays;
        }
    }

    public static String pickAPlayer() {
    /* This class asks the user to pick a player : X or O */
        String userPlays = "";
        while(userPlays.length() == 0){
            System.out.println("Pick a player : X or O ?");        
            String input = scan.nextLine();

            if (input.equalsIgnoreCase("X") || input.equalsIgnoreCase("O")) {
                userPlays = input.toUpperCase();
            }
            else {
                System.out.println("Wrong input, you must pick either \"X\" or \"O\" !");
                
            }
        } 
        return userPlays;
    }
    
    public static String player2Setup(String player1Plays) {
        String player2Plays = "";
        if(player1Plays.equals("X")) {
            player2Plays = "O";
        }
        else{
            player2Plays = "X";
        }
        return player2Plays;
    }

    public static String userPlays(String board, String playerSymbol, Integer playerId) {

        String input = "";
        String updatedBoard = "";
        
        while(updatedBoard == board || updatedBoard == ""){
            System.out.println("Player "+playerId+" ("+playerSymbol+"), where would you like to play ?\n");
            input = scan.nextLine();
            boolean b = Pattern.matches("[1-9]", input);

            if(b == true){
                if(updatedBoard == board){
                    System.out.println("You entered an already occupied position, stay focused ! ;)");
                }
                else{
                    updatedBoard = board.replace(input, playerSymbol);
                }
            }
            else{
                System.out.println("Wrong input, you must input an only integral number between 1 and 9 !");
            }
        }
        return updatedBoard;  
    }        

    public static void main(String[] args){
        System.out.println("Welcome to a brand-new, original and innovative game : Tic-Tac-Toe !\n");        
        
        /*User is asked to pick between X and O*/
        Player player1 = new Player("", 1, null);
        player1.symbol = pickAPlayer();
        System.out.println("You picked "+player1.symbol+".\n");

        /*Sets the bot symbol between X and O depending on the user's choice*/
        Player player2 = new Player("", 2, null);
        player2.symbol = player2Setup(player1.symbol);
        System.out.println("Your opponent kindly accepts your choice and plays "+player2.symbol+".\n");

        /*Creates games and displays empty board*/
        System.out.println("Let's start this game !\n");
        Game game = new Game(null, null, null);
        String board = game.board;

        System.out.println("Here is an empty board :\n"+board);
        System.out.println("To play, just type the number where you'd like to play and press Enter.\n");
        System.out.println("Be careful, as this game is still in development it is not able to detect by itself when the game is done.\n");

        /*Loops until the game is done*/
        while(true){
            board = userPlays(board, player1.symbol, player1.id);
            System.out.println("Player 1 ("+player1.symbol+") played :\n"+board);
            board = userPlays(board, player2.symbol, player2.id);
            System.out.println("Player 2 ("+player2.symbol+") played :\n"+board);
        }
    }
}
    /* To do :

Bug si on rejoue au même endroit
Regex pour récupérer les chiffres dans la string board

     * On implémentera par la suite un moyen de faire en sorte que le jeu sache tout seul qui a gagné
     * Commencer par un 2 joueurs ça sera déjà pas mal
     * Faire une doc correcte
     * Changer les if par des try catch
     * Random who plays first ?
     */


     /* Version simple :
        On affiche un unique string 
+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
| 7 | 8 | 9 |
+---+---+---+
        et chaque play on utilise string.replace("userinput", "user.symbol")
        ça sera déjà pas mal surtout qu'il faut ensuite gérer les placements du bot
      */

      /* dans un second temps on pourra complexifier en transformant le string board en class
       à part :
       
            public static class Board {
        // saves the coordinates of each plays mapped by key:value pairs like 2:"X"
        Map<Integer,String> plays = new HashMap<Integer,String>();

        Board(Map<Integer,String> plays) {
            this.plays = plays;
        }
    }

       */