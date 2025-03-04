import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {

    // Panels
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel displayPnl;
    JPanel controlPnl;

    // Components
    JLabel titleLbl;
    ImageIcon icon;
    JTextArea fortuneTA;
    JScrollPane scroller;
    JButton fortuneBtn;
    JButton quitBtn;

    // Fonts
    Font titleFont;
    Font fortuneFont;
    Font buttonFont;

    // Fortunes
    private ArrayList<String> fortunes;
    Random rnd;
    int lastFortuneIndex = -1;

    public FortuneTellerFrame() {

        JFrame frame =  new JFrame();
        frame.setTitle("Fortune Teller");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int width = (int)(screenSize.width * 0.75);
        int height = (int)(screenSize.height * 0.75);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fortunes
        createFortunesList();
        rnd = new Random();

        // Fonts
        titleFont = new Font("Serif", Font.BOLD, 48);
        fortuneFont = new Font("SansSerif", Font.PLAIN, 16);
        buttonFont = new Font("Monospaced", Font.BOLD, 14);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTitlePanel();
        createDisplayPanel();
        createControlPanel();

        mainPnl.add(titlePnl, BorderLayout.NORTH);
        mainPnl.add(displayPnl, BorderLayout.CENTER);
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        frame.add(mainPnl);
        frame.setVisible(true);
    }

    private void createTitlePanel() {
        titlePnl = new JPanel();

        ImageIcon originalIcon = new ImageIcon("src/fortuneTeller.jpg");
        // Image resize
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizedImage);
        titleLbl = new JLabel("Fortune Teller", icon, JLabel.CENTER);
        titleLbl.setFont(titleFont);
        // Obscure code to align the text to the icon!
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel();

        fortuneTA = new JTextArea(10, 40);
        fortuneTA.setFont(fortuneFont);
        fortuneTA.setEditable(false);
        scroller = new JScrollPane(fortuneTA);
        displayPnl.add(scroller);
    }

    private void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2));

        // Create buttons
        fortuneBtn = new JButton("Read My Fortune!");
        fortuneBtn.setFont(buttonFont);
        fortuneBtn.addActionListener((ActionEvent ae) -> {
            displayFortune();
        });

        quitBtn = new JButton("Quit");
        quitBtn.setFont(buttonFont);
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPnl.add(fortuneBtn);
        controlPnl.add(quitBtn);
    }

    private void createFortunesList() {
        fortunes = new ArrayList<>();

        fortunes.add("I see money in your future... it is not yours though");
        fortunes.add("Future unclear, please try again");
        fortunes.add("Your mother is alive and well, living in your house.");
        fortunes.add("Beware of overambitious squirrels stealing your snacks.");
        fortunes.add("A nap in the most inconvenient place will lead to unexpected revelations.");
        fortunes.add("The universe has a plan for you. It involves pizza.");
        fortunes.add("You will find true love. But first, you must find your other sock.");
        fortunes.add("You will soon be the center of attention—right after your embarrassing sneeze.");
        fortunes.add("An opportunity for advancement will present itself soon.");
        fortunes.add("Your ability to find parking spots will reach legendary status.");
        fortunes.add("A challenging problem will have a surprisingly simple solution.");
        fortunes.add("You will make a new friend with shared interests in programming.");
    }

    private void displayFortune() {

        int index;
        do {
            index = rnd.nextInt(fortunes.size());
        } while (index == lastFortuneIndex);

        lastFortuneIndex = index;
        String fortune = fortunes.get(index);

        fortuneTA.append(fortune + "\n");
    }
}


