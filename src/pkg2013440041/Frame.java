/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013440041;

import aeat.AEATType;
import aeat.AEAType;
import aeat.AEAtypeType;
import aeat.AudienceType;
import aeat.HeaderType;
import aeat.LangType;
import aeat.LiveMediaType;
import aeat.LocationType;
import aeat.LocationTypeType;
import aeat.MediaType;
import aeat.MediaTypeType;
import aeat.TypeType;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author djj94
 */
public class Frame {

    private JFrame frame;
    private AeaPanel aeaPanel;
    private JPanel buttonPanel;
    private JPanel buttonMessagePanel;
    private TimePanel timePanel;
    private EventCodePanel eventCodePanel;
    private LocationPanel locationPanel;
    private TextPanel textPanel;
    private LiveMediaPanel liveMediaPanel;
    private MediaPanel mediaPanel;

    private AeaPanel aeaPanel1;
    private JPanel buttonPanel1;
    private JPanel buttonMessagePanel1;
    private TimePanel timePanel1;
    private EventCodePanel eventCodePanel1;
    private LocationPanel locationPanel1;
    private TextPanel textPanel1;
    private LiveMediaPanel liveMediaPanel1;
    private MediaPanel mediaPanel1;
    // 교수님께서 지정하신 7개 패널 생성 
    // 7개 패널을 frame 에 추가함
    // 추가적으로 받는 패널에 추가하기 위해서 한개 더 생성

    private MyButtonActionListener buttonActionListener;
    // 버튼 액션 리스너 선언

