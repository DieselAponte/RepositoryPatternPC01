import javax.swing.*;
import Vista.BancoHomeVista;

public class Main {
    public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
                    BancoHomeVista vista = new BancoHomeVista();
                    vista.setVisible(true);
                });
    }
}