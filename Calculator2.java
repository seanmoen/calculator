/*
 * Author: Sean Moen
 * A class to create a window for a simple calculator that takes an input of natural numbers
 * with support for binary addition, subtraction, multiplication, division
 * and a square root function.
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator2 extends JFrame implements ActionListener
{
	private final int NUM_BUTTONS = 18;
	private JButton buttons[] = new JButton[NUM_BUTTONS];
	
	//constants for button captions
	private final String S_ONE = "1";
	private final String S_TWO = "2";
	private final String S_THREE = "3";
	private final String S_FOUR = "4";
	private final String S_FIVE = "5";
	private final String S_SIX = "6";
	private final String S_SEVEN = "7";
	private final String S_EIGHT = "8";
	private final String S_NINE = "9";
	private final String S_ZERO = "0";
	private final String S_PLUS = "+";
	private final String S_MINUS = "-";
	private final String S_MULTIPLY = "*";
	private final String S_DIVIDE = "/";
	private final String S_RESULT = "=";
	private final String S_RESET = "AC";
	private final String S_SQRT = "sqrt";
	private final String S_BACKSPACE = "del";
	
	//constants for error messages
	private final String ERROR_INVALID_INPUT = "ERROR: INVALID INPUT";
	private final String ERROR_DIVIDE_BY_ZERO = "ERROR: DIVIDE BY ZERO";
	private final String ERROR_IMAGINARY_NUMBER = "ERROR: RESULT IS IMAGINARY";


	//
	private String captions[] = {	
									S_ONE, S_TWO, S_THREE, S_FOUR,
									S_FIVE, S_SIX, S_SEVEN, S_EIGHT,
									S_NINE, S_ZERO, S_SQRT, S_BACKSPACE,
									S_PLUS, S_MINUS, S_MULTIPLY, S_DIVIDE,
									S_RESET, S_RESULT
								};
	
	private JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
	private JTextField output = new JTextField(20);
	Container frame;
	private String input;
	
	/**
	 * Initializes the calculator, creating a window with a textfield
	 * to display input and output. Also creates a button panel from
	 * with buttons from the captions String and assigns a listener
	 * to each button.
	 */
	public Calculator2()
	{
		input = new String();
		frame = getContentPane();
		for (int count = 0; count < buttons.length; count++)
		{
			buttons[count] = new JButton(captions[count]);
			buttonPanel.add(buttons[count]);
			buttons[count].addActionListener(this);
		}
		
		frame.setLayout(new BorderLayout());
		frame.add(output, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}
	
	/**
	 * Includes all event handling by checking the source of the action.
	 * Simple Inputs (e.g. "1" and "del") are handled entirely in here.
	 * Other event handlers use helper functions.
	 * Sets the textfield to the value of input, except for
	 * result and sqrt which instead set the textfield to the result
	 */
	public void actionPerformed(ActionEvent ae)
	{
		//1
		if(ae.getSource() == buttons[0])
		{
			input = input + 1;
		}
		
		//2
		if(ae.getSource() == buttons[1])
		{
			input = input + 2;
		}
		
		//3
		if(ae.getSource() == buttons[2])
		{
			input = input + 3;
		}
		
		//4
		if(ae.getSource() == buttons[3])
		{
			input = input + 4;
		}
		
		//5
		if(ae.getSource() == buttons[4])
		{
			input = input + 4;
		}
		
		//6
		if(ae.getSource() == buttons[5])
		{
			input = input + 6;
		}
		
		//7
		if(ae.getSource() == buttons[6])
		{
			input = input + 7;
		}
		
		//8
		if(ae.getSource() == buttons[7])
		{
			input = input + 8;
		}
		
		//9
		if(ae.getSource() == buttons[8])
		{
			input = input + 9;
		}
		
		//0
		if(ae.getSource() == buttons[9])
		{
			input = input + 0;
		}
		
		//backspace
		if(ae.getSource() == buttons[11])
		{
			if(!input.isEmpty()) 
			{
				input = input.substring(0, input.length() - 1);
			}
		}
		
		//+
		if(ae.getSource() == buttons[12])
		{
			if(isOperatorAddable())
			{
				input = input + "+";
			}
		}
		
		//-
		if(ae.getSource() == buttons[13])
		{
			if(isOperatorAddable())
			{
				input = input + "-";
			}
		}
		
		//*
		if(ae.getSource() == buttons[14])
		{
			if(isOperatorAddable())
			{
				input = input + "*";
			}
		}
		
		// "/"
		if(ae.getSource() == buttons[15])
		{
			if(isOperatorAddable())
			{
				input = input + "/";
			}
		}
		
		//reset
		if(ae.getSource() == buttons[16])
		{
			resetInput();
		}
		
		//Updates the textfield with the input, if result or sqrt is called
		//then it will set it again to their output
		output.setText(input);
		
		//=
		if(ae.getSource() == buttons[17])
		{
			output.setText(getResult());
			resetInput();
		}
		
		//sqrt
		if(ae.getSource() == buttons[10])
		{
			output.setText(getSqrt());
			resetInput();
		}
		
		
	}
	
	/*
	 * Sets the input to an empty string.
	 */
	private void resetInput()
	{
		input = new String();
	}
	
	/*
	 * Checks if adding an operator to the input would ensure a binary operation
	 * by checking if the input is not empty and that there is currently no operator currently present.
	 * @return true if an operator can be added to ensure a binary operation, returns false otherwise
	 */
	private boolean isOperatorAddable()
	{
		//check if input is not empty and if it doesn't contain an operator
		if(input.isEmpty())
			return false;
		if(input.contains("+") || input.contains("-") 
				|| input.contains("*") || input.contains("/"))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the result of the equation or an error message.
	 * Returns an empty string if the input is empty 
	 * If there is only one operand, returns the operand. Needed for implementation of sqrt
	 * For a binary equation returns the result or error message for division by zero.
	 * If there is an error in input, an invalid input error is returned.
	 * @return A string containing the result of the equation, or an appropriate error message.
	 */
	private String getResult()
	{
		
		//if empty, nothing needs to be done
		if(input.isEmpty())
			return "";
		
		//if an operator can be added, that means there isn't a problem to be solved
		//but if its not empty, then the input is equal to itself
		if(isOperatorAddable())
			return input;
		
		//find operator
		char operator = getOperator(); 
		int operatorPos = input.indexOf(operator);
		if(input.substring(operatorPos + 1).equals(""))
			return ERROR_INVALID_INPUT;
		
		//find operands - substrings are needed since parseInt only works on numerical strings
		int leftOperand = Integer.parseInt(input.substring(0, operatorPos));
		int rightOperand = Integer.parseInt(input.substring(operatorPos + 1));
		
		//calculate result
		if(operator == '+')
			return "" + (leftOperand + rightOperand);
		if(operator == '-')
			return "" + (leftOperand - rightOperand);
		if(operator == '*')
			return "" + (leftOperand * rightOperand);
		if(operator == '/')
		{
			if(rightOperand == 0)
				return ERROR_DIVIDE_BY_ZERO;
			return "" + ((double)leftOperand / rightOperand);
		}
			
		//This should never happen
		return "How did we get here??";
	}
	
	/**
	 * Returns the operator in input.
	 * @return the operator in input
	 */
	private char getOperator()
	{
		if(input.contains("+"))
			return '+';
		if(input.contains("-"))
			return '-';
		if(input.contains("*"))
			return '*';
		if(input.contains("/"))
			return '/';
				
		return 'a';
	}
	
	/**
	 * Returns the square root of the current input.
	 * Can take a unary input or binary input.
	 * Returns an error message on invalid input or if result is imaginary.
	 * @return A string of the square root of the current input or an error message
	 */
	private String getSqrt()
	{
		//get the result
		String operandString = getResult();
		
		if(operandString.equals(""))
			return "";
		double operand;
		try 
		{
			operand = Double.parseDouble(operandString);
		}
		catch(NumberFormatException e)
		{
			return ERROR_INVALID_INPUT;
		}
		//check if 
		if(!(operand > 0))
			return ERROR_IMAGINARY_NUMBER;
		return "" + Math.sqrt(operand);
		
	}
	
	/**
	 * Returns the current input string.
	 */
	public String toString()
	{
		return input;
	}
	
	public static void main(String[] arguments) 
    {
        Calculator2 c = new Calculator2();
    }
}