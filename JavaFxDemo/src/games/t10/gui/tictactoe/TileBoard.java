package t10.gui.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TileBoard {
    private InfoCenter infoCenter;
    private StackPane pane;
    private Tile[][] tiles = new Tile[3][3];

    private char playerTurn = 'X';
    private boolean gameOver = false;
    private String winner = "";
    private Line winningLine;

    public TileBoard(InfoCenter infoCenter) {
        this.infoCenter = infoCenter;
        this.pane = new StackPane();
        pane.setMinSize(UIConstants.APP_WIDTH, UIConstants.TITLE_BOARD_HEIGHT);
        pane.setTranslateX(UIConstants.APP_WIDTH / 2);
        pane.setTranslateY((UIConstants.TITLE_BOARD_HEIGHT/2)+UIConstants.INFO_CENTER_HEIGHT);

        addAllTiles();

        winningLine = new Line();
        pane.getChildren().add(winningLine);
    }

    private void addAllTiles() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX(column * 100 - 100);
                tile.getStackPane().setTranslateY(row * 100 - 100);
                pane.getChildren().add(tile.getStackPane());
                tiles[row][column] = tile;
            }
        }
    }

    public void changePlayerTurn() {
        if (playerTurn == 'X') {
            playerTurn = 'O';
        }
        else {
            playerTurn = 'X';
        }
        infoCenter.updateMessage("Player " + playerTurn + "'s turn");
    }

    public String getPlayerTurn() {
        return String.valueOf(playerTurn);
    }

    public void startNewGame() {
        gameOver = false;
        winner = "";
        playerTurn = 'X';
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                tiles[row][column].setLabelValue("");
            }
        }
        winningLine.setVisible(false);
        infoCenter.updateMessage("Player X's turn");
        infoCenter.gameButtonVisible(false);
    }

    public StackPane getStackPane() {
        return pane;
    }

    private class Tile {
        private StackPane pane;
        private Label label;
        public Tile() {
            pane = new StackPane();
            pane.setMinSize(100, 100);

            Rectangle border = new Rectangle();
            border.setHeight(100);
            border.setWidth(100);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            label = new Label(" ");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(event -> {
                if(label.getText().isEmpty() && !gameOver){
                    label.setText(getPlayerTurn());
                    changePlayerTurn();
                    checkForWinner();
                }
            });
        }

        public StackPane getStackPane() {
            return pane;
        }

        public String getLabelValue() {
            return label.getText();
        }

        public void setLabelValue(String value) {
            label.setText(value);
        }


    }

    private void checkForWinner() {
        checkRowsForWinner();
        checkColumnsForWinner();
        checkTopLeftToBottomRightForWinner();
        checkTopRightToBottomLeftForWinner();
        checkForDraw();

    }

    private void checkForDraw() {
        if(gameOver) return;
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                if(tiles[row][column].getLabelValue().isEmpty()) {
                    return;
                }
            }
        }
        gameOver = true;
        infoCenter.updateMessage("The game ended in a draw");
        infoCenter.gameButtonVisible(true);
    }

    private void checkTopLeftToBottomRightForWinner() {
        if(gameOver) return;
        if(tiles[0][0].getLabelValue().equals(tiles[1][1].getLabelValue()) &&
                tiles[1][1].getLabelValue().equals(tiles[2][2].getLabelValue()) &&
                !tiles[0][0].getLabelValue().isEmpty())
        {
            endGame(new WinningTiles(tiles[0][0], tiles[1][1], tiles[2][2]));
        }
    }

    private void checkTopRightToBottomLeftForWinner() {
        if(gameOver) return;
        if(tiles[0][2].getLabelValue().equals(tiles[1][1].getLabelValue()) &&
                tiles[1][1].getLabelValue().equals(tiles[2][0].getLabelValue()) &&
                !tiles[0][2].getLabelValue().isEmpty())
        {
            endGame(new WinningTiles(tiles[0][2], tiles[1][1], tiles[2][0]));
        }
    }

    private void checkColumnsForWinner() {
        if(gameOver) return;
        for(int column = 0; column < 3; column++) {
            if(tiles[0][column].getLabelValue().equals(tiles[1][column].getLabelValue()) &&
                    tiles[1][column].getLabelValue().equals(tiles[2][column].getLabelValue()) &&
                    !tiles[0][column].getLabelValue().isEmpty())
            {
                endGame(new WinningTiles(tiles[0][column], tiles[1][column], tiles[2][column]));
                return;
            }
        }
    }

    private void checkRowsForWinner() {
         for(int row = 0; row < 3; row++) {
             if(tiles[row][0].getLabelValue().equals(tiles[row][1].getLabelValue()) &&
                     tiles[row][1].getLabelValue().equals(tiles[row][2].getLabelValue()) &&
                     !tiles[row][0].getLabelValue().isEmpty())
             {
                 endGame(new WinningTiles(tiles[row][0], tiles[row][1], tiles[row][2]));
                 return;
             }
         }
    }

    private void endGame(WinningTiles winningTiles) {
        gameOver = true;
        this.winner = winningTiles.start.getLabelValue();
        drawWinningLine(winningTiles);
        infoCenter.updateMessage("Player " + winner + " won the game");
        infoCenter.gameButtonVisible(true);
    }

    private void drawWinningLine(WinningTiles winningTiles) {
        winningLine.setStartX(winningTiles.start.getStackPane().getTranslateX());
        winningLine.setStartY(winningTiles.start.getStackPane().getTranslateY());

        winningLine.setEndX(winningTiles.end.getStackPane().getTranslateX());
        winningLine.setEndY(winningTiles.end.getStackPane().getTranslateY());

        winningLine.setTranslateX(winningTiles.middle.getStackPane().getTranslateX());
        winningLine.setTranslateY(winningTiles.middle.getStackPane().getTranslateY());

        winningLine.setVisible(true);
    }

    private class WinningTiles {
        Tile start;
        Tile middle;
        Tile end;

        public WinningTiles(Tile start, Tile middle, Tile end) {
            this.start = start;
            this.middle = middle;
            this.end = end;
        }

    }
}
