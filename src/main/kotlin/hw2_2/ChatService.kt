package hw2_2


import hw2_2.ChatService.chats

object ChatService {

    val chats = mutableListOf<Chat>()
    var lastChatId = 0
    var lastMessageId: Long = 0


    class NotFoundMessageException(message: String = "Сообщение не найдено") : RuntimeException(message)
    class NotFoundChatException(message: String = "Чат не найден") : RuntimeException(message)

    fun createMessage(fromUser: User, toUser: User, text: String): Message {
        //создаем новое сообщение
        val message = Message(id = ++lastMessageId, fromUser.id, toUser.id, text, false)

        chats.find { it.chatters.contains(fromUser) && it.chatters.contains(toUser) }
            .run {
                if (this != null) this.messages.add(message)
                else {
                    val chat = Chat(id = ++lastChatId, chatters = mutableListOf(fromUser, toUser))
                    chats.add(chat)
                    chat.messages.add(message)
                }
            }
        return message

    }

    fun deleteMessage(messageID: Long) {
        //val chatWithFoundMessage:Chat? = chats.find { it.messages.find{ message -> message.id == messageID}!=null }
        val chatWithFoundMessage = chats.find { chat -> chat.messages.any { it.id == messageID } }
        val message = chatWithFoundMessage?.messages?.find { it.id == messageID }
        chatWithFoundMessage?.let {
            it.messages.remove(message)
            if (it.messages.isEmpty()) chats.remove(it)
            return
        }
        throw NotFoundMessageException()
    }

    fun getChats(ownerId: Long): List<Chat> {

        return chats.filter { chat -> chat.chatters.any { it.id == ownerId } }
        // return chats.filter { it.chatters.contains(User(id = ownerId)) }
    }

    fun getUnreadChatsCount(ownerId: Long): Int =
        chats.filter { chat ->
            chat.chatters.any {
                it.id == ownerId
            }
        }.count { chat ->
            chat.messages.any { !it.isRead }
        }


    fun getChat(chatId: Int, messageFrom: Int, messageOffset: Int): List<Message> =
        chats.find { it.id == chatId }
            .let {
                it?.messages ?: throw NotFoundChatException()
            }
            .asSequence()
            .onEach { it.isRead = true }
            .drop(messageFrom)
            .take(messageOffset)
            .ifEmpty { throw NotFoundMessageException() }
            .toList()


    fun deleteChat(chatId: Int) {
        val chat = chats.find { it.id == chatId }
        chat?.let {
            it.messages.clear()
            chats.remove(it)
            return
        }
        throw NotFoundChatException()
    }
}

fun main() {
    val user = User(0, "Вася")
    val user1 = User(1, "Маша")
    val user2 = User(2, "Костя")
    val user4 = User(3, "Енакентий")

    ChatService.createMessage(user, user1, "Привет!")

    ChatService.createMessage(user1, user, "Привет, Вася")

    ChatService.createMessage(user, user2, "Привет, Костя")

    ChatService.createMessage(user4, user1, "Маша, привет!")

    ChatService.createMessage(user, user1, "Как дела?")

    ChatService.createMessage(user4, user, "Здарова!")

    ChatService.createMessage(user, user4, "Отлично")

    ChatService.createMessage(user4, user, "Как дела?!")

    for (chat in chats) {
        println(chat)
    }
    ChatService.deleteMessage(4)

    println(ChatService.getChats(4))

    println(ChatService.getUnreadChatsCount(0))
    println(ChatService.getChats(0))
    println(ChatService.getChat(1, 0, 3))
    println(ChatService.getChat(2, 0, 3))
    println(ChatService.getUnreadChatsCount(0))
    ChatService.deleteChat(1)
    println(ChatService.getChat(1, 0, 3))

}





