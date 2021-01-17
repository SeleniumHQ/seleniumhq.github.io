---
title: "Remote WebDriver"
chapter: true
weight: 6
---

# Remote WebDriver

로컬 환경에서와 같은 방법으로 WebDriver를 원격으로 사용할 수도 있습니다.
중요한 차이점은 다른 장치에서 작업을 수행하기 위해 원격 WebDriver가 구성되어있어야 한다는 점입니다.

원격 WebDrive는 두 가지 요소로 이루어져 있습니다: 클라이언트 그리고 서버.
클라이언트는 당신의 WebDriver teset이며 서버는 간단한 Java servelet의 일종이고 JEE app 서버에 호스팅될 수 있습니다.
