package Model;

public class Monom {
    private Double coeficient;
    private Integer putere;

    public Monom(Double coeficient, Integer putere) {
        this.coeficient = coeficient;
        this.putere = putere;
    }

    public Integer getPutere() {
        return putere;
    }

    public Double getCoeficient() {
        return coeficient;
    }

    public String toString(int nr) {
        if (coeficient != 0)//daca exista numarul
        {
            if (nr == 0)//2 cazuri: nr = 0 atunci numarul are coeficient int
                return coeficient.intValue() + "x^" + putere; //facem conversie
            else//nr = 1 atunci numarul are coeficient double
                return coeficient + "x^" + putere;
        }
        return ""; //daca nu s-a returnat nimic, returnam sirul vid
    }
}
