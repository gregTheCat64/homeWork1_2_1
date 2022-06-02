package hw2_2


import hw2_2.ChatService.chats

object ChatService {
    //val ownerId: Long = 0


    val chats = mutableListOf<Chat>()
    var lastChatId = 0
    var lastMessageId: Long = 0


    class NotFoundMessageException(message: String = "Сообщение не найдено") : RuntimeException(message)
    class NotFoundChatException(message: String = "Чат не найден") : RuntimeException(message)

    fun createMessage(fromUser: User, toUser: User, text: String) {
        //создаем новое сообщение
        var message = Message(id = ++lastMessageId, fromUser.id, toUser.id, text, false)

        //реализация с extension:
        var existingChat = chats.find { it.chatters.contains(fromUser) && it.chatters.contains(toUser) }
        if (existingChat == null) {
            val chat = Chat(id = ++lastChatId, chatters = mutableListOf(fromUser, toUser))
            chats.add(chat)
            chat.messages.add(message)
        } else
            existingChat.messages.add(message)
    }

    fun deleteMessage(messageID: Long) {
        //val chatWithFoundMessage:Chat? = chats.find { it.messages.find{ message -> message.id == messageID}!=null }
        val chatWithFoundMessage = chats.find { chat -> chat.messages.any{it.id == messageID} }
        val message = chatWithFoundMessage?.messages?.find {it.id == messageID}
        chatWithFoundMessage?.let {
            it.messages.remove(message)
            if (it.messages.isEmpty()) chats.remove(it)
            return
        }
        throw NotFoundMessageException()
    }

    fun getChats(ownerId: Long): List<Chat> {

        return chats.filter { chat -> chat.chatters.any{it.id == ownerId}}
       // return chats.filter { it.chatters.contains(User(id = ownerId)) }
    }

    fun getUnreadChatsCount(ownerId: Long): Int {
        var count = 0
        val chatsWithOwner = chats.filter { chat -> chat.chatters.any { it.id == ownerId } }
        for (chat in chatsWithOwner) {
            for (message in chat.messages) {
                if (!message.isRead) {
                    count++
                    break
                }
            }
        }
        return count
    }

    fun getChat(chatId: Int, messageId: Long, messageCount: Long): List<Message> {
        var messagesFromIndex = listOf<Message>()
        var messageList = listOf<Message>()
        val chat = chats.find { it.id == chatId }
        chat?.let {
            messagesFromIndex = chat.messages.filter { it.id >= messageId }
            messageList = if ((messagesFromIndex.size) >= messageCount) {
                messagesFromIndex.subList(
                    (messagesFromIndex.size - messageCount).toInt(),
                    messagesFromIndex.size
                )
            } else {
                chat.messages.subList((chat.messages.size - messagesFromIndex.size).toInt(), chat.messages.size)
            }
            for (elements in messageList) elements.isRead = true
            return messageList
        }
          throw NotFoundChatException()

    }

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
    var user = User(0,"Вася")
    var user1 = User(1,"Маша")
    var user2 = User(2,"Костя")
    var user4 = User(3,"Енакентий")

    ChatService.createMessage(user, user1, "Привет!")

    ChatService.createMessage(user1,user, "Привет, Вася")

    ChatService.createMessage(user, user2, "Привет, Костя")

    ChatService.createMessage(user4, user1, "Маша, привет!")

    ChatService.createMessage(user, user1, "Как дела?")
    for (chat in chats){
        println(chat)
    }
    ChatService.deleteMessage(4)

    println(ChatService.getChats(4))

    println(ChatService.getUnreadChatsCount(0))
    println(ChatService.getChats(0))
    println(ChatService.getChat(1, 1, 3))
    ChatService.deleteChat(1)


}




