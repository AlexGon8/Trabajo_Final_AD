import com.sun.java.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.beans.*;


// An example that shows how to do a few interesting things using 
// JInternalFrames, JDesktopPane, and DesktopManager.
public class SampleDesktop extends JFrame {

  private JDesktopPane desk;
  private IconPolice iconPolice = new IconPolice();

  public SampleDesktop(String title) {
    super(title);
   addWindowListener(new BasicWindowMonitor());

    // Create a desktop and set it as the content pane. Don't set the layered
    // pane, since it needs to hold the menubar too.
    desk = new JDesktopPane();
    setContentPane(desk);

    // Install our custom desktop manager
    desk.setDesktopManager(new SampleDesktopMgr());

    createMenuBar();
    loadBackgroundImage();
  }

  // Create a menubar to show off a few things.
  protected void createMenuBar() {
    JMenuBar mb = new JMenuBar();
    JMenu menu = new JMenu("Frames");

    menu.add(new AddFrameAction(true)); // add "upper" frame
    menu.add(new AddFrameAction(false)); // add "lower" frame
    menu.add(new TileAction(desk)); // add tiling capability

    setJMenuBar(mb);
    mb.add(menu);
  }

  // Here we load a background image for our desktop.
  protected void loadBackgroundImage() {
    ImageIcon icon = new ImageIcon("images/matterhorn.gif");
    JLabel l = new JLabel(icon);
    l.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

    // Place the image in the lowest possible layer so nothing
    // can ever be painted under it.
    desk.add(l, new Integer(Integer.MIN_VALUE));
  }

  // This class will add a new JInternalFrame when requested.
  class AddFrameAction extends AbstractAction {
    public AddFrameAction(boolean upper) {
      super(upper ? "Add Upper Frame" : "Add Lower Frame");
      if (upper) {
        this.layer = new Integer(2);
        this.name = "Up";
      }
      else {
        this.layer = new Integer(1);
        this.name = "Lo";
      }
    }

    public void actionPerformed(ActionEvent ev) {
      JInternalFrame f = new JInternalFrame(name,true,true,true,true);
      f.addVetoableChangeListener(iconPolice);

      f.setBounds(0, 0, 120, 60);
      desk.add(f, layer);
    }

    private Integer layer;
    private String name;
  }

  // A simple vetoable change listener that insists that there is always at
  // least one noniconified frame (just as an example of the vetoable 
  // properties).
  class IconPolice implements VetoableChangeListener {
    public void vetoableChange(PropertyChangeEvent ev)
     throws PropertyVetoException {

      String name = ev.getPropertyName();
      if (name.equals(JInternalFrame.IS_ICON_PROPERTY)
      && (ev.getNewValue() == Boolean.TRUE)) {
        JInternalFrame[] frames = desk.getAllFrames();
        int count = frames.length;
        int nonicons = 0; // how many are not icons?
        for (int i=0; i<count; i++) {
          if (frames[i].isIcon() == false) {
            nonicons++;
          }
        }
        if (nonicons <= 1) {
          throw new PropertyVetoException("Invalid Iconification!", ev);
        }
      }
    }
  }

  // A simple test program.
  public static void main(String[] args) {
    SampleDesktop td = new SampleDesktop("Sample Desktop");

    td.setSize(300, 220);
    td.setVisible(true);
  }
}
