
fun main(){
    val amountOfLikes = 11
    val lastCharacter = amountOfLikes%10

    val result = if (lastCharacter != 1 || amountOfLikes === 11) "людям" else "человеку"
    println("Ваша фотография понравилась $amountOfLikes $result")

}