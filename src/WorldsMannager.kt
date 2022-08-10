
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader


class WorldsMannager() {
    private val worldsFolderPath: String = "./Worlds/"

    fun getWorldsPaths(): Array<String> {
        val worldsArr = mutableListOf<String>()

        File(worldsFolderPath).walk().forEach {
            worldsArr.add(it.path)
        }

        return worldsArr.toTypedArray()
    }

    fun getWorldData(mainDirPath: String) {
        val info: Map<String, String> = getInfoJson(mainDirPath)
    }

    fun getInfoJson(path: String): Map<String, String> {
        val obj = JSONParser().parse(FileReader("$path/info.json"))
        val jo: JSONObject = obj as JSONObject

        val worldName: String = jo["world_name"] as String
        val startingCity: String = jo["starting_city"] as String
        val currency: String = jo["currency_symbol"] as String
        val bal: String = jo["starting_bal"] as String

        return mapOf("worldName" to worldName, "startingCity" to startingCity, "currency" to currency, "bal" to bal)
    }

    fun getItems(path: String): Array<Item> {
        val obj = JSONParser().parse(FileReader("$path/data/items.json"))
        val jo: JSONObject = obj as JSONObject
        val ja: JSONArray = jo["data"] as JSONArray

        var items = arrayListOf<Item>()

        for (item in ja) {

        }
}