package ui;

import model.Carta;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.beans.Visibility;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.event.ComponentAdapter;
import java.awt.Component;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.awt.event.ComponentEvent;
import javax.print.DocFlavor.STRING;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;

import org.w3c.dom.css.RGBColor;

import java.awt.*;

public class CardUi extends JFrame implements Runnable {
    Border redBorder = BorderFactory.createLineBorder(Color.red);
    Border orangeBorder = BorderFactory.createLineBorder(Color.orange);
    Border yellowBorder = BorderFactory.createLineBorder(Color.yellow);
    Border greenBorder = BorderFactory.createLineBorder(Color.green);
    Border blueBorder = BorderFactory.createLineBorder(Color.blue);
    Border magentaBorder = BorderFactory.createLineBorder(Color.magenta);
    Border twoColorBorder = new CompoundBorder(magentaBorder, blueBorder);
    Border threeColorBorder = new CompoundBorder(twoColorBorder,
            greenBorder);
    Border fourColorBorder = new CompoundBorder(threeColorBorder,
            yellowBorder);
    Border fiveColorBorder = new CompoundBorder(fourColorBorder,
            orangeBorder);
    Border sixColorBorder = new CompoundBorder(fiveColorBorder, redBorder);

    Border blackline2 = BorderFactory.createTitledBorder("Title");
    Border blackline1 = BorderFactory.createLineBorder(Color.black);
    Border blackline3 = BorderFactory.createLineBorder(new Color(252, 204, 51));

    public JPanel Card1, Card2, Card3, Card4, Card5, CartaElegida1, CartaElegida2, botonP;

    public JPanel panelCartasElegidas, panelCartasRandom, infoProbabilidad;
    public CardPickup cardP1 = new CardPickup();
    public CardPickup cardP2 = new CardPickup();

    private String PaloCarta1, PaloCarta2;
    private String NumeroCarta1, NumeroCarta2;

    public JLabel PCarta1, PCarta2;
    public JLabel NCarta1, NCarta2;
    public JLabel probColor, probFull, probPoker;
    public JLabel probReal;

    public JLabel nc1, nc2, nc3, nc4, nc5;
    public JLabel pc1, pc2, pc3, pc4, pc5;

    boolean bCarta1 = false, bCarta2 = false;
    public JButton botonProb;
    public JTextPane textProb;

    // LOGIC THINGS
    public enum PALO {
        PICA, TREBOL, DIAMANTE, CORAZON
    }

    public static String[] palos = { "♠", "♣", "♦", "♥" };

    public static String[] numeros = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    private static ArrayList<Carta> cartas = new ArrayList<>();
    private static ArrayList<Carta> cartasPica = new ArrayList<>();
    private static ArrayList<Carta> cartasTrebol = new ArrayList<>();
    private static ArrayList<Carta> cartasDiamante = new ArrayList<>();
    private static ArrayList<Carta> cartasCorazon = new ArrayList<>();
    private Thread planificar = new Thread(this);

    private static Carta cartaElegida1;
    private static Carta cartaElegida2;

    public static float pColor;
    public static float pPoker;
    public static float pFull;
    public static BigDecimal pReal;

    public CardUi() {
        setTitle("Probabilidades Poker");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setup();
    }

    public void setup() {

        panelCartasRandom = new JPanel();
        panelCartasRandom.setLayout(new GridLayout(1, 5));
        panelCartasRandom.setBounds(0, 200, this.getWidth() - 10, 200);
        panelCartasRandom.setBorder(BorderFactory.createTitledBorder(blackline2, "Cartas Aleatorias"));

        add(panelCartasRandom);

        panelCartasElegidas = new JPanel();
        panelCartasElegidas.setBorder(BorderFactory.createTitledBorder(blackline2, "Cartas Elegidas"));

        panelCartasElegidas.setBounds(0, 500, this.getWidth() - 10, 200);
        panelCartasElegidas.setLayout(new GridLayout(1, 2));

        add(panelCartasElegidas);

        intiCartasRandom();
        initCartasElegidas();

        cardP1.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                Component c = (Component) evt.getSource();

            }

