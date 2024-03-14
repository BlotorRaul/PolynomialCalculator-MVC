package Controller;

import Model.OperationsModel;
import Model.OperationsModelImpl;
import Model.Polinom;
import Helper.Regex;
import View.GuiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerImpl {

    private GuiView guiView;
    private OperationsModel op;
    private Regex regex;

    public ControllerImpl(GuiView view, OperationsModelImpl operationsModelImpl) {
        this.guiView = view;
        this.op = operationsModelImpl;
        this.regex = new Regex();

        addActionListenerAddButton();
        addActionListenerRefreshButton();
        addActionListenerScadereButton();
        addActionListenerMulButton();
        addActionListenerDivButton();
        addActionListenerDerivareButton();
        addActionListenerIntegrareButton();
    }

    private void addActionListenerIntegrareButton() {
        guiView.getBtnIntegrare().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom rezultat = op.integrare(citestePol1());
                guiView.getTfRezultat().setText(rezultat.toString() + " +C");

            }
        });
    }

    private void addActionListenerDerivareButton() {
        guiView.getBtnDerivare().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom rezultat = op.derivare(citestePol1());
                guiView.getTfRezultat().setText(rezultat.toString());

            }
        });
    }

    private void addActionListenerDivButton() {
    }

    private void addActionListenerMulButton() {
        guiView.getBtnMul().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom rezultat = op.inmultire(citestePol1(), citestePol2());
                guiView.getTfRezultat().setText(rezultat.toString());

            }
        });
    }

    private void addActionListenerScadereButton() {
        guiView.getBtnScadere().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom rezultat = op.scadere(citestePol1(), citestePol2());
                guiView.getTfRezultat().setText(rezultat.toString());

            }
        });
    }

    private Polinom citestePol1() {
        return regex.stringToPolinom(guiView.getTfPrimuNumar().getText());
    }

    private Polinom citestePol2() {
        return regex.stringToPolinom(guiView.getTfDoileaPolinom().getText());
    }

    private void addActionListenerRefreshButton() {
        guiView.getBtnRefresh().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiView.getTfPrimuNumar().setText("");
                guiView.getTfDoileaPolinom().setText("");
                guiView.getTfRezultat().setText("");
            }
        });
    }

    private void addActionListenerAddButton() {
        guiView.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom rezultat = op.adunare(citestePol1(), citestePol2());
                guiView.getTfRezultat().setText(rezultat.toString());

            }
        });
    }


}
