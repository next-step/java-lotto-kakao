    package lotto.domain;

    import java.util.Objects;

    public class LottoNumber {

        public static final int MIN_LOTTO_NUMBER = 1;
        public static final int MAX_LOTTO_NUMBER = 45;
        public static final String RANGE_FAIL_MSG = "로또 번호의 범위는 1 ~ 45사이어야 합니다.";

        private static final LottoNumber[] CACHE = new LottoNumber[MAX_LOTTO_NUMBER + 1];

        static  {
            for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
                CACHE[i] = new LottoNumber(i);
            }
        }

        private final int value;

        public LottoNumber(int value) {
            validate(value);
            this.value = value;
        }

        public static void validate(int value) {
            if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(RANGE_FAIL_MSG);
            }
        }

        public int getValue() {
            return value;
        }

        public static LottoNumber of(int value) {
            validate(value);
            return CACHE[value];
        }


        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            LottoNumber that = (LottoNumber) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }
