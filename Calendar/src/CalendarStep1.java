
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CalendarStep1 extends JFrame {
	private JButton prevButton, nextButton;
    private JComboBox<String> monthCombo;
    private JComboBox<Integer> yearCombo;
    private JLabel monthYearLabel;
    private JPanel datePanel;
    private Map<String, String> events = new HashMap<>();

    public CalendarStep1() {
        setTitle("My Calendar Project - Step 3");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background Image
        ImageIcon originalIcon = new ImageIcon("img/pic23.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(550, 455, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel background = new JLabel(resizedIcon);
        background.setLayout(new BorderLayout());
        setContentPane(background);
        setLayout(new BorderLayout());

        // Top panel with navigation
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        JPanel navPanel = new JPanel();
        navPanel.setOpaque(false);

        prevButton = new JButton("←");
        nextButton = new JButton("→");
        styleButton(prevButton);
        styleButton(nextButton);

        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        monthCombo = new JComboBox<>(months);

        yearCombo = new JComboBox<>();
        for (int y = 1990; y <= 2100; y++) yearCombo.addItem(y);

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

        // Listeners
        monthCombo.addActionListener(e -> updateCalendar());
        yearCombo.addActionListener(e -> {
            int y = (int) yearCombo.getSelectedItem();
            removeBangladeshHolidays();
            loadBangladeshHolidays(y);
            updateCalendar();
        });

        prevButton.addActionListener(e -> {
            int m = monthCombo.getSelectedIndex();
            int y = (int) yearCombo.getSelectedItem();
            if (m == 0) { m = 11; y--; yearCombo.setSelectedItem(y); }
            else m--;
            monthCombo.setSelectedIndex(m);
        });

        nextButton.addActionListener(e -> {
            int m = monthCombo.getSelectedIndex();
            int y = (int) yearCombo.getSelectedItem();
            if (m == 11) { m = 0; y++; yearCombo.setSelectedItem(y); }
            else m++;
            monthCombo.setSelectedIndex(m);
        });

        // Initialize current date and holidays
        Calendar now = Calendar.getInstance();
        monthCombo.setSelectedIndex(now.get(Calendar.MONTH));
        yearCombo.setSelectedItem(now.get(Calendar.YEAR));
        loadBangladeshHolidays(now.get(Calendar.YEAR));
        updateCalendar();

        setVisible(true);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setFocusPainted(false);
    }

    private void loadBangladeshHolidays(int year) {
        events.put(String.format("%04d-01-01", year), "New Year's Day");
        events.put(String.format("%04d-03-26", year), "Independence Day");
        events.put(String.format("%04d-05-01", year), "May Day");
        events.put(String.format("%04d-12-16", year), "Victory Day");
        events.put(String.format("%04d-12-25", year), "Christmas Day");
    }

    private void removeBangladeshHolidays() {
        events.entrySet().removeIf(e -> {
            String v = e.getValue();
            return "New Year's Day".equals(v) || "Independence Day".equals(v) ||
                    "May Day".equals(v) || "Victory Day".equals(v) || "Christmas Day".equals(v);
        });
    }

    private void updateCalendar() {
        datePanel.removeAll();
        datePanel.setLayout(new GridLayout(7, 7));

        int month = monthCombo.getSelectedIndex();
        int year = (int) yearCombo.getSelectedItem();

        monthYearLabel.setText(monthCombo.getSelectedItem() + " " + year);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);

        int startDay = cal.get(Calendar.DAY_OF_WEEK); // Sunday=1
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String d : days) {
            JLabel lbl = new JLabel(d, JLabel.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 14));
            lbl.setForeground(Color.BLACK);
            datePanel.add(lbl);
        }

        for (int i = 1; i < startDay; i++) datePanel.add(new JLabel(""));

        Calendar today = Calendar.getInstance();
        int cDay = today.get(Calendar.DAY_OF_MONTH);
        int cMonth = today.get(Calendar.MONTH);
        int cYear = today.get(Calendar.YEAR);

        for (int day = 1; day <= daysInMonth; day++) {
            JPanel dayBox = new JPanel(new BorderLayout());
            dayBox.setBackground(new Color(255, 255, 255, 180));
            dayBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel dayLabel = new JLabel(String.valueOf(day), JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
            dayBox.add(dayLabel, BorderLayout.NORTH); 

            if (day == cDay && month == cMonth && year == cYear) {
                dayBox.setBackground(new Color(135, 206, 250, 180));
                dayBox.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            String key = String.format("%04d-%02d-%02d", year, month + 1, day); 
            if (events.containsKey(key)) {
                String evText = events.get(key);
                JLabel evLabel = new JLabel("<html><body style='text-align:center;'>" + shorten(evText, 15) + "</body></html>", JLabel.CENTER);
                evLabel.setFont(new Font("Arial", Font.ITALIC, 11));
                evLabel.setForeground(new Color(139, 0, 0));
                dayBox.add(evLabel, BorderLayout.CENTER);
            }

            dayBox.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    String currentEvent = events.getOrDefault(key, "");
                    String event = showEventDialog(key, currentEvent);
                    if (event != null) {
                        if (event.trim().isEmpty()) events.remove(key);
                        else events.put(key, event.trim());
                        updateCalendar();
                    }
                }
            });

            datePanel.add(dayBox);
        }

        // Fill remaining cells to keep grid consistent
        int totalCells = 7 * 7;
        int filled = (startDay - 1) + daysInMonth + 7;
        for (int i = filled; i < totalCells; i++) {
            datePanel.add(new JLabel(""));
        }

        datePanel.revalidate();
        datePanel.repaint();
    }

    private String shorten(String text, int maxLen) {
        return (text.length() <= maxLen) ? text : text.substring(0, maxLen - 3) + "...";
    }

    private String showEventDialog(String dateKey, String initialText) {
        JDialog dialog = new JDialog(this, "Add event for " + dateKey, true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(initialText, 4, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel();

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JButton removeButton = new JButton("Remove");

        Color themeColor = new Color(70, 130, 180);
        okButton.setBackground(themeColor); okButton.setForeground(Color.WHITE); okButton.setFocusPainted(false);
        cancelButton.setBackground(themeColor); cancelButton.setForeground(Color.WHITE); cancelButton.setFocusPainted(false);
        removeButton.setBackground(Color.RED); removeButton.setForeground(Color.WHITE); removeButton.setFocusPainted(false);

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(removeButton);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        final String[] result = new String[1];
        result[0] = null;

        okButton.addActionListener(e -> {
            result[0] = textArea.getText();
            dialog.dispose();
        });
        cancelButton.addActionListener(e -> dialog.dispose());
        removeButton.addActionListener(e -> {
            result[0] = "";
            dialog.dispose();
        });

        dialog.getRootPane().setDefaultButton(okButton);
        dialog.setVisible(true);

        return result[0];
    }
 
 
	 
	    public static void main(String[] args) {
	    	 new CalendarStep1();

	    	
	    	 
	    }
	 }