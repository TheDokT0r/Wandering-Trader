fun main(args:Array<String>) {
    val wm = WorldsMannager()
    val worlds = wm.getWorldsPaths()

    print("All World:\n")

    for (world in worlds) {
        print(world + "\n")
    }

    val world = wm.getWorldData(worlds[0])

    val intro = Intro()
    intro.start()

    val parts = intro.readIntro("./data/.intro")
    intro.setIntro(parts, 0)
}