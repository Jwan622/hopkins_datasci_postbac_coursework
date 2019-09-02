import javafx.application.*;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.ActionEvent;


/**
 * This class builds a loan calculator that outputs monthly payments and total payment amounts after the user provides
 * the interest rate, number of years, and loan amount.
 * @author Jeffrey Wan
 * @version 1.0
 */
public class LoanCalculator extends Application {
    private TextField interestRateField;
    private TextField numberOfYearsField;
    private TextField loanAmtField;
    private TextField monthlyPaymentField;
    private TextField totalPaymentField;
    private Label response;


    public static void main(String args[]) {
        launch(args);
    }

    public void start(Stage myStage) {
        myStage.setTitle("Loan Calculator");

        // have to use a Gridpane here because FlowPane tries to fit as many children into one size as possible (trying to use the preferred size.
        // Gridpane lends well to the columnar structure of this mockup
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        // setting horizontal and vertical gaps in between elements.
        grid.setHgap(10);
        grid.setVgap(10);
        //padding around the whole grid
        grid.setPadding(new Insets(25,25,25,25));
        Scene scene = new Scene(grid, 500, 275);

        // creating a label and text field and adding it to the first row of the grid, side by side using the column index.
        Label firstValueLabel = new Label("Annual Interest Rate:");
        grid.add(firstValueLabel, 0, 0);
        interestRateField = new TextField();
        interestRateField.setPromptText("$");
        grid.add(interestRateField, 1, 0);

        // creating a label and text field and adding it to the second row of the grid, side by side using the column index.
        Label secondValueLabel = new Label("Number of Years:");
        grid.add(secondValueLabel, 0, 1);
        numberOfYearsField = new TextField();
        numberOfYearsField.setPromptText("$");
        grid.add(numberOfYearsField, 1, 1);

        // creating a label and text field and adding it to the third row of the grid, side by side using the column index.
        Label thirdValueLabel = new Label("Loan Amount:");
        grid.add(thirdValueLabel, 0, 2);
        loanAmtField = new TextField();
        loanAmtField.setPromptText("$");
        grid.add(loanAmtField, 1, 2);

        // creating a label and text field and adding it to the fourth row of the grid, side by side using the column index.
        Label fourthValueLabel = new Label("Monthly Payment:");
        grid.add(fourthValueLabel, 0, 3);
        monthlyPaymentField = new TextField();
        monthlyPaymentField.setPromptText("$");
        //this field shouldn't be editable
        monthlyPaymentField.setEditable(false);
        grid.add(monthlyPaymentField, 1, 3);

        // creating a label and text field and adding it to the fifth row of the grid, side by side using the column index.
        Label fifthValueLabel = new Label("Total Payment:");
        grid.add(fifthValueLabel, 0, 4);
        totalPaymentField = new TextField();
        totalPaymentField.setPromptText("$");
        //this field shouldn't be editable
        totalPaymentField.setEditable(false);
        grid.add(totalPaymentField, 1, 4);

//         we don't want the sum field to be editable
        Button btnCalculate = new Button("Calculate");
        HBox hbBtn = new HBox(10);
        btnCalculate.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnCalculate);
        // needed to attach the event listener/handler to the button object.
        btnCalculate.setOnAction(new CalculateHandler());
        grid.add(btnCalculate, 1, 5);


        // add a label to display error messages regarding inputs
        response = new Label("");
        grid.add(response, 1, 6);

        myStage.setScene(scene);
        myStage.show();
    }

    class CalculateHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            // have a guard close to ensure that the params are within valid ranges. this will return false if any of
            // the guards fail.
            if(checkParams(interestRateField.getText(), numberOfYearsField.getText(), loanAmtField.getText())) {
                double interestRate = Double.valueOf(interestRateField.getText());
                // making the assumption that years is an integer
                int numberOfYears = Integer.valueOf(numberOfYearsField.getText());
                double loanAmt = Double.valueOf(loanAmtField.getText());

                double monthlyPayment = calculateMonthlyPayment(interestRate, numberOfYears, loanAmt);

                monthlyPaymentField.setText(String.valueOf(monthlyPayment));
                totalPaymentField.setText(String.valueOf(calculateYearlyPayment(monthlyPayment, numberOfYears)));
            }
        };

        private double calculateMonthlyPayment(double interestRate, int numberOfYears, double loanAmt) {
            return (interestRate/12.0 * loanAmt) / (1 - Math.pow(1 + interestRate/12.0, -numberOfYears * 12.0));
        };

        private boolean checkParams(String interestRate, String numberOfYears, String loanAmt) {
            if(interestRate.trim().equals("") || numberOfYears.trim().equals("") || loanAmt.trim().equals("")) {
                response.setText("You cannot leave any field blank");
                return false;
            }
            if((Double.valueOf(interestRate) <= 0)) {
                // interest rate has to be between 0 and 1
                response.setText("Interest Rate cannot be negative");
                // reset the field with an incorrect value so the user can input correct values
                interestRateField.clear();
                return false;
            };
            if(Integer.valueOf(numberOfYears) <= 0) {
                response.setText("Number of Years cannot be negative");
                numberOfYearsField.clear();
                return false;
            };
            if(Double.valueOf(loanAmt) < 0) {
                response.setText("Loan Amount cannot be negative");
                loanAmtField.clear();
                return false;
            }

            // return true if all the gaurds pass
            return true;
        }

        private double calculateYearlyPayment(double monthlyPayment, int numberOfYears) {
            return 12 * monthlyPayment * numberOfYears;
        }
    }
}
