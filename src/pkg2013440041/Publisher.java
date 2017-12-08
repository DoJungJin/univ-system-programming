/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013440041;

import aeat.AEATType;
import aeat.AEAType;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.camel.converter.IOConverter;

/**
 *
 * @author djj94
 */
public class Publisher {

    private Frame frame;
    // Frame 객체 생성
    public static final String AEAT_EXAM = "xml/AEAT-Example-20170920.xml";
    // AEAT_EXAM 경로 생성

    public Publisher() {
        MyButtonActionListener buttonActionListener = new MyButtonActionListener(this);
        frame = new Frame(buttonActionListener);
        // 버튼 리스너를 만들고 Frame 에 추가한다.

    }

    // 마샬링 코드 (그냥 외우는게 좋다)
    // 메소드 명 : aeatMarshalling()
    // 입력 : aeat 변수 루트객체 (AEATType) , 파일 경로(String)
    // 출력 : 없음
    // 부수효과 : 파일 경로에 aeat 값이 XML 로 마샬링 되어 저장된다.
    private void aeatMarshalling(AEATType aeat, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(aeat, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // 언마샬링 코드 (그냥 외우는게 좋다)
    // 메소드 명 : aeatUnmarshalling()
    // 입력 : XML 파일 경로(String)
    // 출력 : XML 파일로 부터 읽어온 것을 언마샬링 하여 aeat 로 반환(AEATType)
    // 부수효과 : 없음
    private AEATType aeatUnmarshalling(String path) {
        AEATType aeat = null;
        try {
            File file = new File(path);

            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            aeat = (AEATType) ((JAXBElement) jaxbUnmarshaller.unmarshal(file)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return aeat;
    }

    // 언마샬링 코드 (그냥 외우는게 좋다)
    // 메소드 명 : aeatReceivedDataUnmarshalling()
    // 입력 : raw 한 String ActiveMQ 에서 받아온 String data(String)
    // 출력 : String 으로 부터 읽어온 것을 언마샬링 하여 aeat 로 반환 (AEATType)
    // 부수효과 : 없음
    private AEATType aeatReceivedDataUnmarshalling(String input) {
        AEATType aeat = null;
        try {
            StringReader data = new StringReader(input);
            // 상기 언마샬 코드와 다르게 StringReader 를 통해 파일 저장 작업을 거치지 않고 바로 언마샬링 가능

            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            aeat = (AEATType) ((JAXBElement) jaxbUnmarshaller.unmarshal(data)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return aeat;
    }

    // onClickedLoadButton()
    // 입력 : 없음
    // 출력 : 없음
    // 부수효과 : aeatUnmarshalling() 을 콜하여 aeat 로 초기화한다.
    // 그리고 frame.loadAeat() 메소드를 콜을 하여 aeat 변수를 넘긴다.
    public void onClickedLoadButton() {
        String path = "xml/";
        path = path + (frame.getLoadFile());
        // 파일의 네임을 기본경로에 추가한다.
        AEATType aeat = aeatUnmarshalling(path);
        frame.loadAeat(aeat);
    }

    // onClickedSaaveButton()
    // 입력 : 없음
    // 출력 : 없음
    // 부수효과 : frame.getAeat() 를 콜하여 aeat 변수에 저장한다.
    // 다음으로 aeatMarshalling 을 콜하여 aeat 변수와 경로(String) 을 전달한다.
    public void onClickedSaveButton() {
        String path = "xml/";
        path = path + (frame.getSaveFile());
        AEATType aeat = frame.getAeat();
        aeatMarshalling(aeat, path);
        // 파일의 네임을 기본경로에 추가한다.

        // 파일을 추가하게 되면 리스트를 갱신함으로 refreshFileList 함수를 콜한다.
        frame.refreshFileList();
    }

    // PlaySound()
    // 입력 : wav 파일
    // 출력 : 없음
    // 부수효과 : 알림 사운드 재생
    static void PlaySound(File Sound) {

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {
        }
    }

    // aeatToXml()
    // 입력 : aeat (AEATType)
    // 출력 : aeat 를 raw 텍스트로 바꾼 String 을 리턴 (String)
    public static String aeatToXml(AEATType aeat) {
        String result = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //AEATType 객체를 "path" 경로에 파일로 저장
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(aeat, stringWriter);
            result = stringWriter.toString();
            // String 으로 반환하기 위해서 stringWriter 사용

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    // onClickdedSendButton()
    // 입력 : 없음
    // 출력 : 없음
    // 부수효과 : ActiveMQ 라이브러리를 통해 서버로 전송
    public void onClickedSendButton() {
        String DESTIANTION_EXAM = frame.getConfigSendGroup(); // 데스티네이션 이름
        String MQ_ADDRESS = frame.getConfigSendServer(); // 메시지 브로커 주소
        AEATType aeat = frame.getAeat();
        String text = aeatToXml(aeat);

        try {
            // 1:1 / 1:다 를 구분하여 함수 실행을 달리 한다.
            ActiveMQProducer producer = new ActiveMQProducer(MQ_ADDRESS);
            if (frame.getSendConfig()) {
                producer.sendMessageTo(text, DESTIANTION_EXAM);
            } else {
                producer.sendMessageToMany(text, DESTIANTION_EXAM);
            }
            Thread.sleep(100);
            producer.closeConnection();
        } catch (InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    // onMessage()
    // 입력 : 메시지 내용 (String)
    // 출력 : 없음
    // 부수효과 : aeat 로 언마샬링하여 loadAeatReceived 를 실행
    public void onMessage(String input) {
        AEATType aeat = aeatReceivedDataUnmarshalling(input);
        frame.loadAeatReceived(aeat);

    }

    // soundPlay()
    // 입력 : 없음
    // 출력 : 없음
    // 부수효과 : File 을 생성하고 receive.wav 파일을 읽어서 PlaySound 함수 실행 및 알림 생성
    public void soundPlay() {
        File Clap = new File("receive.wav");
        PlaySound(Clap);
        JOptionPane.showMessageDialog(null, "AEAT 메시지 도착 \n받은 메시지에서 확인해 주세요.", "알림이 도착하였습니다.", JOptionPane.INFORMATION_MESSAGE);
    }

    // onClickdedReceiveButton()
    // 입력 : 없음
    // 출력 : 없음
    // 부수효과 : ActiveMQ 라이브러리를 통해 서버로 부터 메시지를 받아옴
    // 실제로 Close 를 하지 않음으로 한번 눌러놓으면 계속 메시지를 받는 상태가 됨
    public void onClickedReceiveButton() {
        String DESTIANTION_EXAM = frame.getConfigReceiveGroup(); // 데스티네이션 이름
        String MQ_ADDRESS = frame.getConfigReceiveServer(); // 메시지 브로커 주소

        try {
            ActiveMQConsumer consumer = new ActiveMQConsumer(MQ_ADDRESS, this);

            // Set consumer Destination
            // Send Message to Destination
            // 아래 부분은 받는 방식을 1:1 / 1:다 를 선택하기 위해 만들어 졌다.
            if (frame.getReceiveConfig()) {
                consumer.setConsumerDestination(DESTIANTION_EXAM);
            } else {
                consumer.setConsumerDestinationMany(DESTIANTION_EXAM);
            }
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

}
