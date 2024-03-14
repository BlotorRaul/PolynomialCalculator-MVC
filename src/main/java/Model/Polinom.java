package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Polinom {
    private Map<Integer, Monom> monoame;

    public Polinom(Map<Integer, Monom> monoame) {
        this.monoame = monoame;
    }

    public Map<Integer, Monom> getMonoame() {
        return monoame;
    }

    @Override
    public String toString() {
        StringBuilder sir = new StringBuilder();
        boolean nenulElem = false;
        boolean primul = false;

        for (Monom elem : monoame.values()) //parcurg value din map(monoamele)
        {
            if (elem.getCoeficient() != 0) {
                nenulElem = true;
                if (!primul || elem.getCoeficient() < 0) { //daca e primul elem sau elem negative
                    if (elem.getCoeficient() == elem.getCoeficient().intValue())
                        sir.append(elem.toString(0)); //coeficientul este integer
                    else
                        sir.append(elem.toString(1)); //coeficientul este double
                    primul = true;
                } else {//daca e elem pozitiv atunci adaug un + intre elemente
                    if (elem.getCoeficient() == elem.getCoeficient().intValue())
                        sir.append("+").append(elem.toString(0));
                    else
                        sir.append("+").append(elem.toString(1));
                }
            }
        }

        if (nenulElem == false)
            return "0";

        return sir.toString();

    }
}
