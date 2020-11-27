import org.w3c.dom.*
import org.w3c.dom.url.URL
import kotlinx.browser.document
import kotlinx.dom.appendText
import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    setOf(Title, Header, Expander, Content, Home, Spoiler, FolderTitle, FolderHeader, Table, PreviewPanel, Comments, Hiking).
            forEach { instance -> if (instance.viewElement != null) instance.show() }
}

sealed class View(id: String) {
    val viewElement = if (document.getElementById(id) != null) document.getElementById(id) else null
    open fun show() {}
}

private const val homePage = "index.html"
private const val folderIndexPage = "folderIndex.html"
private const val previewPage = "preview.html"

private val content = listOf(
        FolderContent("Путешествие по Байкальскому хребту.", "baikal/old"),
        FolderContent("Фотографии с выпускного вечера.", "party", 77, "party"),
        FolderContent("Фотографии.", "photo", 46, "img"),
        FolderContent("Девочка.", "bjaha"),
        FolderContent("Пара фоток с дня рождения Ксюши.", "ksjusha", 6, "ksjusha"),
        FolderContent("Лето 2005 в Зуёво.", "summer2005", 71, "summer"),
        FolderContent("Поход 6-7 мая 2006 года.", "pohod", 36, "pohod"),
        FolderContent("Фотографии из университета, весна 2006.", "University0506", 54, "University0506_"),
        FolderContent("Окончание летней сессии 2005 года у нас в гостях.", "18062005", 90, "18062005_"),
        FolderContent("Поход 27-28 июня 2006 года.", "pohod27-2806", 58, "pohod"),
        FolderContent("Гости 29 июня 2006 года.", "29062006", 36, "28062006_"),
        FolderContent("День группы.", "DDay", 49, "DDay"),
        FolderContent("Фотографии 2003-2006.", "photoalot", 135, "photo"),
        FolderContent("Лето 2006", "summer2006", 320),
        FolderContent("Удачные фотографии.", "dacha", 65),
        FolderContent("Поход 9-10 сентября 2006 года.", "pohod 9-10 sentjabrja", 82),
        FolderContent("Прогулка в Москве 6-7 августа 2006 года.", "moskow", 129),
        FolderContent("Гости 24 сентября 2006 года.", "24092006", 43),
        FolderContent("Unpublished", "unpublished"),
        FolderContent("Новогоднии гости. новый 2007 год.", "New Year", 41),
        FolderContent("Школа+", "school", 205),
        FolderContent("Зима-весна 2007.", "march2007"),
        FolderContent("Третий традиционный майский поход.", "tretij majskij pohod", 88),
        FolderContent("17 свечек у Никиты.", "nikita", 47),
        FolderContent("Классики.", "classics", 9, "school0"),
        FolderContent("19", "cumpl", 68),
        FolderContent("Родача.", "rodacha", 60),
        FolderContent("Прогулка 26 июня 2007 года.", "Progulka", 53),
        FolderContent("Лето 2007 года.", "summer2007"),
        FolderContent("Первосентябрье 2007.", "197", 94),
        FolderContent("Третий традиционный осенний поход. 14-15 сентября 2007 года.", "pohod1497", 94),
        FolderContent("Карелия. Осенний поход школьников. 2007 год.", "Karelia2007", 97),
        FolderContent("Моя двадцатая осень.", "XXth", 100),
        FolderContent("Новый 2008 год.", "2008"),
        FolderContent("Студенческие дни.", "UniversityIVV", 185),
        FolderContent("Каток на Дворцовой.", "skating2818", 21),
        FolderContent("У Маши.", "misssh", 32),
        FolderContent("Фотографии из школы. 11 класс.", "schooladdition", 100),
        FolderContent("Возможно, вы искали...", "circo", 10),
        FolderContent("20 лет Андрею.", "2x103", 33),
        FolderContent("Мартовский поход с песней.", "kolvica8", 136),
        FolderContent("Предмай 2008.", "spring2008"),
        FolderContent("Четвёртый традиционный майский поход.", "4thmay", 79),
        FolderContent("Начало лета 2008 года.", "June08", 91),
        FolderContent("Путешествие на Урал.", "NUral", 297),
        FolderContent("В белом платье.", "3178", 16),
        FolderContent("Четвёртый традиционный осенний поход.", "4trados", 26),
        FolderContent("Игры отличников и прогулка с замечательной девочкой.", "september8", 12),
        FolderContent("Прогулка с Машей и Ксюшей.", "Lomonosov", 23),
        FolderContent("Окончание лета 2008 года.", "summer8", 81),
        FolderContent("Праздник.", "22118", 13),
        FolderContent("День рождения Ксюши.", "5128", 16),
        FolderContent("Спорт и отдых.", "SportNrest", 24),
        FolderContent("Немного о школе и мероприятиях на ПМ-ПУ.", "PreNew2009", 15),
        FolderContent("Новый 2009 год.", "New2009", 50),
        FolderContent("Отдых в деревне.", "VilJan9", 27),
        FolderContent("Хибины 2009.", "Hibiny9", 138),
        FolderContent("По весне.", "spring9", 30),
        FolderContent("Встречи.", "PartySpring9", 25),
        FolderContent("Пятый традиционный майский поход.", "5tradmay", 44),
        FolderContent("5.09", "509", 25),
        FolderContent("Начало лета девятого года.", "summer9", 66),
        FolderContent("Армия - это я!", "army", 191),
        FolderContent("Свадьба.", "wedKate", 101),
        FolderContent("Пятый традиционный осенний поход.", "pohodX", 30),
        FolderContent("Моменты.", "summerfall9", 49),
        FolderContent("Вторая редакция.", "Baikal", 127),
        FolderContent("Окончание девятого года.", "fallwinter9", 43),
        FolderContent("Новый 2010 год.", "hny10", 37),
        FolderContent("Зимняя база 2010.", "210"),
        FolderContent("Начало 2010.", "110", 22),
        FolderContent("Каток.", "skating14210", 23),
        FolderContent("Предвесенний альбом.", "winter10", 47),
        FolderContent("Озеро Сейдозеро.", "Lovozero10", 108),
        FolderContent("О негодяях.", "MarchApril10", 90),
        FolderContent("Шестой традиционный майский поход.", "6tradmay", 55),
        FolderContent("Большой альбом для тех, кто соскучился по Ксюше.", "ksjushapack", 308),
        FolderContent("Мои друзья и их приключения.", "adventure"),
        FolderContent("Шестой традиционный осенний поход.", "6trados", 41),
        FolderContent("Лето 2010 и немного осени.", "summerfall10", 60),
        FolderContent("Как я погулял в Праге.", "VisitToNastya", 63),
        FolderContent("Долгая зима.", "Winter1011", 66),
        FolderContent("Ловозёрские и Хибинские тундры.", "ArcticCircleBeyound", 99),
        FolderContent("Седьмой традиционный майский поход.", "7tradmay", 12),
        FolderContent("Свадьба Тани и Андрея.", "TanyaAndrey", 43),
        FolderContent("Поход на байдарках.", "canoeing11", 98),
        FolderContent("Седьмой традиционный осенний поход.", "7trados", 41)
)

