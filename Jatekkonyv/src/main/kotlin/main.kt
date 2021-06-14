//import jdk.incubator.jpackage.internal.Log.flush
import java.io.File
import java.io.FileReader
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Console
import kotlin.random.*

fun main(args: Array<String>) {
    println("#######################################################");
    println(" Legy udvozolve az Ultimate Neptun-ZH-Vizsga Jatek-ban ");
    println("#######################################################");

    var c = Controller();


    var uj = c.start();

    while (uj)
    {
        var k = Controller();
        uj = k.start();
    }



    println("##########");
    println(" Viszlat! ");
    println("##########");

}