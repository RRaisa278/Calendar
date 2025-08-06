import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Calendar;

import javax.swing.ImageIcon;
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
    private JPanel datePanel;

    public CalendarStep1() {
        setTitle("My Calendar Project - Step 3");
        setSize(550,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
        setLocationRelativeTo(null);

        
        ImageIcon originalIcon = new ImageIcon("img/pic23.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(550, 455, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(resizedIcon);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        setLayout(new BorderLayout());

        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        
        JPanel navPanel = new JPanel();
        navPanel.setOpaque(false);
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

        
        monthYearLabel = new JLabel("Month Year", JLabel.CENTER);
        monthYearLabel.setFont(new Font("Arial", Font.BOLD, 20));
        monthYearLabel.setForeground(Color.BLACK);

        
        JPanel weekdayPanel = new JPanel(new GridLayout(1, 7));
        weekdayPanel.setOpaque(false);
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            dayLabel.setForeground(Color.BLACK);
            weekdayPanel.add(dayLabel); 
        }

        
        datePanel = new JPanel(new GridLayout(6, 7));
        datePanel.setOpaque(false);
        updateCalendar(); 

        
        topPanel.add(monthYearLabel, BorderLayout.NORTH);
        topPanel.add(navPanel, BorderLayout.CENTER);
        topPanel.add(weekdayPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(datePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void updateCalendar() {
        datePanel.removeAll();

        int month = monthCombo.getSelectedIndex();
        int year = (int) yearCombo.getSelectedItem();

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);

        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < startDay; i++) {
            datePanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            JLabel dateLabel = new JLabel(String.valueOf(day), JLabel.CENTER);
            dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            dateLabel.setForeground(Color.BLACK);
            datePanel.add(dateLabel);
        }

        datePanel.revalidate();
        datePanel.repaint();
    }

	
	
	    public static void main(String[] args) {
	    	 new CalendarStep1();
	    }
	}
	
	    
	    
	
	        

	
		 

	


