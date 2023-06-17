import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchGUI extends JFrame {
    private JLabel minutesLabel;
    private JLabel secondsLabel;
    private JLabel tensLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private Timer timer;
    private int minutes;
    private int seconds;
    private int tens;

    public StopwatchGUI() {
        setTitle("Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        minutesLabel = new JLabel("00");
        minutesLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        add(minutesLabel);

        JLabel colon1Label = new JLabel(":");
        colon1Label.setFont(new Font("Arial", Font.PLAIN, 48));
        add(colon1Label);

        secondsLabel = new JLabel("00");
        secondsLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        add(secondsLabel);

        JLabel colon2Label = new JLabel(":");
        colon2Label.setFont(new Font("Arial", Font.PLAIN, 48));
        add(colon2Label);

        tensLabel = new JLabel("00");
        tensLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        add(tensLabel);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });
        add(startButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });
        add(stopButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });
        add(resetButton);

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void startTimer() {
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tens++;
                if (tens > 99) {
                    seconds++;
                    tens = 0;
                }
                if (seconds > 59) {
                    minutes++;
                    seconds = 0;
                }
                updateLabels();
            }
        });
        timer.start();

        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        resetButton.setEnabled(true);
    }

    private void stopTimer() {
        timer.stop();

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    private void resetTimer() {
        timer.stop();
        minutes = 0;
        seconds = 0;
        tens = 0;
        updateLabels();

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

    private void updateLabels() {
        minutesLabel.setText(String.format("%02d", minutes));
        secondsLabel.setText(String.format("%02d", seconds));
        tensLabel.setText(String.format("%02d", tens));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StopwatchGUI().setVisible(true);
            }
        });
    }
}