            public void componentHidden(ComponentEvent evt) {

                repaint();
                invalidate();
                validate();

                if (!(cardP2.getPalo() == null)) {
                    if (cardP1.getNumero().equals(cardP2.getNumero()) && cardP1.getPalo().equals(cardP2.getPalo())) {

                    } else {
                        NumeroCarta1 = cardP1.getNumero();
                        PaloCarta1 = cardP1.getPalo();
                        PCarta1.setText(PaloCarta1);
                        NCarta1.setText(NumeroCarta1);
                        System.out.println("Carta 1: " + NumeroCarta1 + " de " + PaloCarta1);
                        if (PaloCarta1.equals("♥") || PaloCarta1.equals("♦")) {
                            PCarta1.setForeground(Color.red);
                            NCarta1.setForeground(Color.red);
                        } else {
                            PCarta1.setForeground(Color.black);
                            NCarta1.setForeground(Color.black);
                        }
                        bCarta1 = true;
                    }
                } else {
                    NumeroCarta1 = cardP1.getNumero();
                    PaloCarta1 = cardP1.getPalo();
                    PCarta1.setText(PaloCarta1);
                    NCarta1.setText(NumeroCarta1);
                    System.out.println("Carta 1: " + NumeroCarta1 + " de " + PaloCarta1);
                    if (PaloCarta1.equals("♥") || PaloCarta1.equals("♦")) {
                        PCarta1.setForeground(Color.red);
                        NCarta1.setForeground(Color.red);
                    } else {
                        PCarta1.setForeground(Color.black);
                        NCarta1.setForeground(Color.black);
                    }
                    bCarta1 = true;

                }
                if (bCarta1 && bCarta2) {
                    botonProb.setEnabled(true);
                    cartaElegida1 = new Carta(PaloCarta1, NumeroCarta1);
                    cartaElegida2 = new Carta(PaloCarta2, NumeroCarta2);
                    cartas.remove(cartaElegida1);
                    cartas.remove(cartaElegida2);

                    generarCartas();

                    planificar.start();
                }
            }

            public void componentMoved(ComponentEvent evt) {

            }

