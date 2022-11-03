package ui;

import javax.swing.*;
import java.awt.*;

public class UIfunc extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel sysID;
    private JLabel place;
    private JLabel sdate;
    private JLabel party;
    private JLabel name;
    private JLabel title;
    private JPanel mainPanel;

    public UIfunc()  {
        setContentPane(mainPanel);
        setTitle("2022選舉候選人查詢系統");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
