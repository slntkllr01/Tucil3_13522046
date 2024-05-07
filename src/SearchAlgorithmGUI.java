import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import backend.AStar;
import backend.DictLoader;
import backend.GBFS;
import backend.UCS;
import backend.Utility;

public class SearchAlgorithmGUI {
    private final JFrame frame;
    private JComboBox<String> algorithmComboBox;
    private JTextField startNodeField, goalNodeField;
    private JButton startButton;
    private JPanel outputPanel;
    private JTextArea infoArea;
    private CardLayout cardLayout = new CardLayout();

    public SearchAlgorithmGUI() {
        frame = new JFrame("Word Ladder Solver");
        frame.setSize(400, 500); // Ukuran window disesuaikan
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        setupInputAndButtonPanel();
        setupOutputPanel();
        setupInfoArea();

        frame.setVisible(true);
    }

    private void setupInputAndButtonPanel() {
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // Menambahkan padding di sekeliling panel

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2));
        fieldsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // Menambahkan margin di sekeliling input fields
        algorithmComboBox = new JComboBox<>(new String[]{"UCS", "GBFS", "A*"});
        startNodeField = new JTextField();
        goalNodeField = new JTextField();

        fieldsPanel.add(new JLabel("Algorithm:"));
        fieldsPanel.add(algorithmComboBox);
        fieldsPanel.add(new JLabel("Start Word:"));
        fieldsPanel.add(startNodeField);
        fieldsPanel.add(new JLabel("End Word:"));
        fieldsPanel.add(goalNodeField);

        startButton = new JButton("Start!");
        startButton.setPreferredSize(new Dimension(40, 25));  // Menetapkan ukuran tombol
        startButton.addActionListener(e -> performSearch());

        inputPanel.add(fieldsPanel, BorderLayout.CENTER);
        inputPanel.add(startButton, BorderLayout.SOUTH);

        frame.add(inputPanel, BorderLayout.NORTH);
    }

    private void setupOutputPanel() {
        outputPanel = new JPanel(cardLayout);
        JTextArea textArea = new JTextArea("Results will appear here...");
        textArea.setEditable(false);
        outputPanel.add(new JScrollPane(textArea), "Text");
        frame.add(outputPanel, BorderLayout.CENTER);
    }

    private void setupInfoArea() {
        infoArea = new JTextArea(3, 20); // Informasi tentang eksekusi dan node
        infoArea.setEditable(false);
        frame.add(new JScrollPane(infoArea), BorderLayout.SOUTH); // Letakkan di bagian bawah frame
    }

    private void performSearch() {
        String startNode = startNodeField.getText().trim();
        String goalNode = goalNodeField.getText().trim();

        if (startNode.isEmpty() || goalNode.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both start and goal nodes.");
            return;
        }

        if (startNode.length() != goalNode.length()) {
            JOptionPane.showMessageDialog(frame, "Error: Start and goal nodes must be of the same length.");
            return;
        }

        DictLoader loader = new DictLoader();
        loader.ReadDict("src/backend/dictionary.txt");

        Utility.Result result = null;
        switch ((String) algorithmComboBox.getSelectedItem()) {
            case "UCS" -> result = UCS.ucs(startNode, goalNode, loader.getDict());
            case "GBFS" -> result = GBFS.gbfs(startNode, goalNode, loader.getDict());
            case "A*" -> result = AStar.a_star(startNode, goalNode, loader.getDict());
            default -> {
                JOptionPane.showMessageDialog(frame, "Algorithm not implemented yet.");
                return;
            }
        }

        if (result != null && result.getPath() != null) {
            WordGrid wordGrid = new WordGrid(result.getPath(), goalNode);
            outputPanel.add(new JScrollPane(wordGrid), "WordGrid");
            infoArea.setText("Path Length: " + result.getPath().size() + "\nExecution Time: " + result.getTime() + " ms\nNodes Visited: " + result.getNodesVisited());
            cardLayout.show(outputPanel, "WordGrid");
        } else {
            infoArea.setText("Execution Time: " + result.getTime() + " ms\nNodes Visited: " + result.getNodesVisited());
            JTextArea textArea = new JTextArea("No path found.");
            textArea.setEditable(false);
            outputPanel.add(new JScrollPane(textArea), "Text");
            cardLayout.show(outputPanel, "Text");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SearchAlgorithmGUI::new);
    }
}