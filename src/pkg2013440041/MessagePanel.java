
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013440041;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import pkg2013440041.MyButtonActionListener;

/**
 *
 * @author djjproject
 */
// 하위 코드는 comkeen 코드를 거의 그대로 사용했음 AeaPanel 코멘트 참고
// Recieve 버튼만 있는 패널임.
public class MessagePanel extends JPanel {

    private final MyButtonActionListener buttonActionListener;
    private JButton button;

    public MessagePanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.buttonActionListener = buttonActionListener;
        initComponents();
    }

    private void initComponents() {
        this.button = new JButton("Receive");
        button.addActionListener(buttonActionListener);
        
        JLabel label = new JLabel("메시지 받기를 시작하려면 Receive 버튼을 누르세요. -> ", JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        this.add(label);
        this.add(button);
        //    this.add(textArea);

    }
}
