package annotation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by YuanXiang on 2017/5/31.
 */
public class ButtonFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private JPanel panel;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton resButton;

    public ButtonFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        panel = new JPanel();
        add(panel);
        yellowButton = new JButton("Yellow");
        blueButton = new JButton("Blue");
        resButton = new JButton("Red");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(resButton);

        ActionListenerInstaller.processAnnotations(this);
    }

    @ActionListenerFor(soure = "yellowButton")
    public void yellowBackground(){
        panel.setBackground(Color.YELLOW);
    }


    @ActionListenerFor(soure = "blueButton")
    public void blueBackground(){
        panel.setBackground(Color.BLUE);
    }

    @ActionListenerFor(soure = "redButton")
    public void redBackground(){
        panel.setBackground(Color.RED);
    }

    public static void main(String[] args) {
        ButtonFrame buttonFrame = new ButtonFrame();
    }

}
