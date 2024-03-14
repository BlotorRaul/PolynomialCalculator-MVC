package View;

import javax.swing.*;
import java.awt.*;

public class GuiView extends JFrame {
    private JPanel mainPanel;
    private JPanel operatiiPanel;
    private JPanel afisarePanel;
    private JButton btnAdd;
    private JButton btnScadere;
    private JButton btnMul;
    private JButton btnDiv;
    private JButton btnDerivare;
    private JButton btnIntegrare;
    private JTextField tfPrimuNumar;
    private JTextField tfDoileaPolinom;
    private JLabel lbPrimuPolinom;
    private JLabel lbDoileaPolinom;
    private JButton btnRefresh;
    private JPanel main;
    private JTextField tfRezultat;
    private JLabel lbRezultat;

    public GuiView(String title) {
        super(title);

        this.prepareGui();


    }

    private void prepareGui() {
        setMinimumSize(new Dimension(500, 250));
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        setContentPane(main);
        setVisible(true);

    }


    public JTextField getTfPrimuNumar() {
        return tfPrimuNumar;
    }

    public JTextField getTfDoileaPolinom() {
        return tfDoileaPolinom;
    }

    public JTextField getTfRezultat() {
        return tfRezultat;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnScadere() {
        return btnScadere;
    }

    public JButton getBtnMul() {
        return btnMul;
    }

    public JButton getBtnDiv() {
        return btnDiv;
    }

    public JButton getBtnDerivare() {
        return btnDerivare;
    }

    public JButton getBtnIntegrare() {
        return btnIntegrare;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }
}
