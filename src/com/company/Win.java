package com.company;

import javax.swing.*;


public class Win extends JFrame {

    JButton button = null;

    public static void main(String[] args) {
        // write your code here
        Win W = new Win();
    }

    public Win(){
        this.setTitle("The first window");
        this.setSize(350, 180);
        this.setLocation(100, 260);
        button = new JButton("The first button");
        this.add(button);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
