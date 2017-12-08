/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author djj94
 */
package pkg2013440041;

import aeat.AEATType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author djj94
 */
// 하위 코드는 comkeen 코드를 거의 그대로 사용했음 AeaPanel 코멘트 참고
public class ConfigPanel extends JPanel {

    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    private Map<String, JRadioButton> nameToRadio;

    public ConfigPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 125));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createTitledBorder("Config"));

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        nameToRadio = new HashMap<>();
        initComponents();
    }

    // 설정 페이지에 4개의 필드 생성
    private void initComponents() {
        this.add(createRecord("Send Server"));
        this.add(createRecord("Send Group"));
        this.add(createRadio("BroadCasting"));
        this.add(createRecord("Receive Server"));
        this.add(createRecord("Receive Group"));
        this.add(createRadio("Method"));

    }

    // 브로드 캐스팅을 선택하는 라디오 버튼  생성
    private Box createRadio(String name) {
        Box box = Box.createHorizontalBox();
        JPanel hole = new JPanel();
        hole.setMinimumSize(new Dimension(400, 20));

        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        // 일단 수평 박스를 만들고 라디오 버튼을 생성한다.
        box.add(Box.createHorizontalGlue());

        JRadioButton trueRadio1 = new JRadioButton("1 : 1");
        trueRadio1.setFont(Frame.LABEL_FONT);
        JRadioButton falseRadio1 = new JRadioButton("1 : ∞");
        falseRadio1.setFont(Frame.LABEL_FONT);
        // 라디오 버튼 2개 생성

        // 라디오 버튼 그룹에 버튼 넣음 (한개만 선택이 됨)
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(trueRadio1);
        buttonGroup.add(falseRadio1);
        falseRadio1.setSelected(true);
        // 기본적으로 1:다 로 선택 되도록 설정

        box.add(trueRadio1);
        box.add(hole);
        box.add(Box.createHorizontalGlue());
        box.add(falseRadio1);
        box.add(hole);
        // 박스에 true false 추가함

        if (name.equals("BroadCasting")) {
            nameToRadio.put("sendTrue", trueRadio1);
            nameToRadio.put("sendFalse", falseRadio1);
        } else {
            nameToRadio.put("receiveTrue", trueRadio1);
            nameToRadio.put("receiveFalse", falseRadio1);
        }
        // HashMap 에 넣는다.
        return box;
    }

    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);

        box.add(Box.createHorizontalGlue());

        String data = "";

        // 기본값을 설정하기 위해서 아래의 구문 작성
        // name 에 따라 TextField 의 기본값을 다르게 하도록 작성
        switch (name) {
            case "Send Server":
                data = "tcp://djjproject.com:61616";
                break;
            case "Send Group":
                data = "djjproject";
                break;
            case "Receive Server":
                data = "tcp://djjproject.com:61616";
                break;
            case "Receive Group":
                data = "djjproject";
                break;
            default:
                break;
        }

        JTextField textField = new JTextField(data);
        box.add(textField);

        nameToTextField.put(name, textField);
        return box;
    }

    public String getSendServer() {
        return nameToTextField.get("Send Server").getText();
    }

    public String getSendGroup() {
        return nameToTextField.get("Send Group").getText();
    }

    public String getReceiveServer() {
        return nameToTextField.get("Receive Server").getText();
    }

    public String getReceiveGroup() {
        return nameToTextField.get("Receive Group").getText();
    }

    // 브로드캐스트 설정을 리턴함 (boolean)
    public boolean getBroadCast() {
        if (nameToRadio.get("sendTrue").isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    // 수신 방식을 리턴함 (boolean)
    public boolean getReceiveMethod() {
        if (nameToRadio.get("receiveTrue").isSelected()) {
            return true;
        } else {
            return false;
        }
    }

}
