package hw2_2

import hw2_2.ChatService.chats
import hw2_2.ChatService.lastChatId
import hw2_2.ChatService.lastMessageId
import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun createMessage() {
        val user = User(0,"Вася")
        val user1 = User(1,"Маша")
        ChatService.createMessage(user, user1, "Привет!")
        ChatService.createMessage(user1,user, "Привет, Вася")
        assertTrue(lastChatId == 1)
    }

    @Test
    fun deleteMessage() {
        lastMessageId = 0
        lastChatId = 0
        chats.clear()
        val user = User(0,"Вася")
        val user1 = User(1,"Маша")
        ChatService.createMessage(user, user1, "Привет!")

        ChatService.deleteMessage(1)
        assertTrue(chats.isEmpty())

    }

    @Test
    fun getChats() {
        lastMessageId = 0
        lastChatId = 0
        chats.clear()
        val user = User(0,"Вася")
        val user1 = User(1,"Маша")
        ChatService.createMessage(user, user1, "Привет!")

        ChatService.getChats(0)
        val result = chats.size
        assertTrue(result == 1)
    }

    @Test
    fun getUnreadChatsCount() {
        lastMessageId = 0
        lastChatId = 0
        chats.clear()
        val user = User(0,"Вася")
        val user1 = User(1,"Маша")
        ChatService.createMessage(user, user1, "Привет!")
        ChatService.getUnreadChatsCount(0)
        assertTrue(chats.size == 1)

    }

    @Test
    fun getChat() {
        lastMessageId = 0
        lastChatId = 0
        chats.clear()
        val user = User(0,"Вася")
        val user1 = User(1,"Маша")
        val user2 = User(2,"Костя")
        val user4 = User(3,"Енакентий")

        ChatService.createMessage(user, user1, "Привет!")
        ChatService.createMessage(user1,user, "Привет, Вася")
        ChatService.createMessage(user, user2, "Привет, Костя")
        ChatService.createMessage(user4, user1, "Маша, привет!")
        ChatService.createMessage(user, user1, "Как дела?")

        val result = ChatService.getChat(1, 1, 3)


        assertTrue(result.isNotEmpty())
    }

    @Test
    fun deleteChat() {
        lastMessageId = 0
        lastChatId = 0
        chats.clear()
        val user = User(0,"Вася")
        val user1 = User(1,"Маша")

        ChatService.createMessage(user, user1, "Привет!")
        ChatService.createMessage(user1,user, "Привет, Вася")


        ChatService.deleteChat(1)

        assertTrue(chats.isEmpty())

    }
}