    public static final String TITLE = "AEAT Publisher (djjproject)";
    public static final Dimension LABEL_DIMENSION = new Dimension(100, 40);
    public static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 14);
    // 전역변수의 느낌으로 타이틀 / 라벨 / 폰트 설정
    private MessagePanel messagePanel;
    private ConfigPanel configPanel;
    // 메시지 패널과 설정 패널을 생성

    // file load save 포인터 느낌의 변수 생성
    private JComboBox loadFile;
    private JTextField saveFile;
    
    // dirFile 메소드를 통해 해당 폴더의 파일 리스트 생성
    String path = "xml/";
    File dirFile = new File(path);
    File[] fileList = dirFile.listFiles();

    /**
     *
     * @param buttonActionListener
     */
    // 초기화 , 버튼 리스너와 Tab 을 추가하고 사이즈를 설정함
    public Frame(MyButtonActionListener buttonActionListener) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame frame = new JFrame(TITLE);
        this.buttonActionListener = buttonActionListener;
        JTabbedPane mainTabbedPanel = new JTabbedPane();
        // 프레임을 생성하고 타이틀을 설정함
        // 버튼 리스너를 추가함
        // JTabbedPane 을 추가함

        mainTabbedPanel.add("편집 및 전송", initAeatEditTabPanel());
        mainTabbedPanel.add("받은 메시지", initMessageTabPanel());
        mainTabbedPanel.add("설정 및 설명", initConfigPanel());
        // 탭에 3개의 탭을 추가함 

        frame.getContentPane().add(mainTabbedPanel);
        // 만들어진 탭을 frame 에 추가함

        frame.setPreferredSize(new Dimension(600, 720));
        frame.setSize(new Dimension(600, 720));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        // 사이즈와 기타 설정
        
   //     loadFile = new JComboBox();
    //    saveFile = new JTextField();

    }

    // initCongifPanel()
    // 입력 : 없음
    // 출력 : 설정 패널을 리턴한다. (JPanel)
    private JPanel initConfigPanel() {
        JPanel aeatConfigPanel = new JPanel();
        aeatConfigPanel.setLayout(new BoxLayout(aeatConfigPanel, BoxLayout.Y_AXIS));
        // aeatConfigPanel 을 생성하고 Y축으로 정렬되도록 한다.

        this.configPanel = new ConfigPanel(buttonActionListener);
        aeatConfigPanel.add(configPanel);
        // 버튼 리스너를 추가하고 configPanel 을 JPanel 에 추가한다.

        // 설명을 적을 TextArea 생성
        JTextArea text = new JTextArea(" 일단 설정 탭에서 보낼 서버 주소와 보내 고자 하는 그룹 이름을 설정한다. \n 그리고 받기 위한 서버 주소와 그룹을 설정한다. \n 추가적으로 메시지를 브로드캐스팅 할 것인지 아닌지 선택가능하다. \n 브로드 캐스팅일 경우 받는 쪽도 브로드캐스팅으로 설정되어야 한다. \n 즉, 1:1 전송 방법은 테스트 목적으로만 사용한다. \n 기본값으로 djjproject.com 이 설정되어 있다. \n djjproject.com 은 항상 운영 중이다. 그러나 언제 중단될지 모름. \n 그리고 편집 및 전송에서 샘플을 Load 버튼으로 불러온다 \n 불러온 샘플을 마음껏 수정하고 Save 하거나 Send 버튼을 통해 서버로 전송한다. \n 받은 메시지 탭에서 Recive 버튼을 눌러 놓으면 실시간으로 수신 가능하다. \n 추가적으로 메시지가 오면 소리와 알림으로 알려준다. \n \n \n 2013440041 djjproject / 변윤관 조교님 수고하십니다.");
        text.setFont(LABEL_FONT);
        aeatConfigPanel.add(text);
        // 폰트 설정을 하고 패널에 추가한다.

        // 스크롤바 구현을 위해서 JScrollPane 사용
        // aeatConfigPanel -> JScrollPane -> JPanel (returnPanel)
        // 몇가지 부분은 정적으로 있고 나머지 부분은 스크롤 되게 하기 위해서 returnPanel 생성
        JScrollPane scrollPane = new JScrollPane(aeatConfigPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        // scrollPane 에 aeatConfigPanel 추가
        // 수직은 항상 나타나게 설정하고 , 수평은 필요할때에만 나타나도록 설정
        // 스크롤 속도를 16으로 알맞게 설정

        // 스크롤바와 고정 요소를 추가한 리턴 패널 생성
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));

        returnPanel.add(scrollPane);

        // 2013440041 / djjproject 를 추가하기위한 박스 생성
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(" 2013440041 / djjproject", JLabel.LEFT);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(new Dimension(400, 20));

        box.add(label);
        box.add(Box.createHorizontalGlue());

        returnPanel.add(box);
        // 박스 추가

        return returnPanel;

    }

    // initAeatEditTabPanel()
    // 입력 : 없음
    // 출력 : 패널을 추가한 패널 리턴 (JPanel)
    private JPanel initAeatEditTabPanel() {
        JPanel aeatEditTabPanel = new JPanel();
        aeatEditTabPanel.setLayout(new BoxLayout(aeatEditTabPanel, BoxLayout.Y_AXIS));
        // aeatEditTabPanel 을 생성하고 패널들을 추가

        this.aeaPanel = new AeaPanel(buttonActionListener);
        aeatEditTabPanel.add(aeaPanel);
        this.timePanel = new TimePanel(buttonActionListener);
        aeatEditTabPanel.add(timePanel);
        this.eventCodePanel = new EventCodePanel(buttonActionListener);
        aeatEditTabPanel.add(eventCodePanel);
        this.locationPanel = new LocationPanel(buttonActionListener);
        aeatEditTabPanel.add(locationPanel);
        this.textPanel = new TextPanel(buttonActionListener);
        aeatEditTabPanel.add(textPanel);
        this.liveMediaPanel = new LiveMediaPanel(buttonActionListener);
        aeatEditTabPanel.add(liveMediaPanel);
        this.mediaPanel = new MediaPanel(buttonActionListener);
        aeatEditTabPanel.add(mediaPanel);

        // 스크롤바 관련 코드 (상기 부분 참고)
        // 이하 코드는 configpanel 코멘트 참고
        JScrollPane scrollPane = new JScrollPane(aeatEditTabPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));

        this.buttonPanel = initButtonPanel();
        aeatEditTabPanel.add(buttonPanel);
        returnPanel.add(scrollPane);
        returnPanel.add(buttonPanel);

        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(" 2013440041 / djjproject", JLabel.LEFT);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(new Dimension(400, 40));

        box.add(label);
        box.add(Box.createHorizontalGlue());

        returnPanel.add(box);

        return returnPanel;
    }

    // 패널 초기화 메소드 작성
    // initButtonPanel()
    // 입력 : 없음
    // 출력 : 버튼 두개가 추가된 panel 이 리턴됨
    // 부수효과 : 없음
    // 버튼을 3개 생성 및 패널에 추가
    private JPanel initButtonPanel() {
        
        JPanel panel = new JPanel();
        // 버튼 패널 생성
        
        // 파일 리스트를 받기 위한 어레이리스트 생성
        List<String> AEATypeItems = new ArrayList();
        
        // 파일 리스트가 존재할 때 까지 (약간 추상적인 개념)
        for (File tempFile : fileList) {

            // 파일 네임을 읽어서 아이템에 추가함
            String tempPath = tempFile.getParent();
            String tempFileName = tempFile.getName();
            AEATypeItems.add(tempFileName);
        }
        
        // 콤보박스 모델을 사용함
        ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(AEATypeItems.toArray());
        JComboBox fileBox = new JComboBox();
        fileBox.setModel(comboBoxModelAEAType);
        
        // 포인터 처럼 사용하기 위함임으로 fileBox 를 전역변수 loadFile 에 추가함
        // 해쉬맵을 쓸 수도 있으나 지금 필요한 것은 2개 임으로 그냥 전역변수 2개를 추가
        loadFile = fileBox;
        panel.add(fileBox);
        
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(buttonActionListener);
        panel.add(loadButton);
        
        // 파일 저장 이름을 입력하는 텍스트 박스 추가
        JTextField fileSave = new JTextField();
        fileSave.setText("Insert save file name.");
        
        // 사이즈를 가로로 100 세로로 30을 줌
        fileSave.setPreferredSize(new Dimension(100,30));
        saveFile = fileSave;
        // 포인터 처럼 쓰기 위해서 saveFile 에 대입함
        panel.add(fileSave);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(buttonActionListener);
        panel.add(saveButton);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(buttonActionListener);
        panel.add(sendButton);

        return panel;
        // 패널에 로드와 세이브 버튼을 추가하고 panel 을 리턴한다.
    }

    // initMessageTabPanel()
    // 입력 : 없음
    // 출력 : 하위 패널들을 추가한 패널 리턴
    private JPanel initMessageTabPanel() {
        JPanel aeatEditTabPanel = new JPanel();
        aeatEditTabPanel.setLayout(new BoxLayout(aeatEditTabPanel, BoxLayout.Y_AXIS));

        this.messagePanel = new MessagePanel(buttonActionListener);

        this.aeaPanel1 = new AeaPanel(buttonActionListener);
        aeatEditTabPanel.add(aeaPanel1);
        this.timePanel1 = new TimePanel(buttonActionListener);
        aeatEditTabPanel.add(timePanel1);
        this.eventCodePanel1 = new EventCodePanel(buttonActionListener);
        aeatEditTabPanel.add(eventCodePanel1);
        this.locationPanel1 = new LocationPanel(buttonActionListener);
        aeatEditTabPanel.add(locationPanel1);
        this.textPanel1 = new TextPanel(buttonActionListener);
        aeatEditTabPanel.add(textPanel1);
        this.liveMediaPanel1 = new LiveMediaPanel(buttonActionListener);
        aeatEditTabPanel.add(liveMediaPanel1);
        this.mediaPanel1 = new MediaPanel(buttonActionListener);
        aeatEditTabPanel.add(mediaPanel1);

        JScrollPane scrollPane = new JScrollPane(aeatEditTabPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));

        returnPanel.add(messagePanel);
        returnPanel.add(scrollPane);

        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(" 2013440041 / djjproject", JLabel.LEFT);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(new Dimension(400, 40));

        box.add(label);
        box.add(Box.createHorizontalGlue());

        returnPanel.add(box);

        // 상기 코드는 configPanel 과 동일함
        return returnPanel;
    }

