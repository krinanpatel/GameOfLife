package conwaygame;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;

import javax.print.attribute.standard.MediaSize.NA;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {

        StdIn.setFile(file);
        int row = StdIn.readInt(); //reads number of rows (first line)
        int col = StdIn.readInt(); //?? does it automaticcal read "2nd" line//reads number of columns (second line)
         totalAliveCells=0; //call this somethig else????  ********
        grid = new boolean[row][col]; 
       
        for (int i=0; i<row; i++){    //goes through each r and c to read t or f
            for (int j=0; j<col; j++){
                boolean element = StdIn.readBoolean();
                grid[i][j] = element;
                if (element == true){
                    //grid[i][j]=ALIVE;
                    totalAliveCells=totalAliveCells+1;
                    //System.out.print("LOL"+ totalAliveCells);
                }
                
            } 
         }
      }
    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {
         return grid[row][col];
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

            System.out.println(totalAliveCells);
            if (totalAliveCells>0) {
                return true;
            }
            return false;
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    private int[][] getNeighbors (int row, int col) {
        int neighbors[][] = new int[8][2];
        int rows = grid.length;
        int cols = grid[0].length;
        int neighborNow = -1;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col) 
                continue;
  
                int a = i;
                int b = j;
                if (b == -1)
                    b = cols - 1;
                else if (b == cols)
                    b = 0;
               
                if (a == -1)
                    a = rows - 1;
                else if (a == rows)
                    a = 0;
                
                neighborNow++;
                neighbors[neighborNow][0] = a;
                neighbors[neighborNow][1] = b;
            }
        }
        return neighbors;
    }
 
    public int numOfAliveNeighbors (int row, int col) {
        //     int count=0;
        //     for (int i=-1; i<=1; i++){ //looks at the neighbors staritng with one row behind and one row ahead
        //         for (int j=-1; j<=1; j++){
        //             int a = (row+i+(grid[0].length))%(grid[0].length); //col=number of columns
        //             int b = (col+j+grid.length)%grid.length;
        //            if (grid[a][b]==true){ //changed i and j to a and b //****check the variables a and b */
        //               count++;
        //         }
        //     }
        // }
        // if (grid[row][col]==true){ 
        //     count = count -1;
        // }
        // return count;
        int[][] neighbors = getNeighbors(row, col);
       int numberofAlives = 0;
       for (int i = 0; i < neighbors.length; i++) {
           if (getCellState(neighbors[i][0], neighbors[i][1]))
           numberofAlives++;
       }
       return numberofAlives;
} 
   /* -------------------------------------------    
        for (row=0; row<grid.length; row++){
            for (col=0; col<row; col++){
                if (grid[row-1][col]==true) {
                    count=count+1;
                }
                if (grid[row+1][col]==true){
                    count=count+1;
                }
                if (grid[row-1][col-1]==true) {
                    count=count+1;
                }
                if (grid[row-1][col+1]==true) {
                    count=count+1;
                }
                if (grid[row+1][col-1]==true) {
                    count=count+1;
                }
                if (grid[row+1][col+1]==true) {
                    count=count+1;
                }
                if (grid[row][col-1]==true) {
                    count=count+1;
                }
                if (grid[row][col+1]==true) {
                    count=count+1;
                }
            }
        }
            return count;}*/

                
                    //do we indicate true && false with 0 and 1

          

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {
       
        boolean [][] future = new boolean [grid.length][grid[0].length];
    
        for (int row=0; row<grid.length; row++){
            for (int col=0; col<grid[0].length; col++){
                int numberofAlives = numOfAliveNeighbors(row, col);
                boolean currentState = getCellState(row, col);
                boolean newState;
               
               if (currentState==true){
                   if ((numberofAlives==0) || (numberofAlives==1)){
                       newState=false;
                   }
                   else if (numberofAlives == 2 || numberofAlives ==3){
                        newState=true;
                   }
                   else if (numberofAlives>=4){
                       newState=false;
                   }
                   else newState =false;
                   }
                else{
                    if (numberofAlives==3){
                        newState=true;
                    }
                    else {
                        newState=false;
                    }
                }
                future[row][col]=newState;
            }
        }
        return future;
    }
               
               
               
    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */  

     public void nextGeneration () { 
       grid=computeNewGrid();
       int nAlive=0;
       for (int row=0; row<grid.length; row++){  
        for (int col=0; col<grid[0].length; col++){
            if (grid[row][col]==true){ 
                 nAlive++;
            }

     }
    }
    totalAliveCells=nAlive;
     //****check this */
 }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {
        for (int i=0; i<n; i++){
            nextGeneration();
        }
        
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {
        int rows=grid.length;
        int cols=grid[0].length;
        int roots=0;
        WeightedQuickUnionUF var = new WeightedQuickUnionUF(rows, cols);
        for (int row=0; row<grid.length; row++){
            for (int col=0; col<grid[0].length; col++){
                if (getCellState(row, col)){
                    int [][] newneighbor = getNeighbors(row, col);
                    for (int i = 0; i<newneighbor.length; i++)
                    {
                        int newrow = newneighbor[i][0];
                        int newcol = newneighbor[i][1];
                        if (getCellState(newrow, newcol)){
                            var.union(row, col, newrow, newcol);
                        }

                    }   
             }
            }
        }
        int [][]index= new int[rows][cols];
        for (int row=0; row<grid.length; row++){
            for (int col=0; col< grid[0].length; col++){
                if (getCellState(row, col)){
                    index[row][col]=var.find(row, col);
                }
                else{
                    index[row][col]=-1;
                }
            }
        }
        int allCells = rows * cols;
       int[] counter = new int[allCells];
       for (int row = 0; row < grid.length; row++) {
           for (int col = 0; col < grid[0].length; col++) {
               if (index[row][col] >= 0)
                   counter[index[row][col]]++;
           }
       }

       for (int row = 0; row < counter.length; row++) {
        if (counter[row] > 0){
            roots++;
        }
    }
    return roots;
}
       

    }

