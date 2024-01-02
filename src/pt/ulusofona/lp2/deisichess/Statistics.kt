package pt.ulusofona.lp2.deisichess

fun getStatsCalculator(statType: StatType): (GameManager) -> ArrayList<String> =
    when (statType) {
        StatType.TOP_5_CAPTURAS -> { gameManager -> top5Capturas(gameManager) }
        StatType.PECAS_MAIS_5_CAPTURAS -> { gameManager -> pecasMais5Capturas(gameManager) }
        StatType.TIPOS_CAPTURADOS -> { gameManager -> tiposCapturados(gameManager) }
        StatType.TOP_5_PONTOS -> { gameManager -> top5Pontos(gameManager) }
        StatType.PECAS_MAIS_BARALHADAS -> { gameManager -> pecasMaisBaralhadas(gameManager) }
    }

fun top5Capturas(gameManager: GameManager): ArrayList<String> {

    return gameManager.gameBoard.getPecasEmJogo().values.sortedByDescending { it.getNumCapturas() }.take(5)
            .map {
            "${it.getNomePeca()} (${if (it.getEquipaPeca() == 20) "BRANCA" else "PRETA"}) fez ${it.getNumCapturas()} capturas"}
            .toCollection(ArrayList())
}
fun top5Pontos(gameManager: GameManager): ArrayList<String> {

    return gameManager.gameBoard.getPecasEmJogo().values.filter { it.getPontosObtidos() > 0 }
            .sortedWith(compareByDescending<Peca> { it.getPontosObtidos() }.thenBy { it.getNomePeca() }).take(5)
            .map {
            "${it.getNomePeca()} (${if (it.getEquipaPeca() == 20) "BRANCA" else "PRETA"}) tem ${it.getPontosObtidos()} pontos" }
            .toCollection(ArrayList())
}
fun pecasMais5Capturas(gameManager: GameManager): ArrayList<String> {

    return gameManager.gameBoard.getPecasEmJogo().values.filter {it.getNumCapturas() > 5}
            .map {
            "${if (it.getEquipaPeca() == 20) "BRANCA" else "PRETA"}:${it.getNomePeca()}:${it.getNumCapturas()}"}
            .toCollection(ArrayList())
}
fun pecasMaisBaralhadas(gameManager: GameManager): ArrayList<String> {

    return gameManager.gameBoard.getPecasEmJogo().values.filter { it.getNrJogadasInvalidas() > 0 }
            .sortedWith(compareByDescending<Peca> { if (it.getNrJogadasValidas() > 0) it.getNrJogadasInvalidas() / it.getNrJogadasValidas() else it.getNrJogadasInvalidas() }).take(3)
            .map {
            "${(it.getEquipaPeca())}:${it.getNomePeca()}:${it.getNrJogadasInvalidas()}:${it.getNrJogadasValidas()}"}
            .toCollection(ArrayList())
}
fun tiposCapturados(gameManager: GameManager): ArrayList<String> {
    return gameManager.gameBoard.getPecasEmJogo().values.filter {it.getEstado() == "capturado"}
            .map {it.getTipoPecaString()}
            .toCollection(ArrayList())
}