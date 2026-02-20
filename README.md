# 로또 프로그램

## 기능 요구 사항

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급한다.
- 로또 1장의 가격은 1000원이다.
- 구입 금액에서 1000원으로 나눈 몫만큼 로또를 발급하며 나머지는 무시한다.
- 예: 2500원 입력 시 2장 구매
- 발급된 로또는 6개의 숫자로 구성되며 숫자 범위는 1~45이다.
- 로또 숫자는 중복될 수 없고, 출력 시 오름차순으로 정렬한다.
- 수동으로 구매할 로또 수를 입력받고, 해당 수만큼 번호를 직접 입력한다.
- 수동 구매 수를 제외한 나머지 로또는 자동으로 발급한다.
- 지난 주 당첨 번호 6개와 보너스 볼 1개를 입력받는다.
- 당첨 번호는 1~45 범위의 중복 없는 6개 숫자여야 한다.
- 보너스 볼은 1~45 범위의 숫자이며 당첨 번호와 중복될 수 없다.
- 발급된 모든 로또에 대해 당첨 통계를 집계한다.
- 당첨 등수는 아래 기준으로 판단한다.
    - 3개 일치: 5,000원
    - 4개 일치: 50,000원
    - 5개 일치: 1,500,000원
    - 5개 일치 + 보너스 볼: 30,000,000원
    - 6개 일치: 2,000,000,000원
- 총 수익률은 `총 당첨금 / 구입 금액`으로 계산해 출력한다.

## 입출력 예시

```text
구입금액을 입력해 주세요.
14000

수동으로 구매할 로또 수를 입력해 주세요.
3

수동으로 구매할 번호를 입력해 주세요.
8, 21, 23, 41, 42, 43
3, 5, 11, 16, 32, 38
7, 11, 16, 35, 36, 44

수동으로 3장, 자동으로 11개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

## 프로젝트 구조 설계

```text
src/main/java
  lotto
    LottoApplication
    controller
      LottoController
    domain
      Lotto
      LottoMachine
      LottoNumber
      LottoPurchasePolicy
      LottoResult
      LottoStatistics
      LottoGenerator
      ManualLottoGenerator
      RandomLottoGenerator
      WinningNumbers
    view
      InputView
      OutputView

src/test/java
  lotto
    LottoIntegrationTest
    controller
      LottoControllerTest
    domain
      LottoMachineTest
      LottoNumberTest
      LottoPurchasePolicyTest
      LottoStatisticsTest
      LottoGeneratorTest
      LottoResultTest
      LottoTest
      WinningNumbersTest
```

## 클래스 책임

### lotto

- `LottoApplication`: 프로그램 시작점, 컨트롤러 실행

### lotto.controller

- `LottoController`: 입력-도메인-출력 흐름 조율

### lotto.view

- `InputView`: 구매 금액, 당첨 번호, 보너스 볼 입력 처리
- `OutputView`: 로또 목록, 통계, 수익률 출력 처리

### lotto.domain

- `Lotto`: 로또 한 장의 숫자 집합, 유효성 검증 및 자동 생성
- `LottoMachine`: 여러 생성기를 실행해 로또 목록을 조합하는 컴포지트
- `LottoNumber`: 로또 번호(1~45) 값 객체
- `LottoPurchasePolicy`: 구입 금액/수동 개수 기반 랜덤 발급 개수 계산 정책
- `LottoResult`: 당첨 등급과 상금 정의
- `LottoStatistics`: 전체 당첨 통계 및 총 상금 계산
- `LottoGenerator`: 로또 생성 전략 인터페이스
- `ManualLottoGenerator`: 수동 번호 기반 생성
- `RandomLottoGenerator`: 랜덤 로또 생성
- `WinningNumbers`: 당첨 번호 + 보너스 볼 보관 및 검증

## 프로젝트 구현 계획

1. 요구사항과 입출력 예시를 기준으로 기능 흐름을 정리한다.
2. 도메인 모델과 패키지 구조를 확정하고 골격 코드를 만든다.
3. 로또 번호 생성/정렬/중복/범위 검증 테스트를 작성한 뒤 구현한다.
4. 로또 구매 기능 테스트를 작성한 뒤 구현한다.
5. 당첨 번호/보너스 볼 입력 검증 기능 테스트를 작성한 뒤 구현한다.
6. 결과 판단/집계 기능 테스트를 작성한 뒤 구현한다.
7. 총 당첨금/수익률 계산과 출력 형식 테스트를 작성한 뒤 구현한다.
8. 입력-도메인-출력 통합 테스트를 작성한 뒤 전체 흐름을 연결한다.
