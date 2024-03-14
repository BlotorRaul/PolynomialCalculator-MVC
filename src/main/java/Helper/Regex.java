package Helper;

import Model.Monom;
import Model.Polinom;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clasa aceasta ia un string il diseaca in (putere,coeficient)Monoid
 * Dupa care il converteste intr-un treeMap<rank, Monoid>
 * Cu o metoda ajutatoare leaga aceste functii intre ele si returneaza un Polinom
 */
public class Regex {

    public Map<Integer, Double> parse(String string) {
        Pattern pattern = Pattern.compile("(-?\\b\\d+(?:\\.\\d+)?)(?:[xX]\\^(-?\\d+\\b)(?![&%$@~`!#]))?");
        Matcher matcher = pattern.matcher(string);
        Map<Integer, Double> perechi = new HashMap<>();

        while (matcher.find()) {
            String coeficient = matcher.group(1);//iau prima bucata( ce e inainte de X)
            String putere = matcher.group(2);//iau a doua bucata( ce e dupa X)

            Integer putereConv;
            Double coeficientConv;
            //verific ca nu cumva coeficient sa fie null->il transform in 1
            try {
                coeficientConv = Double.parseDouble(coeficient);

            } catch (NumberFormatException e) {
                coeficientConv = 1.0;

            }
            //verific ca nu cumva putere sa fie null->il transform in 0
            try {
                putereConv = Integer.parseInt(putere);

            } catch (NumberFormatException e) {
                putereConv = 0;
            }
            perechi.put(putereConv, coeficientConv);

        }
        return perechi;

    }


    public Map<Integer, Monom> conversieToTreeHash(Map<Integer, Double> perechi) {
        ReverseComparator reverseComparator = new ReverseComparator();
        Map<Integer, Monom> monomMap = new TreeMap<>(reverseComparator);
        for (Map.Entry<Integer, Double> pair : perechi.entrySet()) {
            int rank = pair.getKey();
            Monom monom = new Monom(pair.getValue(), pair.getKey());
            monomMap.put(rank, monom);
        }
        return monomMap;
    }

    public Polinom stringToPolinom(String string) {
        Regex regex = new Regex();
        Map<Integer, Double> perechi = regex.parse(string);
        Map<Integer, Monom> conversie = regex.conversieToTreeHash(perechi);

        return new Polinom(conversie);
    }


}
