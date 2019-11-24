package com.company;

import static java.lang.Math.pow;

public class Polynom {

    protected int n; // private
    protected double[] Coef;

    public Polynom(int k, double[] mas) {
        if ((k >= 0) && (mas.length == k + 1)) {
            int i = mas.length - 1;
            while ((mas[i] == 0) && (i > 0)) {
                i--;
            }
            if (i != k) {
                n = i;
                Coef = new double[i + 1];
                for (int j = 0; j < Coef.length; j++) {
                    Coef[j] = mas[j];
                }
            } else {
                n = k;
                Coef = new double[n + 1];
                for (i = 0; i < Coef.length; i++) {
                    Coef[i] = mas[i];
                }
            }
        } else {
            System.out.println("Введена неверная степень полинома!");
        }
    }

    public Polynom(int k) {
        if (k >= 0) {
            n = k;
            Coef = new double[n + 1];
            for (int i = 0; i < Coef.length; i++) {
                Coef[i] = 0;
            }
        } else {
            System.out.println("Неверная степень полинома!");
        }
    }

    public void copy(Polynom P) {
        if (n == P.n) {
            n = P.n;
            for (int i = 0; i < P.Coef.length; i++) {
                Coef[i] = P.Coef[i];
            }
        } else {
            System.out.println("Копирование невозможно.");
        }
    }

    public void assign(Polynom P) {
        n = P.n;
        Coef = new double[P.n + 1];
        for (int i = 0; i < P.Coef.length; i++) {
            Coef[i] = P.Coef[i];
        }
    }

    public Polynom addition(Polynom P) {
        int k;
        int m;
        boolean f = false;
        if (n < P.n) {
            k = n;
            m = P.n;
        } else {
            k = P.n;
            m = n;
            f = true;
        }
        double[] mas = new double[m + 1];
        for (int i = 0; i < mas.length; i++) {
            if (i < k + 1) {
                mas[i] = Coef[i] + P.Coef[i];
            } else {
                if (f) {
                    mas[i] = Coef[i];
                } else {
                    mas[i] = P.Coef[i];
                }
            }
        }
        Polynom R = new Polynom(m, mas);
        return R;
    }
    public Polynom subtraction(Polynom P) {
        int k;
        int m;
        boolean f = false;
        if (n < P.n) {
            k = n;
            m = P.n;
        } else {
            k = P.n;
            m = n;
            f = true;
        }
        double[] mas = new double[m + 1];
        for (int i = 0; i < mas.length; i++) {
            if (i < k + 1) {
                mas[i] = Coef[i] - P.Coef[i];
            } else {
                if (f) {
                    mas[i] = Coef[i];
                } else {
                    mas[i] = (-1) * P.Coef[i];
                }
            }
        }
        Polynom R = new Polynom(m, mas);
        return R;
    }

    public Polynom multConst(double c) {
        for (int i = 0; i < Coef.length; i++) {
            Coef[i] *= c;
        }
        return this; // ?
    }

    public Polynom multiplication(Polynom P) {
        double[] mas = new double[n + P.n + 1];
        for (int i = Coef.length - 1; i >= 0; i--) {
            for (int j = P.Coef.length - 1; j >= 0; j--) {
                mas[i + j] += Coef[i] * P.Coef[j];
            }
        }
        Polynom R = new Polynom(n + P.n, mas);
        return R;
    }

    public double pointValue(double x) {
        double res = 0;
        for (int i = 0; i < Coef.length; i++) {
            res += Coef[i] * pow(x, i);
        }
        return res;
    }

