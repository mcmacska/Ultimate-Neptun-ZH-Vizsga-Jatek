import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

class Controller {

    fun neptun(): Boolean
    {
        if(Random.nextInt((2-1)+1) == 1){
            return true;
        }
        return false;
    }

    fun ZH(g_: Game): Boolean
    {
        var id: Int = Random.nextInt((3-1)+1);

        println("Egy " + g_.zhk[id].name + " jon szembe.");
        println("Az eletereje: " + g_.zhk[id].hp + ".");
        println(g_.player.name + " osszpontszama: " + (g_.player.jegyzet + g_.player.eberseg)+ ".");
        if(g_.player.jegyzet + g_.player.eberseg >= g_.zhk[id].hp){
            println(g_.player.name + " legyozte es mehet tovabb.");
            return true;
        }
        println(g_.player.name + " nem tudta legyozni.");
        return false;
    }

    fun SzamE(s: String): Boolean
    {
        return try {
            s.toInt();
            true;
        } catch (ex: NumberFormatException) {
            false;
        }
    }

    fun letezik(g_: Game, id: Int): Boolean
    {
        if(id < g_.mezok.size)
        {
            return true;
        }
        return false;

    }

    fun start(): Boolean {
        var megbukott: Boolean = false;
        var atment: Int = 0;
        var legyen_uj_jatek: Boolean = false;
        var uj_jatek: Boolean = false;

        while (true) {
            println("Ha uj jatekot akarsz kezdeni, ird be, hogy: 'uj'");
            println("Ha ki akarsz lepni, ird be, hogy: 'x'\n");
            when (readLine()) {
                "uj" -> {
                    uj_jatek = true;
                    break;
                }
                "x" -> break
            }
        }


        if (uj_jatek) {

            println("Mi legyen az uj jatek neve?");
            var jateknev: String;
            while (true) {
                var reader = BufferedReader(InputStreamReader(System.`in`));
                jateknev = reader.readLine().toString();
                if (jateknev != "") {
                    break;
                } else {
                    println("Irj be egy nevet.");
                }
            }

            println("Mi legyen a jatekos neve?");
            var jatekosnev: String;
            while (true) {
                var reader = BufferedReader(InputStreamReader(System.`in`));
                jatekosnev = reader.readLine().toString();
                if (jatekosnev != "") {
                    break;
                } else {
                    println("Irj be egy nevet.");
                }
            }

            var g = Game(jateknev, jatekosnev);
            g.betolt();

            var valasz: String = g.lepes(0);

            while (true) {
                if(SzamE(valasz) && letezik(g, valasz.toInt()) && g.mezok[valasz.toInt()].zhMezo && !ZH(g))
                {
                    megbukott = true;
                    break;
                }
                else if(valasz.equals("28", true))
                {
                    megbukott = true;
                    break;
                }else if(valasz.equals("30", true))
                {
                    atment = g.kiszamol();
                    break;
                }else if(valasz.equals("23", true) && neptun())
                {
                    valasz = "9";
                }else{
                    if (valasz.equals("x", true)) {
                        break;
                    } else {
                        valasz = g.lepes(valasz.toInt());
                    }
                }
            }

            if(megbukott)
            {
                legyen_uj_jatek = g.jatekvege(false);
            }else if(atment == 2)
            {
                legyen_uj_jatek = g.jatekvege(true);
            }else if(atment == 1)
            {
                legyen_uj_jatek = g.jatekvege(false);
            }else {
                println("Kilepes a jatekbol.");
            }

        }

        return legyen_uj_jatek;

    }

}