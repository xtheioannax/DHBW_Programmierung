module t10.gui.tictactoe {
    requires javafx.controls;
    opens t10.gui.tictactoe to javafx.graphics, javafx.fxml;
}