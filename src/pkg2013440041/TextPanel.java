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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author djj94
 */
// 하위 코드는 comkeen 코드를 거의 그대로 사용했음 AeaPanel 코멘트 참고
public class TextPanel extends JPanel {

    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    private JTabbedPane tabPane;

    public TextPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 50));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createTitledBorder("Text"));

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }

    private void initComponents() {
        // 탭을 선언하여 탭을 5개 추가한다.
        tabPane = new JTabbedPane();
        tabPane.add("Text 1", createRecord(1));
        tabPane.add("Text 2", createRecord(2));
        tabPane.add("Text 3", createRecord(3));
        tabPane.add("Text 4", createRecord(4));
        tabPane.add("Text 5", createRecord(5));
        this.add(tabPane);
    }

    private Box createRecord(int number) {
        Box box = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        JLabel label = new JLabel("AEAText", JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        JLabel label2 = new JLabel("lang", JLabel.CENTER);
        label2.setFont(Frame.LABEL_FONT);
        label2.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        box2.add(label2);

        JTextField textField = new JTextField("");
        textField.setPreferredSize(new Dimension(100, 20));
        JTextField textField2 = new JTextField("");
        textField2.setPreferredSize(new Dimension(100, 20));
        box.add(textField);
        box2.add(textField2);

        box.add(Box.createHorizontalGlue());
        box2.add(Box.createHorizontalGlue());
        // 상기 코드는 박스1에 TextArea 를 박스2 에 Lang 을 추가하는 코드

        // 편하게 가져다 쓸 수 있도록 해쉬맵에 추가한다.
        nameToTextField.put("AEAText" + String.valueOf(number), textField);
        nameToTextField.put("lang" + String.valueOf(number), textField2);

//        JPanel returnPanel = new JPanel();
//        returnPanel.setMinimumSize(new Dimension(400, 50));
//        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));
//        returnPanel.add(box);
//        returnPanel.add(box2);

        // 하나로 통합해서 리턴해야함으로 리턴 전용 박스에 모두 넣고 리턴한다.
        Box returnBox = Box.createVerticalBox();
        returnBox.add(box);
        returnBox.add(box2);

        return returnBox;
    }

    public void loadAeat(AEATType aeat) {
        // 초기에 0번 인덱스는 값이 무조껀 존재함을 가정으로 하기 때문에 try / catch 구문을 사용하지 않는다.
        if (aeat.getAEA().get(0).getAEAText().get(0).getValue().length() > 0) {
            nameToTextField.get("AEAText1").setText(aeat.getAEA().get(0).getAEAText().get(0).getValue());
            nameToTextField.get("lang1").setText(aeat.getAEA().get(0).getAEAText().get(0).getLang());
        }

        // 1번 인덱스는 사용자가 값을 넣었는지 체크를 해야 함으로 아래와 같이 try catch 구문으로 비교를 진행한다.
        try {
            if (aeat.getAEA().get(0).getAEAText().get(1).getValue().length() > 0) {
                // 상기 IF 문 체크 자체가 배열의 바운더리 익셉션이 발생하기 때문에 try catch 구문을 사용해야한다.
                // 데이타 lengh 길이를 비교해서 데이터가 존재하면 아래의 코드를 수행하도록 되어 있다.
                nameToTextField.get("AEAText2").setText(aeat.getAEA().get(0).getAEAText().get(1).getValue());
                // AEAT 는 실제로 0~N 까지 다수의 인덱스를 가질 수 있는 것들이 여러개 있는데 Text 가 그 중에 하나이다.
                // 처음의 get(0) 은 AEA 의 인덱스이고 나중에 나오는 get(1) 은 AEAText 의 인덱스이다.
                nameToTextField.get("lang2").setText(aeat.getAEA().get(0).getAEAText().get(1).getLang());
            }
        } catch (Exception e) {

        }
        
        // 이하 상기와 동일
        try {
            if (aeat.getAEA().get(0).getAEAText().get(2).getValue().length() > 0) {
                nameToTextField.get("AEAText3").setText(aeat.getAEA().get(0).getAEAText().get(2).getValue());
                nameToTextField.get("lang3").setText(aeat.getAEA().get(0).getAEAText().get(2).getLang());
            }
        } catch (Exception e) {

        }

        try {
            if (aeat.getAEA().get(0).getAEAText().get(3).getValue().length() > 0) {
                nameToTextField.get("AEAText4").setText(aeat.getAEA().get(0).getAEAText().get(3).getValue());
                nameToTextField.get("lang4").setText(aeat.getAEA().get(0).getAEAText().get(3).getLang());
            }
        } catch (Exception e) {

        }

        try {
            if (aeat.getAEA().get(0).getAEAText().get(4).getValue().length() > 0) {
                nameToTextField.get("AEAText5").setText(aeat.getAEA().get(0).getAEAText().get(4).getValue());
                nameToTextField.get("lang5").setText(aeat.getAEA().get(0).getAEAText().get(4).getLang());
            }
        } catch (Exception e) {

        }

    }

    // 미리 선언한 Text 와 Lang 을 가져오도록 함수를 여러개 생성한다. 총 10개
    public String getAEAText1() {
        return nameToTextField.get("AEAText1").getText();
    }

    public String getLang1() {
        return nameToTextField.get("lang1").getText();
    }

    public String getAEAText2() {
        return nameToTextField.get("AEAText2").getText();
    }

    public String getLang2() {
        return nameToTextField.get("lang2").getText();
    }

    public String getAEAText3() {
        return nameToTextField.get("AEAText3").getText();
    }

    public String getLang3() {
        return nameToTextField.get("lang3").getText();
    }

    public String getAEAText4() {
        return nameToTextField.get("AEAText4").getText();
    }

    public String getLang4() {
        return nameToTextField.get("lang4").getText();
    }

    public String getAEAText5() {
        return nameToTextField.get("AEAText5").getText();
    }

    public String getLang5() {
        return nameToTextField.get("lang5").getText();
    }
    
    public void clear() {
        // 내부에 존재하는 것들을 모두 초기화 한다.
        nameToTextField.get("AEAText1").setText("");
        nameToTextField.get("lang1").setText("");
        nameToTextField.get("AEAText2").setText("");
        nameToTextField.get("lang2").setText("");
        nameToTextField.get("AEAText3").setText("");
        nameToTextField.get("lang3").setText("");
        nameToTextField.get("AEAText4").setText("");
        nameToTextField.get("lang4").setText("");
        nameToTextField.get("AEAText5").setText("");
        nameToTextField.get("lang5").setText(""); 
    }
}
