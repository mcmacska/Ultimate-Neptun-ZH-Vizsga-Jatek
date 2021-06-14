import kotlin.random.Random
import java.util.*

class Game(name_: String, playername_: String) {
    val name: String = name_;
    var zhk: Array<ZH> = arrayOf();
    private val zh_filename: String = "zhk.txt";
    private val mezok_filename: String = "mezok.txt";
    private val veg_mezok_filename: String = "vegmezo.txt";
    private val eredmenyek_filename: String = "highscore.txt";
    var mezok: Array<SimaMezo> = arrayOf();
    var vegmezok: Array<VegMezo> = arrayOf();
    var eredmenyek: Array<eredmeny> = arrayOf();
    var player = Player(playername_, 0, 0, 20);
    var l = Load();
    fun betolt(){
        try {
            var tomb = l.beolvas(zh_filename);
            for (it in tomb) {
                var egyzh: Array<String> = it.split("/").toTypedArray();
                var m = ZH(egyzh[0].toInt(), egyzh[1], egyzh[2].toInt());
                zhk = l.appendToMonster(zhk, m);
            };
        }catch (ex: Exception){
            print(ex.message)
        }

        try {
            var tomb = l.beolvas(mezok_filename);
            for (it in tomb) {
                var egymezo: Array<String> = it.split("/").toTypedArray();
                var s = SimaMezo(egymezo[0].toInt(), egymezo[1], egymezo[2].toInt(),
                    egymezo[3].toInt(), egymezo[4].toInt(), egymezo[5].toInt(),
                    egymezo[6].toInt(), egymezo[7], egymezo[8], egymezo[9].toBoolean());
                mezok = l.appendToSimamezok(mezok, s);
            };
        }catch (e: Exception){
            print(e.message)
        }

        try {
            var tomb = l.beolvas(veg_mezok_filename);
            for (it in tomb) {
                var egymezo: Array<String> = it.split("/").toTypedArray();
                var v = VegMezo(egymezo[0].toInt(), egymezo[1], egymezo[2].toInt());
                vegmezok = l.appendToVegmezok(vegmezok, v);
            };
        }catch (ey: Exception){
            print(ey.message)
        }

        try {
            var tomb = l.beolvas(eredmenyek_filename);
            if (tomb.isNotEmpty()){
                for (it in tomb) {
                    var egymezo: Array<String> = it.split("/").toTypedArray();
                    var e = eredmeny(egymezo[0], egymezo[1], egymezo[2].toInt(), egymezo[3].toBoolean());
                    eredmenyek = l.appendToEredmenyek(eredmenyek, e);
                }
            }

        }catch (e: Exception){
            print(e.message)
        }

    }

    fun JegyzethezHozzaad(j: Int){
        player.jegyzet += j;
    }

    fun EberseghezhezHozzaad(e: Int){
        player.eberseg += e;
    }

    fun PuskahozHozzaad(p: Int){
        player.puska += p;
    }

    fun lepes(mezo_id: Int): String{
        var elsoV: String = this.mezok[mezo_id].elsoValaszID.toString();
        var masodikV: String = this.mezok[mezo_id].masodikValaszID.toString();
        println(this.mezok[mezo_id].leiras + "\n");
        var aValasz: String;
        while (true) {
            JegyzethezHozzaad(this.mezok[mezo_id].jegyzet);
            EberseghezhezHozzaad(this.mezok[mezo_id].eberseg);
            PuskahozHozzaad(this.mezok[mezo_id].puska);

            println(this.mezok[mezo_id].elsoValaszLeiras + "'" + elsoV + "'");
            println(this.mezok[mezo_id].masodikValaszLeiras + "'" + masodikV + "'");
            println("Ha ki akarsz lepni, ird be, hogy: 'x'");
            when (readLine().toString()) {
                elsoV -> {
                    aValasz = elsoV;
                    break;
                }
                masodikV -> {
                    aValasz = masodikV;
                    break;
                }
                "x" -> {
                    aValasz = "x";
                    break
                }
            }
        }
        return aValasz;
    }

    fun vizsgakivalaszt(): VegMezo
    {
        return vegmezok[Random.nextInt((vegmezok.size-1)+1)];
    }

    fun kiszamol(): Int
    {
        var vizsga: VegMezo = vizsgakivalaszt();
        if(player.jegyzet + player.eberseg + player.puska >= vizsga.hp)
        {
            return 2;
        }
        return 1;
    }



    fun jatekvege(atment: Boolean): Boolean{
        if(atment){
            println("Gratulalok, " + this.player.name + " sikeresen zarta a felevet!");
        }else{
            println("Sajnos a felev nem sikerult " + this.player.name + " szamara.");
        }
        println(this.player.name + " ennyi jegyzetet gyujtott: " + this.player.jegyzet);

        var E = eredmeny(this.name, this.player.name, this.player.jegyzet, atment);
        eredmenyek = l.appendToEredmenyek(eredmenyek, E);

        var w = writeF(eredmenyek);
        w.writetoFile();

        println();
        println("Eddigi eredmenyek:");
        var siker_: String;

        for (i in eredmenyek)
        {
            if (i.nyerte){
                siker_ = "";
            }else{
                siker_ = "nem ";
            }

            println("Jatek neve: " + i.jateknev + ", a jatekos neve: " + i.jatekosnev + ", ennyi jegyzetet gyujtott: "
            + i.jegyzet + ", a felevet " + siker_ + "teljesitette.");
        }


        while (true) {
            println("Ha visszamesz a fomenube, ird be, hogy: 'f'");
            println("Ha ki akarsz lepni, ird be, hogy: 'x'\n");
            when (readLine().toString()) {
                "f" -> return true;
                "x" -> return false;
            }
        }
    }

}