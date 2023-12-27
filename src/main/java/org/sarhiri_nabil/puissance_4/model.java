package org.sarhiri_nabil.puissance_4;


public class model {
    public final int[][] cases=new int[6][7];
    public int player=1;
    public boolean endGame=false;
    public int winner;


    //Set the player as the player who start he game and initialize the grid by calling function initCases()
    public void setGame(){
        this.player=1;
        initCases();
    }

    // initialize all the grid cases to 0:
    public void initCases(){
        for(int i=0;i<6;i++){
            for (int j=0; j<7;j++)
                this.cases[i][j]=0;
        }
    }

    //Change player who plays during the game
    public void changePlayer(){
        if(this.player==1){
            this.player=2;
        }
        else{
            this.player=1;
        }
    }

    //Allow a player to put a token in the grid
    public int[] playToken(int col){
        boolean played=false;
        int li=-1;
        while(!played){
            try{
                while( li<5 && this.cases[li+1][col]==0){
                    li=li+1;
                }
                if (li==-1){
                    System.out.println("column full, choose another column");
                }
                else {
                    if (this.player==1){
                        this.cases[li][col]=1;
                        played = true;
                    }
                    else if (this.player==2) {
                        this.cases[li][col]=2;
                        played = true;
                    }
                }
            }
            catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
                System.out.println("Please choose a number between 1 and 7");
            }
        }
        int[] tab= new int[2];
        tab[0]=li;
        tab[1]=col;
        return tab;
    }

    //Check if there is a horizontal alignment of 4 token
    public int checkHorizontal(){
        int winPlayer=0;
        for (int i=0;i<6;i++){
            for (int j=0;j<4;j++){
                if (this.cases[i][j]==1&&this.cases[i][j+1]==1&&this.cases[i][j+2]==1&&this.cases[i][j+3]==1){
                    winPlayer=1;
                }
                else if (this.cases[i][j]==2&&this.cases[i][j+1]==2&&this.cases[i][j+2]==2&&this.cases[i][j+3]==2){
                    winPlayer=2;
                }
            }
        }
        return winPlayer;
    }

    //Check if there is a vertical alignment of 4 token
    public int checkVertical(){
        int winPlayer=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<7;j++){
                if (this.cases[i][j]==1&&this.cases[i+1][j]==1&&this.cases[i+2][j]==1&&this.cases[i+3][j]==1){
                    winPlayer=1;
                }
                else if (this.cases[i][j]==2&&this.cases[i+1][j]==2&&this.cases[i+2][j]==2&&this.cases[i+3][j]==2){
                    winPlayer=2;
                }
            }
        }
        return winPlayer;
    }

    //Check if there is a diagonal alignment of 4 token in the right direction
    public int checkRightDiagonal(){
        int winPlayer=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                if (this.cases[i][j]==1&&this.cases[i+1][j+1]==1&&this.cases[i+2][j+2]==1&&this.cases[i+3][j+3]==1){
                    winPlayer=1;
                }
                else if (this.cases[i][j]==2&&this.cases[i+1][j+1]==2&&this.cases[i+2][j+2]==2&&this.cases[i+3][j+3]==2){
                    winPlayer=2;
                }
            }
        }
        return winPlayer;
    }

    //Check if there is a diagonal alignment of 4 token in the left direction
    public int checkLeftDiagonal(){
        int winPlayer=0;
        for (int i=0;i<3;i++){
            for (int j=3;j<7;j++){
                if (this.cases[i][j]==1&&this.cases[i+1][j-1]==1&&this.cases[i+2][j-2]==1&&this.cases[i+3][j-3]==1){
                    winPlayer=1;
                }
                else if (this.cases[i][j]==2&&this.cases[i+1][j-1]==2&&this.cases[i+2][j-2]==2&&this.cases[i+3][j-3]==2){
                    winPlayer=2;
                }
            }
        }
        return winPlayer;
    }

    //Check if the game is finished
    public void isEndGame(){
        int vert=checkVertical();
        int horiz=checkHorizontal();
        int ld=checkLeftDiagonal();
        int rd =checkRightDiagonal();
        if (vert==1 || horiz==1 || ld==1 || rd==1){
            this.winner=1;
            this.endGame=true;
            //System.exit(0);
        }
        else if (vert==2 || horiz==2 || ld==2 || rd==2){
            this.winner=2;
            this.endGame=true;
            //System.exit(0);
        }
    }

    //Display the game grid
    public void displayGrid(){
        for(int i=0;i<6;i++){
            for (int j=0; j<7;j++){
                System.out.print("["+this.cases[i][j]+"]");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


}
