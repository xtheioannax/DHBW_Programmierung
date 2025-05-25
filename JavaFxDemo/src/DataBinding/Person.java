package DataBinding;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Person extends Application {
    private final StringProperty name = new SimpleStringProperty();

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public void start(Stage primaryStage){
        Person person = new Person();
        person.setName("John");

        TextField textField = new TextField();
        Label label = new Label("Name: ");

        // Bidirektionales DataBinding.Binding zwischen TextFiels & Model
        textField.textProperty().bindBidirectional(person.nameProperty());


        // Unidirektionales DataBinding.Binding vom Label zum Model
        label.textProperty().bind(person.nameProperty());

        VBox root = new VBox(10, textField, label);
        Scene scene = new Scene(root, 300, 150);

        primaryStage.setTitle("Data DataBinding.Binding");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
