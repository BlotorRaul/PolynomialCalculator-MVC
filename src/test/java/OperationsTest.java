import Helper.Regex;
import Helper.ReverseComparator;
import Model.*;
import org.junit.*;

import java.util.Map;
import java.util.TreeMap;

public class OperationsTest {
    private static Regex regex;
    private static OperationsModelImpl op;

    private static int numberOfTests = 0;

    private static int numberOfTestsPassed = 0;
    static String s1;
    static String s2;
    static String s3;
    static String s4;
    static String s5;

    static Polinom p1;
    static Polinom p2;
    static Polinom p3;
    static Polinom p4;
    static Polinom p5;

    @BeforeClass
    public static void setup() {
        System.out.println("Initializare calculator");
        regex = new Regex();
        op = new OperationsModelImpl();
        s1 = "-5X^5-3X^2+4X^1-3x^18";
        s2 = "1X^1";
        s3 = "3X^2-1X^1+1";
        s4 = "1X^1-2";
        s5 = "1x^3+4x^2+5";
        p1 = regex.stringToPolinom(s1);
        p2 = regex.stringToPolinom(s2);
        p3 = regex.stringToPolinom(s3);
        p4 = regex.stringToPolinom(s4);
        p5 = regex.stringToPolinom(s5);
    }

    @AfterClass
    public static void finalMethod() {
        regex = null;
        System.out.println(numberOfTests + " has been executed and " + numberOfTestsPassed + " has passed");
    }

    // Call before each test
    @Before
    public void increment() {
        numberOfTests++;
    }

    @After
    public void afterEachTest() {
        System.out.println("Test Finished");
    }


    @Test
    public void testParse() {
        Map<Integer, Double> perechi = regex.parse(s1);
        double delta = 0.0001;
        //verificam toate perechile sunt corecte
        Assert.assertEquals(-5.0, perechi.get(5), delta);
        Assert.assertEquals(-3.0, perechi.get(2), delta);
        Assert.assertEquals(4.0, perechi.get(1), delta);
        Assert.assertEquals(-3.0, perechi.get(18), delta);
        numberOfTestsPassed++;

    }

    @Test
    public void testConversieToTreeHash() {
        Map<Integer, Double> testMap = Map.of(5, -5.0, 2, -3.0, 1, 4.0, 18, -3.0);
        Map<Integer, Monom> conversie = regex.conversieToTreeHash(testMap);
        double delta = 0.0001;

        Assert.assertEquals(4, conversie.size());

        Assert.assertTrue(conversie.containsKey(5));
        Assert.assertTrue(conversie.containsKey(2));
        Assert.assertTrue(conversie.containsKey(1));
        Assert.assertTrue(conversie.containsKey(18));

        Assert.assertEquals(-5.0, conversie.get(5).getCoeficient(), delta);
        Assert.assertEquals(-3.0, conversie.get(2).getCoeficient(), delta);
        Assert.assertEquals(4.0, conversie.get(1).getCoeficient(), delta);
        Assert.assertEquals(-3.0, conversie.get(18).getCoeficient(), delta);
        numberOfTestsPassed++;

    }

    @Test
    public void testStringToPolinom1() {
        Polinom polinom = regex.stringToPolinom(s1);
        Assert.assertNotNull(polinom);
        Assert.assertEquals(4, polinom.getMonoame().size());

        numberOfTestsPassed++;
    }

    @Test
    public void testStringToPolinom2() {
        Polinom polinom = regex.stringToPolinom(s2);
        Assert.assertNotNull(polinom);
        Assert.assertEquals(1, polinom.getMonoame().size());

        numberOfTestsPassed++;
    }

    @Test
    public void testToStringPolinom() {
        ReverseComparator reverseComparator = new ReverseComparator();
        Map<Integer, Monom> monoame = new TreeMap<>(reverseComparator);
        double delta = 0.0001;
        monoame.put(5, new Monom(-5.0, 5));
        monoame.put(2, new Monom(-3.0, 2));
        monoame.put(1, new Monom(4.0, 1));
        monoame.put(18, new Monom(-3.0, 18));

        Polinom polinom = new Polinom(monoame);

        // Verifică rezultatul metodei toString pentru polinomul specific
        Assert.assertEquals("-3x^18-5x^5-3x^2+4x^1", polinom.toString());

        numberOfTestsPassed++;
    }

    @Test
    public void testInmultire1() {

        Polinom inmultire = op.inmultire(p1, p2);
        Assert.assertEquals("-3x^19-5x^6-3x^3+4x^2", inmultire.toString());

        numberOfTestsPassed++;
    }

    @Test
    public void testInmultire2() {

        Polinom inmultire = op.inmultire(p3, p4);
        Assert.assertEquals("3x^3-7x^2+3x^1-2x^0", inmultire.toString());

        numberOfTestsPassed++;
    }

    @Test
    public void testAdunare() {
        Polinom adunare = op.adunare(p3, p4);
        Assert.assertEquals("3x^2-1x^0", adunare.toString());
        numberOfTestsPassed++;
    }

    @Test
    public void testScadere1() {
        Polinom scadere = op.scadere(p3, p4);
        Assert.assertEquals("3x^2-2x^1+3x^0", scadere.toString());
        numberOfTestsPassed++;
    }

    @Test
    public void testScadere2() {
        Polinom scadere = op.scadere(p1, p3);
        Assert.assertEquals("-3x^18-5x^5-6x^2+5x^1+1x^0", scadere.toString());
        numberOfTestsPassed++;
    }

    @Test
    public void testDerivare1() {
        Polinom derivare = op.derivare(p1);
        Assert.assertEquals("-54x^17-25x^4-6x^1+4x^0", derivare.toString());
        numberOfTestsPassed++;
    }

    @Test
    public void testDerivare2() {
        Polinom derivare = op.derivare(p2);
        Assert.assertEquals("1x^0", derivare.toString());
        numberOfTestsPassed++;
    }

    @Test
    public void testIntegrare() {
        Polinom integrare = op.integrare(p5);
        Assert.assertEquals("0.25x^4+1.33x^3+5x^1", integrare.toString());
        numberOfTestsPassed++;
    }

    @Test
    public void testCautMaxim() {
        Map.Entry<Integer, Monom> integrare = op.cautMaxim(p1);
        Assert.assertEquals("18", integrare.getValue().getPutere().toString());
        numberOfTestsPassed++;
    }

    @Test
    public void testCautMaxim2() {
        Map.Entry<Integer, Monom> integrare = op.cautMaxim(p2);
        Assert.assertEquals("1", integrare.getValue().getPutere().toString());
        numberOfTestsPassed++;
    }

    @Test
    public void testCautMaxim3() {
        Map.Entry<Integer, Monom> integrare = op.cautMaxim(p5);
        Assert.assertEquals("3", integrare.getValue().getPutere().toString());
        numberOfTestsPassed++;
    }
}

