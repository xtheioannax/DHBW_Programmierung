module t10.gui.tictactoe {
    requires javafx.controls;
    requires java.desktop;
    opens t10.gui.tictactoe to javafx.graphics, javafx.fxml;
}