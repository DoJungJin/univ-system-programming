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
public class MediaPanel extends JPanel {
    
    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    
    public MediaPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 200));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createTitledBorder("Media"));

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("lang"));
        this.add(createRecord("mediaDesc"));
        this.add(createRecord("mediaType"));
        this.add(createRecord("url"));
        this.add(createRecord("alternateUrl"));
        this.add(createRecord("contentType"));
        this.add(createRecord("contentLength"));
        this.add(createRecord("mediaAssoc"));
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
        nameToTextField.get("lang").setText(aeat.getAEA().get(0).getMedia().get(0).getLang());
        nameToTextField.get("mediaDesc").setText(aeat.getAEA().get(0).getMedia().get(0).getMediaDesc());
        nameToTextField.get("mediaType").setText(aeat.getAEA().get(0).getMedia().get(0).getMediaType().value());
        nameToTextField.get("url").setText(aeat.getAEA().get(0).getMedia().get(0).getUrl());
        nameToTextField.get("alternateUrl").setText(aeat.getAEA().get(0).getMedia().get(0).getAlternateUrl());
        nameToTextField.get("contentType").setText(aeat.getAEA().get(0).getMedia().get(0).getContentType());
        nameToTextField.get("contentLength").setText(aeat.getAEA().get(0).getMedia().get(0).getContentLength().toString());
        nameToTextField.get("mediaAssoc").setText(aeat.getAEA().get(0).getMedia().get(0).getMediaAssoc());
    }
    public String getLang() {
        return nameToTextField.get("lang").getText();
    }
    
    public String getMediaDesc() {
        return nameToTextField.get("mediaDesc").getText();
    }
    
    public String getMediaType() {
        return nameToTextField.get("mediaType").getText();
    }
    
    public String getUrl() {
        return nameToTextField.get("url").getText();
    }
    public String getAlternateUrl() {
        return nameToTextField.get("alternateUrl").getText();
    }
    
    public String getContentType() {
        return nameToTextField.get("contentType").getText();
    }
    
    public String getContentLength() {
        return nameToTextField.get("contentLength").getText();
    }
    
    public String getMediaAssoc() {
        return nameToTextField.get("mediaAssoc").getText();
    }
}
