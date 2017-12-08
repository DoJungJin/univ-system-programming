### 소프트웨어프로그래밍 실습 (2017 in UOS)
코드는 서울시립대학교의 소프트웨어프로그래밍 실습에서 프로그래밍 하고 있는 것들을 업로드 하고 있습니다.

### 코드 설명
굉장히 대충 만들었습니다. 저는 자바에 대한 완벽한 이해도 없으며, 남의 것을 읽어서 붙여넣는 기술만으로 상기 코드를 작성했습니다.
JAVA 를 기반으로 하고 있으며 프로젝트를 여실 경우 NetBeans 를 사용하셔서 여시면 되겠습니다.

### 실행 영상 및 사진 및 설명
- 초기 실행 사진
- 간단한 콤보박스와 라디오 버튼이 구현되어 있다.
- 파일경로에서 원하는 파일을 선택 후 로딩이 가능하다.
- 저장을 할 경우 이름을 지정 가능하다.
- Send 버튼으로 메시지를 보낼 수 있다.

![Alt text](https://github.com/djjproject/univ-system-programming/blob/master/img/1.png?raw=true)

- 메시지 샘플을 로드하면 하기와 같이 데이터가 채워진다.

![Alt text](https://github.com/djjproject/univ-system-programming/blob/master/img/2.png?raw=true)

- Text 의 경우 최대 5개 엘리먼트를 추가할 수 있도록 구현하였다.

![Alt text](https://github.com/djjproject/univ-system-programming/blob/master/img/3.png?raw=true)

- Recive 버튼을 누르면 메시지를 받는 대기상태로 전환된다.
- 이때 메시지가 오면 소리와 푸쉬 알림으로 알려준다.

![Alt text](https://github.com/djjproject/univ-system-programming/blob/master/img/4.png?raw=true)

- 설정 패널로 ActiveMQ 서버와 브로커를 설정할 수 있도록 하였다.
- 보내고 받는 곳 모두 설정이 가능하다.
- 추가적으로 보낼 때 1:1 로 전송할 것인지 1:다 로 전송할 것인지 선택이 가능하다.
- 만약에 보내는 쪽이 1:다 로 보낸다면 받는 쪽도 1:다 로 설정을 해야 메시지가 수신 가능하다.

![Alt text](https://github.com/djjproject/univ-system-programming/blob/master/img/5.png?raw=true)



- https://youtu.be/BeyCrjRCFVs

### 참고한 자료들
- https://github.com/comkeen
- https://www.google.com/
- https://github.com/doorBW/AEATsoftware

### 작성자 정보
- 도정진 (djjproject)
- http://www.djjproject.com/ (심신 미약자는 들어가지 마세요)
- http://blog.djjproject.com/
- KAKAO : djj9405
- EMAIL : djj9404@gmail.com