            public void componentResized(ComponentEvent evt) {

            }
        });

        cardP2.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                Component c = (Component) evt.getSource();

            }

            public void componentHidden(ComponentEvent evt) {

                repaint();
                invalidate();
                validate();

                if (!(cardP1.getPalo() == null)) {
                    if (cardP2.getNumero().equals(cardP1.getNumero()) && cardP2.getPalo().equals(cardP1.getPalo())) {

                    } else {
                        NumeroCarta2 = cardP2.getNumero();
                        PaloCarta2 = cardP2.getPalo();
                        PCarta2.setText(PaloCarta2);
                        NCarta2.setText(NumeroCarta2);
                        System.out.println("Carta 2: " + NumeroCarta2 + " de " + PaloCarta2);
                        if (PaloCarta2.equals("♥") || PaloCarta2.equals("♦")) {
                            PCarta2.setForeground(Color.red);
                            NCarta2.setForeground(Color.red);
                        } else {
                            PCarta2.setForeground(Color.black);
                            NCarta2.setForeground(Color.black);
                        }
                        bCarta2 = true;

                    }
                } else {
                    NumeroCarta2 = cardP2.getNumero();
                    PaloCarta2 = cardP2.getPalo();
                    PCarta2.setText(PaloCarta2);
                    NCarta2.setText(NumeroCarta2);
                    System.out.println("Carta 2: " + NumeroCarta2 + " de " + PaloCarta2);
                    if (PaloCarta2.equals("♥") || PaloCarta2.equals("♦")) {
                        PCarta2.setForeground(Color.red);
                        NCarta2.setForeground(Color.red);
                    } else {
                        PCarta2.setForeground(Color.black);
                        NCarta2.setForeground(Color.black);
                    }
                    bCarta2 = true;
                }

                if (bCarta1 && bCarta2) {
                    botonProb.setEnabled(true);
                    cartaElegida1 = new Carta(PaloCarta1, NumeroCarta1);
                    cartaElegida2 = new Carta(PaloCarta2, NumeroCarta2);
                    cartas.remove(cartaElegida1);
                    cartas.remove(cartaElegida2);

                    generarCartas();

                    planificar.start();
                }

            }

            public void componentMoved(ComponentEvent evt) {

            }

            public void componentResized(ComponentEvent evt) {

            }
        });

        infoProbabilidad = new JPanel();
        infoProbabilidad.setLayout(new GridLayout(4, 1));

        infoProbabilidad.setBounds(0, 700, this.getWidth() - 10, 200);
        probColor = new JLabel("Probabilidad COLOR: ");
        probColor.setFont(new Font("Helvetica", Font.BOLD, 20));

        probFull = new JLabel("Probabilidad FULL HOUSE: ");
        probFull.setFont(new Font("Helvetica", Font.BOLD, 20));

        probPoker = new JLabel("Probabilidad POKER: ");
        probPoker.setFont(new Font("Helvetica", Font.BOLD, 20));

        probReal = new JLabel("Probabilidad ESCALERA REAL: ");
        probReal.setFont(new Font("Helvetica", Font.BOLD, 20));

        infoProbabilidad.add(probColor);
        infoProbabilidad.add(probFull);
        infoProbabilidad.add(probPoker);
        infoProbabilidad.add(probReal);

        add(infoProbabilidad);

    }

    public void calcularProbabilidades() {

        COLOR();
        FULLHOUSE();
        POKER();
        REAL();

        probColor.setText("Probabilidad COLOR: " + pColor);
        probFull.setText("Probabilidad FULL HOUSE: " + pFull);
        probPoker.setText("Probabilidad POKER: " + pPoker);
        probReal.setText("Probabilidad ESCALERA REAL: " + pReal);

    }

    public static void generarCartas() {

        for (String palo : palos) {
            for (String numero : numeros) {
                if (cartaElegida1.getPalo().equals(palo.toString())
                        && cartaElegida1.getNumero().toString().equals(numero.toString())) {
                } else if (cartaElegida2.getPalo().toString().equals(palo.toString())
                        && cartaElegida2.getNumero().toString().equals(numero.toString())) {
                } else {
                    if (palo.equals("♥")) {
                        Carta carta = new Carta(palo.toString(), numero.toString());
                        cartas.add(carta);
                        cartasCorazon.add(carta);
                    } else if (palo.equals("♦")) {
                        Carta carta = new Carta(palo.toString(), numero.toString());
                        cartas.add(carta);
                        cartasDiamante.add(carta);
                    } else if (palo.equals("♠")) {
                        Carta carta = new Carta(palo.toString(), numero.toString());
                        cartas.add(carta);
                        cartasPica.add(carta);
                    } else if (palo.equals("♣")) {
                        Carta carta = new Carta(palo.toString(), numero.toString());
                        cartas.add(carta);
                        cartasTrebol.add(carta);
                    }
                }
            }
        }

    }

    public void run() {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

        ses.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {
                    int index = (int) (Math.random() * cartas.size() - 2 + 1);

                    if (i == 0) {
                        if (cartas.get(index).getPalo().equals("♥") || cartas.get(index).getPalo().equals("♦")) {
                            nc1.setForeground(Color.red);
                            pc1.setForeground(Color.red);
                        } else {
                            nc1.setForeground(Color.black);
                            pc1.setForeground(Color.black);
                        }
                        nc1.setText(cartas.get(index).getNumero());
                        pc1.setText(cartas.get(index).getPalo());

                    } else if (i == 1) {
                        if (cartas.get(index).getPalo().equals("♥") || cartas.get(index).getPalo().equals("♦")) {
                            nc2.setForeground(Color.red);
                            pc2.setForeground(Color.red);
                        } else {
                            nc2.setForeground(Color.black);
                            pc2.setForeground(Color.black);
                        }
                        nc2.setText(cartas.get(index).getNumero());
                        pc2.setText(cartas.get(index).getPalo());
                    } else if (i == 2) {
                        if (cartas.get(index).getPalo().equals("♥") || cartas.get(index).getPalo().equals("♦")) {
                            nc3.setForeground(Color.red);
                            pc3.setForeground(Color.red);
                        } else {
                            nc3.setForeground(Color.black);
                            pc3.setForeground(Color.black);
                        }
                        nc3.setText(cartas.get(index).getNumero());
                        pc3.setText(cartas.get(index).getPalo());
                    } else if (i == 3) {
                        if (cartas.get(index).getPalo().equals("♥") || cartas.get(index).getPalo().equals("♦")) {
                            nc4.setForeground(Color.red);
                            pc4.setForeground(Color.red);
                        } else {
                            nc4.setForeground(Color.black);
                            pc4.setForeground(Color.black);
                        }
                        nc4.setText(cartas.get(index).getNumero());
                        pc4.setText(cartas.get(index).getPalo());
                    } else if (i == 4) {
                        if (cartas.get(index).getPalo().equals("♥") || cartas.get(index).getPalo().equals("♦")) {
                            nc5.setForeground(Color.red);
                            pc5.setForeground(Color.red);
                        } else {
                            nc5.setForeground(Color.black);
                            pc5.setForeground(Color.black);
                        }
                        nc5.setText(cartas.get(index).getNumero());
                        pc5.setText(cartas.get(index).getPalo());
                    }

                }
            }

        }, 0, 150, TimeUnit.MILLISECONDS);

    }

    public static BigInteger Combinaciones(int x, int y) {
        BigInteger result;
        // N! / (N - R)! * R!
        BigInteger N = factorial(x);
        int M = (x - y);
        BigInteger F = factorial(M);
        BigInteger G = F.multiply(factorial(y));
        result = N.divide(G);
        return result;
    }

    public static BigInteger Arreglos(int x, int y) {
        BigInteger result;
        BigInteger N = factorial(x);
        int M = (x - y);
        BigInteger F = factorial(M);
        result = N.divide(F);
        return result;
    }

    public static BigInteger factorial(int j) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= j; ++i) {
            // factorial = factorial * i;
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    public static void REAL() {

        if ((cartaElegida1.getNumero().equals("A") || cartaElegida1.getNumero().equals("K")
                || cartaElegida1.getNumero().equals("Q") || cartaElegida1.getNumero().equals("J")
                || cartaElegida1.getNumero().equals("10"))
                && !(cartaElegida2.getNumero().equals("A") || cartaElegida2.getNumero().equals("K")
                        || cartaElegida2.getNumero().equals("Q") || cartaElegida2.getNumero().equals("J")
                        || cartaElegida2.getNumero().equals("10"))
                ||

                (cartaElegida2.getNumero().equals("A") || cartaElegida2.getNumero().equals("K")
                        || cartaElegida2.getNumero().equals("Q") || cartaElegida2.getNumero().equals("J")
                        || cartaElegida2.getNumero().equals("10"))
                        && !(cartaElegida1.getNumero().equals("A") || cartaElegida1.getNumero().equals("K")
                                || cartaElegida1.getNumero().equals("Q") || cartaElegida1.getNumero().equals("J")
                                || cartaElegida1.getNumero().equals("10"))

        ) {

        }

        // TOTAL POSIBILIDADES

        if ((cartaElegida1.getNumero().equals("A") || cartaElegida1.getNumero().equals("K")
                || cartaElegida1.getNumero().equals("Q") || cartaElegida1.getNumero().equals("J")
                || cartaElegida1.getNumero().equals("10"))
                && !(cartaElegida2.getNumero().equals("A") || cartaElegida2.getNumero().equals("K")
                        || cartaElegida2.getNumero().equals("Q") || cartaElegida2.getNumero().equals("J")
                        || cartaElegida2.getNumero().equals("10"))
                ||

                (cartaElegida2.getNumero().equals("A") || cartaElegida2.getNumero().equals("K")
                        || cartaElegida2.getNumero().equals("Q") || cartaElegida2.getNumero().equals("J")
                        || cartaElegida2.getNumero().equals("10"))
                        && !(cartaElegida1.getNumero().equals("A") || cartaElegida1.getNumero().equals("K")
                                || cartaElegida1.getNumero().equals("Q") || cartaElegida1.getNumero().equals("J")
                                || cartaElegida1.getNumero().equals("10"))) {

            BigInteger total = Combinaciones(52, 5);
            BigDecimal xB = new BigDecimal(total);

            BigDecimal xA = new BigDecimal(Combinaciones(3, 1));
            BigDecimal xN = new BigDecimal(Combinaciones(48, 2));

            BigDecimal xC = xA.multiply(xN);

            BigDecimal general1 = xC.divide(xB, 15, RoundingMode.HALF_EVEN);

            BigDecimal xF = new BigDecimal(Combinaciones(4, 1));
            BigDecimal xH = xF.divide(xB, 15, RoundingMode.HALF_EVEN);

            BigDecimal xG = xH.multiply(BigDecimal.valueOf(3));
            BigDecimal general = xG.add(general1);
            BigDecimal xV = general.multiply(BigDecimal.valueOf(100));

            System.out.println("POSIBILIDAD TOTAL REAL: " + xV + "%");
            pReal = xV;

        } else if ((cartaElegida1.getPalo().equals("♥") && cartaElegida2.getPalo().equals("♥"))
                || (cartaElegida1.getPalo().equals("♦") && cartaElegida2.getPalo().equals("♦"))
                || (cartaElegida1.getPalo().equals("♠") && cartaElegida2.getPalo().equals("♠"))
                || (cartaElegida1.getPalo().equals("♣") && cartaElegida2.getPalo().equals("♣"))) {

            BigInteger total = Combinaciones(52, 5);
            BigDecimal xB = new BigDecimal(total);

            BigDecimal xA = new BigDecimal(Combinaciones(3, 1));
            BigDecimal xN = new BigDecimal(Combinaciones(50, 3));

            BigDecimal xC = xA.multiply(xN);
            BigDecimal general1 = xC.divide(xB, 15, RoundingMode.HALF_EVEN);

            BigDecimal xF = new BigDecimal(Combinaciones(4, 1));
            BigDecimal xH = xF.divide(xB, 15, RoundingMode.HALF_EVEN);

            BigDecimal xG = xH.multiply(BigDecimal.valueOf(3));
            BigDecimal general = xG.add(general1);
            BigDecimal xV = general.multiply(BigDecimal.valueOf(100));

            System.out.println("POSIBILIDAD TOTAL REAL: " + xV + "%");
            pReal = xV;

        } else {

            BigInteger total = Combinaciones(52, 5);
            BigDecimal xB = new BigDecimal(total);

            BigDecimal xA = new BigDecimal(Combinaciones(4, 1));
            BigDecimal xC = xA.multiply(BigDecimal.valueOf(4));

            BigDecimal general = xC.divide(xB, 15, RoundingMode.HALF_EVEN);
            BigDecimal xV = general.multiply(BigDecimal.valueOf(100));

            System.out.println("POSIBILIDAD TOTAL REAL: " + xV + "%");
            pReal = xV;
        }

        // caso donde hay un palo real

    }

    public static void COLOR() {

        // TOTAL POSIBILIDADES
        BigInteger total = Combinaciones(52, 5);
        // COLOR
        BigDecimal xA = new BigDecimal(Combinaciones(26, 5));
        BigDecimal xB = new BigDecimal(total);
        BigDecimal general2 = xA.divide(xB, 15, RoundingMode.HALF_EVEN);

        //// POSIBILIDADES General
        BigDecimal gA = new BigDecimal(Combinaciones(12, 5));
        BigDecimal gB = new BigDecimal(total);
        BigDecimal general = gA.divide(gB, 15, RoundingMode.HALF_EVEN);

        if (cartaElegida1.getPalo().equals("♥") && cartaElegida2.getPalo().equals("♣")
                || cartaElegida1.getPalo().equals("♣") && cartaElegida2.getPalo().equals("♥")) {
            //// POSIBILIDADES CON CORAZON
            BigDecimal cA = new BigDecimal(Combinaciones(cartasCorazon.size(), 4));
            BigDecimal cM = cA.multiply(BigDecimal.valueOf(38));
            BigDecimal cB = new BigDecimal(total);
            BigDecimal cX = cM.divide(cB, 15, RoundingMode.HALF_EVEN);
            BigDecimal pColorCorazon = cX.add(general);
            System.out.println("Posibildiades Color Corazon: " + pColorCorazon);

            //// POSIBILIDADES CON TREBOL
            BigDecimal tA = new BigDecimal(Combinaciones(cartasTrebol.size(), 4));
            BigDecimal tM = tA.multiply(BigDecimal.valueOf(38));
            BigDecimal tB = new BigDecimal(total);
            BigDecimal tX = tM.divide(tB, 15, RoundingMode.HALF_EVEN);
            BigDecimal pColorTrebol = tX.add(general);
            System.out.println("Posibildiades Color Trebol: " + pColorTrebol);

            BigDecimal posibilidadColorTotal1 = pColorTrebol.add(pColorCorazon);
            BigDecimal posibilidadColorTotal2 = posibilidadColorTotal1.add(general2);
            BigDecimal posibilidadColorTotal3 = posibilidadColorTotal2.add(general2);

            System.out.println("POSIBILIDAD TOTAL COLOR: " + posibilidadColorTotal3.floatValue() * 100 + "%");
            pColor = posibilidadColorTotal3.floatValue() * 100;

            //////////////////////////////////
        } else if (cartaElegida1.getPalo().equals("♥") && cartaElegida2.getPalo().equals("♠")
                || cartaElegida1.getPalo().equals("♠") && cartaElegida2.getPalo().equals("♥")) {
            BigDecimal cA = new BigDecimal(Combinaciones(cartasCorazon.size(), 4));
            BigDecimal cM = cA.multiply(BigDecimal.valueOf(38));
            BigDecimal cB = new BigDecimal(total);
            BigDecimal cX = cM.divide(cB, 15, RoundingMode.HALF_EVEN);
            BigDecimal pColorCorazon = cX.add(general);

            //// POSIBILIDADES CON TREBOL
            BigDecimal tA = new BigDecimal(Combinaciones(cartasPica.size(), 4));
            BigDecimal tM = tA.multiply(BigDecimal.valueOf(38));
            BigDecimal tB = new BigDecimal(total);
            BigDecimal tX = tM.divide(tB, 15, RoundingMode.HALF_EVEN);
            BigDecimal pColorPica = tX.add(general);

            BigDecimal posibilidadColorTotal1 = pColorPica.add(pColorCorazon);
            BigDecimal posibilidadColorTotal2 = posibilidadColorTotal1.add(general2);
            BigDecimal posibilidadColorTotal3 = posibilidadColorTotal2.add(general2);

            System.out.println("POSIBILIDAD TOTAL COLOR: " + posibilidadColorTotal3.floatValue() * 100 + "%");
            pColor = posibilidadColorTotal3.floatValue() * 100;

        } else if (cartaElegida1.getPalo().equals("♥") && cartaElegida2.getPalo().equals("♦")
                || cartaElegida1.getPalo().equals("♦") && cartaElegida2.getPalo().equals("♥")) {
            BigDecimal cA = new BigDecimal(Combinaciones(cartasCorazon.size(), 4));
            BigDecimal cM = cA.multiply(BigDecimal.valueOf(38));
            BigDecimal cB = new BigDecimal(total);
            BigDecimal cX = cM.divide(cB, 15, RoundingMode.HALF_EVEN);
            BigDecimal pColorCorazon = cX.add(general);

            //// POSIBILIDADES CON TREBOL
            BigDecimal tA = new BigDecimal(Combinaciones(cartasDiamante.size(), 4));
            BigDecimal tM = tA.multiply(BigDecimal.valueOf(38));
            BigDecimal tB = new BigDecimal(total);
            BigDecimal tX = tM.divide(tB, 15, RoundingMode.HALF_EVEN);
            BigDecimal pColorDiamante = tX.add(general);

            BigDecimal posibilidadColorTotal1 = pColorDiamante.add(pColorCorazon);
            BigDecimal posibilidadColorTotal2 = posibilidadColorTotal1.add(general2);
            BigDecimal posibilidadColorTotal3 = posibilidadColorTotal2.add(general2);

            System.out.println("POSIBILIDAD TOTAL COLOR: " + posibilidadColorTotal3.floatValue() * 100 + "%");
            pColor = posibilidadColorTotal3.floatValue() * 100;

        } else {

            BigDecimal tA = new BigDecimal(Combinaciones(13, 5));
            BigDecimal tM = tA.multiply(BigDecimal.valueOf(3));

            BigDecimal tF = new BigDecimal(Combinaciones(11, 3));
            BigDecimal tG = tF.multiply(BigDecimal.valueOf(39));
            BigDecimal tH = tG.multiply(BigDecimal.valueOf(38));

            BigDecimal tB = new BigDecimal(total);
            BigDecimal tX = tH.divide(tB, 15, RoundingMode.HALF_EVEN);

            BigDecimal tL = new BigDecimal(Combinaciones(39, 5));

            BigDecimal tK = tL.divide(tB, 15, RoundingMode.HALF_EVEN);

            BigDecimal tI = tK.add(tX);

            System.out.println("POSIBILIDAD TOTAL COLOR: " + tI.floatValue() * 100 + "%");
            pColor = tI.floatValue() * 100;

        }

    }

    public static void FULLHOUSE() {
        // TOTAL POSIBILIDADES
        BigInteger total = Combinaciones(50, 5);
        BigDecimal cB = new BigDecimal(total);

        // PRIMER CASO
        BigDecimal primerCasoA = new BigDecimal(Combinaciones(3, 2));
        BigDecimal primerCasoB = new BigDecimal(Combinaciones(3, 1));
        BigDecimal primerCasoC = new BigDecimal(Combinaciones(44, 2));
        BigDecimal primerCasoD = primerCasoA.multiply(primerCasoB);
        BigDecimal primerCasoE = primerCasoD.multiply(primerCasoC);

        BigDecimal PosibilidadPrimerCaso = primerCasoE.divide(cB, 15, RoundingMode.HALF_EVEN);

        // SEGUNDO CASO
        BigDecimal segundoCasoA = new BigDecimal(Combinaciones(3, 2));
        BigDecimal segundoCasoB = new BigDecimal(Combinaciones(3, 1));
        BigDecimal segundoCasoC = new BigDecimal(Combinaciones(44, 2));
        BigDecimal segundoCasoD = segundoCasoA.multiply(segundoCasoB);
        BigDecimal segundoCasoE = segundoCasoC.multiply(segundoCasoD);

        BigDecimal PosibilidadSegundoCaso = segundoCasoE.divide(cB, 15, RoundingMode.HALF_EVEN);

        // TERCER CASO
        BigDecimal tercerCasoA = new BigDecimal(Combinaciones(3, 2));
        BigDecimal tercerCasoB = new BigDecimal(Combinaciones(3, 2));
        BigDecimal tercerCasoC = new BigDecimal(Combinaciones(4, 2));
        BigDecimal tercerCasoD = tercerCasoA.multiply(tercerCasoB);
        BigDecimal tercerCasoE = tercerCasoC.multiply(tercerCasoD);

        BigDecimal PosibilidadTercerCaso = tercerCasoE.divide(cB, 15, RoundingMode.HALF_EVEN);

        // CUARTO CASO
        BigDecimal cuartoCasoA = new BigDecimal(Combinaciones(4, 3));
        BigDecimal cuartoCasoB = new BigDecimal(Combinaciones(4, 2));
        BigDecimal cuartoCasoC = cuartoCasoA.multiply(cuartoCasoB);
        BigDecimal cuartoCasoD = cuartoCasoC.multiply(new BigDecimal(11));

        BigDecimal PosibilidadCuartoCaso = cuartoCasoD.divide(cB, 15, RoundingMode.HALF_EVEN);

        // SUMA TOTAL DE CASOS
        BigDecimal total1 = PosibilidadPrimerCaso.add(PosibilidadSegundoCaso);
        BigDecimal total2 = total1.add(PosibilidadTercerCaso);
        BigDecimal total3 = total2.add(PosibilidadCuartoCaso);

        System.out.println("POSIBILIDAD TOTAL FULL HOUSE: " + total3.floatValue() * 100 + "%");
        pFull = total3.floatValue() * 100;
    }

    public static void POKER() {

        if ((cartaElegida1.getNumero() != cartaElegida2.getNumero())) {
            // TOTAL POSIBILIDADES
            BigInteger total = Combinaciones(50, 5);
            BigDecimal cB = new BigDecimal(total);

            // PRIMER CASO
            BigDecimal primerCasoA = new BigDecimal(Combinaciones(3, 3));
            BigDecimal primerCasoB = new BigDecimal(Combinaciones(48, 2));
            BigDecimal primerCasoC = primerCasoA.multiply(primerCasoB);
            BigDecimal PosibilidadPrimerCaso = primerCasoC.divide(cB, 15, RoundingMode.HALF_EVEN);

            // SEGUNDO CASO
            BigDecimal segundoCasoA = new BigDecimal(Combinaciones(3, 3));
            BigDecimal segundoCasoB = new BigDecimal(Combinaciones(48, 2));
            BigDecimal segundoCasoC = segundoCasoA.multiply(segundoCasoB);
            BigDecimal PosibilidadSegundoCaso = segundoCasoC.divide(cB, 15, RoundingMode.HALF_EVEN);

            // TERCER CASO
            BigDecimal tercerCasoA = new BigDecimal(Combinaciones(13, 1));
            BigDecimal tercerCasoB = new BigDecimal(Combinaciones(4, 1));
            BigDecimal tercerCasoC = new BigDecimal(Combinaciones(4, 1));
            BigDecimal tercerCasoD = new BigDecimal(Combinaciones(12, 1));
            BigDecimal tercerCasoE = tercerCasoA.multiply(tercerCasoB);
            BigDecimal tercerCasoF = tercerCasoE.multiply(tercerCasoC);
            BigDecimal tercerCasoG = tercerCasoF.multiply(tercerCasoD);
            BigDecimal PosibilidadTercerCaso = tercerCasoG.divide(cB, 15, RoundingMode.HALF_EVEN);

            // SUMA TOTAL DE CASOS
            BigDecimal total1 = PosibilidadPrimerCaso.add(PosibilidadSegundoCaso);
            BigDecimal total2 = total1.add(PosibilidadTercerCaso);

            System.out.println("POSIBILIDAD TOTAL POKER: " + total2.floatValue() * 100 + "%");
            pPoker = total2.floatValue() * 100;

        }else{
            
        }

    }

    private void initCartasElegidas() {
        botonProb = new JButton("Calcular");
        CartaElegida1 = new JPanel(null);
        CartaElegida2 = new JPanel(null);
        botonP = new JPanel(null);

        JPanel carta1 = new JPanel(new GridLayout(1, 1));
        carta1.setBorder(BorderFactory.createTitledBorder(blackline1));

        carta1.setBackground(Color.cyan);
        carta1.setBounds(335, 15, 100, 150);
        PCarta1 = new JLabel("?");
        NCarta1 = new JLabel("?");

        PCarta1.setForeground(Color.black);
        NCarta1.setForeground(Color.black);

        NCarta1.setFont(new Font("Helvetica", Font.BOLD, 40));
        NCarta1.setHorizontalAlignment(SwingConstants.CENTER);
        PCarta1.setFont(new Font("Helvetica", Font.BOLD, 40));
        PCarta1.setHorizontalAlignment(SwingConstants.CENTER);

        carta1.add(NCarta1);
        carta1.add(PCarta1);

        CartaElegida1.add(carta1);
        botonProb.setBounds(260, 15, 100, 150);
        botonProb.setEnabled(false);
        botonP.add(botonProb);

        JPanel carta2 = new JPanel(new GridLayout(1, 1));
        carta2.setBorder(BorderFactory.createTitledBorder(blackline1));

        carta2.setBackground(Color.cyan);
        carta2.setBounds(200, 15, 100, 150);
        PCarta2 = new JLabel("?");
        NCarta2 = new JLabel("?");

        PCarta2.setForeground(Color.black);
        NCarta2.setForeground(Color.black);

        PCarta2.setFont(new Font("Helvetica", Font.BOLD, 40));
        PCarta2.setHorizontalAlignment(SwingConstants.CENTER);

        NCarta2.setForeground(Color.black);
        NCarta2.setFont(new Font("Helvetica", Font.BOLD, 40));
        NCarta2.setHorizontalAlignment(SwingConstants.CENTER);

        carta2.add(NCarta2);
        carta2.add(PCarta2);
        CartaElegida2.add(carta2);

        panelCartasElegidas.add(CartaElegida1);
        panelCartasElegidas.add(botonP);
        panelCartasElegidas.add(CartaElegida2);

        carta1.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {

                cardP1.setVisible(true);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                carta1.setBorder(BorderFactory.createTitledBorder(blackline3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                carta1.setBorder(BorderFactory.createTitledBorder(blackline1));

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        carta2.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                cardP2.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                carta2.setBorder(BorderFactory.createTitledBorder(blackline3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                carta2.setBorder(BorderFactory.createTitledBorder(blackline1));

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        botonProb.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                calcularProbabilidades();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

    }

    private void intiCartasRandom() {
        Card1 = new JPanel(null);
        Card2 = new JPanel(null);
        Card3 = new JPanel(null);
        Card4 = new JPanel(null);
        Card5 = new JPanel(null);

        JPanel carta1 = new JPanel(new GridLayout(1, 1));
        carta1.setBorder(BorderFactory.createTitledBorder(sixColorBorder));

        carta1.setBackground(Color.cyan);
        carta1.setBounds(135, 15, 100, 150);
        nc1 = new JLabel("?");
        nc1.setFont(new Font("Helvetica", Font.BOLD, 40));
        nc1.setHorizontalAlignment(SwingConstants.CENTER);
        pc1 = new JLabel("?");
        pc1.setFont(new Font("Helvetica", Font.BOLD, 40));
        pc1.setHorizontalAlignment(SwingConstants.CENTER);
        carta1.add(nc1);
        carta1.add(pc1);

        Card1.add(carta1);

        JPanel carta2 = new JPanel(new GridLayout(1, 1));
        carta2.setBorder(BorderFactory.createTitledBorder(sixColorBorder));

        carta2.setBackground(Color.cyan);
        carta2.setBounds(135, 15, 100, 150);
        nc2 = new JLabel("?");
        nc2.setFont(new Font("Helvetica", Font.BOLD, 40));
        nc2.setHorizontalAlignment(SwingConstants.CENTER);
        pc2 = new JLabel("?");
        pc2.setFont(new Font("Helvetica", Font.BOLD, 40));
        pc2.setHorizontalAlignment(SwingConstants.CENTER);
        carta2.add(nc2);
        carta2.add(pc2);

        Card2.add(carta2);

        JPanel carta3 = new JPanel(new GridLayout(1, 1));
        carta3.setBorder(BorderFactory.createTitledBorder(sixColorBorder));

        carta3.setBackground(Color.cyan);
        carta3.setBounds(135, 15, 100, 150);
        nc3 = new JLabel("?");
        nc3.setFont(new Font("Helvetica", Font.BOLD, 40));
        nc3.setHorizontalAlignment(SwingConstants.CENTER);
        pc3 = new JLabel("?");
        pc3.setFont(new Font("Helvetica", Font.BOLD, 40));
        pc3.setHorizontalAlignment(SwingConstants.CENTER);
        carta3.add(nc3);
        carta3.add(pc3);
        Card3.add(carta3);

        JPanel carta4 = new JPanel(new GridLayout(1, 1));
        carta4.setBorder(BorderFactory.createTitledBorder(sixColorBorder));

        carta4.setBackground(Color.cyan);
        carta4.setBounds(135, 15, 100, 150);
        nc4 = new JLabel("?");
        nc4.setFont(new Font("Helvetica", Font.BOLD, 40));
        nc4.setHorizontalAlignment(SwingConstants.CENTER);
        pc4 = new JLabel("?");
        pc4.setFont(new Font("Helvetica", Font.BOLD, 40));
        pc4.setHorizontalAlignment(SwingConstants.CENTER);
        carta4.add(nc4);
        carta4.add(pc4);
        Card4.add(carta4);

        JPanel carta5 = new JPanel(new GridLayout(1, 1));
        carta5.setBorder(BorderFactory.createTitledBorder(sixColorBorder));

        carta5.setBackground(Color.cyan);
        carta5.setBounds(135, 15, 100, 150);
        nc5 = new JLabel("?");
        nc5.setFont(new Font("Helvetica", Font.BOLD, 40));
        nc5.setHorizontalAlignment(SwingConstants.CENTER);
        pc5 = new JLabel("?");
        pc5.setFont(new Font("Helvetica", Font.BOLD, 40));
        pc5.setHorizontalAlignment(SwingConstants.CENTER);
        carta5.add(nc5);
        carta5.add(pc5);
        Card5.add(carta5);

        panelCartasRandom.add(Card1);
        panelCartasRandom.add(Card2);
        panelCartasRandom.add(Card3);
        panelCartasRandom.add(Card4);
        panelCartasRandom.add(Card5);
    }

    public static void main(String args[]) {

        new CardUi().setVisible(true);
    }
}
