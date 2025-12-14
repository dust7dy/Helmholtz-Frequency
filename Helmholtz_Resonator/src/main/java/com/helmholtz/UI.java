package com.helmholtz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Dust DY (dust7dy) on GitHub
 * This class creates the user interface of the helmholtz-calculator.
 */
public class UI{
    int x;
    int y;
    int ins;
    JFrame frame;
    JPanel bg;
    JPanel infoBar;
    JButton start;
    JButton youtube;
    JButton exit;

    public UI(int x, int y, int ins){
        this.x = x;
        this.y = y;
        this.ins = ins;

        frame = new JFrame();
        bg = new JPanel();
        infoBar = new JPanel();
        start = new JButton();
        youtube = new JButton();
        exit = new JButton();

        bg.setBounds(0,0,this.x,this.y);
        bg.setBackground(new Color(0x000000));

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setSize(x,y);
        frame.setResizable(false);

        frame.setTitle("Dusty Desk");
        frame.setIconImage(new ImageIcon("assets/icon_ddy.PNG").getImage());

        infoBar.setBounds((int)(frame.getX()*0.5-100),50,frame.getX(),50);
        infoBar.setBackground(new Color(0x005050));

        start.setBounds((int)(this.x*0.5)-100, (int)(this.y*0.3), 200,50);
        start.setText("Open calculator");
        start.setBackground(new Color(0x008080));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == start){
                    new UI_calc(x,y);
                    frame.dispose();
                }
            }
        });

        exit.setBounds((int)(this.x*0.5)-100, (int)(this.y*0.8), 200,50);
        exit.setText("Close winow");
        exit.setBackground(new Color(0x008080));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exit){
                    frame.dispose();
                }
            }
        });



        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        frame.add(bg);
        frame.add(infoBar);
        frame.add(start);
        //frame.add(youtube);
        frame.add(exit);
    }

}
