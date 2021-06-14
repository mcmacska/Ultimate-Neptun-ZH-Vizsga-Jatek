import java.io.File
class Load {
    fun beolvas(fileName: String): List<String> = File(fileName).bufferedReader().readLines()

    fun appendToMonster(arr: Array<ZH>, element: ZH): Array<ZH> {
        val list: MutableList<ZH> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    fun appendToSimamezok(arr: Array<SimaMezo>, element: SimaMezo): Array<SimaMezo> {
        val list: MutableList<SimaMezo> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    fun appendToVegmezok(arr: Array<VegMezo>, element: VegMezo): Array<VegMezo> {
        val list: MutableList<VegMezo> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    fun appendToEredmenyek(arr: Array<eredmeny>, element: eredmeny): Array<eredmeny> {
        val list: MutableList<eredmeny> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }
}