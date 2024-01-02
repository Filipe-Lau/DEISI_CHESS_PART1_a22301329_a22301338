package pt.ulusofona.lp2.deisichess

fun getStatsCalculator(a: StatType): (GameManager) -> List<String> {
    return { gameManager -> listOf("Stat1: $gameManager", "Stat2: $gameManager")
    }
}