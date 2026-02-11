package level1.domain;

public class Constant {

    // TODO: price 는 누가 가지고 있어야 하는가?
    public static final int LOTTERY_PRICE = 1_000;

    /*
    유틸성 클래스는 인스턴스화를 방지하기 위해 private 생성자를 추가하는 것이좋습니다.

    --> private 생성자 구성
     */
    private Constant() {
    }
}
