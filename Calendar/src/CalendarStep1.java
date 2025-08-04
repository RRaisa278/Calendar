
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarStep1 extends JFrame {
	private JButton prevButton, nextButton;
    private JComboBox<String> monthCombo;
    private JComboBox<Integer> yearCombo;
    private JLabel monthYearLabel;

    public CalendarStep1() {
        setTitle("My Calendar Project - Step 2");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //  panel
        JPanel topPanel = new JPanel(new BorderLayout());

        //  buttons 
        JPanel navPanel = new JPanel();
        prevButton = new JButton("←");
        nextButton = new JButton("→");

        String[] months = {"January", "February", "March", "April", "May", "June",
                           "July", "August", "September", "October", "November", "December"};
        monthCombo = new JComboBox<>(months);

        yearCombo = new JComboBox<>();
        for (int year = 1990; year <= 2100; year++) {
            yearCombo.addItem(year);
        }

        navPanel.add(prevButton);
        navPanel.add(monthCombo);
        navPanel.add(yearCombo);
        navPanel.add(nextButton);

        // Month-Year
        monthYearLabel = new JLabel("Month Year", JLabel.CENTER);
        monthYearLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Weekday 
        JPanel weekdayPanel = new JPanel(new GridLayout(1, 7));
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            dayLabel.setForeground(Color.BLUE);
            weekdayPanel.add(dayLabel);
        }

        // topPanel order
        topPanel.add(navPanel, BorderLayout.NORTH);
        topPanel.add(monthYearLabel, BorderLayout.CENTER);
        topPanel.add(weekdayPanel, BorderLayout.SOUTH);

        // topPanel 
        add(topPanel, BorderLayout.NORTH);

        setVisible(true);
    }	
	
	    public static void main(String[] args) {
	    	 new CalendarStep1();
	    }
	}
	
	    
	    
	
	        

	
		 

	


