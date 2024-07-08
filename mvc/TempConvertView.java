package TempConvert.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import TempConvert.mvc.command_processor.ConvertToCelsiusCommand;
import TempConvert.mvc.command_processor.ConvertToFahrenheitCommand;
import TempConvert.mvc.command_processor.Command;
import TempConvert.mvc.command_processor.CommandProcessor;
import TempConvert.mvc.observer.Subcriber;

public class TempConvertView extends JFrame implements Subcriber {

    // field
    private String title;
    private JPanel jPanel;
    private JLabel jLabelInput, jLabelOutput;
    private JTextField jTextFieldInput,jTextFieldOutput;
    private JButton convertButton;
    private JMenuBar menuBar = null;

    private CommandProcessor commandProcessor;
    private TempConvertModel tempConvertModel;
    private TempConvertController tempConvertController = null;
    
    private ConvertToCelsiusMenuItemController convertToCelsiusMenuItemController = null;
    private ConvertToFahrenheitMenuItemController convertToFahrenheitMenuItemController = null;

    private String celsius = "℃";
    private String fahrenheit = "℉";

    private String state = "convertTo℉";
    


    // function , method
    public TempConvertView() {
        tempConvertModel = new TempConvertModel();
        tempConvertModel.subcribe(this);// dang ky subcriber voi publisher
        tempConvertController = new TempConvertController();
        convertToFahrenheitMenuItemController = new ConvertToFahrenheitMenuItemController();
        convertToCelsiusMenuItemController = new ConvertToCelsiusMenuItemController();
        
        commandProcessor = CommandProcessor.makeCommandProcessor();

        buildMenu();
        
        buildPanel();
        add(jPanel);
        title = "Temp Convert";
        setTitle(title);
        setJMenuBar(menuBar);
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void buildMenu() {
        menuBar = new JMenuBar();
        
        JMenu convertMenu = new JMenu("Command");
        
        JMenuItem  convertToCelsiusMenuItem = new JMenuItem("convertTo℃");
        convertToCelsiusMenuItem.addActionListener(convertToCelsiusMenuItemController);
        
        JMenuItem  convertTofahrenheintMenuItem = new JMenuItem("convertTo℉");
        convertTofahrenheintMenuItem.addActionListener(convertToFahrenheitMenuItemController);
        
        convertMenu.add(convertToCelsiusMenuItem);
        convertMenu.add(convertTofahrenheintMenuItem);

        menuBar.add(convertMenu);

    }

    public void buildPanel() {
        jPanel = new JPanel();
        
        jLabelInput = new JLabel(celsius);
        jPanel.add(jLabelInput);
        
        jTextFieldInput = new JTextField(10);
        jPanel.add(jTextFieldInput);
        
        jLabelOutput = new JLabel(fahrenheit);
        jPanel.add(jLabelOutput);
        
        jTextFieldOutput = new JTextField(10);
        jTextFieldOutput.setEditable(false);
        jPanel.add(jTextFieldOutput);

        
        
        convertButton = new JButton("SEND COMMAND");
        convertButton.addActionListener(tempConvertController);
        jPanel.add(convertButton);

        // jPanel.add(addButtonRemote);
        // subButtonRemote = new JButton("SUB");
        // jPanel.add(subButtonRemote);
        // subButtonRemote.addActionListener(this);

        }

    public void setState(String state){
        this.state = state;
    }


    public void clrscr(){
        jTextFieldInput.setText(null);
        jTextFieldOutput.setText(null);
    }


    class TempConvertController implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Command command = null;
            
            String button = e.getActionCommand();
            double num = Double.parseDouble(jTextFieldInput.getText());
            
            if (button.equals("SEND COMMAND")) {
                if(state.equals("convertTo℉")){
                        command = new ConvertToFahrenheitCommand(tempConvertModel, num);
                    }
                    else if(state.equals("convertTo℃")){
                        command = new ConvertToCelsiusCommand(tempConvertModel, num);
                    }
                    
                    commandProcessor.execute(command);
                } 
            }

    }

    @Override
    public void update() {
        jTextFieldOutput.setText("" + tempConvertModel.getResult());
    }

    
    class ConvertToCelsiusMenuItemController implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clrscr();
            jLabelInput.setText(fahrenheit);
            jLabelOutput.setText(celsius);
            setState("convertTo℃");
        }

    }

    class ConvertToFahrenheitMenuItemController implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clrscr();
            jLabelInput.setText(celsius);
            jLabelOutput.setText(fahrenheit);
            setState("convertTo℉");
       }
   }


}
