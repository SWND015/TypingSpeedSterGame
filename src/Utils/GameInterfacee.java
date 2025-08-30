package Utils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

//import org.eclipse.wb.swing.FocusTraversalOnArray;

import Game.AccuracyCalculator;
import Game.BonusCalculator;
import Game.PerformanceRecorder;
import Game.SpeedCalculator;
import Game.TypingComponent;
import Game.TypingSpeedster;
import Game.WordGenerator;

import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;

public class GameInterfacee extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private CardLayout cardLayout;


   // Backend Logic components
    private final PerformanceRecorder performanceRecorder = new PerformanceRecorder();
    private final AccuracyCalculator accuracyCalculator = new AccuracyCalculator();
    private final SpeedCalculator speedCalculator = new SpeedCalculator();


    private JTable recordsTable;
    private JComboBox<String> difficultyComboBox;
    private JSpinner wordCountSpinner;
    private JTextArea targetTextArea;
    private JTextArea typedTextArea;
    private JLabel wpmResult;
    private JLabel accuracyResult;
    private JLabel scoreLabel;
    private long startTime;
    private String targetText;


  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GameInterfacee frame = new GameInterfacee();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public GameInterfacee() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    cardLayout = new CardLayout();
    contentPane = new JPanel(cardLayout);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
//		contentPane.setLayout(new CardLayout(0, 0));



//		MENU PANEL
    JPanel menuPanel = new JPanel();
    menuPanel.setLayout(null);
    menuPanel.setToolTipText("TypingSpeedster | Menu");
    menuPanel.setBackground(Color.GRAY);
    contentPane.add(menuPanel, "menuPanel");

    JLabel lblNewLabel = new JLabel("TypingSpeedster");
    lblNewLabel.setForeground(new Color(0, 0, 160));
    lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 20));
    lblNewLabel.setBounds(123, 21, 195, 49);
    menuPanel.add(lblNewLabel);

    JLabel lblNewLabel_1 = new JLabel("Select Difficulty");
    lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    lblNewLabel_1.setBounds(71, 80, 127, 30);
    menuPanel.add(lblNewLabel_1);

    JLabel lblNewLabel_1_1 = new JLabel("Word Count");
    lblNewLabel_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    lblNewLabel_1_1.setBounds(71, 120, 127, 30);
    menuPanel.add(lblNewLabel_1_1);

    difficultyComboBox = new JComboBox<>();
        difficultyComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        difficultyComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Easy", "Medium", "Hard" }));
        difficultyComboBox.setBounds(208, 83, 150, 25);
        menuPanel.add(difficultyComboBox);

        wordCountSpinner = new JSpinner();
        wordCountSpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        wordCountSpinner.setModel(new SpinnerNumberModel(10, 5, 50, 1));
        wordCountSpinner.setBounds(208, 123, 150, 25);
        menuPanel.add(wordCountSpinner);

    JButton generateBtn = new JButton("Generate Sentence");





    generateBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
    generateBtn.setBounds(230, 181, 157, 36);
    menuPanel.add(generateBtn);

    JButton viewRecordsBtn = new JButton("View Records");
    viewRecordsBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
    viewRecordsBtn.setBounds(46, 181, 157, 36);
    menuPanel.add(viewRecordsBtn);


//		TYPING PANEL
    JPanel typingPanel = new JPanel();
    typingPanel.setLayout(null);
    typingPanel.setBackground(Color.GRAY);
    contentPane.add(typingPanel, "typingPanel");

    JLabel lblNewLabel_2 = new JLabel("Generated Text:");
    lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
    lblNewLabel_2.setBounds(32, 10, 132, 34);
    typingPanel.add(lblNewLabel_2);

    JLabel lblNewLabel_3 = new JLabel("Type the above text here:");
    lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
    lblNewLabel_3.setBounds(32, 112, 255, 13);
    typingPanel.add(lblNewLabel_3);

     targetTextArea = new JTextArea();
    targetTextArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
    targetTextArea.setLineWrap(true);
    targetTextArea.setWrapStyleWord(true);
    targetTextArea.setEditable(false);
    JScrollPane targetScrollPane = new JScrollPane(targetTextArea);
        targetScrollPane.setBounds(32, 48, 368, 49);
        typingPanel.add(targetScrollPane);

    typedTextArea = new JTextArea();
    typedTextArea.setWrapStyleWord(true);
    typedTextArea.setLineWrap(true);
    typedTextArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
    JScrollPane inputScrollPane = new JScrollPane(typedTextArea);
        inputScrollPane.setBounds(32, 135, 368, 56);
        typingPanel.add(inputScrollPane);

    JButton finishedBtn = new JButton("Finished");
    finishedBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    finishedBtn.setBounds(147, 211, 122, 21);
    typingPanel.add(finishedBtn);



