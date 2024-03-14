import Controller.ControllerImpl;
import Model.OperationsModelImpl;
import View.GuiView;

public class Main {
    public static void main(String[] args) {
        GuiView guiView = new GuiView("Calculator de Polinoame");
        OperationsModelImpl operationsModelImpl = new OperationsModelImpl();
        ControllerImpl controllerImpl = new ControllerImpl(guiView, operationsModelImpl);
    }
}