// Effective 와 Expired 의 데이터 값은 XMLGregorianCalendar 의 형식 임으로 String 형식을 해당 형식으로 컨버팅 하는 것
// 스트링을 받아서 XMLGregorianCalendar 형식을 리턴하는 메소드
// stringToXMLGregorianCalendar()
// 입력 : 시간값 (String)
// 출력 : 형이 변환된 시간값(XMLGregorianCalendar)
// 부수효과 : 없음
    private XMLGregorianCalendar stringToXMLGregorianCalendar(String s) {
        XMLGregorianCalendar result = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = simpleDateFormat.parse(s);
            GregorianCalendar gregorianCalendar;
            gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        } catch (ParseException ex) {
            Logger.getLogger(Frame.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Frame.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Publisher 에서 loadAeat() 를 콜하면 동작하는 함수
    // 각 패널에 loadAeat() 함수를 콜을 하고 aeat 의 값을 읽어서 화면에 출력하게 되어있다.
    // loadAeat()
    // 입력 : XML 파일에서 언마샬링 된 aeat 변수 (AEATType)
    // 출력 : 없음
    // 부수효과 : 각 패널의 loadAeat() 를 호출하여 aeat 변수를 넘겨준다.
    public void loadAeat(AEATType aeat) {
        aeaPanel.loadAeat(aeat);
        timePanel.loadAeat(aeat);
        eventCodePanel.loadAeat(aeat);
        locationPanel.loadAeat(aeat);
        textPanel.clear();
        textPanel.loadAeat(aeat);
        liveMediaPanel.loadAeat(aeat);
        mediaPanel.loadAeat(aeat);
        // 7개 패널에 대해서 동일 작업 진행
    }

    public void loadAeatReceived(AEATType aeat) {
        aeaPanel1.loadAeat(aeat);
        timePanel1.loadAeat(aeat);
        eventCodePanel1.loadAeat(aeat);
        locationPanel1.loadAeat(aeat);

        // 아래 텍스트 패널은 로드 할 때마다 사이즈가 변함으로 클리어한 다음에 로드해야한다.
        textPanel1.clear();
        textPanel1.loadAeat(aeat);
        liveMediaPanel1.loadAeat(aeat);
        mediaPanel1.loadAeat(aeat);
        // 7개 패널에 대해서 동일 작업 진행
    }

    // 각 패널에 입력되어 있는 값들을 String 으로 가져와서 aeat 에 추가한다.
    // 그리고 마지막으로 aeat 를 리턴하도록 되어 있다.
    // getAeat()
    // 입력 : 없음
    // 출력 : aeat 변수에 패널들의 데이터를 추가하여 리턴한다. (AEATType)
    // 부수효과 : 없음
    public AEATType getAeat() {
        // AeaPanel
        AEATType aeat = new AEATType();
        AEAType aea = new AEAType();
        // 차후에 마샬링을 위한 변수 aeat , JAXB 바인딩에서 자동으로 생성된 AEAType 의 aea 변수 생성
        // aeat 에 아래의 데이터를 추가하려면 AEAType 형 변수를 필요로 한다.
        aea.setAeaId(aeaPanel.getAeaId());
        aea.setIssuer(aeaPanel.getIssuer());
        aea.setAudience(AudienceType.fromValue(aeaPanel.getAudience()));
        aea.setAeaType(AEAtypeType.fromValue(aeaPanel.getAeaType()));
        // 상기는 aea.setAeaType 이 AEAtypeType 의 형을 받음으로 선언을 위와 같이 해야한다.
        aea.setRefAEAId(aeaPanel.getRefAEAId());
        aea.setPriority(Short.valueOf(aeaPanel.getPriority()));
        // Priority 의 경우 Short 형 변수가 들어가야함으로 String 을 Short 로 컨버팅하여 넘겨준다.
        String str = aeaPanel.isWakeup();
        Boolean bool;
        if (str.equals("true")) {
            bool = true;
        } else {
            bool = false;
        }
        aea.setWakeup(bool);

        // TimePanel
        HeaderType header = new HeaderType();
        // 헤더에 있는 값을 추가하기 위해서 HeaderType 변수를 하나 선언한다.
        header.setEffective(stringToXMLGregorianCalendar(timePanel.getEffective()));
        header.setExpires(stringToXMLGregorianCalendar(timePanel.getExpires()));
        // stringToXMLGregorianCalendar 함수를 호출하여 String을 XMLCalendar 형식으로 변환하여 header 에 저장한다.

        // Event Code Panel
        TypeType eventCode = new TypeType();
        eventCode.setValue(eventCodePanel.getEventCode());
        header.setEventCode(eventCode);
        // eventCode 는 TypeType 형을 받음으로 eventCode 라는 변수를 하나 선언하여 setValue() 메소드로 초기화를 한다.
        // 다음으로 header.setEventCode() 를 통해서 header 변수에 추가한다.
        header.getEventCode().setType(eventCodePanel.getType());

        LangType eventDesc = new LangType();
        eventDesc.setValue(eventCodePanel.getEventDesc());
        header.getEventDesc().add(0, eventDesc);
        // eventDesc 는 실제로 LangType 변수를 받게 되어 있고 추가적으로 ArrayList 를 활용해야 한다.
        // 그래서 차후에 헤더에 추가할 경우 add(index,element) 로 0번째 배열을 추가하는 방식으로 해야한다.

        header.getEventDesc().get(0).setLang(eventCodePanel.getLang());
        // 나머지 코드는 바로 String 으로 적용이 가능함

        // Location Panel
        LocationType locationType = new LocationType();
        header.getLocation().add(0, locationType);
        // 헤더의 로케이션도 ArrayList 라서 add(index,element) 로 전달해야한다. 그래서 LocationType 변수를 하나 생성하였고 
        // 이 부분은 그냥 Dummy 로 배열을 하나 생성해 주는 역할만 한다.

        header.getLocation().get(0).setValue(locationPanel.getLocate());
        header.getLocation().get(0).setType(LocationTypeType.fromValue(locationPanel.getType()));
        // 상기에서 어레이를 하나 생성하였고 그 어레이에 setValue() 하는 부분이다.
        // setType 은 LocationTypeType 형을 받음으로 선언을 한 다음 fromValue() 를 통해 전달한다.

        // Text Panel
        LangType aeaText = new LangType();
        aea.getAEAText().add(0, aeaText);
        // 해당도 마찬가지로 배열 구조로 되어 있어서 add(index,element) 로 작성함.
        // element 타입은 LangType 으로 초기화는 setValue() 를 통해서 한다.
        aea.getAEAText().get(0).setValue(textPanel.getAEAText1());
        aea.getAEAText().get(0).setLang(textPanel.getLang1());
        // 상기에서 배열 하나를 추가하였음으로 get(0) 에서 setLang() 으로 초기화 한다.
        // 일단 위 0 번 인덱스는 무조껀 존재한다고 가정하고 위는 그대로 둔다.

        // 아래 부터는 Text 의 존재 유무에 따라 aeat 에 추가하는 작업을 진행한다.
        if (textPanel.getAEAText2().length() > 0) {
            LangType aeaText1 = new LangType(); // 인덱스에 추가하기 위한 더비 LangType 을 선언한다.
            aea.getAEAText().add(1, aeaText1);  // 인덱스 1에 넣는다.
            aea.getAEAText().get(1).setValue(textPanel.getAEAText2());  // 텍스트 패널의 텍스트2 를 aea 에 넣는다.
            aea.getAEAText().get(1).setLang(textPanel.getLang2());      // 텍스트 패널의 lang2 를 aea 에 넣는다.
        }

        // 이하 상기와 동일
        if (textPanel.getAEAText3().length() > 0) {
            LangType aeaText2 = new LangType();
            aea.getAEAText().add(2, aeaText2);
            aea.getAEAText().get(2).setValue(textPanel.getAEAText3());
            aea.getAEAText().get(2).setLang(textPanel.getLang3());
        }

        if (textPanel.getAEAText4().length() > 0) {
            LangType aeaText3 = new LangType();
            aea.getAEAText().add(3, aeaText3);
            aea.getAEAText().get(3).setValue(textPanel.getAEAText4());
            aea.getAEAText().get(3).setLang(textPanel.getLang4());
        }

        if (textPanel.getAEAText5().length() > 0) {
            LangType aeaText4 = new LangType();
            aea.getAEAText().add(4, aeaText4);
            aea.getAEAText().get(4).setValue(textPanel.getAEAText5());
            aea.getAEAText().get(4).setLang(textPanel.getLang5());
        }

        // Live Media Panel
        LiveMediaType liveMedia = new LiveMediaType();
        liveMedia.getBsid().add(0, Integer.parseInt(liveMediaPanel.getBsid()));
        liveMedia.setServiceId(Integer.parseInt(liveMediaPanel.getServiceId()));
        // bsid 를 전달하기 위해서 해당도 ArrayList 이기 때문에 add(index,elemnet) 를 사용한다.
        // 그리고 INT 형을 받기 때문에 String 을 Integer 로 변환한다.
        // Service ID 도 마찬가지 이다.

        LangType serviceName = new LangType();
        liveMedia.getServiceName().add(0, serviceName);
        // service Name 도 상기와 마찬가지이다.

        liveMedia.getServiceName().get(0).setValue(liveMediaPanel.getServiceName());
        liveMedia.getServiceName().get(0).setLang(liveMediaPanel.getLang());
        // 상기 코드에서 ServiceName 에 어레이 0 번을 생성했음으로 해당 어레이에 setValue(), setLang() 을 통해 값을 저장한다.

        // Media Panel
        MediaType media = new MediaType();
        // 미디어 파넬을 사용하기 위해서 MediaType 형 변수가 필요하다.
        media.setLang((mediaPanel.getLang()));
        media.setMediaDesc(mediaPanel.getMediaDesc());
        media.setMediaType(MediaTypeType.fromValue(mediaPanel.getMediaType()));
        media.setUrl(mediaPanel.getUrl());
        media.setAlternateUrl(mediaPanel.getAlternateUrl());
        media.setContentType(mediaPanel.getContentType());
        media.setContentLength((new BigInteger(mediaPanel.getContentLength())));
        media.setMediaAssoc(mediaPanel.getMediaAssoc());
        // 다른것과 다르게 이는 모두 set 메소드를 통해서 String 을 넣도록 되어 있다.
        // ContentLength 는 BigInteger 이다.

        aea.setLiveMedia(liveMedia);
        // liveMedia변수를 넣어 setLiveMedia() 메소드를 호출한다.
        aea.setHeader(header);
        // 마찬가지이다.
        aea.getMedia().add(media);
        // 미디어는 getMedia().add(media) 로 처리한다.
        aeat.getAEA().add(aea);
        // getAEA().add(aea) 로 ArrayList 0 을 추가한다.
        return aeat;
    }

    // config 패널에서 데이터를 가져오는 부분
    public String getConfigSendServer() {
        return configPanel.getSendServer();
    }

    public String getConfigSendGroup() {
        return configPanel.getSendGroup();
    }

    public String getConfigReceiveServer() {
        return configPanel.getReceiveServer();
    }

    public String getConfigReceiveGroup() {
        return configPanel.getReceiveGroup();
    }
    
    // 현재 선택된 파일 이름을 Publisher 에 전달하기 위한 함수
    public String getLoadFile() {
        return loadFile.getSelectedItem().toString();
    }
    
    // 현재 사용자가 입력한 파일 이름을 Publisher 에 전달하기 위한 함수
    public String getSaveFile() {
        return saveFile.getText();
    }
    
    // 세이브 할 경우 파일리스트가 갱신 되어야 함으로 작성한 함수
    // 부수효과 : 파일리스트 갱신
    public void refreshFileList() {
        String path = "xml/";
        File dirFile=new File(path);
        File []fileList=dirFile.listFiles();
        List<String> AEATypeItems = new ArrayList();
        for(File tempFile : fileList){
            String tempPath=tempFile.getParent();
            String tempFileName=tempFile.getName();
            AEATypeItems.add(tempFileName);  
        }
        ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(AEATypeItems.toArray());
        // 상기코드는 initButtonPanel 과 동일하다.
        
        // loadFile 에 넣어 놓은 객체를 액세스 하여 수정한다. (일종의 포인터 개념)
        loadFile.setModel(comboBoxModelAEAType);
    }
    
    // 하위 함수는 config 패널의 값을 publisher 로 전달하기 위함이다.
    public boolean getSendConfig() {
        return configPanel.getBroadCast();
    }
    
    public boolean getReceiveConfig() {
        return configPanel.getReceiveMethod();
    }

}