private data class FolderContent(
        val title: String,
        val folder: String,
        val numberOfPhotos: Int = 0,
        val pathName: String = "Picture",
        val folderIndex: String = folderIndexPage,
        val folderParent: String = homePage
)

private fun folder() : String? {
    return URL(document.URL).searchParams.get("folder")
}

private fun n() : Int {
    return (URL(document.URL).searchParams.get("n") ?: "0").toIntOrNull() ?: 0
}

private fun folderTitle() : String? {
    val folder = URL(document.URL).searchParams.get("folder")
    return folder?.let { content.firstOrNull { folder == it.folder }?.title }
}

private fun numberOfPhotos() : Int {
    val folder = URL(document.URL).searchParams.get("folder")
    return folder?.let { content.firstOrNull { folder == it.folder }?.numberOfPhotos } ?: 0
}

private fun folderParent() : String {
    val folder = URL(document.URL).searchParams.get("folder")
    return folder?.let { content.firstOrNull { folder == it.folder }?.folderParent } ?: homePage
}

private fun pathName() : String {
    val folder = URL(document.URL).searchParams.get("folder")
    return folder?.let { content.firstOrNull { folder == it.folder }?.pathName } ?: "Picture"
}

private fun Element.addBreak() {
    append(document.createElement("br") as HTMLBRElement)
}

