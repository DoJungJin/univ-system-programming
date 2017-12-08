/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013440041;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author djj94
 */
// 버튼 리스너 생성 , Publisher 에서 가져다 씀으로 Publisher 도 선언한다.
public class MyButtonActionListener implements ActionListener {

    private Publisher publisher;

    public MyButtonActionListener(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 커맨드에 따라서 실행하는 함수 지정
        String command = e.getActionCommand();
        switch (command) {
            case "Load":
                publisher.onClickedLoadButton();
                break;
            case "Save":
                publisher.onClickedSaveButton();
                break;
            case "Send":
                publisher.onClickedSendButton();
                break;
            case "Receive":
                publisher.onClickedReceiveButton();
                break;
            default:
                break;
        }
    }
}
