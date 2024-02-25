// TileAction.java
//
import com.sun.java.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.beans.*;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

// An action that tiles all internal frames when requested.
public class TileAction extends AbstractAction {
  private JDesktopPane desk; // the desktop to work with

  public TileAction(JDesktopPane desk) {
    super("Tile Frames");
    this.desk = desk;
  }

  public void actionPerformed(ActionEvent ev) {

    // How many frames do we have?
    JInternalFrame[] allframes = desk.getAllFrames();
    int count = allframes.length;
    if (count == 0) return;

    // Determine the necessary grid size
    int sqrt = (int)Math.sqrt(count);
    int rows = sqrt;
    int cols = sqrt;
    if (rows*cols < count) {
      cols++;
      if (rows*cols < count) {
        rows++;
      }
    }

    // Define some initial values for size & location
    Dimension size = desk.getSize();

    int w = size.width/cols;
    int h = size.height/rows;
    int x = 0;
    int y = 0;

    // Iterate over the frames, deiconifying any iconified frames and then
    // relocating & resizing each
    for (int i=0; i<rows; i++) {
      for (int j=0; j<cols && ((i*cols)+j<count); j++) {
        JInternalFrame f = allframes[(i*cols)+j];

        if ((f.isClosed() == false) && (f.isIcon() == true)) {
          try {
            f.setIcon(false);
          }
          catch (PropertyVetoException ex) {}
        }

        desk.getDesktopManager().resizeFrame(f, x, y, w, h);
        x += w;
      }
      y += h; // start the next row
      x = 0;
    }
  }
}
