import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

import model.Carta;

public class App {

    public enum PALO {
        PICA, TREBOL, DIAMANTE, CORAZON
    }

    public enum NUMERO {
        AS, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ, J, Q, K
    }

    private static ArrayList<Carta> cartas = new ArrayList<>();
    private static ArrayList<Carta> cartasPica = new ArrayList<>();
    private static ArrayList<Carta> cartasTrebol = new ArrayList<>();
    private static ArrayList<Carta> cartasDiamante = new ArrayList<>();
    private static ArrayList<Carta> cartasCorazon = new ArrayList<>();

    private static Carta cartaElegida1;
    private static Carta cartaElegida2;

    public static void generarCartas() {
        for (PALO palo : PALO.values()) {
            for (NUMERO numero : NUMERO.values()) {

                if (cartaElegida1.getPalo().equals(palo.toString())
                        && cartaElegida1.getNumero().toString().equals(numero.toString())) {
                } else if (cartaElegida2.getPalo().toString().equals(palo.toString())
                        && cartaElegida2.getNumero().toString().equals(numero.toString())) {
                } else {
                    if (palo.equals(PALO.CORAZON)) {
                        Carta carta = new Carta(palo.toString(), numero.toString());
                        cartas.add(carta);
                        cartasCorazon.add(carta);
                    } else if (palo.equals(PALO.DIAMANTE)) {
                        Carta carta = new Carta(palo.toString(), numero.toString());
                        cartas.add(carta);
                        cartasDiamante.add(carta);
                    } else if (palo.equals(PALO.PICA)) {
                        Carta carta = new Carta(palo.toString(), numero.toString());
                        cartas.add(carta);
                        cartasPica.add(carta);
                    } else if (palo.equals(PALO.TREBOL)) {
                        Carta carta = new Carta(palo.toString(), numero.toString());
                        cartas.add(carta);
                        cartasTrebol.add(carta);
                    }

                }

            }
        }
        // for (Carta c : cartas) {
        // System.out.println(c);
        // }
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

        if (cartaElegida1.getPalo().equals("CORAZON") && cartaElegida2.getPalo().equals("TREBOL")
                || cartaElegida1.getPalo().equals("TREBOL") && cartaElegida2.getPalo().equals("CORAZON")) {
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

            //////////////////////////////////
        } else if (cartaElegida1.getPalo().equals("CORAZON") && cartaElegida2.getPalo().equals("PICA")
                || cartaElegida1.getPalo().equals("PICA") && cartaElegida2.getPalo().equals("CORAZON")) {
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
        } else if (cartaElegida1.getPalo().equals("CORAZON") && cartaElegida2.getPalo().equals("DIAMANTE")
                || cartaElegida1.getPalo().equals("DIAMANTE") && cartaElegida2.getPalo().equals("CORAZON")) {
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
        }

        BigDecimal cA = new BigDecimal(Combinaciones(11, 3));
        BigDecimal cM = cA.multiply(BigDecimal.valueOf(76));
        BigDecimal cB = new BigDecimal(total);
        BigDecimal cX = cM.divide(cB, 15, RoundingMode.HALF_EVEN);
        BigDecimal pColorCorazon = cX.add(general);
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
    }

    public static void POKER() {
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

    }

    public static void main(String[] args) throws Exception {

        cartaElegida1 = new Carta(PALO.CORAZON.toString(), NUMERO.CINCO.toString());
        cartaElegida2 = new Carta(PALO.DIAMANTE.toString(), NUMERO.TRES.toString());

        generarCartas();

        cartas.remove(cartaElegida1);
        cartas.remove(cartaElegida2);

        COLOR();
        FULLHOUSE();
        POKER();

    }

}
