import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class create GUI frame and start the program
 */
public class Main extends JFrame{
    private JPanel panel;
    private JButton submit;
    private JTextField fileIn;
    private JTextField fileOut;
    private JLabel label1;
    private JLabel label2;
    private JTextArea area;

    MovieParse movieParse;

    public Main(){
        movieParse = new MovieParse();
        this.setTitle("test1");
        this.setSize(400,300);
        panel = new JPanel();
        area = new JTextArea(10,20);
        label1 = new JLabel("Input file name:");
        label2 = new JLabel("Output file name:");
        fileIn = new JTextField("text.txt");
        fileOut = new JTextField("out.txt");
        submit = new JButton("Submit");
        //add button listener, change in and out file, then do logic operations
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieParse.setAttribute(fileIn.getText(),fileOut.getText());
                go();
            }
        });
        panel.add(label1);
        panel.add(fileIn);
        panel.add(label2);
        panel.add(fileOut);
        //add labels and textfileds
        this.getContentPane().add(BorderLayout.NORTH,panel);
        //add textarea
        this.getContentPane().add(BorderLayout.CENTER,area);
        //add button
        this.getContentPane().add(BorderLayout.SOUTH,submit);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * do the logical operations
     */
    private void go(){
        movieParse.movieRead();
        area.append("Read movies information successfully!\n");
        movieParse.findBestMovie();
        area.append("Find best movies successfully!\n");
        movieParse.movieWrite();
        area.append("Write best movies to file successfully!\n");
        area.append("================\n");
        area.append("=======END======\n");
        area.append("================\n");
    }

    /**
     * main method to start the program
     * @param args none
     */
    public static void main(String[] args) {
        new Main();
    }


}
