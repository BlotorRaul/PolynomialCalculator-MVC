package Model;

import Helper.ReverseComparator;

import java.util.Map;
import java.util.TreeMap;

public class OperationsModelImpl implements OperationsModel{
    public Polinom adunare(Polinom P, Polinom Q)//P + Q
    {
        ReverseComparator reverseComparator = new ReverseComparator();
        Map<Integer, Monom> rezultat = new TreeMap<>(reverseComparator);
        rezultat.putAll(P.getMonoame());
        //parcurg celalalt polinom
        for (Map.Entry<Integer, Monom> elem : Q.getMonoame().entrySet()) {
            Integer putere = elem.getValue().getPutere();
            Double coeficient = elem.getValue().getCoeficient();
            if (rezultat.containsKey(putere)) {
                coeficient = coeficient + rezultat.get(putere).getCoeficient();
            }
            Monom monom = new Monom(coeficient, putere);
            rezultat.put(putere, monom);
        }


        return new Polinom(rezultat);
    }

    public Polinom scadere(Polinom P, Polinom Q) //P-Q (ordinea aceasta)
    {
        ReverseComparator reverseComparator = new ReverseComparator();
        Map<Integer, Monom> rezultat = new TreeMap<>(reverseComparator);
        rezultat.putAll(P.getMonoame());
        //parcurg celalalt polinom
        for (Map.Entry<Integer, Monom> elem : Q.getMonoame().entrySet()) {
            Integer putere = elem.getValue().getPutere();
            Double coeficient = elem.getValue().getCoeficient();
            if (rezultat.containsKey(putere)) {
                coeficient = rezultat.get(putere).getCoeficient() - coeficient;
            }
            Monom monom = new Monom(coeficient, putere);
            rezultat.put(putere, monom);
        }


        return new Polinom(rezultat);
    }


    public Polinom inmultire(Polinom P, Polinom Q) {
        ReverseComparator reverseComparator = new ReverseComparator();
        Map<Integer, Monom> rezultat = new TreeMap<>(reverseComparator);

        for (Map.Entry<Integer, Monom> i : P.getMonoame().entrySet()) {
            for (Map.Entry<Integer, Monom> j : Q.getMonoame().entrySet()) {
                Integer putere = i.getValue().getPutere() + j.getValue().getPutere();
                Double coef = i.getValue().getCoeficient() * j.getValue().getCoeficient();

                // Dacă puterea există deja în rezultat, adăugăm la coeficientul existent
                if (rezultat.containsKey(putere))
                    coef = coef + rezultat.get(putere).getCoeficient();
                Monom monom = new Monom(coef, putere);
                rezultat.put(putere, monom);
            }
        }

        return new Polinom(rezultat);
    }

    public Polinom derivare(Polinom P) {
        ReverseComparator reverseComparator = new ReverseComparator();
        Map<Integer, Monom> rezultat = new TreeMap<>(reverseComparator);

        for (Map.Entry<Integer, Monom> i : P.getMonoame().entrySet()) {
            if (i.getKey() != 0) {
                Integer putere = i.getValue().getPutere() - 1;
                Double coef = i.getValue().getCoeficient() * i.getValue().getPutere();

                Monom monom = new Monom(coef, putere);
                rezultat.put(putere, monom);
            }

        }

        return new Polinom(rezultat);
    }


    public Polinom integrare(Polinom P) {
        ReverseComparator reverseComparator = new ReverseComparator();
        Map<Integer, Monom> rezultat = new TreeMap<>(reverseComparator);

        for (Map.Entry<Integer, Monom> i : P.getMonoame().entrySet()) {
            Integer putere = i.getValue().getPutere() + 1;
            Double coef = i.getValue().getCoeficient() / (i.getValue().getPutere() + 1);
            String coefFormatat = String.format("%.2f", coef);
            coef = Double.parseDouble(coefFormatat);

            Monom monom = new Monom(coef, putere);
            rezultat.put(putere, monom);


        }

        return new Polinom(rezultat);
    }

    /*
    public Polinom[] impartire(Polinom P,Polinom Q) throws ArithmeticException // P / Q
    {
        ReverseComparator reverseComparator = new ReverseComparator();
        Map<Integer,Monom> cat = new TreeMap<>(reverseComparator);
        Map<Integer,Monom> rest = new TreeMap<>(reverseComparator);
        Map.Entry<Integer,Monom> maxMonomP = cautMaxim(P);
        Map.Entry<Integer,Monom> maxMonomQ = cautMaxim(Q);

        if(Q.getMonoame().isEmpty()) //daca Q este 0
        {
            throw new ArithmeticException("Nu este permis impartirea cu 0!");
        }
        else
            if(P.getMonoame().isEmpty()) //daca P este 0
            {
                cat.put(0,new Monom(0.,0));
                rest.put(0,new Monom(0.,0));
            }
            else
                if(maxMonomP.getValue().getPutere() < maxMonomQ.getValue().getPutere())// grad P mai mic ca Q
                {
                    cat.put(0,new Monom(0.,0));
                    rest.put(maxMonomP.getKey(),maxMonomP.getValue());
                }
                else //fac P/Q propriu zis
                {


                }


    }
    private Map.Entry<Integer,Monom> impartireHelper(Map.Entry<Integer,Monom> p, Map.Entry<Integer,Monom> q)
    {
        Integer putere=p.getValue().getPutere()-q.getValue().getPutere();
        Double coef=p.getValue().getCoeficient()/q.getValue().getCoeficient();
        return new Map<Integer,Monom>(putere, new Monom(coef,putere));
    }
    */
    public Map.Entry<Integer, Monom> cautMaxim(Polinom monoame) {
        for (Map.Entry<Integer, Monom> elem : monoame.getMonoame().entrySet()) {
            if (elem.getValue().getCoeficient() != 0)
                return elem;
        }
        return null;
        //Map.Entry<Integer, Monom> maximulEntry = treeMap.firstEntry(); // Obține prima intrare (maximul)
        //        return maximulEntry;
    }


}
