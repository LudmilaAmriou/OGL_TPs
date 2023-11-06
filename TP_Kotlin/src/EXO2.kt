import java.util.*
var score = 0 // initialisation du score à 0
val scanner = Scanner(System.`in`)
fun playGame2() {


    val phrases = mutableListOf(
            mutableListOf("je", "suis", "un", "étudiant", "en", "informatique"),
            mutableListOf("le", "chat", "est", "assis", "sur", "le", "lit"),
            mutableListOf( "il", "fait", "beau", "et", "chaud"),
            mutableListOf("plus", "tard,","il", "sera", "trop", "tard"),
            mutableListOf("soyez", "vous-même", "tous" ,"les", "autres", "sont","déjà", "pris"),
            mutableListOf("il","y","a","plus","de","courage","que","de","talent","dans","la","plupart","des","réussites"),
            mutableListOf("le", "ciel", "est", "bleu", "aujourd'hui"),
            mutableListOf("la", "vie", "est", "belle", "quand", "on", "est", "heureux"),
            mutableListOf("il", "pleut", "des", "cordes", "dehors"),
            mutableListOf("elle", "est", "la", "reine", "de", "la", "nuit"),
            mutableListOf("les", "oiseaux", "chantent", "dans", "les", "arbres"),
            mutableListOf("je", "ne", "suis", "pas", "d'accord", "avec", "toi"),
            mutableListOf("un", "chat", "a", "neuf", "vies"),
            mutableListOf("le", "bonheur", "est", "une", "fleur", "qui", "pousse", "dans", "le", "jardin", "de", "l'âme")
    )
    val phrase = phrases.random()
    val element = (0 until phrase.size).random()
    val missingWords = phrase.set(element, "______")
    val correctPhrase = phrase.joinToString(" ")
    var attempts = 0

    while (true) {
        println("Reconstituez la phrase:")
        for (word in phrase) {
            print("$word ")
        }
        println()
        val input = scanner.nextLine()
        attempts++
        println("Nombre de temptations: $attempts")
        when {
            input == missingWords && attempts == 1 -> {
                score += 5
                println("Bravo ! Vous avez obtenu 5 points, le score total est : $score points")
                break
            }

            input == missingWords && attempts == 2 -> {
                score += 2
                println("Bravo ! Vous avez obtenu 2 points, le score total est : $score points")
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
        playGame2()
        println("Voulez-vous jouer encore ? (O/N)")
        val answer = scanner.nextLine()
        if (answer.equals("n", ignoreCase = true)) {
            break
        }
    }
}
