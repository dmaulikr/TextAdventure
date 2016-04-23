import java.util.Scanner; 
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;              
import javax.swing.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.File;


/** @Author Roee Landesman
  * @Version BETA
  */

public class GameEngine 
                        implements ActionListener, MouseListener
{

  private File allNodes;
  protected Scanner myScanner; 

  protected GameNode current; 

  private JButton buttA;
  private JButton buttB;

  private String aText;
  private String bText;

  private String imageURL; 

  private int choiceA;
  private int choiceB;

  private JPanel textPanel;
  private JPanel imagePanel;
  private JPanel buttonPanel;

  private JLabel imageLabel;
  private JTextArea mainText;
  private ImageIcon mainImage;
  private JFrame window;

  protected ArrayList<GameNode> nodeNames;

  public static final String DEBUG = "This is debugging: ";

	public GameEngine () {
    current = null;

    window = new JFrame();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Finds the parameters of the user's computer screen (Height and width)
      window.setSize(screenSize.width,screenSize.height);
      window.setTitle("PurpleBerg");
      window.setLocationRelativeTo(null);

    textPanel = new JPanel();
    imagePanel = new JPanel();

    mainText = new JTextArea(1,screenSize.width/20);
    mainText.setFont(new Font("Serif",Font.PLAIN,25));
    mainText.setWrapStyleWord(true);
    mainText.setLineWrap(true);
    mainText.setOpaque(false);
    mainText.setEditable(false);
    mainText.setFocusable(false);
    textPanel.add(mainText);
    
    
    mainImage = new ImageIcon(imageURL);
    imageLabel = new JLabel();
    imageLabel.setIcon(mainImage);
    imageLabel.setPreferredSize(new Dimension(400,400));
    imagePanel.add(imageLabel);


    window.add(imagePanel, BorderLayout.PAGE_START);
    window.add(textPanel, BorderLayout.CENTER);


    buttA = new JButton(aText);
    buttB = new JButton(bText);

    LeftListener left = new LeftListener();
    buttA.addActionListener(left);

    RightListener right = new RightListener();
    buttB.addActionListener(right);



    buttonPanel = new JPanel();
    buttonPanel.add(buttA);
    buttonPanel.add(buttB);
    buttonPanel.setLayout(new GridLayout(1,0));

    window.add(buttonPanel, BorderLayout.PAGE_END);

    window.setVisible(true);

    // END OF JFRAME, MOVING INTO NODES 
    nodeNames = new ArrayList<GameNode>();

    try {
      File myFile = new File("nodes.txt");
      Scanner myScanner = new Scanner(myFile);
      myScanner.useDelimiter("[/]"); //Will seperate the tokens of the scanned file- Used to put multiple lines in the "text" area of Nodes. 

      while(myScanner.hasNext()){
        String name = myScanner.next();
        String image = myScanner.next();
        String text = myScanner.next();

                        // System.out.println(DEBUG + "text " + text); // DEBUG

        String aText = myScanner.next();
                        // System.out.println(DEBUG + "aText " + aText); // DEBUG

        String bText = myScanner.next();
                        // System.out.println(DEBUG + "bText " + bText); // DEBUG

        int choiceA = Integer.parseInt(myScanner.next());
                        // System.out.println(DEBUG + "choiceA " + choiceA); // DEBUG

        int choiceB = Integer.parseInt(myScanner.next());

        myScanner.nextLine();
        myScanner.nextLine();

        GameNode thisNode = new GameNode(name,text,image,aText,bText,choiceA,choiceB);
        nodeNames.add(thisNode);
      }

    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("File Not found");
    }

    current = nodeNames.get(0); //Sets it up to origin node
    GameNode secondBug = nodeNames.get(1); //DEBUG- IGNORE

      // System.out.println(current.choiceA); // DEBUG
      // System.out.println(secondBug.text + " REALLY SHOULD BE 3");

    display(current);
	}


  // IGNORE -- All the following are to fulfill requirements of interface for ActionPerformed and MouseListener. 
  public void actionPerformed(ActionEvent e){}
  public void mouseReleased(MouseEvent event) {}
  public void mouseClicked(MouseEvent event) {}
  public void mouseEntered(MouseEvent event) {}
  public void mouseExited(MouseEvent event) {}
  public void mousePressed(MouseEvent event) {}

  class LeftListener implements ActionListener {  // buttA - Left button action performed 
    public void actionPerformed(ActionEvent e) {
        current = nodeNames.get(current.choiceA);
        display(current);
        }
    }
  

  class RightListener implements ActionListener {  // buttB -  Right button action performed 
    public void actionPerformed(ActionEvent e) {
        current = nodeNames.get(current.choiceB);
        display(current);
    }
  }

  public void display(GameNode currentNode){ 
   mainText.setText(currentNode.text);   
   imageURL = currentNode.image;
   buttA.setText(currentNode.aText);
   buttB.setText(currentNode.bText);
   choiceA = currentNode.choiceA;
   choiceB = currentNode.choiceB;

   window.repaint();
  }

}

