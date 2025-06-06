package t10.gui.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TicTacToe  extends Application {
    private InfoCenter infoCenter;
    private TileBoard titleBoard;

    @Override
    public void start(Stage primaryStage) {
        try{
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);

            initLayout(root);

            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initLayout(BorderPane root) {
        initInfoCenter(root);
        initTitleBoard(root);
    }

    private void initTitleBoard(BorderPane root) {
        titleBoard = new TileBoard(infoCenter);
        root.getChildren().add(titleBoard.getStackPane());
    }

    private void initInfoCenter(BorderPane root) {
        infoCenter = new InfoCenter();
        infoCenter.setStartGameButtonAction(startNewGame());
        root.getChildren().add(infoCenter.getStackPane());
    }

    private EventHandler<ActionEvent> startNewGame(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                infoCenter.gameButtonVisible(false);
                infoCenter.updateMessage("Player X's turn");
                titleBoard.startNewGame();
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
