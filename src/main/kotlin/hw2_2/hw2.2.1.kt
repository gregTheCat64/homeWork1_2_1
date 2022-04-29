package hw2_2

import java.util.*

data class Post(
    var id:Int = 0,
    val ownerId:Int =0,
    val fromId:Int = 0,
    val createdBy:Int =0,
    val date: Int =0,
    val text: String = "",
    val replyOwnerId: Int = 0,
    val replyPostId:Int = 0,
    val friendsOnly:Boolean = false,
    val comments: Comments,
    val copyright: Copyright,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val postType: String = "post",
    val signerId: Int = 0,
    val canPin:Boolean = true,
    val canDelete:Boolean = true,
    val canEdit:Boolean = true,
    val isPinned:Boolean = true,
    val markAsAdds: Boolean = false,
    val isFavourite: Boolean = false,
    val postPonedId: Int =0


)

data class Comments(
    val count:Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost:Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = true

)

data class Copyright(
    val id: Int = 0,
    val link: String ="link",
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
    val count: Int=0
)

data class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int =0,
    val placeHolder: PlaceHolder,
    val canPublishFreeCopy: Boolean = true,
    val editMode:String = "all"

)

class PlaceHolder(
    val text: String = "Чтобы увидеть содержимое, оплатите подписку"
)

fun main() {
    val comments = Comments()
    val copyright = Copyright()
    val likes = Likes()
    val reposts = Reposts()
    val views = Views()

    val placeHolder = PlaceHolder()
    val donut = Donut(placeHolder = placeHolder)

    val post = Post(comments = comments, copyright = copyright, likes = likes, reposts = reposts, views = views)
    val post2 =Post(comments = comments, copyright = copyright, likes = likes, reposts = reposts, views = views)
    println(post.toString())
    WallService.add(post)
}

