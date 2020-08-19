---
title: Selenium 사이트와 문서에 기여하기
disableToc: true
---

Selenium은 거대한 소프트웨어 프로젝트로, 사이트와 문서는 작동 원리를 이해하고 잠재력을 효과적으로 활용하는 데 중요한 역할을 합니다.

이 프로젝트는 Selenium 사이트와 문서를 담고 있습니다.
여기엔 Selenium을 효과적으로 사용하기 위한 업데이트 정보를 제공하고, 어떻게 참여하고 기여할 수 있는지에 대한 지속적인 노력이 담겨 있습니다.

사이트와 문서에 기여하기 위한 방법은 아래 기술된 과정을 따릅니다. 여러분은 익숙해질 때 까지
[더 보기]({{< ref "/introduction/about_this_documentation.ko.md" >}})를 충분한 시간을 들여 읽어보시고 바랍니다.

---

Selenium은 여러분의 기여를 환영합니다. 기여하는 방법은 여러 가지가 있습니다:

## 이슈 제기하기

새로운 이슈를 제기하거나 기존 이슈에 코멘트를 남길 때, 논의 내용이 Selenium 소프트웨어, 사이트 또는 문서의 구체적인 기술적인 문제와 관련이 있는지 확인해주시기 바랍니다.

Selenium의 모든 구성요소는 빠르게 변하기 때문에, 문서 내용이 업데이트 전의 내용일 수도 있습니다.
이런 경우를 발견하신다면, 주저하지 마시고 이슈를 제기해주시기 바랍니다.
문서 내용을 직접 최신화하실 수 있다면, 변경 내용에 대한 pull request를 해주시기 바랍니다.

만약 여러분이 발견한 것이 이슈인지 아닌지 헷갈리신다면,
다음의 커뮤니케이션 채널을 통해 질문해주시기 바랍니다.
https://selenium.dev/support.

## 기여하기

Selenium 프로젝트는 새로운 기여자를 환영합니다. 꾸준히 중요하고 가치가 높은 기여를 해주신 기여자는 _Committers_ 가 되어 프로젝트에 커밋 접근 권한을 갖게 됩니다.

아래 가이드는 여러분에게 기여하는 과정을 안내합니다.

### 1 단계: Fork

