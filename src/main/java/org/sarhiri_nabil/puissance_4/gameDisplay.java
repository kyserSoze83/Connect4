package org.sarhiri_nabil.puissance_4;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class gameDisplay extends Application {
    static model game = new model();
    int MAX_WIDTH = 1520;
    int MAX_HEIGHT = 780;
    static GridPane buttonGrid=new GridPane();
    StackPane info = new StackPane();
    static Circle[][] circleTab=new Circle[6][7];
    static GridPane gameGrid=new GridPane();
    static int colPlayed;

    static int[]coordPlayed=new int[2];

    static Text playerInfo = new Text("It is up to player "+game.player);

    //static StackPane left = new StackPane();
    static FlowPane left = new FlowPane(Orientation.VERTICAL,20,20);
    static Text winnerIs=new Text("The winner is player "+game.winner);

    static Button restart=new Button("Restart");


    @Override
    public void start(Stage primaryStage){
    game.setGame();


    gameGrid.setStyle("-fx-border-color: black;-fx-alignment:center;-fx-background-color: blue");

    for(int i=0;i<6;i++){
        for(int j=0;j<7;j++){
            Circle circle = new Circle();
            circle.setRadius(48);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.WHITE);
            circleTab[i][j]=circle;
            gameGrid.add(circle,j,i);
            gameGrid.setVgap(10);
            gameGrid.setHgap(10);
        }
    }


    for (int i=0;i<7;i++){
        Button button=new Button("Col "+(i+1));
        changeCircleColorHandler handler= new changeCircleColorHandler(game);
        int finalI = i;
        button.setOnAction(event -> {
                    colPlayed= finalI;
                    handler.handle();
                });
        button.setPrefSize(50,50);
        buttonGrid.add(button,i,0);
        buttonGrid.setMinHeight(70);
        buttonGrid.setStyle("-fx-alignment: center");
        buttonGrid.setHgap(58);
    }


    info.setMinWidth((double) MAX_WIDTH /4);
    info.getChildren().add(playerInfo);
    info.setStyle("-fx-border-color: black; -fx-alignment: center");

    left.setMinWidth((double) MAX_WIDTH /4);
    left.getChildren().add(winnerIs);
    left.getChildren().add(restart);
    winnerIs.setVisible(false);
    restart.setVisible(false);
    left.setStyle("-fx-border-color: black;-fx-alignment: center");

    BorderPane mainPane = new BorderPane();
    mainPane.setCenter(gameGrid);
    mainPane.setRight(info);
    mainPane.setLeft(left);
    mainPane.setBottom(buttonGrid);
    Scene gameScene= new Scene(mainPane,MAX_WIDTH,MAX_HEIGHT );



    primaryStage.setScene(gameScene);
    primaryStage.setTitle("CONNECT 4");
    primaryStage.show();


}


public static class changeCircleColorHandler {
        model game;
    public void resetGame() {
        game.setGame();  // Appelle setGame pour réinitialiser l'état du jeu
        // Réinitialisation de l'affichage des éléments graphiques
        buttonGrid.setVisible(true);
        playerInfo.setVisible(true);
        winnerIs.setVisible(false);
        restart.setVisible(false);
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                circleTab[i][j].setFill(Color.WHITE);
            }
        }
        System.out.println("New game \n");
        //handle();
    }

    changeCircleColorHandler(model game){
        this.game=game;
    }

    public void handle() {
        coordPlayed=game.playToken(colPlayed);
        while (coordPlayed[0]==-1 && coordPlayed[1]==-1){
            System.out.println("column full, choose another column 2");
            coordPlayed=game.playToken(colPlayed);
            break;
        }
        int li=coordPlayed[0];
        int col=coordPlayed[1];
        game.displayGrid();

        game.changePlayer();
        if (game.player==1){
            circleTab[li][col].setFill(Color.YELLOW);
        }
        if (game.player==2){
            circleTab[li][col].setFill(Color.RED);
        }
        playerInfo.setText("It is up to player "+game.player);
        game.isEndGame();
        if (game.endGame){
            restart.setOnAction(event -> resetGame());
            buttonGrid.setVisible(false);
            playerInfo.setVisible(false);
            winnerIs.setVisible(true);
            restart.setVisible(true);
            winnerIs.setText("The winner is the player "+game.winner);

        }
    }
}



public static void main(String[] args){
        launch(args);
        game.setGame();
}
}


