package hw2_2

import java.util.*
import kotlin.collections.ArrayList

data class Post(
    var id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val copyright: Copyright = Copyright(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: String = "post",
    val signerId: Int = 0,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = true,
    val markAsAdds: Boolean = false,
    val isFavourite: Boolean = false,
    val postPonedId: Int = 0,
    //добавляем поля:
    val postSource: PostSource = PostSource(),
    val geo: Geo = Geo(),
    val copyHistory: ArrayList<Reposts>? = arrayListOf<Reposts>(),
    val attachments: ArrayList<Attachment>? = arrayListOf()

)

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = true

)

data class Copyright(
    val id: Int = 0,
    val link: String = "link",
    val name: String = "name",
    val type: String = "default"
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class Views(
    val count: Int = 0
)

data class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = 0,
    val placeHolder: PlaceHolder,
    val canPublishFreeCopy: Boolean = true,
    val editMode: String = "all"

)

class PlaceHolder(
    val text: String = "Чтобы увидеть содержимое, оплатите подписку"
)

//добавляем недостающие поля:

data class PostSource(
    val type: String = "vk",
    val platform: String = "android",
    val data: String? = null,
    val url: String? = null
)

data class Geo(
    val type: String? = null,
    val coordinates: String? = null,
    val place: String? = null
)


fun main() {


    val placeHolder = PlaceHolder()
    val donut = Donut(placeHolder = placeHolder)

    val post = Post()
    val post2 = Post(isFavourite = true)

    //меняем избранное предыдущего поста на Фолс, а френдсОнли на Тру:
    val postUpdated = Post(id = 2, friendsOnly = true, isFavourite = false)


    WallService.add(post)
    WallService.add(post2)


    WallService.update(postUpdated)



    val attachment = AudioAttachment(audio = Audio("Ария", "Беспечный ангел"))
    val attachment1 = AudioAttachment(audio = Audio("Король и Шут", "Проклятый старый дом"))
    val attachment3 = VideoAttachment(video = Video("Панда пукает", "смешное видео"))

    WallService.addAttachment(attachment)
    WallService.addAttachment(attachment1)
    println(attachment.id)
    println(attachment1.id)




    // println(post)
    //println(post2)
    //println(postUpdated)

}

