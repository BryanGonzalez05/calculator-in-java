import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class calculator implements ActionListener{

	JFrame frame;
	JTextField textfield;		//where the number get printed
	JButton [] NumberB = new JButton[10];	//stores the number buttons
	JButton [] FunctionB = new JButton[9];	//stores the function buttons
	JButton addbutton, subbutton, multibutton, divbutton;
	JButton clearbutton, decbutton, delbutton, equalbutton , negbutton;
	JPanel bpanel;		//holds the button to add to the GUI
	Font myfont = new Font("Areial",Font.PLAIN,25);
	
	double num1 = 0, num2 = 0, result = 0;	//holds the first, second, and result number	
	char operator;		//stores the function operator 
	boolean iserror = false;
	
	public calculator() {
		
		//setting up the JFrame
		frame = new JFrame();
		frame.setSize(400,475);
		frame.setLocation(500,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);		//not using a layout 
		frame.setTitle("Calculator");
		
		//setting up the textfield to print the numbers on the gui
		textfield = new JTextField();
		textfield.setBounds(45,15,300,50);
		textfield.setFont(myfont);
		textfield.setEditable(false);		//disables the ability to click and type
		
		//adding buttons to the loop
		addbutton = new JButton ("+");
		subbutton = new JButton ("-");
		multibutton = new JButton ("*");
		divbutton = new JButton ("/");
		clearbutton = new JButton ("CLEAR");
		decbutton = new JButton (".");
		delbutton = new JButton ("DELETE");
		equalbutton = new JButton ("=");
		negbutton = new JButton ("(-)");
		
		FunctionB[0] = addbutton;
		FunctionB[1] = subbutton;
		FunctionB[2] = multibutton;
		FunctionB[3] = divbutton;
		FunctionB[4] = decbutton;
		FunctionB[5] = equalbutton;
		FunctionB[6] = delbutton;
		FunctionB[7] = clearbutton;
		FunctionB[8] = negbutton;
		
		//adding action listener and font
		for(int i = 0; i < 9; i++) {
			FunctionB[i].addActionListener(this);
			FunctionB[i].setFont(myfont);
			FunctionB[i].setFocusable(false);
			FunctionB[i].setBorderPainted(false);
		}
		
		//adding number to the buttons and actionlistner 
		for(int i = 0; i < 10; i++) {
			NumberB[i] = new JButton(String.valueOf(i));
			NumberB[i].setFont(myfont);
			NumberB[i].addActionListener(this);
			NumberB[i].setFocusable(false);
			NumberB[i].setBorderPainted(false);
		}
		
		//setting up the panel
		bpanel = new JPanel();
		bpanel.setBounds(45,75,300,300);
		bpanel.setLayout(new GridLayout(4,4,10,10));
		bpanel.setBackground(Color.BLUE);
		
		//adding buttons to the panel in their respective button
		bpanel.add(NumberB[1]);
		bpanel.add(NumberB[2]);
		bpanel.add(NumberB[3]);
		bpanel.add(addbutton);
		bpanel.add(NumberB[4]);
		bpanel.add(NumberB[5]);
		bpanel.add(NumberB[6]);
		bpanel.add(subbutton);
		bpanel.add(NumberB[7]);
		bpanel.add(NumberB[8]);
		bpanel.add(NumberB[9]);
		bpanel.add(multibutton);
		bpanel.add(decbutton);
		bpanel.add(NumberB[0]);
		bpanel.add(equalbutton);
		bpanel.add(divbutton);
		
		//setting the position of the buttons
		negbutton.setBounds(45,385,70,40);
		delbutton.setBounds(117, 385, 120, 40);
		clearbutton.setBounds(240,385,105,40);
		
		
		//adding everything to the GUI
		frame.add(bpanel);
		frame.add(delbutton);
		frame.add(clearbutton);
		frame.add(negbutton);
		frame.add(textfield);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 10; i++) {
			
			if(iserror == true) {
				textfield.setText("");
				iserror = false;
			}
			//e gets the source of the button and captures it, if it is pressed and equals the number of the button 
			//the number gets printed to the text area
			if(e.getSource() == NumberB[i]) {	
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}
		
		if(e.getSource() == decbutton) {
			textfield.setText(textfield.getText().concat("."));
		}
		
		if(e.getSource() == addbutton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '+';
			textfield.setText("");
		}
		
		if(e.getSource() == subbutton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '-';
			textfield.setText("");
		}
		
		if(e.getSource() == multibutton){
			num1 = Double.parseDouble(textfield.getText());
			operator = '*';
			textfield.setText("");
		}
		
		if(e.getSource() == divbutton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '/';
			textfield.setText("");
		}
		
		if(e.getSource() == equalbutton) {
			
			if(textfield.getText() == null || operator == '\0') {
				textfield.setText("Error");
				iserror = true;
			}
			
			try {
				
				num2 = Double.parseDouble(textfield.getText());
				
			switch(operator){
			case '+':
				result = num1 + num2;	
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				if(num2 == 0) {
					textfield.setText("cannot divide by 0");
					iserror = true;
					return;
				}
				result = num1 / num2;
				break;
			}
			textfield.setText(String.valueOf(result));
			num1 = result;
		}
			catch(NumberFormatException ex) {
				textfield.setText("Invalid Input");
				iserror = true;
			}
		}
		
		if(e.getSource() == clearbutton) {
			textfield.setText("");
		}
		
		if(e.getSource() == delbutton) {
			String string = textfield.getText();
			textfield.setText("");
			for(int i = 0; i < string.length()-1; i++) {
				textfield.setText(textfield.getText() + string.charAt(i));
			}
		}
		if(e.getSource() == negbutton) {
			double temp = Double.parseDouble(textfield.getText());
			temp *= -1;
			textfield.setText(String.valueOf(temp));
		}
	}

}
