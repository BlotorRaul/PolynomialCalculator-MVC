package Model;

import java.util.Map;

public interface OperationsModel {
    public Polinom adunare(Polinom P, Polinom Q);
    public Polinom scadere(Polinom P, Polinom Q);
    public Polinom inmultire(Polinom P, Polinom Q);
    public Polinom derivare(Polinom P);
    public Polinom integrare(Polinom P);
   // public Polinom[] impartire(Polinom P,Polinom Q) throws ArithmeticException;

}
