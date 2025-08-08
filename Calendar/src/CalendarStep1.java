import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Calendar;

import javax.swing.BorderFactory;
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
	        setSize(550, 450);
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

	        datePanel = new JPanel(); 
	        datePanel.setOpaque(false);

	        
	        topPanel.add(monthYearLabel, BorderLayout.NORTH);
	        topPanel.add(navPanel, BorderLayout.CENTER);
	        add(topPanel, BorderLayout.NORTH);
	        add(datePanel, BorderLayout.CENTER);

	        // action listeners
	        monthCombo.addActionListener(e -> updateCalendar());
	        yearCombo.addActionListener(e -> updateCalendar());
	        prevButton.addActionListener(e -> {
	            int month = monthCombo.getSelectedIndex();
	            int year = (int) yearCombo.getSelectedItem();
	            if (month == 0) {
	                month = 11;
	                year--;
	            } else {
	                month--;
	            }
	            monthCombo.setSelectedIndex(month);
	            yearCombo.setSelectedItem(year);
	        });
	        nextButton.addActionListener(e -> {
	            int month = monthCombo.getSelectedIndex();
	            int year = (int) yearCombo.getSelectedItem(); 
	            if (month == 11) {
	                month = 0;
	                year++;
	            } else { 
	                month++;
	            }
	            monthCombo.setSelectedIndex(month);
	            yearCombo.setSelectedItem(year);
	        });

	        // Set current month & year
	        Calendar now = Calendar.getInstance();
	        monthCombo.setSelectedIndex(now.get(Calendar.MONTH)); 
	        yearCombo.setSelectedItem(now.get(Calendar.YEAR)); 
	        updateCalendar(); 
	        setVisible(true); 
	    }

	    private void updateCalendar() {
	        datePanel.removeAll();
	        datePanel.setLayout(new GridLayout(7, 7)); 

	        int month = monthCombo.getSelectedIndex();
	        int year = (int) yearCombo.getSelectedItem();

	        monthYearLabel.setText(monthCombo.getSelectedItem() + " " + year);

	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.YEAR, year);
	        cal.set(Calendar.MONTH, month);
	        cal.set(Calendar.DAY_OF_MONTH, 1);

	        int startDay = cal.get(Calendar.DAY_OF_WEEK); 
	        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

	        
	        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	        for (String day : days) {
	            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
	            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));  
	            dayLabel.setForeground(Color.BLACK);
	            datePanel.add(dayLabel);
	        }

	        
	        int blanks = startDay - 1; 
	        for (int i = 0; i < blanks; i++) {
	            datePanel.add(new JLabel(""));  
	        }


	        for (int day = 1; day <= daysInMonth; day++) {
	            JPanel dayBox = new JPanel(new BorderLayout());
	            dayBox.setBackground(new Color(255, 255, 255, 180));
	            dayBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

	            JLabel dayLabel = new JLabel(String.valueOf(day), JLabel.CENTER);
	            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
	            dayBox.add(dayLabel, BorderLayout.CENTER);

	            datePanel.add(dayBox);
	        }

	
	        int totalCells = 7 * 7;
	        int currentCells = blanks + daysInMonth + 7; 
	        for (int i = currentCells; i < totalCells; i++) {
	            datePanel.add(new JLabel(""));
	        }

	        datePanel.revalidate();
	        datePanel.repaint();
	    }
public static void main(String[] args) {
	    	 new CalendarStep1();
	    }
	}
	        

	
		 

	


