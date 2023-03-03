package root.tictactoe;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.HashSet;


public class tictactoe {

    // Scanner object opened to listen for user input throughout the program
    static Scanner scan = new Scanner(System.in);

    public static class Game {
    /*This class is used to define the game's properties :
     * A 3x3 board represented in ASCII art string, with coordinates represented as integers from 1 to 9;
     * A set of 8 combinations of 3 integers representing the known winning positions;
     * And a method to check whether the game is done or not.
    */
        String board = "\n+---+---+---+\n| 1 | 2 | 3 |\n+---+---+---+\n| 4 | 5 | 6 |\n+---+---+---+\n| 7 | 8 | 9 |\n+---+---+---+\n";
        Set<Integer> positionsToWin1 = Set.of(1,2,3);
        Set<Integer> positionsToWin2 = Set.of(4,5,6);
        Set<Integer> positionsToWin3 = Set.of(7,8,9);
        Set<Integer> positionsToWin4 = Set.of(1,4,7);
        Set<Integer> positionsToWin5 = Set.of(2,5,8);
        Set<Integer> positionsToWin6 = Set.of(3,6,9);
        Set<Integer> positionsToWin7 = Set.of(3,5,7);
        Set<Integer> positionsToWin8 = Set.of(1,5,9);
        Set <Set<Integer>> allPositionsToWin = Set.of(positionsToWin1,positionsToWin2,positionsToWin4,positionsToWin5,positionsToWin6,positionsToWin7,positionsToWin8);

        Game(){ //Game object constructor, setting the properties of objects in class Game
            this.board = "\n+---+---+---+\n| 1 | 2 | 3 |\n+---+---+---+\n| 4 | 5 | 6 |\n+---+---+---+\n| 7 | 8 | 9 |\n+---+---+---+\n";
            this.allPositionsToWin = Set.of(positionsToWin1,positionsToWin2,positionsToWin4,positionsToWin5,positionsToWin6,positionsToWin7,positionsToWin8);
        }

        public Boolean checkIfGameDone(Set<Integer> plays, Set <Set<Integer>> allPositionsToWin) {
            
            Boolean gameDone;
            
            if (allPositionsToWin.contains(plays)) {
                gameDone = true;
            }
            else {
                gameDone = false;
            }
            return gameDone;
        }
    }

    public static class Player {
        /*A class defining the properties of a Player object :
         * It bears a symbol chosen between X and O;
         * An ID to differenciate Player 1 and Player 2;
         * And a set of integers to record each player's plays throughout the game.
         */
        String symbol; /*"X" or "O"*/
        Integer id;
        Set<Integer> plays = new HashSet<Integer>(); //positions on the board

        Player(String symbol, Integer id) {
            this.symbol = symbol;
            this.id = id;
        }
    }

    public static String pickAPlayer() {
    /* This method asks the user to pick a player between X or O*/
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
        /* This method sets the symbol of Player 2 according to the choice of Player 1*/
        String player2Plays = "";
        if(player1Plays.equals("X")) {
            player2Plays = "O";
        }
        else{
            player2Plays = "X";
        }
        return player2Plays;
    }

    public static String userPlays(String board, String playerSymbol, Integer playerId, Set<Integer> plays, Set<Integer> otherPlayerPlays) {
        /* This method manages the action of a user playing :
         * First it asks the user for an input and checks it matches the expected form : an integer between 1 and 9;
         * Then it checks if the input is already present in the recorded plays of each player, to ensure every position can be played an only time;
         * If it's ok, the input is recorded in user's plays and the game board is updated.
         */
        String input = "";
        String updatedBoard = "";
        
        while(updatedBoard == board || updatedBoard == ""){
            System.out.println("Player "+playerId+" ("+playerSymbol+"), where would you like to play ?\n");
            input = scan.nextLine();
            boolean b = Pattern.matches("[1-9]", input);

            if(b == true){
                if(otherPlayerPlays.contains(Integer.parseInt(input)) == true || plays.contains(Integer.parseInt(input)) == true){
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
        plays.add(Integer.parseInt(input));
        return updatedBoard;
    }        

    public static void main(String[] args){
        System.out.println("Welcome to a brand-new, original and innovative game : Tic-Tac-Toe !\n");        
        
        /*User is asked to pick between X and O*/
        Player player1 = new Player("", 1);
        player1.symbol = pickAPlayer();
        System.out.println("You picked "+player1.symbol+".\n");

        /*Sets the bot symbol between X and O depending on the user's choice*/
        Player player2 = new Player("", 2);
        player2.symbol = player2Setup(player1.symbol);
        System.out.println("Your opponent kindly accepts your choice and plays "+player2.symbol+".\n");

        /*Creates games and displays empty board*/
        System.out.println("Let's start this game !\n");
        Game game = new Game();
        String board = game.board;
        Set<Set<Integer>> allPositionsToWin = game.allPositionsToWin;

        System.out.println("Here is an empty board :\n"+board);
        System.out.println("To play, just type the number where you'd like to play and press Enter.\n");

        /*Loops until the game is done*/
        while(true){
            /* Player 1 plays */
            board = userPlays(board, player1.symbol, player1.id, player1.plays, player2.plays);
            System.out.println("Player 1 ("+player1.symbol+") played :\n"+board);
            
            if (game.checkIfGameDone(player1.plays, allPositionsToWin) == true) {
                System.out.println("Player 1 won !\n");
                System.out.println("Thanks for playing :-)\n");
                System.exit(0);
            }
            /* Player 2 plays */
            board = userPlays(board, player2.symbol, player2.id, player2.plays, player1.plays);
            System.out.println("Player 2 ("+player2.symbol+") played :\n"+board);

            if (game.checkIfGameDone(player2.plays, allPositionsToWin) == true) {
                System.out.println("Player 2 won !\n");
                System.out.println("Thanks for playing :-)\n");
                System.exit(0);
            }
        }
    }
}