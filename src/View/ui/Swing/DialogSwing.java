package View.ui.Swing;

import View.ui.Dialog;
import Controller.MainFrame;
import Model.Currency;
import Model.Money;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class DialogSwing extends JPanel implements Dialog{
    
    protected final List<Currency> currencies;
    public double amount = 0;
    private final MainFrame controller;
    private Currency currencyTo;
    private Currency currencyFrom;
    private JComboBox comboBoxFrom;
    private JComboBox comboBoxTo;

    public DialogSwing(List<Currency> currencies, MainFrame controller) {
        this.currencies = currencies;
        this.controller = controller;
        initComponents();
    }   

    @Override
    public Money getMoney() {
        return new Money(this.amount, this.currencyFrom);
    }

    @Override
    public Currency getCurrencyTo() {
        return currencyTo;
    }
    
    // JPanel components creation
    private JComboBox comboBoxCurrencyFrom() {
        comboBoxFrom = createComboBox();
        comboBoxFrom.addItemListener(currencyFromChange());
        currencyFrom = (Currency) comboBoxFrom.getSelectedItem();
        return comboBoxFrom;
    }
    
    private JComboBox comboBoxCurrencyTo() {
        comboBoxTo = createComboBox();
        comboBoxTo.addItemListener(currencyToChange());
        currencyTo = (Currency) comboBoxTo.getSelectedItem();
        return comboBoxTo;
    }
    
    private JScrollPane textAreaAmount() {
        JTextArea textArea = new JTextArea(2, 15);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.getDocument().addDocumentListener(amountChange());
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(textArea);
        
        scroll.setSize(211, 50);
        scroll.setMaximumSize(new Dimension(211, 50));
        return scroll;
    }
    
    
    // ItemListener events management
    private ItemListener currencyFromChange(){
        return (ItemEvent e) -> {
            if ((e.getStateChange() == ItemEvent.SELECTED)) {
                currencyFrom = (Currency) e.getItem();
                controller.updateDisplay();
            }
        };
    }
    
    private ItemListener currencyToChange(){
        return (ItemEvent e) -> {
            if ((e.getStateChange() == ItemEvent.SELECTED)) {
                currencyTo = (Currency) e.getItem();
                controller.updateDisplay();
            }
        };
    }
    
    private DocumentListener amountChange(){
        return new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent de) {
                amountUpdate(de.getDocument());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                amountUpdate(de.getDocument());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                amountUpdate(de.getDocument());
            }
            
            private void amountUpdate(Document d){
                try{
                    String line = d.getText(0, d.getLength());
                    try{
                        amount = Double.parseDouble(line);
                        controller.updateDisplay();
                    } catch (NumberFormatException e){
                        controller.DisplayError();
                    }
                } catch (BadLocationException ble) {
                    System.out.println(ble.getMessage());
                }
            }
        };
    }
    
    // ComboBox auxiliar method
    private JComboBox createComboBox(){
        JComboBox cb = new JComboBox();
        cb.setSize(80, 20);
        cb.setMaximumSize(new Dimension(80, 20));
        currencies.forEach((currency) -> {
            cb.addItem(currency);
        });
        
        return cb;
    }

    // GUI creation
    private void initComponents(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JLabel TitleLabel = new JLabel("MoneyCalculator");
        TitleLabel.setFont(new java.awt.Font("Arial", 1, 25));
        TitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(TitleLabel);
        
        this.add(Box.createRigidArea(new Dimension(0, 25)));  // Space
        
        JLabel FromLabel = new JLabel("FROM CURRENCY");
        FromLabel.setFont(new java.awt.Font("Arial", 1, 15));
        FromLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(FromLabel);
        
        // From ComboBox
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(comboBoxCurrencyFrom());
        //comboBoxFrom.setAlignmentX(RIGHT_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel AmountLabel = new JLabel("AMOUNT");
        this.add(AmountLabel);
        AmountLabel.setFont(new java.awt.Font("Arial", 1, 14));
        AmountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.add(textAreaAmount());
        
        this.add(Box.createRigidArea(new Dimension(0, 20)));  // Space
        
        JLabel ToLabel = new JLabel("TO CURRENCY");
        ToLabel.setFont(new java.awt.Font("Arial", 1, 14));
        ToLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(ToLabel);
        
        // To ComboBox
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(comboBoxCurrencyTo());
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JLabel TotalLabel = new JLabel("TOTAL");
        this.add(TotalLabel);
        TotalLabel.setFont(new java.awt.Font("Arial", 1, 14));
        TotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
    }
}