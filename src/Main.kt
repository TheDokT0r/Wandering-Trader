fun main(args:Array<String>) {
    val wm = WorldsMannager()
    val worlds = wm.getWorldsPaths()

    print("All World:\n")

    for (world in worlds) {
        print(world + "\n")
    }

    val world = wm.getWorldData(worlds[0])

    for (shop in world.Shops) {
        shop.setInventory()
        println("Inv set for shop #" + shop.id)
    }

    val intro = Intro(world)
    intro.start()
}