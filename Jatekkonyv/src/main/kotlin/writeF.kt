import java.io.File
import java.io.FileWriter

class writeF(eredmenyek_: Array<eredmeny> = arrayOf()) {
    var eredmenyek: Array<eredmeny> = eredmenyek_;
    private val filename: String = "highscore.txt";
    private var szoveg: String = "";

    fun writetoFile(){
        var F = File(filename);
        F.writeText("");
        for (it in eredmenyek)
        {
            szoveg =  it.jateknev + "/" + it.jatekosnev + "/" + it.jegyzet + "/" + it.nyerte + "\n";
            F.appendText(szoveg)
        }
    }
}