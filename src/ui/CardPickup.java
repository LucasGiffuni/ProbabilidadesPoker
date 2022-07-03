package ui;

import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.beans.Visibility;
import java.util.Hashtable;

import javax.print.DocFlavor.STRING;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.awt.*;

public class CardPickup extends JFrame {
    public JPanel panel, panelinferior, panelNumero, panelPalo, panelInfo;

    Border blackline1 = BorderFactory.createLineBorder(Color.black);
    Border blackline3 = BorderFactory.createLineBorder(new Color(252, 204, 51));

    JPanel aux = new JPanel();
    public JLabel paloElegido, numeroElegido;

    public JButton botonSalir;
    private String Palo;
    private String Numero;

    public CardPickup() {
        setTitle("Configuracion");
        setAlwaysOnTop(true);
        setUndecorated(true);
        setSize(750, 450);
        setResizable(false);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(false);
        panel = new JPanel();
        panel.setBackground(Color.pink);
        panel.setSize(this.getSize());
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panelinferior = new JPanel(new GridLayout(1, 2));
        panelinferior.setSize(this.getSize());

        panelinferior.setBackground(Color.pink);
        panel.setLayout(new GridLayout(1, 2));

        add(panel);
        add(panelinferior);

        setup();

    }

    public void setup() {
        panelNumero = new JPanel(new GridLayout(5, 3));
        panelNumero.setSize(20, this.getWidth());

        panelPalo = new JPanel(new GridLayout(4, 1));
        panelPalo.setSize(this.getSize());

        initNumbers();
        initPalos();

        this.panel.add(panelNumero);
        this.panel.add(panelPalo);

        botonSalir = new JButton();
        botonSalir.setText("Salir");
        botonSalir.setSize(100, 50);

        JPanel panelInfo2 = new JPanel(new GridLayout(2, 1));
        panelPalo.setSize(this.getSize());
        paloElegido = new JLabel();
        numeroElegido = new JLabel();
        paloElegido.setFont(new Font("Helvetica", Font.BOLD, 20));
        numeroElegido.setFont(new Font("Helvetica", Font.BOLD, 20));

        paloElegido.setText("Palo Elegido: ");
        numeroElegido.setText("Numero Elegido: ");
        panelInfo2.add(paloElegido);
        panelInfo2.add(numeroElegido);

        panelinferior.add(panelInfo2);
        panelinferior.add(botonSalir);
        botonSalir.setEnabled(false);

        botonSalir.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);

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

