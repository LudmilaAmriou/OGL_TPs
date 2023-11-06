import java.util.*
var score0 = 0 // initialisation du score à 0
val scanner0 = Scanner(System.`in`)
fun playGame() {

    val phrases = listOf(
            listOf("je", "suis", "un", "étudiant", "en", "informatique"),
            listOf("le", "chat", "est", "assis", "sur", "le", "lit"),
            listOf( "il", "fait", "beau", "et", "chaud"),
            listOf("plus", "tard,","il", "sera", "trop", "tard"),
            listOf("soyez", "vous-même", "tous" ,"les", "autres", "sont","déjà", "pris"),
            listOf("il","y","a","plus","de","courage","que","de","talent","dans","la","plupart","des","réussites"),
            listOf("le", "ciel", "est", "bleu", "aujourd'hui"),
            listOf("la", "vie", "est", "belle", "quand", "on", "est", "heureux"),
            listOf("il", "pleut", "des", "cordes", "dehors"),
            listOf("elle", "est", "la", "reine", "de", "la", "nuit"),
            listOf("les", "oiseaux", "chantent", "dans", "les", "arbres"),
            listOf("je", "ne", "suis", "pas", "d'accord", "avec", "toi"),
            listOf("un", "chat", "a", "neuf", "vies"),
            listOf("le", "bonheur", "est", "une", "fleur", "qui", "pousse", "dans", "le", "jardin", "de", "l'âme")
    )
    val phrase = phrases.random()
    val shuffledWords = phrase.shuffled()
    val correctPhrase = phrase.joinToString(" ")
    var attempts = 0

    while (true) {
        println("Reconstituez la phrase:")
        for (word in shuffledWords) {
            print("$word ")
        }
        println()
        val input = scanner0.nextLine()
        attempts++
        println("Nombre de temptations: $attempts")
        when {
            input == correctPhrase && attempts == 1 -> {
                score0 += 5
                println("Bravo ! Vous avez obtenu 5 points, le score total est : $score0 points")
                break
            }

            input == correctPhrase && attempts == 2 -> {
                score0 += 2
                println("Bravo ! Vous avez obtenu 2 points, le score total est : $score0 points")
                break
            }

            attempts == 3 -> {
                println("Désolé, vous n'avez pas réussi à trouver la réponse. La phrase correcte est : $correctPhrase")
                break
            }
            else -> {
                var life = 3 - attempts
                println("Désolé, essayez encore. Il vous reste $life attempts")
            }
        }
    }
}

fun main() {
    while (true) {
        playGame()
        println("Voulez-vous jouer encore ? (O/N)")
        val answer = scanner0.nextLine()
        if (answer.equals("n", ignoreCase = true)) {
            break
        }
    }
}
