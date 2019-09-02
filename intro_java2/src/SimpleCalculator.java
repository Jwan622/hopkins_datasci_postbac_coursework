/*
Jeffrey Wan
Class 605.210.81 SU19
Assignment 12
*/


import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class SimpleCalculator extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    public void start(Stage myStage) {
        myStage.setTitle("Simple Calculator");

        // have to use a Gridpane here because FlowPane tries to fit as many children into one size as possible (trying to use the preferred size.
        // Gridpane lends well to the columnar structure of this mockup
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        // setting horizontal and vertical gaps in between elements.
        grid.setHgap(10);
        grid.setVgap(10);
        //padding around the whole grid
        grid.setPadding(new Insets(25,25,25,25));
        Scene scene = new Scene(grid, 300, 275);

        // creating a label and text field and adding it to the first row of the grid, side by side using the column index.
        Label firstValueLabel = new Label("First Value:");
        grid.add(firstValueLabel, 0, 0);
        TextField firstValueField = new TextField("$");
        grid.add(firstValueField, 1, 0);

        // creating a label and text field and adding it to the second row of the grid, side by side using the column index.
        Label secondValueLabel = new Label("Second Value:");
        grid.add(secondValueLabel, 0, 1);
        TextField secondValueField = new TextField("$");
        grid.add(secondValueField, 1, 1);

        // creating a label and text field and adding it to the third row of the grid, side by side using the column index.
        Label thirdValueLabel = new Label("Sum is:");
        grid.add(thirdValueLabel, 0, 2);
        TextField thirdValueField = new TextField("$");
        thirdValueField.setEditable(false);
        grid.add(thirdValueField, 1, 2);

//         we don't want the sum field to be editable
        Button btnCalculate = new Button("Calculate");
        HBox hbBtn = new HBox(10);
        btnCalculate.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnCalculate);
        grid.add(btnCalculate, 1, 4);

        myStage.setScene(scene);
        myStage.show();
    }
}
