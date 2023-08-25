fun main() {
    /**
     * val  = variavel que nao vai mudar, nao pode ser reatribuido
     * var = variavel normal
     *
     * nao necessariamente precisa declerar o tipo da variavel
     *  val idade do tipo Inteiro recebe 21
     */
    val idade : Int = 21
    val salario = 90.0 //Double l, f, i no final pra converter
    val nomeVal : String = "Eduardo"
    val ehAposentado : Boolean = false

    var nomeVar : String? = "Eduardo 2" //o ponto de ? no final do tipo permite null

    idade.inc() //idade++

    nomeVar = null
    println(nomeVar)

    if ( nomeVar != null) {
        val nomeMaiusculo = nomeVal.uppercase()
    }

    val resultado  = salario + idade
    println(resultado)
    println("Idade = " + idade)
    println("Idade = ${idade}")
    println("Hello World!")

   /* var situacao : String =  if ( idade < 18 ) {
        "Menor de Idade"
    } else if (idade > 65)  {
        "Pode se aposentar"
    } else {
        "Maior de Idade"
    }

    if ( nomeVar != null) {
        val nomeMaiusculo = nomeVal.uppercase()
    }*/

    var situacao : String

    if ( idade < 18 ) {
        situacao = "Menor de Idade"
    } else if (idade > 65)  {
        situacao = "Pode se aposentar"
    } else {
        situacao =  "Maior de Idade"
    }
    println(situacao)

    when {
        idade < 18 -> situacao = "Menor de idade"
        idade > 65 -> situacao = "Pode se aposentar"
        else -> situacao = "maior de idade"
    }

    println(situacao)


    var situacao2 : String = when {
        idade < 18 ->  "Menor de idade"
        idade > 65 ->  "Pode se aposentar"
        else ->  "maior de idade"
    }

    println(situacao2)

    imprime()
    imprimeLooping()
}

fun imprime() : Boolean {
    println("Olá")

    if ( 1 == 1)  {
        return true;
    } else {
        return false;
    }
}

fun imprimeLooping () {
    repeat(10) {
        print("=")
    }

    println()

    for (i in 1..5) {
        print(i)
    }

    println()

    val colecao = 1..10

    for (item in colecao) {
        print(item)
    }

    println()

    var controle = 1

    while ( controle <= 10) {
        print(controle)
        controle = controle.inc()
    }

    println()

    controle = 10

    do {
        print(controle)
        controle = controle.dec()
    } while (controle > 1)
}