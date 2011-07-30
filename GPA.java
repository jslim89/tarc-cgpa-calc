/* This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

class GPA extends JFrame implements ActionListener
{
	JTextField txtGrade[] = new JTextField[40];
	JTextField txtCredit[] = new JTextField[40];
	JLabel lblTotalSubject = new JLabel("");
	JLabel lblTotalGPA = new JLabel("");
	JLabel lblAvrGPA = new JLabel("");
	JLabel lblCredit = new JLabel("");
	JButton jbtCal = new JButton("Calculate");
	JButton jbtReset = new JButton("Reset");
	JButton jbtExit = new JButton("Exit");
	JButton jbtGuide = new JButton("Guide");
	int count = 0;
	int credit = 0;
	double total = 0.0;
	double avr = 0.0;
	
	public GPA()
	{
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(23,6));
		p1.add(new JLabel(""));
		p1.add(new JLabel("GRADE"));
		p1.add(new JLabel("CREDITS"));
		p1.add(new JLabel(""));
		p1.add(new JLabel("GRADE"));
		p1.add(new JLabel("CREDITS"));
		for(int i=0;i<txtGrade.length;i++)
		{
			txtGrade[i] = new JTextField();
			txtCredit[i] = new JTextField();
			p1.add(new JLabel("Subject "+(i+1)));
			p1.add(txtGrade[i]);
			p1.add(txtCredit[i]);
		}
		p1.add(new JLabel("Total Subject"));
		p1.add(lblTotalSubject);
		p1.add(new JLabel(""));
		p1.add(new JLabel("Total GPA"));
		p1.add(lblTotalGPA);
		p1.add(new JLabel(""));
		p1.add(new JLabel("Total Credit Hours"));
		p1.add(lblCredit);
		p1.add(new JLabel(""));
		p1.add(new JLabel("Average GPA"));
		p1.add(lblAvrGPA);
		p1.add(new JLabel(""));
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(jbtCal);
		p2.add(jbtReset);
		p2.add(jbtGuide);
		p2.add(jbtExit);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("Copyrighted by JS Lim"));
		
		JPanel mixP2P3 = new JPanel();
		mixP2P3.setLayout(new GridLayout(2,1));
		mixP2P3.add(p2);
		mixP2P3.add(p3);
		
		c.add(p1,BorderLayout.CENTER);
		c.add(mixP2P3,BorderLayout.SOUTH);
		
		jbtCal.addActionListener(this);
		jbtReset.addActionListener(this);
		jbtExit.addActionListener(this);
		jbtGuide.addActionListener(this);
		
		lblTotalSubject.setForeground(Color.RED);
		lblAvrGPA.setForeground(Color.RED);
		lblCredit.setForeground(Color.RED);
		lblTotalGPA.setForeground(Color.RED);
		
		setTitle("GPA");
		setVisible(true);
		//setLocationRelativeTo(null);
		setLocation(150,100);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700,600);
	}
	
	public static void main(String args[])
	{
		new GPA();
	}
	
	public double grade(String x)
	{
		if(x.equalsIgnoreCase("A"))
			return 4.0;
		else if(x.equalsIgnoreCase("A-"))
			return 3.75;
		else if(x.equalsIgnoreCase("B+"))
			return 3.5;
		else if(x.equalsIgnoreCase("B"))
			return 3.0;
		else if(x.equalsIgnoreCase("B-"))
			return 2.75;
		else if(x.equalsIgnoreCase("C+"))
			return 2.5;
		else if(x.equalsIgnoreCase("C"))
			return 2.0;
		else if(x.equalsIgnoreCase("C-"))
			return 1.75;
		else if(x.equalsIgnoreCase("D"))
			return 1.0;
		else
			return 0.0;
	}
	
	public void actionPerformed(ActionEvent e)
	{		
		if(e.getSource()==jbtCal)
		{					
			for(int i=0;i<txtGrade.length;i++)
			{				
				if(!txtGrade[i].getText().equals("") && !txtCredit[i].getText().equals(""))
				{
					total += (Integer.parseInt(txtCredit[i].getText()) * grade(txtGrade[i].getText()));
					credit += Integer.parseInt(txtCredit[i].getText());
					count++;
				}
			}
			if(count == 0)
					JOptionPane.showMessageDialog(null,"Incompleted Information! Please re-check!");
			
			else
			{
				avr = total / credit;			
				lblTotalSubject.setText(""+count);
				lblTotalGPA.setText(""+total);
				lblAvrGPA.setText(""+avr);
				lblCredit.setText((String.valueOf(credit)));
				
				count = 0;
				credit = 0;
				total = 0.0;
				avr = 0.0;
			}
		}
		
		else if(e.getSource()==jbtReset)
		{
			for(int i=0;i<txtGrade.length;i++)
			{
				txtGrade[i].setText("");
				txtCredit[i].setText("");
			}
			lblTotalSubject.setText("");
			lblTotalGPA.setText("");
			lblAvrGPA.setText("");
			lblCredit.setText("");
			count = 0;
			total = 0.0;
			avr = 0.0;
			credit = 0;
		}
		
		else if(e.getSource() == jbtGuide)
		{
			String help;
			help = "In <GRADE> column, you can only input (A,A-,B+,B,B-,C+,C,C-,D,F)\n";
			help += "In <CREDITS> column, you can only input integer\n";
			JOptionPane.showMessageDialog(null,help);
		}
		else if(e.getSource()==jbtExit)
		{
			JOptionPane.showMessageDialog(null,"Thanks for using this program!");
			System.exit(0);
		}
	}
}
