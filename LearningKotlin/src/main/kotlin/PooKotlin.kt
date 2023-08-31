import kotlin.math.PI

fun main() {
    //val dwelling =  Dwelling() - não é possivel instanciar um objeto abstrato
    // codigos muitp grandes nao usar o with
    val squareCabin = SquareCabin(6, 50.0)
    with (squareCabin) {
        println("===SquareCabin===")
        println("Capacity: ${capacity}")
        println("Building Material: ${buildingMaterial}")
        println("Has1 Room: ${hasRoom()}")
        println("Floor Area: ${floorArea()}")
        println("=================")
    }

    val roundHut = RoundHut(3, 10.0)
    with (roundHut) {
        println("=====RoundHut=====")
        println("Capacity: ${capacity}")
        println("Building Material: ${buildingMaterial}")
        println("Has Room: ${hasRoom()}")
        println("Floor Area: ${floorArea()}")
        println("=================")
    }

    val roundTower = RoundTower(4, 2,15.5)
    with (roundTower) {
        println("====RoundTower====")
        println("Capacity: ${capacity}")
        println("Building Material: ${buildingMaterial}")
        println("Has Room: ${hasRoom()}")
        println("=================")
    }
}

abstract class Dwelling(private val residents: Int) {
    abstract val buildingMaterial: String
    abstract val capacity: Int

    abstract fun floorArea(): Double

    fun hasRoom(): Boolean {
        return residents < capacity
    }
}

class SquareCabin(residents: Int, val length: Double) : Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6

    override fun floorArea(): Double {
        return length * length
    }
}


open class RoundHut(residents: Int, val radius: Double) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4

    override fun floorArea(): Double {
        return PI * radius * radius
    }
}

class RoundTower(residents: Int, floors: Int = 2, radius: Double) : RoundHut(residents, radius) {
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors
}