package hw2_2

import hw2_2.ChatService.createMessage


data class Chat(
    var id: Int = 0,
    var chatters: MutableList<User> = mutableListOf<User>(),
    val messages: MutableList<Message> = mutableListOf<Message>()
)

data class Message(
    var id: Long,
    var fromUserId: Long,
    var toUserId: Long,
    var text: String,
    var isRead: Boolean
)

data class User(
    var id: Long,
    var nameOfUser: String
)