private fun imageSource(index: Int, thumbnail: Boolean = false): String {
    val path = StringBuilder(folder() ?: "")
    path.append("/")
    if (thumbnail) {
        path.append("1_")
    }
    path.append(pathName())
    if (index < 10) {
        path.append("0")
    }
    if (index < 100) {
        path.append("0")
    }
    path.append(index)
    path.append(".jpg")
    return path.toString()
}

object Title: View("title") {
    override fun show() {
        folderTitle()?.let { title -> viewElement?.appendText(title) }
    }
}

object Header: View("header") {
    override fun show() {
        viewElement?.appendChild(buildHeaderText())
    }

    private fun buildHeaderText() : Element {
        val header = document.createElement("div") as HTMLDivElement
        "Здравствуйте, Дети Бесцветных Дней!".forEach { letter ->
            val letterElement = document.createElement("font") as HTMLFontElement
            letterElement.appendText(letter.toString())
            letterElement.size = Random.nextInt(3..7).toString()
            letterElement.color = setOf(
                    "aqua", "blue", "blueviolet", "brown", "charteuse", "chocolate", "coral", "crimson", "cyan", "darkblue", "darkgreen", "darkmagenta", "darkorange", "deeppink", "firebrick", "forestgreen", "green", "indigo", "indianred", "maroon", "mediumblue", "olivedrab", "orange", "orangered", "purple", "red", "sandybrown", "tomato", "violet", "yellow", "yellowgreen"
            ).random()
            header.append(letterElement)
        }
        return header
    }

}

object Content: View("content") {
    private var hideTop = document.getElementById("expander") != null

    override fun show() {
        val list = document.createElement("div") as HTMLDivElement
        val listElement = document.createElement("div") as HTMLDivElement

        val dataList = if (hideTop) content.filter { it.numberOfPhotos > 0 }.takeLast(10) else content.filter { it.numberOfPhotos > 0 }
        dataList.forEach {
            val anchor = document.createElement("a") as HTMLAnchorElement
            anchor.href = "${folderIndexPage}?folder=${it.folder}"
            anchor.appendText(it.title)
            listElement.append(anchor)
            listElement.addBreak()
        }
        list.append(listElement)

        viewElement?.appendChild(list)
    }
}

object Expander: View("expander") {

    override fun show() {
        val extendElement = document.createElement("a") as HTMLAnchorElement
        extendElement.appendText("1+")
        extendElement.href = "all.html"
        viewElement?.append(extendElement)
        viewElement?.addBreak()
    }
}

object Home: View("home") {
    override fun show() {
        val homeElement = document.createElement("a") as HTMLAnchorElement
        homeElement.appendText("На главную.")
        homeElement.href = homePage
        viewElement?.addBreak()
        viewElement?.append(homeElement)
    }
}

object Spoiler: View("spoiler") {
    override fun show() {
        val spoiler = document.createElement("font") as HTMLFontElement
        spoiler.size = "1"
        spoiler.appendText("under construction")

        viewElement?.appendChild(spoiler)
    }
}

object FolderTitle: View("folder-title") {
    override fun show() {
        viewElement?.appendText(folderTitle() ?: "")
    }
}

object FolderHeader: View("folder-header") {
    override fun show() {
        val header = document.createElement("h1") as HTMLHeadingElement
        folderTitle()?.let { header.appendText(it) }

        viewElement?.append(header)
    }
}