//		RESULTS PANEL
    JPanel resultsPanel = new JPanel();
    resultsPanel.setBackground(new Color(128, 128, 128));
    contentPane.add(resultsPanel, "resultsPanel");
    resultsPanel.setLayout(null);

    JLabel lblNewLabel_5 = new JLabel("Session Results");
    lblNewLabel_5.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
    lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_5.setBounds(105, 25, 229, 34);
    resultsPanel.add(lblNewLabel_5);

     wpmResult = new JLabel("WPM: 0.00");
    wpmResult.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
    wpmResult.setBounds(56, 58, 219, 62);
    resultsPanel.add(wpmResult);

     accuracyResult = new JLabel("Accuracy: 0.00%");
    accuracyResult.setHorizontalAlignment(SwingConstants.RIGHT);
    accuracyResult.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
    accuracyResult.setBounds(165, 58, 219, 62);
    resultsPanel.add(accuracyResult);

    JButton tryAgainBtn = new JButton("Try Again");
    tryAgainBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    tryAgainBtn.setBounds(83, 189, 116, 34);
    resultsPanel.add(tryAgainBtn);

    JButton showRecordsBtn = new JButton("Show Records");
    showRecordsBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
    showRecordsBtn.setBounds(220, 189, 137, 34);
    resultsPanel.add(showRecordsBtn);
    
    scoreLabel = new JLabel("Score: 0.00");
    scoreLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
    scoreLabel.setBounds(152, 122, 181, 42);
    resultsPanel.add(scoreLabel);



//		RECORDS PANEL
    JPanel recordsPanel = new JPanel();
    recordsPanel.setBackground(new Color(128, 128, 128));
    contentPane.add(recordsPanel, "recordsPanel");
    recordsPanel.setLayout(null);

    JLabel lblNewLabel_4 = new JLabel("Performance History");
    lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
    lblNewLabel_4.setBounds(24, 10, 213, 45);
    recordsPanel.add(lblNewLabel_4);


    JScrollPane recordsScrollPane = new JScrollPane();
    recordsScrollPane.setBounds(21, 51, 345, 145);
    recordsPanel.add(recordsScrollPane);

    		recordsTable = new JTable();
        recordsScrollPane.setViewportView(recordsTable);

        JButton backToMenuBtn = new JButton("Back to Menu");
        backToMenuBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        backToMenuBtn.setBounds(53, 206, 126, 37);
        recordsPanel.add(backToMenuBtn);
        
        JButton clearRecordsBtn = new JButton("Clear Records");
        clearRecordsBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        clearRecordsBtn.setBounds(205, 206, 126, 37);
        recordsPanel.add(clearRecordsBtn);


        generateBtn.addActionListener(e -> startTypingSession());
        finishedBtn.addActionListener(e -> finishTypingSession());
        viewRecordsBtn.addActionListener(e -> showRecords());
        tryAgainBtn.addActionListener(e -> cardLayout.show(contentPane, "menuPanel"));
        backToMenuBtn.addActionListener(e -> cardLayout.show(contentPane, "menuPanel"));
        clearRecordsBtn.addActionListener(e -> clearRecords());
        showRecordsBtn.addActionListener(e -> showRecords());
        
        // Show the menu panel first
        cardLayout.show(contentPane, "menuPanel");

  }

    private void startTypingSession() {
    int difficulty = difficultyComboBox.getSelectedIndex();
	  System.out.println(difficulty+1);
    int words = (int) wordCountSpinner.getValue();

    WordGenerator generator = new WordGenerator();
    targetText = generator.generateSentence((difficulty+1), words);
    typedTextArea.setText("");
    targetTextArea.setText(targetText);
    accuracyCalculator.setTargetText(targetText);
    speedCalculator.setTargetText(targetText);
    
    typedTextArea.requestFocus();

    cardLayout.show(contentPane, "typingPanel");

    startTime = System.currentTimeMillis();
    
  }

  

  private void finishTypingSession() {
	  
    long endTime = System.currentTimeMillis();
    double timeTaken = (endTime - startTime);

    String typedText = typedTextArea.getText();
    

    speedCalculator.setTypedText(typedText);
    accuracyCalculator.setTypedText(typedText);
    speedCalculator.setTimeTaken(timeTaken);
    accuracyCalculator.setTimeTaken(timeTaken);
    
    
    double wpm = speedCalculator.calculateWPM();
    double accuracy = accuracyCalculator.calculateAccuracy();

    BonusCalculator bonusScore = new BonusCalculator();
    double score = bonusScore.calculateScore(wpm, accuracy);

    performanceRecorder.addRecord(wpm, accuracy, score); 

    wpmResult.setText(String.format("WPM: %.2f", wpm));
    accuracyResult.setText(String.format("Accuracy: %.2f%%", accuracy));
    scoreLabel.setText(String.format("Score: %.2f", score));
    cardLayout.show(contentPane, "resultsPanel");
  }

  private void showRecords() {
    // Get the records from the performanceRecorder
    java.util.List<PerformanceRecorder.Record> records = performanceRecorder.showRecords();
    String[] columnNames = {"WPM", "Accuracy", "Score"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    for (PerformanceRecorder.Record record : records) {
        model.addRow(new Object[] {
            String.format("%.2f", record.wpm),
            String.format("%.2f", record.accuracy),
            String.format("%.2f", record.score)
        });
    }
    recordsTable.setModel(model);
    cardLayout.show(contentPane, "recordsPanel");
}
  
  private void clearRecords() {
	  performanceRecorder.clearRecords();
	  
  }
}