    private void initPalos() {

        JPanel Trebol = new JPanel(new GridLayout(1, 1));
        Trebol.setBounds(30, 0, 30, 50);
        Trebol.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nTrebol = new JLabel("♣");
        nTrebol.setFont(new Font("Helvetica", Font.BOLD, 40));
        nTrebol.setForeground(Color.black);

        nTrebol.setHorizontalAlignment(SwingConstants.CENTER);
        Trebol.add(nTrebol);

        JPanel Diamante = new JPanel(new GridLayout(1, 1));
        Diamante.setBounds(30, 0, 30, 50);
        Diamante.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nDiamante = new JLabel("♦");
        nDiamante.setFont(new Font("Helvetica", Font.BOLD, 40));
        nDiamante.setForeground(Color.red);

        nDiamante.setHorizontalAlignment(SwingConstants.CENTER);
        Diamante.add(nDiamante);

        JPanel Corazon = new JPanel(new GridLayout(1, 1));
        Corazon.setBounds(30, 0, 30, 50);
        Corazon.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nCorazon = new JLabel("♥");
        nCorazon.setFont(new Font("Helvetica", Font.BOLD, 40));
        nCorazon.setForeground(Color.red);

        nCorazon.setHorizontalAlignment(SwingConstants.CENTER);
        Corazon.add(nCorazon);

        JPanel Pica = new JPanel(new GridLayout(1, 1));
        Pica.setBounds(30, 0, 30, 50);
        Pica.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nPica = new JLabel("♠");
        nPica.setFont(new Font("Helvetica", Font.BOLD, 40));
        nPica.setForeground(Color.black);

        nPica.setHorizontalAlignment(SwingConstants.CENTER);
        Pica.add(nPica);

        panelPalo.add(Trebol);
        panelPalo.add(Diamante);
        panelPalo.add(Corazon);
        panelPalo.add(Pica);

        Trebol.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Trebol.setBorder(BorderFactory.createTitledBorder(blackline3));
                setPalo("♣");
                paloElegido.setText("Palo Elegido: ♣");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Trebol.setBorder(BorderFactory.createTitledBorder(blackline3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                Trebol.setBorder(BorderFactory.createTitledBorder(blackline1));

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
        Diamante.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Diamante.setBorder(BorderFactory.createTitledBorder(blackline3));
                setPalo("♦");
                paloElegido.setText("Palo Elegido: ♦");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Diamante.setBorder(BorderFactory.createTitledBorder(blackline3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                Diamante.setBorder(BorderFactory.createTitledBorder(blackline1));

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
        Corazon.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Corazon.setBorder(BorderFactory.createTitledBorder(blackline3));
                setPalo("♥");
                paloElegido.setText("Palo Elegido: ♥");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Corazon.setBorder(BorderFactory.createTitledBorder(blackline3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                Corazon.setBorder(BorderFactory.createTitledBorder(blackline1));

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
        Pica.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Pica.setBorder(BorderFactory.createTitledBorder(blackline3));
                setPalo("♠");
                paloElegido.setText("Palo Elegido: ♠");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Pica.setBorder(BorderFactory.createTitledBorder(blackline3));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                Pica.setBorder(BorderFactory.createTitledBorder(blackline1));

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

    private void initNumbers() {
        JPanel uno = new JPanel(new GridLayout(1, 1));
        uno.setBounds(30, 0, 30, 50);
        uno.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nUno = new JLabel("As");
        nUno.setHorizontalAlignment(SwingConstants.CENTER);
        uno.add(nUno);

        JPanel dos = new JPanel(new GridLayout(1, 1));
        dos.setBounds(30, 0, 30, 50);
        dos.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel ndos = new JLabel("Dos");
        ndos.setHorizontalAlignment(SwingConstants.CENTER);
        dos.add(ndos);

        JPanel tres = new JPanel(new GridLayout(1, 1));
        tres.setBounds(30, 0, 30, 50);
        tres.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel ntres = new JLabel("Tres");
        ntres.setHorizontalAlignment(SwingConstants.CENTER);
        tres.add(ntres);

        JPanel cuatro = new JPanel(new GridLayout(1, 1));
        cuatro.setBounds(30, 0, 30, 50);
        cuatro.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel ncuatro = new JLabel("Cuatro");
        ncuatro.setHorizontalAlignment(SwingConstants.CENTER);
        cuatro.add(ncuatro);

        JPanel cinco = new JPanel(new GridLayout(1, 1));
        cinco.setBounds(30, 0, 30, 50);
        cinco.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel ncinco = new JLabel("Cinco");
        ncinco.setHorizontalAlignment(SwingConstants.CENTER);
        cinco.add(ncinco);

        JPanel seis = new JPanel(new GridLayout(1, 1));
        seis.setBounds(30, 0, 30, 50);
        seis.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nseis = new JLabel("Seis");
        nseis.setHorizontalAlignment(SwingConstants.CENTER);
        seis.add(nseis);

        JPanel siete = new JPanel(new GridLayout(1, 1));
        siete.setBounds(30, 0, 30, 50);
        siete.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nsiete = new JLabel("Siete");
        nsiete.setHorizontalAlignment(SwingConstants.CENTER);
        siete.add(nsiete);

        JPanel Ocho = new JPanel(new GridLayout(1, 1));
        Ocho.setBounds(30, 0, 30, 50);
        Ocho.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nOcho = new JLabel("Ocho");
        nOcho.setHorizontalAlignment(SwingConstants.CENTER);
        Ocho.add(nOcho);

        JPanel Nueve = new JPanel(new GridLayout(1, 1));
        Nueve.setBounds(30, 0, 30, 50);
        Nueve.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nNueve = new JLabel("Nueve");
        nNueve.setHorizontalAlignment(SwingConstants.CENTER);
        Nueve.add(nNueve);

        JPanel Diez = new JPanel(new GridLayout(1, 1));
        Diez.setBounds(30, 0, 30, 50);
        Diez.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nDiez = new JLabel("Diez");
        nDiez.setHorizontalAlignment(SwingConstants.CENTER);
        Diez.add(nDiez);

        JPanel Jota = new JPanel(new GridLayout(1, 1));
        Jota.setBounds(30, 0, 30, 50);
        Jota.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nJota = new JLabel("J");
        nJota.setHorizontalAlignment(SwingConstants.CENTER);
        Jota.add(nJota);

        JPanel Q = new JPanel(new GridLayout(1, 1));
        Q.setBounds(30, 0, 30, 50);
        Q.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nQ = new JLabel("Q");
        nQ.setHorizontalAlignment(SwingConstants.CENTER);
        Q.add(nQ);

        JPanel K = new JPanel(new GridLayout(1, 1));
        K.setBounds(30, 0, 30, 50);
        K.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nK = new JLabel("K");
        nK.setHorizontalAlignment(SwingConstants.CENTER);
        K.add(nK);

        JPanel nose = new JPanel(new GridLayout(1, 1));
        nose.setBounds(30, 0, 30, 50);
        nose.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nnose = new JLabel("");
        nnose.setHorizontalAlignment(SwingConstants.CENTER);
        nose.add(nnose);

        JPanel nose2 = new JPanel(new GridLayout(1, 1));
        nose2.setBounds(30, 0, 30, 50);
        nose2.setBorder(BorderFactory.createTitledBorder(blackline1));
        JLabel nnose2 = new JLabel("");
        nnose2.setHorizontalAlignment(SwingConstants.CENTER);
        nose2.add(nnose2);

        panelNumero.add(uno);
        panelNumero.add(dos);
        panelNumero.add(tres);
        panelNumero.add(cuatro);
        panelNumero.add(cinco);
        panelNumero.add(seis);
        panelNumero.add(siete);
        panelNumero.add(Ocho);
        panelNumero.add(Nueve);
        panelNumero.add(Diez);
        panelNumero.add(Jota);
        panelNumero.add(Q);
        panelNumero.add(K);
        panelNumero.add(nose);
        panelNumero.add(nose2);

        uno.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("A");
                numeroElegido.setText("Numero Elegido: As");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                uno.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                uno.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        dos.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("2");
                numeroElegido.setText("Numero Elegido: Dos");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dos.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dos.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        tres.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("3");
                numeroElegido.setText("Numero Elegido: Tres");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                tres.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tres.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        cuatro.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("4");
                numeroElegido.setText("Numero Elegido: Cuatro");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                cuatro.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cuatro.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        cinco.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("5");
                numeroElegido.setText("Numero Elegido: Cinco");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                cinco.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cinco.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        seis.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("6");
                numeroElegido.setText("Numero Elegido: Seis");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                seis.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                seis.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        siete.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("7");
                numeroElegido.setText("Numero Elegido: Siete");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                siete.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                siete.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        Ocho.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("8");
                numeroElegido.setText("Numero Elegido: Ocho");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Ocho.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Ocho.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        Nueve.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("9");
                numeroElegido.setText("Numero Elegido: Nueve");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Nueve.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Nueve.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        Diez.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("10");
                numeroElegido.setText("Numero Elegido: Diez");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Diez.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Diez.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        Jota.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("J");
                numeroElegido.setText("Numero Elegido: J");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Jota.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Jota.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        Q.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("Q");
                numeroElegido.setText("Numero Elegido: Q");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Q.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Q.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        K.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setNumero("K");
                numeroElegido.setText("Numero Elegido: K");
                if (!(getPalo() == null) && !(getNumero()== null)) {
                    botonSalir.setEnabled(true);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                K.setBorder(BorderFactory.createTitledBorder(blackline3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                K.setBorder(BorderFactory.createTitledBorder(blackline1));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

    }

    public void setPalo(String palo) {
        Palo = palo;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getPalo() {
        return Palo;
    }

    public String getNumero() {
        return Numero;
    }

    public static void main(String args[]) {

        new CardPickup().setVisible(true);
    }

}
