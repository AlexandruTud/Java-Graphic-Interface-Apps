import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class HorseRaceSimulator extends JFrame implements ActionListener {
  private final JButton startRaceButton;
  private final JPanel trackPanel;
  private final ArrayList<Horse> horses;
  private Timer timer;
  private final Random random;
  private final JButton stopRaceButton;
  public HorseRaceSimulator() {
    super("Horse Race Simulator");
    setResizable(false);
    startRaceButton = new JButton("Start");
    stopRaceButton = new JButton("Stop");
    trackPanel = new JPanel();
    horses = new ArrayList<Horse>();
    timer = new Timer(50, this);
    random = new Random();
    setLocationRelativeTo(null);
    
    trackPanel.setPreferredSize(new Dimension(500, 200));
    trackPanel.setLayout(null);
    
    for (int i = 0; i < 5; i++) {
      Horse horse = new Horse(i + 1);
      horses.add(horse);
      trackPanel.add(horse);
    }
    
    startRaceButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.start();
      }
    });
    
    stopRaceButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.stop();
      }
    });
    
    
    Container container = getContentPane();
    container.setLayout(new BorderLayout());
    container.add(trackPanel, BorderLayout.CENTER);
    container.add(startRaceButton, BorderLayout.NORTH);
    container.add(stopRaceButton, BorderLayout.SOUTH);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }
  

  public void actionPerformed(ActionEvent e) {
    for (Horse horse : horses) {
      horse.move();
      if (horse.getX() >= 450) {
        timer.stop();
        JOptionPane.showMessageDialog(HorseRaceSimulator.this, "Horse "  + horse.getHorseNumber() + " is the winner!");
        dispose();
      }
    }
    trackPanel.repaint(); 
  }
  
  public static void main(String[] args) {
    new HorseRaceSimulator();
  }
 
  class Horse extends JComponent {
    private int x, y;
    private int horseNumber;
    
    public Horse(int horseNumber) {
      x = 0;
      y = (horseNumber - 1) * 40;
      this.horseNumber = horseNumber;
    }
    
    public void move() {
      x += random.nextInt(10) + 1;
      setBounds(x, y, 50, 40);
    }
    
     public int getHorseNumber() {
      return horseNumber ;
    }

    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, 50, 40);
      g.setColor(Color.WHITE);
      g.drawString("Horse " + horseNumber,3, 20);
      
    }
  }
}