프로젝트를 [Github](https://github.com/seleniumhq/seleniumhq.github.io) 에서 Fork 하시고,
로컬에서 확인하세요.

```shell
% git clone git@github.com:seleniumhq/seleniumhq.github.io.git
% cd seleniumhq.github.io
```

#### 의존성: Hugo

우리는 [Hugo](https://gohugo.io/) 를 사용해 사이트와 문서를 빌드하고 렌더링합니다.
바뀐 내용을 커밋하기 전에 로컬에서 확인하기 위해,
[Hugo 설치](https://gohugo.io/getting-started/installing/) 하고, 익숙해지셨다면 [로컬 서버 실행](https://gohugo.io/getting-started/usage/#livereload)
를 통해 사이트를 렌더링해 확인해주시기 바랍니다. 자세한 설명은 다음 단계에서 보실 수 있습니다.

### 2 단계: Branch

기능을 추가한 branch를 생성하고 개발을 시작하세요:

```shell
% git checkout -b my-feature-branch
```

우리는 HEAD-based development 지향하고 있어, 모든 변경사항은 trunk에 직접 적용됩니다.

### 3 단계: Make changes

Repository는 사이트와 문서의 서로 다른 2개의 Hugo 프로젝트로 구성되어 있습니다.
사이트를 변경하시려면 `site_source_files` 디렉토리에서 작업해주시기 바랍니다.
변경된 모습을 미리 보시려면 사이트의 루트 디렉토리에서 `hugo server` 를 실행하시면 됩니다.

```shell
% cd site_source_files
% hugo server
```

문서를 변경하시려면, `docs_source_files` 디렉토리로 변경하시기 바랍니다. 

```shell
% cd docs_source_files
% hugo server
```

문서는 다양한 언어로 번역되어 있으며, 번역은 영문에 기반을 두고 있습니다.
문서를 변경할 때는, 다른 모든 번역 문서도 변경해야 하는 점을 **반드시 유의** 해주시기 바랍니다.
이는 어떤 부분을 변경하느냐에 따라 달라질 수 있습니다, 예를 들어:
 
* 만약 여러분이 `browser_manipulation.en.md` 파일에 코드 예제를 추가했다면,
`browser_manipulation.es.md`, `browser_manipulation.ef.md`, 
`browser_manipulation.ja.md`, 그리고 다른 모든 번역 문서에도 추가해주셔야 합니다.
* 만약 여러분이 번역 문서의 개선사항을 발견했다면,
해당 번역 문서만 변경하시면 됩니다.
* 만약 여러분이 새로운 언어의 번역을 추가하고자 한다면, 알맞은 접미사 파일명의 문서를 추가해주셔야 합니다. PR을 할 때, 모든 문서가 번역되어있을 필요는 없습니다, 번역은 때마다 반복적으로 하셔도 됩니다. `config.toml` 파일에 필요한 설정값을 잊지 마시고 확인해주시기 바랍니다.
* 만약 여러분이 영문 문서에 내용을 바꿨다면, 다른 번역 문서의 같은 부분에 변경된 내용(영어로)을 넣어주시기고, 문서의 최상단부에 아래와 같은 알림을 추가해주시기 바랍니다.


```
{{%/* notice info */%}}
<i class="fas fa-language"></i> Page being translated from 
English to {LANGUAGE}. Do you speak {LANGUAGE}? Help us to translate
it by sending us pull requests!
{{%/* /notice */%}}
```

### 4 단계: Commit

먼저 git에 이름과 이메일 주소를 입력하시기 바랍니다:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**적절한 커밋 메시지를 쓰는 것이 중요합니다.** 커밋 메세지는
무엇이, 왜 변경되었는지 설명해야 하며, 수정된 이슈들을 언급해야 합니다(만약에 있다면).
작성할 때 이 가이드라인들을 따르시기 바랍니다.

1. 첫 줄은 50자 내외 혹은 그 이하이어야 하며 변경 사항의 짧은 설명을 포함해야 합니다.  
2. 두 번째 줄은 빈 줄로 두시기 바랍니다. 
3. 다른 모든 줄은 72자 내로 작성하십시오. 
4. _N_ 이 수정사항을 커밋한 이슈 번호인, `Fixes #N`을 포함하십시오.  


좋은 커밋 메세지는 이렇습니다:

```text
explain commit normatively in one line

Body of commit message is a few lines of text, explaining things
in more detail, possibly giving some background about the issue
being fixed, etc.

The body of the commit message can be several paragraphs, and
please do proper word-wrap and keep columns shorter than about
72 characters or so. That way `git log` will show things
nicely even when it is indented.

Fixes #141
```

첫 줄은 사람들이 `git shortlog` 나 `git log --oneline` 을 실행하면 보는 것이기 때문에 의미있어야합니다. 

### 5 단계 : Rebase

작업을 동기화하려면 `git rebase`를 사용하십시오. (`git merge`가 아니라)

```shell
% git fetch upstream
% git rebase upstream/trunk
```

### 6 단계: 테스트

항상 로컬 서버를 실행하는 것을 기억하십시오. 그러면 당신의 변경 사항들이 아무것도 안 건드리고 안전합니다. 

### 7 단계 : Push

```shell
% git push origin my-feature-branch
```

https://github.com/yourusername/seleniumhq.github.io.git 에서  _Pull Request_ 를 누르고 형식을 채우시기 바랍니다. **CLA에 서명했는지 나타내주시길 바랍니다** (7 단계 참고)

Pull request 는 몇 일 내에 확인됩니다. 주소에 코멘트들이 있다면, 새로운 커밋에 변경 사항(가급적
[fixups](http://git-scm.com/docs/git-commit))을 적용하고 같은 branch 에 push 해주시기 바랍니다. 

### 8 단계 : Integration

코드 확인이 완료되었으면, committer는 당신의 PR을 저장소의 trunk branch에 통합할 것입니다. 왜냐하면 우리는 trunk branch에 순차적인 이력을 유지하고 싶기 때문에, 우리는 당신의 branch 이력은 squash나 rebase 할 것입니다. 

## 커뮤니케이션


프로젝트 기여자들과 커뮤니티와 어떻게 소통할지에 대한 자세한 사항은 https://selenium.dev/support 에서 찾을 수 있습니다. 

