package hw2_2

import java.net.URL

sealed class Attachment(val type: String) {
    var id: Int = 1
    var albumId: Int = 1
    var ownerId: Int = 1
    var userId: Int = 1
}

data class AudioAttachment(
    val audio: Audio = Audio()
) : Attachment("audio")

data class Audio(
    val artist: String = "",
    val title: String = "",
    val duration: Int = 0,
    val url: String = "",
    val albumIdForTrack: Int = 0,
    val genreId: Int = 0,
    val lyrics: Int? = null,
    val date: Int = 0,
    val lyricsId: Int = 0,
    val no_search: Boolean = false,
    val isHq: Boolean = true
)

data class PhotoAttachment(
    val photo: Photo = Photo()
) : Attachment("photo")

data class Photo(
    val text: String = "",
    val date: Int = 0,
    val width: Int = 0,
    val height: Int = 0
)

data class VideoAttachment(

    val video: Video = Video()
) : Attachment("video") {

}

class Video(
    val title: String = "",
    val description: String = "",
    val image: String = "",
    val date: Int = 0,
    val views: Int = 0,
    val comments: Int? = 0,
    val isPrivate: Boolean = false
)


class DocumentAttachment(
    val document: Document = Document()
) : Attachment("document") {

}

class Document(
    val title: String = "",
    val size: Int = 0,
    val ext: String = "",
    val url: String = "",
    val date: Int = 0,
    val typeOfDocument: Int = 0,
)


class GraffitiAttachment(
    val graffiti: Graffiti = Graffiti()
) : Attachment("graffiti") {

}

class Graffiti(
    val text: String = "",
    val date: Int = 0,
    val width: Int = 0,
    val height: Int = 0
)