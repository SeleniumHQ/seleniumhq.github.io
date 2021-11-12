---
title: "Guidelines and recommendations"
linkTitle: "Guidelines"
weight: 7
description: >
  Selenium 프로젝트의 테스트에 대한 일부 지침과 권고사항.
aliases: ["/documentation/ko/guidelines_and_recommendations/"]  
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Korean. Do you speak Korean? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

"모범 사례"에 대한 참고 사항: 본 설명서에서는 "모범 사례"라는 문구를 의도적으로 피했습니다. 
모든 상황에 맞는 접근법은 없습니다. 우리는 "지침과 권고"라는 아이디어를 선호하므로,
특정 환경에서 어떤 접근 방식이 적합한지 신중하게 결정하시기 바랍니다.

기능 테스트는 여러 가지 이유로 올바르게 수행하기가 어렵습니다. 애플리케이션 상태, 복잡성 및 의존성이 
테스트를 충분히 어렵게 하지 않는 것처럼 브라우저(특히 크로스 브라우저 비호환성)를 다루는 것은 좋은
테스트 작성을 어렵게 만듭니다.

Selenium은 기능적인 사용자 상호 작용을 쉽게 할 수 있는 도구를 제공하지만 잘 구성된 테스트 제품군을
작성하는 데는 도움이 되지 않습니다. 이 장에서는 기능성 웹 페이지 자동화에 접근하는 방법에 대한 조언, 
지침 및 권장 사항을 제공합니다.

이 장에서는 셀레늄 사용자들 중 수년에 걸쳐 성공한 것으로 입증된 많은 소프트웨어 설계 패턴을 기록합니다.
