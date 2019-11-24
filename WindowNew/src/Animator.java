package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Animator implements Runnable {

    Graphics G;
    ArrayList<Dot> L = new ArrayList();
    Polynom P;
    ArrayList<Polynom> LP = new ArrayList();

    public Animator(Graphics G, ArrayList<Dot> L) {
        this.G = G;
        this.L = L;

    }



    public void getPolynom(Polynom P) {
        this.P = P;
        LP.add(P);
    }

    public void Draw1(double x, double y) {
        G.drawString(Double.toString(x), 100, 100);
        G.drawString(Double.toString(y), 200, 200);
    }

    public void Draw() {
//        G.setColor(Color.MAGENTA);
//        G.fillRect(10, 10, 20, 90);
        G.setColor(Color.lightGray);
        G.drawLine(10, 250, 480, 250); // ось X серая
        G.drawLine(240, 10, 240, 380); // ось Y серая
        G.setColor(Color.black);
        G.drawLine(20, 250, 470, 250); // ось X
        G.drawLine(240, 20, 240, 370); // ось Y
        G.drawLine(455, 245, 470, 250);
        G.drawLine(455, 255, 470, 250);
        G.drawLine(245, 35, 240, 20);
        G.drawLine(235, 35, 240, 20);
        G.setColor(Color.lightGray);
        G.setColor(Color.black);
        Font font = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);
        G.setFont(font);
        G.drawString("x", 460, 270);
        G.drawString("y", 254, 35);
    }

    public void DrawPoint(int n) {
        G.setColor(Color.red);
        G.fillOval(backTransformX(L.get(n)) - 3, backTransformY(L.get(n)) - 3, 4, 4);
        G.setColor(Color.black);
    }

    public void DrawCross(int x, int y) {
        G.setColor(Color.blue);
        G.drawLine(x - 2, y - 2, x + 2, y + 2);
        G.drawLine(x - 2, y + 2, x + 2, y - 2);
        G.setColor(Color.black);
    }

    public int backTransformX(Dot dot) {
        int x = (int) (dot.x * 10 + 240);
        return x;
    }

    public int backTransformY(Dot dot) {
        int y = (int) (250 - dot.y * 10);
        return y;
    }

    public void DrawPolynom(Polynom P) {
        double x1 = -24;
        // double x2 = -24;
        Random r = new Random();
        Color C = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        G.setColor(C);

        for (int i = 0; i <= 50000; i++) {
            x1 += 0.001;
            double y1 = P.pointValue(x1);
            Dot dot1 = new Dot(x1, y1);
            G.fillOval(backTransformX(dot1), backTransformY(dot1), 1, 1);
        }

        G.setColor(Color.black);
    }

    @Override
    public void run() {
        Polynom P1;
        P1 = LP.get(LP.size() - 1);
        double x1 = -24;
        Random r = new Random();
        Color C = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        G.setColor(C);
        for (int i = 0; i <= 5000; i++) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
            x1 += 0.01;
            double y1 = P1.pointValue(x1);
            Dot dot1 = new Dot(x1, y1);
            G.setColor(C);
            G.fillOval(backTransformX(dot1), backTransformY(dot1), 1, 1);
        }
        G.setColor(Color.black);
    }


}

