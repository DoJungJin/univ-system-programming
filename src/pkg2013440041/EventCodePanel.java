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
import javax.swing.JTextField;

/**
 *
 * @author djj94
 */
// 하위 코드는 comkeen 코드를 거의 그대로 사용했음 AeaPanel 코멘트 참고
public class EventCodePanel extends JPanel {
    
    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    
    public EventCodePanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 125));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createTitledBorder("EventCode"));

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("EventCode"));
        this.add(createRecord("type"));
        this.add(createRecord("EventDesc"));
        this.add(createRecord("lang"));
    }
    
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        
        box.add(Box.createHorizontalGlue());
        
        JTextField textField = new JTextField("");
        box.add(textField);
        
        nameToTextField.put(name, textField);
        return box;
    }
    
    public void loadAeat(AEATType aeat) {
        nameToTextField.get("EventCode").setText(aeat.getAEA().get(0).getHeader().getEventCode().getValue());
        nameToTextField.get("type").setText(aeat.getAEA().get(0).getHeader().getEventCode().getType().toString());
        nameToTextField.get("EventDesc").setText(aeat.getAEA().get(0).getHeader().getEventDesc().get(0).getValue());
        nameToTextField.get("lang").setText(aeat.getAEA().get(0).getHeader().getEventDesc().get(0).getLang());
    }
   public String getEventCode() {
        return nameToTextField.get("EventCode").getText();
    }
    
    public String getType() {
        return nameToTextField.get("type").getText();
    }
    
    public String getEventDesc() {
        return nameToTextField.get("EventDesc").getText();
    }
    
    public String getLang() {
        return nameToTextField.get("lang").getText();
    } 
}