object Table : View("table") {
    override fun show() {
        val table = document.createElement("table") as HTMLTableElement
        val first = (URL(document.URL).searchParams.get("first") ?: "0").toIntOrNull() ?: 0
        for (i in 0 .. 3) {
            val tr = table.insertRow()
            for (j in 1 .. 4) {
                val n = first + i * 4 + j
                if (n <= numberOfPhotos()) {
                    val imageThumbnail = document.createElement("img") as HTMLImageElement
                    imageThumbnail.src = imageSource(index = n, thumbnail = true)
                    val previewLink = document.createElement("a") as HTMLAnchorElement
                    previewLink.append(imageThumbnail)
                    previewLink.href = "${previewPage}?folder=${folder()}&n=${n}"
                    val td = tr.insertCell()
                    td.append(previewLink)
                }
            }
        }
        if (numberOfPhotos() <= 0) {
            val tr = table.insertRow().insertCell() as HTMLTableCellElement
            tr.colSpan = 4
            tr.appendText("Эти фотографии недоступны")
        }
        val tr = table.insertRow()
        val tdPrevious = tr.insertCell()
        if (first != 0) {
            val nfirst = if (first < 16) 0 else first - 16
            val previousLink = document.createElement("a") as HTMLAnchorElement
            previousLink.href = "${folderIndexPage}?folder=${folder()}&first=${nfirst}"
            previousLink.appendText("Предыдущие фотографии")
            tdPrevious.append(previousLink)
        }
        val tdUp = tr.insertCell() as HTMLTableCellElement
        tdUp.colSpan = 2
        val upLink = document.createElement("a") as HTMLAnchorElement
        upLink.href = folderParent()
        upLink.appendText("Вверх")
        tdUp.append(upLink)
        val tdNext = tr.insertCell()
        if (first + 16 < numberOfPhotos()) {
            val nextLink = document.createElement("a") as HTMLAnchorElement
            nextLink.href = "${folderIndexPage}?folder=${folder()}&first=${first + 16}"
            nextLink.appendText("Следущие фотографии")
            tdNext.append(nextLink)
        }

        viewElement?.append(table)
    }
}

object PreviewPanel : View("preview-panel") {
    override fun show() {
        val table = document.createElement("table") as HTMLTableElement
        val trPicture = table.insertRow()
        val image = document.createElement("img") as HTMLImageElement
        image.src = imageSource(n())
        image.className = "preview-image"
        val anchoredImage = document.createElement("a") as HTMLAnchorElement
        anchoredImage.append(image)
        anchoredImage.href = "${previewPage}?folder=${folder()}&n=${n() % numberOfPhotos() + 1}"
        val td = trPicture.insertCell() as HTMLTableCellElement
        td.colSpan = 3
        td.append(anchoredImage)

        val trControls = table.insertRow()
        val tdPrevious = trControls.insertCell()
        if (n() > 1) {
            val previousImageThumbnail = document.createElement("img") as HTMLImageElement
            previousImageThumbnail.src = imageSource(index = n() - 1, thumbnail = true)
            previousImageThumbnail.className = "thumbnail-image"
            val previousLink = document.createElement("a") as HTMLAnchorElement
            previousLink.append(previousImageThumbnail)
            previousLink.href = "${previewPage}?folder=${folder()}&n=${n() - 1}"
            tdPrevious.append(previousLink)
        }
        val tdUp = trControls.insertCell()
        val upLink = document.createElement("a") as HTMLAnchorElement
        upLink.href = folder()?.let { "${folderIndexPage}?folder=${it}&first=${n() - 1}" } ?: homePage
        upLink.appendText("Вверх")
        tdUp.append(upLink)
        val tdNext = trControls.insertCell()
        if (n() < numberOfPhotos()) {
            val nextImageThumbnail = document.createElement("img") as HTMLImageElement
            nextImageThumbnail.src = imageSource(index = n() + 1, thumbnail = true)
            nextImageThumbnail.className = "thumbnail-image"
            val nextLink = document.createElement("a") as HTMLAnchorElement
            nextLink.append(nextImageThumbnail)
            nextLink.href = "${previewPage}?folder=${folder()}&n=${n() + 1}"
            tdNext.append(nextLink)
        }

        viewElement?.append(table)
    }
}

object Comments : View("comments")

object Hiking: View("hiking") {
    override fun show() {
        val hiking = document.createElement("a") as HTMLAnchorElement
        hiking.href = "hiking"
        hiking.appendText("Поход")
        viewElement?.appendChild(hiking)
        viewElement?.addBreak()
    }
}
