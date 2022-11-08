package converter;

public class Main {

    public static void main(String[] args) {

//        UserInterface ui = new CommandLineInterface();
        UserInterface ui = new GraphicalUserInterface();

        ui.run();
    }
}
