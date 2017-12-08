/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013440041;

import aeat.AEATType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author djj94
 */
// Jpanel 을 사용하는 AeaPanel 선언
public class AeaPanel extends JPanel {

    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    private Map<String, JComboBox> nameToComboBox;
    private Map<String, JRadioButton> nameToRadioButton;
    // 버튼 리스너와 nanmeToTextField 의 Map 을 선언한다.
    // JComboBox 를 용과 JRadioButton 을 선언한다. HashMap

    // AeaPanel 이 선언되면 동작하는 코드.
    public AeaPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 150));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createTitledBorder("Aea"));
        // 사이즈와 레이아웃, Border 설정을 한다.
        // 디자인 때문에 Border 를 Title 로 설정함

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        nameToComboBox = new HashMap<>();
        nameToRadioButton = new HashMap<>();
        // 버튼 리스너와 HashMap 을 초기화
        
        initComponents();
        // initComponents() 실행
    }

    // initComponents()
    // 입력 : 없음
    // 출력 : 없음
    // 부수효과 : 패널에 createRecord() , createRadio(), createChoice() 의 코드가 실행됨
    private void initComponents() {
        this.add(createRecord("aeaId"));
        this.add(createRecord("issuer"));
        // 라벨과 텍스트 박스 추가
        
        this.add(createChoice("audience"));
        this.add(createChoice("aeaType"));
        // 라벨과 콤보박스 추가
        
        this.add(createRecord("refAEAId"));
        // 라벨과 텍스트 박스 추가
        
        this.add(createRadio("priority"));
        this.add(createRadio("wakeup"));
        // 라벨과 라디오 버튼 추가
        
    }

    // createRadio()
    // 입력 : 버튼 그룹 이름 (Srting)
    // 출력 : 박스에 라벨을 추가하고 버튼을 필요한 만큼 추가하고 그룹으로 묶고 박스에 추가한다. (Box)
    private Box createRadio(String name) {
        Box box = Box.createHorizontalBox();
        JPanel hole = new JPanel();
        hole.setMinimumSize(new Dimension(400, 20));

        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        // 일단 수평 박스를 만들고 들어온 String 에 따라서 라디오 생성을 나눈다

        box.add(Box.createHorizontalGlue());

        // wakeup 일 경우 아래의 코드 실행
        // 홀을 추가하여 센터 정렬함
        if (name.equals("wakeup")) {
            JRadioButton trueRadio = new JRadioButton("TRUE");
            trueRadio.setFont(Frame.LABEL_FONT);
            JRadioButton falseRadio = new JRadioButton("FALSE");
            falseRadio.setFont(Frame.LABEL_FONT);
            // 라디오 버튼 2개 생성
            
            // 라디오 버튼 그룹에 버튼 넣음 (한개만 선택이 됨)
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(trueRadio);
            buttonGroup.add(falseRadio);

            box.add(trueRadio);
            box.add(hole);
            box.add(falseRadio);
            box.add(hole);
            // 박스에 true false 추가함
            
            nameToRadioButton.put("wakeUpTrue", trueRadio);
            nameToRadioButton.put("wakeUpFalse", falseRadio);
            // HashMap 에 넣는다.

        // priority 의 경우 아래의 코드 실행
        } else if (name.equals("priority")) {
            JRadioButton radio0 = new JRadioButton("VeryLow");
            radio0.setFont(Frame.LABEL_FONT);
            JRadioButton radio1 = new JRadioButton("Low");
            radio1.setFont(Frame.LABEL_FONT);
            JRadioButton radio2 = new JRadioButton("Normal");
            radio2.setFont(Frame.LABEL_FONT);
            JRadioButton radio3 = new JRadioButton("High");
            radio3.setFont(Frame.LABEL_FONT);
            JRadioButton radio4 = new JRadioButton("VeryHigh");
            radio4.setFont(Frame.LABEL_FONT);
            // 라디오 버튼 5개 생성
            
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(radio0);
            buttonGroup.add(radio1);
            buttonGroup.add(radio2);
            buttonGroup.add(radio3);
            buttonGroup.add(radio4);
            // 버튼 그룹에 추가
            
            // 박스에 빈공간의 JPanel 을 추가해서 레이아웃 센터 정렬이 되도록 설정한다.
            box.add(radio4);
            box.add(hole);
            box.add(radio3);
            box.add(hole);
            box.add(radio2);
            box.add(hole);
            box.add(radio1);
            box.add(hole);
            box.add(radio0);
            box.add(hole);

            
            nameToRadioButton.put("priority0", radio0);
            nameToRadioButton.put("priority1", radio1);
            nameToRadioButton.put("priority2", radio2);
            nameToRadioButton.put("priority3", radio3);
            nameToRadioButton.put("priority4", radio4);
            // HashMap 에 넣는다.
        }

        return box;

    }

    // createChoice()
    // 입력 : label 의 이름
    // 출력 : 라벨과 콤보박스가 추가된 박스 리턴 (Box)
    private Box createChoice(String name) {
        Box box = Box.createHorizontalBox();
        JComboBox combo = new JComboBox();
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        // 매개변수 name 을 라벨로 박스에 추가
        
        // 매개변수 name 에 따라 다른 콤보박스 생성
        if (name.equals("audience")) {
            combo.addItem("public");
            combo.addItem("restricted");
            combo.addItem("private");
        } else if (name.equals("aeaType")) {
            combo.addItem("alert");
            combo.addItem("update");
            combo.addItem("cancel");
        }
//        } else if (name.equals("priority")) {
//            combo.addItem("4");
//            combo.addItem("3");
//            combo.addItem("2");
//            combo.addItem("1");
//            combo.addItem("0");
//        }
        box.add(combo);
        // 상기에서 만들어진 콤보박스를 박스에 추가
        
        // 콤보박스를 해쉬맵에 추가
        nameToComboBox.put(name, combo);
        return box;
    }

    // createRecord()
    // 입력 : 라벨의 이름 (String)
    // 출력 : 입력된 String 을 가진 라벨과 텍스트 Area 를 추가한 Box 를 리턴 (Box)
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        // 입력된 String 에 대한 Label 을 박스에 추가

        box.add(Box.createHorizontalGlue());
        // 수평으로 왼쪽이나 오른쪽에 달라붙도록 설정

        JTextField textField = new JTextField("");
        box.add(textField);
        // 빈 텍스트 필드를 박스에 추가
        
        nameToTextField.put(name, textField);
        // 해쉬맵에 추가
        return box;
    }

    // loadAeat()
    // 입력 : aeat 변수 (AEATType)
    // 출력 : 없음
    // 부수효과 : aeat 변수에서 필드의 데이터를 가져와서 패널에 데이터 추가
    public void loadAeat(AEATType aeat) {
        nameToTextField.get("aeaId").setText(aeat.getAEA().get(0).getAeaId());
        nameToTextField.get("issuer").setText(aeat.getAEA().get(0).getIssuer());
        // HashMap 에서 aeaId 라는 이름을 가진 항목을 가져오고 setText 를 통해 텍스트 필드를 초기화 함.
        // aeat.getAEA() 는 JAXB 바인딩으로 자동으로 생성되었고 스키마파일 기반으로 생성되었다.
        // get(0) 의 경우 필드 항목이 0~N 임으로 일단 첫번째 것을 가져오기 위함이다.
        // 이하 항목 동일.
        
        // 콤보박스 HashMap 에 추가한 것을 가져올때 setSelectedItem 메소드로 값 반영이 가능하다.
        nameToComboBox.get("audience").setSelectedItem(aeat.getAEA().get(0).getAudience().value());
        nameToComboBox.get("aeaType").setSelectedItem(aeat.getAEA().get(0).getAeaType().value());
        
        // 상기는 동일
        nameToTextField.get("refAEAId").setText(aeat.getAEA().get(0).getRefAEAId());
        
        // 일단 priority 는 라디오 버튼이다. 그렇기 때문에 버튼이 선택되었는지 일일이 비교를 해야한다.
        // priority 에 aeat 에서 가져온 스트링 값을 저장해 두고 비교를 한다.
        String priority = aeat.getAEA().get(0).getPriority().toString();
        switch (priority) {
            // priority 를 비교하여 01234 일 경우에 버튼을 선택함.
            case "0" : nameToRadioButton.get("priority0").setSelected(true); break;
            case "1" : nameToRadioButton.get("priority1").setSelected(true); break;
            case "2" : nameToRadioButton.get("priority2").setSelected(true); break;
            case "3" : nameToRadioButton.get("priority3").setSelected(true); break;
            case "4" : nameToRadioButton.get("priority4").setSelected(true); break;
            default : break;
        }
        
//        nameToComboBox.get("priority").setSelectedItem(aeat.getAEA().get(0).getPriority().toString());

        // 상기와 동일하게 wakeUp 변수에 aeat 스트링을 저장한다.
        String wakeUp = String.valueOf(aeat.getAEA().get(0).isWakeup());
        
        // 경우에 따라 라디오 버튼을 선택한다.
        if (wakeUp.equals("true")) {
            nameToRadioButton.get("wakeUpTrue").setSelected(true);
        } else if (wakeUp.equals("false")) {
            nameToRadioButton.get("wakeUpFalse").setSelected(true);
        }
//        nameToRadioButton.get("wakeup").setText(String.valueOf(aeat.getAEA().get(0).isWakeup()));
    }

    public String getAeaId() {
        return nameToTextField.get("aeaId").getText();
    }

    public String getIssuer() {
        return nameToTextField.get("issuer").getText();
    }

    public String getAudience() {
        return nameToComboBox.get("audience").getSelectedItem().toString();
    }

    public String getAeaType() {
        return nameToComboBox.get("aeaType").getSelectedItem().toString();
    }

    public String getRefAEAId() {
        return nameToTextField.get("refAEAId").getText();
    }
    // 상기는 String 이기 때문에 그냥 getText() 로 사용이 가능하다.
    
    
    // 라디오 버튼이기 때문에 각 라디오 버튼을 직접 isSelected() 로 비교해야한다.
    public String getPriority() {
        if (nameToRadioButton.get("priority0").isSelected()) return "0";
        else if (nameToRadioButton.get("priority1").isSelected()) return "1";
        else if (nameToRadioButton.get("priority2").isSelected()) return "2";
        else if (nameToRadioButton.get("priority3").isSelected()) return "3";
        else if (nameToRadioButton.get("priority4").isSelected()) return "4";
        else return "default";
//        return nameToComboBox.get("priority").getSelectedItem().toString();
    }

    // 라디오 버튼이기 때문에 각 라디오 버튼을 직접 isSelected() 로 비교해야한다.
    public String isWakeup() {
        if (nameToRadioButton.get("wakeUpTrue").isSelected()) {
            return "true";
        } else if (nameToRadioButton.get("wakeUpFalse").isSelected()) {
            return "false";
        } else {
            return "default";
        }
    }

}