    public void show() {
        double eps = 0.00000000001;
        for (int i = 0; i < Coef.length; i++) {
            if (Math.abs(Coef[i]) < eps) {
                Coef[i] = 0;
            }
            if (Math.abs(Coef[i] - 1) < eps) {
                Coef[i] = 1;
            }
            if (Math.abs(Coef[i] + 1) < eps) {
                Coef[i] = -1;
            }
        }
        boolean first = true;
        if (n == 0) {
            System.out.print(String.format("%.0f", Coef[0]));
        } else {
            // первый и последний члены
            if (Coef[Coef.length - 1] != 0) {
                if (Coef[Coef.length - 1] == 1) {
                    if (Coef.length - 1 == 1) {
                        System.out.print("x ");
                        first = false;
                    } else {
                        System.out.print("x^" + (Coef.length - 1) + " ");
                        first = false;
                    }
                }
                if (Coef[Coef.length - 1] == -1) {
                    if (Coef.length - 1 == 1) {
                        System.out.print("- x ");
                        first = false;
                    } else {
                        System.out.print("- x^" + (Coef.length - 1) + " ");
                        first = false;
                    }
                }
                if ((Coef[Coef.length - 1] != 1) && (Coef[Coef.length - 1] != -1)) {
                    if (Coef.length - 1 == 1) {
                        System.out.print(String.format("%.0f", Coef[Coef.length - 1]) + "x ");
                        first = false;
                    } else {
                        System.out.print(String.format("%.0f", Coef[Coef.length - 1]) + "x^" + (Coef.length - 1) + " ");
                        first = false;
                    }
                }
            }
            for (int i = Coef.length - 2; i > 0; i--) {
                if (Coef[i] > 0) {
                    if (Coef[i] == 1) {
                        if (i == 1) {
                            if (first) {
                                System.out.print("x ");
                                first = false;
                            } else {
                                System.out.print("+ x ");
                                first = false;
                            }
                        } else {
                            if (first) {
                                System.out.print("x^" + i + " ");
                                first = false;
                            } else {
                                System.out.print("+ x^" + i + " ");
                                first = false;
                            }
                        }
                    } else {
                        if (i == 1) {
                            if (first) {
                                System.out.print(String.format("%.0f", Coef[i]) + "x ");
                                first = false;
                            } else {
                                System.out.print("+ " + String.format("%.0f", Coef[i]) + "x ");
                                first = false;
                            }
                        } else {
                            if (first) {
                                System.out.print(String.format("%.0f", Coef[i]) + "x^" + i + " ");
                                first = false;
                            } else {
                                System.out.print("+ " + String.format("%.0f", Coef[i]) + "x^" + i + " ");
                                first = false;
                            }
                        }
                    }
                }
                if (Coef[i] < 0) {
                    if (Coef[i] == -1) {
                        if (i == 1) {
                            System.out.print("- x ");
                            first = false;
                        } else {
                            System.out.print("- x^" + i + " ");
                            first = false;
                        }
                    } else {
                        if (i == 1) {
                            System.out.print("- " + String.format("%.0f", Coef[i] * (-1)) + "x ");
                            first = false;
                        } else {
                            System.out.print("- " + String.format("%.0f", Coef[i] * (-1)) + "x^" + i + " ");
                            first = false;
                        }
                    }
                }
            }
            if (Coef[0] != 0) {
                if (Coef[0] > 0) {
                    if (first) {
                        System.out.print(String.format("%.0f", Coef[0]));
                        first = false;
                    } else {
                        System.out.print("+ " + String.format("%.0f", Coef[0]));
                        first = false;
                    }
                } else {
                    System.out.print("- " + String.format("%.0f", Coef[0] * (-1)));
                    first = false;
                }
            }
        }
        System.out.println();
    }

    public Polynom fundPolynom(int k, int n, double[] fnodes) {
        Polynom res = new Polynom(0);
        res.Coef[0] = 1;
        for (int i = 0; i < n; i++) {
            double[] mas = new double[2];
            mas[0] = fnodes[i] * (-1);
            mas[1] = 1;
            Polynom temp = new Polynom(1, mas);
            if (i != k) {
                res = res.multiplication(temp.multConst(1 / (fnodes[k] - fnodes[i]))); // присвоение ?
            }
        }
        return res;
    }

    public Polynom LagrangePolynom(double[] nodes) {
        Polynom res = new Polynom(0);
        if (nodes.length == 0) {
            System.out.println("Узлов нет!");
        } else {
            res = new Polynom(nodes.length - 1);
            for (int i = 0; i < nodes.length; i++) {
                res = res.addition(fundPolynom(i, nodes.length, nodes).multConst(pointValue(nodes[i])));
            }
        }
        return res;
    }

    public Polynom LagrangePolynom(double[] nodes, double[] values) {
        Polynom res = new Polynom(0);
        if ((nodes.length == 0) || (values.length == 0)) {
            System.out.println("Ошибка ввода!");
        } else {
            res = new Polynom(nodes.length - 1);
            for (int i = 0; i < nodes.length; i++) {
                res = res.addition(fundPolynom(i, nodes.length, nodes).multConst(values[i]));
            }
        }
        return res;
    }
}
