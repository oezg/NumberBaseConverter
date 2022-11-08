package converter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalUserInterface extends JFrame implements UserInterface, ActionListener {

    public GraphicalUserInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setTitle("Number Base Converter");
        JPanel basePanel = new JPanel(new FlowLayout());
        JPanel numberPanel = new JPanel(new FlowLayout());
        JPanel resultPanel = new JPanel(new FlowLayout());
        add(basePanel);
        add(numberPanel);
        add(resultPanel);
        JLabel sourceBaseLabel = new JLabel("Source Base");
        JTextField sourceBaseField = new JTextField();
        JLabel targetBaseLabel = new JLabel("Target Base");
        JTextField targetBaseField = new JTextField();
        basePanel.add(sourceBaseLabel);
        basePanel.add(sourceBaseField);
        basePanel.add(targetBaseLabel);
        basePanel.add(targetBaseField);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
