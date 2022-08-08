import java.io.File

class WorldsMannager() {
    private val worldsFolderPath: String = "./Worlds/"

    fun getWorldsPaths(): Array<String> {
        val worldsArr = mutableListOf<String>()

        File(worldsFolderPath).walk().forEach {
            worldsArr.add(it.path)
        }

        return worldsArr.toTypedArray()
    }
}