import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Gui extends Component {
    private JTextArea textArea;
    private JFrame frame;
    private JButton fileChooser;
    private JFileChooser file;
    private JTextField searchingText;
    private JTextArea result;

    public void go() {

        frame = new JFrame("String Finder");
        JPanel mainPanel = new JPanel();

        fileChooser = new JButton("File Chooser");
        fileChooser.addActionListener(new fileChooserAL());

        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.RED));

        searchingText = new JTextField(20);
        searchingText.addActionListener(new SearchingAL());
        searchingText.setBorder( BorderFactory.createLineBorder(Color.YELLOW) );

        result = new JTextArea(20, 40);
        result.setEditable(false);
        result.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        JScrollPane scroller1 = new JScrollPane(textArea);
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane scroller2 = new JScrollPane(result);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(fileChooser);
        mainPanel.add(scroller1);
        mainPanel.add(searchingText);
        mainPanel.add(scroller2);

        frame.add(BorderLayout.CENTER, mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 750);
        frame.setResizable(false);
    }

    class fileChooserAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            file = new JFileChooser();
            file.setAcceptAllFileFilterUsed(true);
            file.showDialog(Gui.this, "Load");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file.getSelectedFile()));
                String line;
                int lineNum = 1;
                while ((line = reader.readLine()) != null) {
                    textArea.append((lineNum++) + "   : " + line + "\n");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class SearchingAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                result.setText("");
                BufferedReader reader = new BufferedReader(new FileReader(file.getSelectedFile()));
                String query = searchingText.getText();
                int lineNum = 1;
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(query)) {
                        result.append("Line:" + (lineNum) + ": " + line + "\n");
                    }
                    lineNum++;
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}


