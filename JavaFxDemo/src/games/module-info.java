module t10.gui.tictactoe {
    requires javafx.controls;
    requires java.desktop;
    requires javafx.fxml;
    exports DataBinding;
    opens t10.gui.tictactoe to javafx.graphics, javafx.fxml;
}