import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrowRectangles extends JFrame {
  private JComboBox<String> colorList;
  private MyPanel drawPanel;

  public DrowRectangles() {
    setLayout(new BorderLayout());

    colorList = new JComboBox<>(new String[] {"Red", "Green", "Blue", "Yellow"});
    add(colorList, BorderLayout.NORTH);

    drawPanel = new MyPanel();
    drawPanel.addMouseListener(new MouseEventController());
    add(drawPanel, BorderLayout.CENTER);
    
  }

  private class MyPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
    }
  }

  private class MouseEventController extends MouseAdapter {
    private int x1, y1, x2, y2;

    @Override
    public void mousePressed(MouseEvent e) {
      x1 = e.getX();
      y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      x2 = e.getX();
      y2 = e.getY();

      int width = Math.abs(x2 - x1);
      int height = Math.abs(y2 - y1);

      Graphics g = drawPanel.getGraphics();
      g.setColor(getColor());
      g.fillRect(Math.min(x1, x2), Math.min(y1, y2), width, height);
    }

    private Color getColor() {
      String selectedColor = (String) colorList.getSelectedItem();
      switch (selectedColor) {
        case "Red":
          return Color.RED;
        case "Green":
          return Color.GREEN;
        case "Blue":
          return Color.BLUE;
        case "Yellow":
          return Color.YELLOW;
        default:
          return Color.BLACK;
      }
    }
  }

  public static void main(String[] args) {
    DrowRectangles frame = new DrowRectangles();
    frame.setTitle("Draw Rectangles");
    frame.setSize(400, 300);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}




