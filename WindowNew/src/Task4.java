package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class Task4 extends JFrame {

    JPanel panel;
    JButton refresh;
    Animator A;
    ArrayList<Dot> L = new ArrayList();      //массив из точек
    Polynom P = new Polynom(0);

    public Task4() {
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        panel = new JPanel();
        panel.setBounds(50, 50, 500, 400);
        panel.setBackground(Color.white);
        add(panel);


        panel.addMouseListener(new MouseListener() {



            @Override
            public void mouseClicked(MouseEvent e) {
                Dot dot = new Dot();
                dot.x = affineTransformX(e.getX()); //точка относительно начала координат
                dot.y = affineTransformY(e.getY());
                boolean f = true;
                for (int i = 0; i < L.size(); i++) {   // до длины массива из точек
                    if (Math.abs(L.get(i).x - dot.x) < 0.5) {
                        f = false;
                        break;
                    }
                }
                if (f) {
                    L.add(dot);
                    A.DrawPoint(L.size() - 1);
                    double[] nodes = new double[L.size()];
                    double[] values = new double[L.size()];
                    for (int i = 0; i < L.size(); i++) {
                        nodes[i] = L.get(i).x;
                        values[i] = L.get(i).y;
                    }
                    P = P.LagrangePolynom(nodes, values);
                    A.getPolynom(P);
                    new Thread(A).start();
                } else {
                    A.DrawCross(e.getX(), e.getY());
                }

            }





            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.setVisible(true);

        A = new Animator(panel.getGraphics(), L);

    }



    public static void main(String[] args) {
        new Task4();
    }

    @Override
    public void paint(Graphics G) {
        super.paint(G);
        if (A != null) {
            A.Draw();
            for (int i = 0; i < L.size(); i++) {
                A.DrawPoint(i);
            }
        }

    }

    public double affineTransformX(double x) {
        double x1 = x - 240.0;
        x1 /= 10;
        return x1;
    }

    public double affineTransformY(double y) {
        double y1 = 250.0 - y;
        y1 /= 10;
        return y1;
    }
}


