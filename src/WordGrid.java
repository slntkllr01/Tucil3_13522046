import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WordGrid extends JPanel {
    public WordGrid(java.util.List<String> words, String lastWord) {
        super(new GridLayout(words.size(), lastWord.length())); // Menggunakan GridLayout
        char[] lastChars = lastWord.toCharArray();

        // Menambahkan setiap huruf ke panel dengan border terpisah
        for (String word : words) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                JPanel letterPanel = new JPanel(new BorderLayout());
                letterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                JLabel label = new JLabel(String.valueOf(chars[i]), SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 20));

                if (chars[i] == lastChars[i]) {
                    label.setOpaque(true);
                    label.setBackground(Color.GREEN);
                }

                letterPanel.add(label);
                add(letterPanel);
            }
        }
    }
}
