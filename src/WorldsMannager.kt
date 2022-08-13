
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader

//NOTE: converting data to long first, and then to int, because for some reason, the Kotlin debuger says it's a bug otherwise
class WorldsMannager() {
    private val worldsFolderPath: String = "./Worlds/"

    fun getWorldsPaths(): Array<String> {
        val worldsArr = mutableListOf<String>()

        File(worldsFolderPath).walk().forEach {
            val s = it.path.split("\\", "//")
            if (s.size == 3) { //To not get subdirectories or files
                worldsArr.add(it.path)
            }
        }

        return worldsArr.toTypedArray()
    }

    fun getWorldData(mainDirPath: String): World { //Get all of the world data
        val info = getInfoJson(mainDirPath)
        val items = getItems(mainDirPath)
        val shops = getShops(mainDirPath, items)
        val cities = getCities(mainDirPath, shops)

        val player = Player("Idk", (info["startingCity"] as Long).toInt(), (info["bal"] as Long).toInt())

        return World(info["worldName"] as String, 0, player, cities,shops, items, info["currency"] as String) //TODO: change ID
    }

    private fun getInfoJson(path: String): Map<String, Any> { //General info json
        val obj = JSONParser().parse(FileReader("$path/info.json"))
        val jo: JSONObject = obj as JSONObject

        val worldName = jo["world_name"] as String
        val startingCity = jo["starting_city"] as Long
        val currency = jo["currency_symbol"] as String
        val bal = jo["starting_bal"] as Long

        return mapOf("worldName" to worldName, "startingCity" to startingCity, "currency" to currency, "bal" to bal)
    }

    private fun getItems(path: String): Array<Item> { //All available items
        val obj = JSONParser().parse(FileReader("$path/data/items.json"))
        val jo: JSONObject = obj as JSONObject
        val ja: JSONArray = jo["data"] as JSONArray

        val items = arrayListOf<Item>()

        val t = ja.iterator()
        while(t.hasNext()) {
            val item = t.next() as JSONObject
            val ID = item["ID"] as Long
            val name = item["name"] as String
            val min_val = item["min_val"] as Long
            val max_val = item["max_val"] as Long
            val tradable = item["tradable"] as Boolean
            val scarcity = item["scarcity"] as Long

            items.add(Item(ID.toInt(), name, min_val.toInt(), max_val.toInt(), tradable, scarcity.toInt()))
        }

        return items.toTypedArray()
    }


    private fun getShops(path: String, allItems: Array<Item>): Array<Shop> { //All shops in the world
        val obj = JSONParser().parse(FileReader("$path/data/shops.json"))
        val jo: JSONObject = obj as JSONObject
        val ja: JSONArray = jo["data"] as JSONArray

        val shops = arrayListOf<Shop>()

        val t = ja.iterator()
        while(t.hasNext()) {
            val shop = t.next() as JSONObject
            val ID = shop["ID"] as Long
            val name = shop["name"] as String
            val trader_name = shop["trader_name"] as String
            val shop_type = shop["shop_type"] as String
            val itemsID = shop["items"] as JSONArray

            val items = arrayListOf<Item>()
            for (itemID in itemsID) {
                items.add(getItemFromID((itemID as Long).toInt(), allItems))
            }

            shops.add(Shop(ID.toInt(), name, trader_name, shop_type, items.toTypedArray()))
        }

        return shops.toTypedArray()
    }

    private fun getCities(path: String, allShops: Array<Shop>): Array<City> {
        val obj = JSONParser().parse(FileReader("$path/data/cities.json"))
        val jo: JSONObject = obj as JSONObject
        val ja: JSONArray = jo["data"] as JSONArray

        val cities = arrayListOf<City>()

        val t = ja.iterator()
        while(t.hasNext()) {
            val city = t.next() as JSONObject

            val ID = city["ID"] as Long
            val name = city["name"] as String
            val pop = city["pop"] as Long
            val lvl = city["lvl"] as Long
            //val connectedCities = city["connected_cities"] as IntArray
            val connectedCities = JSonToInt(city["connected_cities"] as JSONArray)
            val shops_ids = city["shops_ids"] as JSONArray

            val shops = arrayListOf<Shop>()
            for (shopID in shops_ids) {
                shops.add(getShopFromID((shopID as Long).toInt(), allShops))
            }

            cities.add(City(ID.toInt(), name, pop.toInt(), lvl.toInt(), shops.toTypedArray(), connectedCities))
        }

        return cities.toTypedArray()
    }

    private fun getItemFromID(ID: Int, items: Array<Item>): Item { //Get a specific item through it's ID
        for (item in items) {
            if (item.id == ID) {
                return item
            }
        }

        return Item(-1, "NULL", -1, -1, false, -1)
    }

    private fun getShopFromID(ID : Int, shops : Array<Shop>): Shop {
        for (shop in shops) {
            if (shop.id == ID) {
                return shop
            }
        }

        return Shop(-1, "NULL", "NULL", "NULL", emptyArray<Item>()) //NULL Values
    }

    private fun JSonToInt(ja : JSONArray) : IntArray {
        val arr = IntArray(ja.size)

        for((counter, item) in ja.withIndex()) {
            arr[counter] = (item as Long).toInt()
        }

        return arr
    }
}