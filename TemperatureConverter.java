import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends Frame {

    // Declare UI components
    Label labelInput, labelOutput;
    TextField textInput, textOutput;
    Choice choiceInput, choiceOutput;
    Button convertButton;

    public TemperatureConverter() {
        // Frame setup
        setTitle("Temperature Converter");
        setSize(400, 250);
        setLayout(new FlowLayout());

        // Set background color for the frame
        setBackground(Color.LIGHT_GRAY);

        // Input Label
        labelInput = new Label("Enter Temperature:");
        add(labelInput);

        // Input Text Field
        textInput = new TextField(10);
        add(textInput);

        // Input Choice (Celsius, Fahrenheit, Kelvin)
        choiceInput = new Choice();
        choiceInput.add("Celsius");
        choiceInput.add("Fahrenheit");
        choiceInput.add("Kelvin");
        add(choiceInput);

        // Output Label
        labelOutput = new Label("Converted Temperature:");
        add(labelOutput);

        // Output Text Field (Read-Only)
        textOutput = new TextField(10);
        textOutput.setEditable(false);
        textOutput.setBackground(Color.WHITE);
        add(textOutput);

        // Output Choice (Celsius, Fahrenheit, Kelvin)
        choiceOutput = new Choice();
        choiceOutput.add("Celsius");
        choiceOutput.add("Fahrenheit");
        choiceOutput.add("Kelvin");
        add(choiceOutput);

        // Convert Button
        convertButton = new Button("Convert");
        
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
        add(convertButton);

        // Window close operation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    // Temperature conversion logic
    private void convertTemperature() {
        try {
            // Trim whitespace to prevent errors from accidental spaces
            double inputTemp = Double.parseDouble(textInput.getText().trim());
            String inputUnit = choiceInput.getSelectedItem();
            String outputUnit = choiceOutput.getSelectedItem();
            double outputTemp;

            // Convert input to Celsius first as a base unit
            if (inputUnit.equals("Fahrenheit")) {
                inputTemp = (inputTemp - 32) * 5.0 / 9.0;
            } else if (inputUnit.equals("Kelvin")) {
                inputTemp = inputTemp - 273.15;
            }
            // If Celsius, no change needed

            // Convert from Celsius base to selected output unit
            if (outputUnit.equals("Fahrenheit")) {
                outputTemp = (inputTemp * 9.0 / 5.0) + 32.0;
            } else if (outputUnit.equals("Kelvin")) {
                outputTemp = inputTemp + 273.15;
            } else { // Output is Celsius
                outputTemp = inputTemp;
            }

            // Display the result formatted to 2 decimal places
            textOutput.setText(String.format("%.2f", outputTemp));

        } catch (NumberFormatException ex) {
            textOutput.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}