# 개요  
2019-2 빅데이터 캡스톤 디자인 Hallym Assistant 프로젝트 관리 페이지

# 팀 소개
- 팀명 : Hallym Assistant  
- 팀원 및 역할
  - 박석주 : (라즈베리파이, 파이썬)을 결합한 키오스크 개발
  - 임형규 : Danbee 인텐트 구성, DB 관리
  - 정연재 : 안드로이드 앱 개발, API 연결성 검토
  - 최정명 : 프로젝트 문서관리, (라즈베리파이, 파이썬)을 결합한 키오스크 개발

# 주제 및 목표
- 프로젝트 명 : Hallym Assistant  
- 한림대학교 커뮤니티에 올라오는 교내 질문들을 해결
- 기대효과
  - 질문 데이터 축적으로 추론율 증가 그것으로 인한 만족도 증가
  - 이전 처럼 질문 후 대기하는 것이 아닌 정확한 답변을 빠른 시간내에 해결 하여 시간을 절약
  - 커뮤니티에 올라오는 같은 질문에 대한 불편한 감소
  - 커뮤니티 내 "Hallym Assistant에서 검색 해보세요" 같은 추천 답변에 의한 사용자 수 증가.

# 기능
- 공통기능 
  - 건물위치, 서류제출 장소, 행정실 전화번호, 도서관 정보, 통학,셔틀 버스 정보, 학교의 이벤트 등 질문에 대한 답변을 수행
- [안드로이드 앱](https://github.com/JeongMyeong/Capstone-Design-6-/tree/master/AndroidApp)
  - 자체 제작 앱으로 [공통기능]을 수행
  - 가장 빠른 응답속도를 가짐
- 라즈베리파이(키오스크)
  - 사람의 말을 음성인식을 통해 질문을 입력하여 스피커를 통해 음성으로 [공통기능]을 수행.
  - 라즈베리파이와 결합한 터치스크린으로 터치를 통하여 작동할 수 있음.
- [텔레그램](https://github.com/JeongMyeong/Capstone-Design-6-/tree/master/Telegram/telegram.md)
  - DanbeeAI에서 제공해주는 채널기능으로 [공통기능]을 수행함
  
![pic1](https://github.com/JeongMyeong/Capstone-Design-6-/blob/master/pic/%EA%B7%B8%EB%A6%BC1.png)


# 프로젝트 전체 구성
![pic2](https://github.com/JeongMyeong/Capstone-Design-6-/blob/master/pic/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%20%EA%B5%AC%EC%84%B1.png)
