package View.ui.Swing;

import View.ui.Display;
import Model.Money;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DisplaySwing extends JPanel implements Display{
    
    private final JTextArea displayArea;
    
    public DisplaySwing() {
        
        displayArea = new JTextArea(3, 19);
        displayArea.setEditable(false);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(displayArea);
        
        this.setSize(211, 59);
        this.setMaximumSize(new Dimension(211, 59));
        this.add(scroll);
    }

    @Override
    public void display(Money money) {
        this.displayArea.setText(money.toString());
    }
    
    @Override
    public void resetDisplay(){
        this.displayArea.setText(null);
    }

}
