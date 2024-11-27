package study.lotto

import study.lotto.model.Lotto
import study.lotto.model.LottoStats
import study.lotto.model.Lottos
import kotlin.math.floor

/**
 * @author 이상준
 */
class LottoService {
    fun buyLotto(money: Int): Lottos {
        require(money >= LOTTO_PRICE) { "로또 구입금액은 최소 ${LOTTO_PRICE}원입니다." }
        Lottos().apply {
            repeat(money / LOTTO_PRICE) {
                addLotto(Lotto(randomLotto()))
            }
        }.let { return it }
    }

    fun profitLotto(
        lottoStats: LottoStats,
        money: Int,
    ): Double {
        val total = lottoStats.getStat().sumOf { it.rank.amount * it.count }
        return (total.toDouble() / (money * 10)).let { floor(it * 100) / 100 }
    }

    private fun randomLotto(): Set<Int> {
        return (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).shuffled().take(LOTTO_NUMBER_COUNT).sorted().toSet()
    }

    companion object {
        const val LOTTO_PRICE = 1000
        const val LOTTO_NUMBER_COUNT = 6
        const val MIN_LOTTO_NUMBER = 1
        const val MAX_LOTTO_NUMBER = 45
    }
}