package com.Ben;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CCValidator extends JFrame
{
    // All the form objects.
    private JPanel rootPanel;
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton exitButton;
    private JLabel validMessageLabel;

    public CCValidator()
    {
        // This line just sets the text that shows up at the top of the window.
        super("Credit Card Validator");
        // Set up the form, make it visible, set it so the app exits when the window is closed.
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 150);

        // The event handler for the validate button.
        validateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Take the card number from the text field.
                String ccnumber = creditCardNumberTextField.getText();

                // Call the isValid method with the number the user entered.
                boolean valid = isVisaCreditCardNumberValid(ccnumber);

                // Change the result label to display whether or not the number is valid.
                if (valid)
                {
                    validMessageLabel.setText("Credit card number is valid.");
                }
                else
                {
                    validMessageLabel.setText("Credit card number is NOT valid.");
                }
            }
        });

        // The exit button. Just closes the program when clicked.
        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }

    private boolean isVisaCreditCardNumberValid(String cc)
    {
        // If the first digit is not a 4, return false right away.
        if (!cc.startsWith("4"))
        { return false; }

        // Initialize a total counter and create an array of the number's digits as characters.
        int total = 0;
        char[] chars = cc.toCharArray();

        // If it's not 16 digits, return false.
        if (chars.length != 16)
        { return false; }

        // Empty int array for the numeric values.
        int[] ints = new int[chars.length];

        // For each loop with a counter. Loops through characters in the char array and puts their int
        // equivalents into the int array.
        int i = 0;
        for (char c : chars)
        { ints[i] = Character.getNumericValue(c); i++; }

        // A for loop to do the totaling.
        for (int x = 0; x < ints.length; x++)
        {
            // If it's the first number, or every other number thereafter, just add that value to the total.
            if (x % 2 != 0)
            { total += ints[x]; }

            // Otherwise, double the number. If it's one digit, just add that digit. If it's two digits,
            // split it into digits and add each to the total individually.
            else
            {
                if (ints[x] * 2 < 10)
                { total += ints[x] * 2; }

                else
                {
                    int y = ints[x] * 2;
                    String s = Integer.toString(y);
                    char[] digits = s.toCharArray();

                    for (char d : digits)
                    { total += Character.getNumericValue(d); }
                }
            }
        }

        // Return whether or not the total is evenly divisible by 10.
        return total % 10 == 0;
    }
}
