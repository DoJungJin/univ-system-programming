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
public class LocationPanel extends JPanel {
    
    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    
    public LocationPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 50));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createTitledBorder("Location"));

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("Location"));
        this.add(createRecord("type"));
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
        nameToTextField.get("Location").setText(aeat.getAEA().get(0).getHeader().getLocation().get(0).getValue());
        nameToTextField.get("type").setText(aeat.getAEA().get(0).getHeader().getLocation().get(0).getType().value());
    }

    public String getLocate() {
        return nameToTextField.get("Location").getText();
    }
    
    public String getType() {
        return nameToTextField.get("type").getText();
    }
}
