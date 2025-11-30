import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;



public class Indie {
    static Scanner sc = new Scanner(System.in);
    //cada booleano es para una cosa distinta, los cuales se van activando a lo largo del juego, menos el de vivo que solo se desactiva si fallas
    static boolean TieneLlave1 = false;
    static boolean TieneLlave2 = false;
    static boolean TieneArma1 = false;
    static boolean TieneArma2 = false;
    static boolean TieneArma3 = false;
    static boolean TieneTesoro = false;
    static boolean vivo = true;
    static Clip musicaAmbiente = null;

    public static void reproducirSonido(String ruta) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

            // Espera hasta que termine de reproducirse
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            clip.close();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠️ No se pudo reproducir el sonido: " + ruta);
        }
    }

    public static void detener_musica() {
        try {
            if (musicaAmbiente != null) {
                musicaAmbiente.stop();
                musicaAmbiente.flush();
                musicaAmbiente.close();
                musicaAmbiente = null;
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al detener la música: " + e.getMessage());
        }
    }

    public static void reproducir_musica(String ruta, boolean enBucle) {
        try {
            detener_musica();

            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(ruta));
            musicaAmbiente = AudioSystem.getClip();
            musicaAmbiente.open(audio);

            if (enBucle) {
                musicaAmbiente.loop(Clip.LOOP_CONTINUOUSLY);
            }
            musicaAmbiente.start();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠️ No se pudo reproducir la música: " + ruta);
        }
    }

    public static void main(String[] args) {
        reproducir_musica("out/production/INDIANA JONES/Sonidos/737938__ricardoemfield__amazon-forest-only-crickets-and-cicadas.wav", true);
        System.out.println("========BIENVENIDO A INDIANA JONES Y EL TEMPLO DE LAS SOMBRAS ETERNAS========"); //cabezera de bienvenida
        //llama a las funiones
        PrimeraPantalla();
        SegundaPantalla();

    }
    //declaro la primera funcion que muestra la pantalla 1
    static void PrimeraPantalla(){
        int opcion=0;
        while(opcion!=1){
            System.out.println("Te encuentras en la entrada del Templo de las Sombras. Hay dos puertas: ");
            System.out.println("1. Puerta de la derecha");
            System.out.println("2. Puerta de la izquierda");
            System.out.print("Elige una puerta: ");
            opcion = sc.nextInt();

            //este if es para elegir una puerta u otra, de las cuales solo una es correcta

            if(opcion==1){
                System.out.println("Has entrado por la puerta correcta, la otra era una trampa. Pasas a la siguiente zona.\n ");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/243699__ertfelda__hidden-wall-opening.wav");



            }else if(opcion==2){
                System.out.println("=======☠GAME OVER☠=========");
                System.out.println("Era una trampa. Has caido al vacío y has muerto");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");

            }else {
                System.out.println("Opción no válida");
            }
        }

    }






    //declaro la segunda función que muestra la pantalla 2

    static void SegundaPantalla(){
        reproducir_musica("out/production/INDIANA JONES/Sonidos/665195__erokia__gse-1_3-underwater-drone-horror-background-sound.wav", true);
        int opcion;
        boolean JuegoEnCurso = true;
        while(JuegoEnCurso){
            MostarOpcionesSegundaPantalla();
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    SalaAbajo();
                    break;
                case 2:
                    SalaArriba();
                    break;
                case 3:
                    SalaDerecha();
                    JuegoEnCurso = false;
                    break;


            }
        }
    }

    static void MostarOpcionesSegundaPantalla(){
        System.out.println("Te encuentras en una sala con tres puertas");
        System.out.println("1. Sala de abajo");
        System.out.println("2. Sala de arriba(Necesitas Llave)");
        System.out.println("3. Sala de derecha(Necesitas Llave)");
        System.out.println("4.Revisar inventario");
        System.out.print("Elige una opción o acción: ");

    }
    static void SalaAbajo(){
        int v = 0;
        reproducirSonido("out/production/INDIANA JONES/Sonidos/470741__adrian-_-115__pasos-tacon-wav.wav");
        reproducirSonido("out/production/INDIANA JONES/Sonidos/409231__mariana045__puerta-abriendo.wav");
        System.out.println("Entras en la sala y ves un cofre en el cúal se necesita colocar una palabra para abrirlo. Encima del cofre hay un cartel en lo que pone lo siguiente:");
        while (TieneLlave1 == false){
            System.out.print("Tiene ciudad pero no casas, tiene río pero no agua, tiene bosque pero no árboles. ¿Qué es?: ");
            String respuesta = sc.next();
            if(respuesta.equalsIgnoreCase("mapa")){
                System.out.println("¡Correcto! Abres el cofre y obtienes la llave de la sala de arriba.");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/408001__judith136__20.wav");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/787559__interstellarcat__video-game-level-complete-sound-effect.wav");
                TieneLlave1 = true;
            }else {
                System.out.println("¡Incorrecto! Vuelve a intentarlo");
                v++;
                if(v == 3){
                    System.out.println("=======☠GAME OVER☠=========");
                    reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");


                    return;

                }

            }
        }
    }
    static void SalaArriba(){
        if(!TieneLlave1){
            reproducirSonido("out/production/INDIANA JONES/Sonidos/378451__vaguaisa__puerta-cerrada.wav");
            System.out.println("La puerta está cerrada, necesitas una llave.\n");
            return;
        }
        reproducirSonido("out/production/INDIANA JONES/Sonidos/470741__adrian-_-115__pasos-tacon-wav.wav");
        reproducirSonido("out/production/INDIANA JONES/Sonidos/409231__mariana045__puerta-abriendo.wav");
        System.out.println("Usas la llave y entras");
        System.out.println("En la sala, hay tres armas de las cuales solo puedes elegir una");
        System.out.print("1.Látigo ");
        System.out.print("2.Pistola ");
        System.out.print("3.Cuchillo  ");
        System.out.print("Elige un arma: ");
        String arma = sc.next();


        if(arma.equalsIgnoreCase("1")){
            System.out.println("Has elegido el látigo");
            reproducirSonido("out/production/INDIANA JONES/Sonidos/649381__zypce__whip-or-stick-beating.wav");
            System.out.println("Aparecen tres enemigos. Uno con una espada, otro con una pistola y el úlimo sin arma.");
            System.out.println("Puedes desarmar a alguien con el látigo");
            System.out.println("¿A quién desarmas? ");
            System.out.println("1. Enemigo con pistola, 2. Enemigo con espada, 3. Enemigo desarmado");
            int latigo = sc.nextInt();
            if(latigo == 1){
                System.out.println("Desarmas al enemigo de la pistola");
                System.out.println("Te ataca el enemigo de la espada");
                System.out.println("elige que hacer: 1 atacar, 2 esquivar");
                int espada = sc.nextInt();
                if(espada == 2){
                    System.out.println("Esquivas el ataque con éxito");
                    System.out.println("Después de esquivar el ataque, atacas al de la espada");
                    System.out.println("1. Atacar con latigo, 2. Atacar con el puño");
                    int ataque  = sc.nextInt();
                    if(ataque<3 &&  ataque > 0){
                        System.out.println("Derrotastes al enemigo de la espada");
                        System.out.println("Te lanza un puñetazo un enemigo");
                        System.out.println("¿Qué haces? 1. Parar golpe y contraatacar  2. Esquivar");
                        int parar = sc.nextInt();
                        if(parar == 1){
                            System.out.println("Noqueas al atacante");
                            System.out.println("El enemigo desarmado huye");
                            System.out.println("Consigues la llave para la última sala");
                            TieneLlave2 = true;
                        }else if(parar == 2){
                            System.out.println("Esquivas el primer ataque pero te atacan los dos a la vez y te noquean");
                            System.out.println("=======☠GAME OVER☠=========");
                            reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                        }else {
                            System.out.println("Opción incorrecta, elige entre 1 y 2");
                        }
                    }else {
                        System.out.println("Opción incorrecta, elige entre 1 y 2");
                    }
                }else if(espada == 1) {
                    System.out.println("=======☠GAME OVER☠=========");
                    reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                }else{
                    System.out.println("Opción incorrecta, elige entre 1 y 2");
                }
            }else if(latigo >= 2 && latigo <= 3) {
                System.out.println("Te disparan y mueres");
                System.out.println("=======☠GAME OVER☠=========");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
            }else {
                System.out.println("Opción incorrecta, elige entre 1,2 y 3");
            }





        } else if(arma.equalsIgnoreCase("2")) {
            System.out.println("Has elegido la pistola");
            reproducirSonido("out/production/INDIANA JONES/Sonidos/363766__juandaroa02__recargapistola.wav");
            System.out.println("Aparecen tres enemigos. Uno con una espada, otro con una pistola y el úlimo sin arma.");
            System.out.println("Puedes disparar a alguien");
            System.out.println("¿A quién disparar? ");
            System.out.println("1. Enemigo con pistola, 2. Enemigo con espada, 3. Enemigo desarmado");
            int pistola = sc.nextInt();
            if (pistola == 1) {
                System.out.println("Disparas al enemigo de la pistola");
                System.out.println("Te ataca el enemigo de la espada");
                System.out.println("elige que hacer: 1 atacar, 2 esquivar");
                int espada2 = sc.nextInt();
                if (espada2 == 1) {
                    System.out.println("Disparas antes que el enemigo y lo matas");
                    System.out.println("Después de matar al enemigo, te lanza un puñetazo un enemigo");
                    System.out.println("¿Qué haces? 1. disparar  2. Esquivar y golpearle con la culata");
                    int parar = sc.nextInt();
                    if (parar == 2) {
                        System.out.println("Noqueas al atacante");
                        System.out.println("Consigues la llave para la última sala");
                        TieneLlave2 = true;
                    } else if (parar == 1) {
                        System.out.println("se te encasquilla el arma y te noquean");
                        System.out.println("=======☠GAME OVER☠=========");
                        reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                    } else {
                        System.out.println("Opción incorrecta, elige entre 1 y 2");
                    }
                } else if (espada2 == 2) {
                    System.out.println("=======☠GAME OVER☠=========");
                    reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                } else {
                    System.out.println("Opción incorrecta, elige entre 1 y 2");
                }
            } else if (pistola >= 2 && pistola <= 3) {
                System.out.println("Te disparan y mueres");
                System.out.println("=======☠GAME OVER☠=========");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
            } else {
                System.out.println("Opción incorrecta, elige entre 1,2 y 3");
            }




        }else if(arma.equalsIgnoreCase("3")) {
            System.out.println("Has elegido el cuchillo");
            reproducirSonido("out/production/INDIANA JONES/Sonidos/471628__lextao__11_cuchilloafilado.wav");
            System.out.println("Aparecen tres enemigos. Uno con una espada, otro con una pistola y el úlimo sin arma.");
            System.out.println("Puedes atacar a alguien con el cuchillo");
            System.out.println("¿A quién atacas? ");
            System.out.println("1. Enemigo con pistola, 2. Enemigo con espada, 3. Enemigo desarmado");
            int cuchillo = sc.nextInt();
            if (cuchillo == 3) {
                System.out.println("eliminas al enemigo desarmado");
                System.out.println("El enemigo con pistola no tiene angulo de tiro, te ataca el enemigo de la espada");
                System.out.println("elige que hacer: 1 atacar, 2 esquivar");
                int espada = sc.nextInt();
                if (espada == 2) {
                    System.out.println("Esquivas el ataque con éxito");
                    System.out.println("Después de esquivar el ataque, atacas al de la espada");
                    System.out.println("1. Atacar con el cuchillo, 2. Atacar con el puño");
                    int ataque = sc.nextInt();
                    if (ataque < 3 && ataque > 0) {
                        System.out.println("Derrotastes al enemigo de la espada");
                        System.out.println("El enemigo con la pistola te esta apuntando");
                        System.out.println("¿Qué haces? 1.  Esquivar y lanzarle el cuchillo  2. atacar 3. Rezar");
                        int parar = sc.nextInt();
                        if (parar == 1) {
                            System.out.println("el tirador falla el tiro y le das con el cuchillo");
                            System.out.println("recoges el arma");
                            System.out.println("Consigues la llave para la última sala");
                            TieneLlave2 = true;
                        } else if (parar >= 1 && parar <= 3) {
                            System.out.println("Recibes el disparo y mueres");
                            System.out.println("=======☠GAME OVER☠=========");
                            reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                        } else {
                            System.out.println("Opción incorrecta, elige entre 1, 2 y 3");
                        }
                    } else {
                        System.out.println("Opción incorrecta, elige entre 1 y 2");
                    }
                } else if (espada == 1) {
                    System.out.println("=======☠GAME OVER☠=========");
                    reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                } else {
                    System.out.println("Opción incorrecta, elige entre 1 y 2");
                }
            } else if (cuchillo == 1) {
                System.out.println("Te disparan y mueres");
                System.out.println("=======☠GAME OVER☠=========");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
            }else if(cuchillo == 2){
                System.out.println("Te atacan con la espada y mueres");
                System.out.println("=======☠GAME OVER☠=========");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
            }else{
                System.out.println("Opción incorrecta, elige entre 1,2 y 3");
            }







        }

    }
    static void SalaDerecha(){
        if (!TieneLlave2) {
            System.out.println("Necesitas la llave final.\n");
            return;
        }
        sc.nextLine();
        System.out.println("Abres la puerta y entras en la sala final...");
        System.out.print("Un general alemán aparece junto a la vitrina lleno de tesoro.");
        System.out.println("Va vestido con un uniforme militar gris con una bandera alemana. Esta junto a otros dos soldados alemanes");
        System.out.println("Te grita algo pero no lo entiendes: ¡WER DAS LIEST, IST SCHWUL!");
        System.out.println("Se hace un silencio y empieza el combate.\n");

        System.out.println("Empieza el combate contra el primer soldado alemán");
        System.out.println("Se decide en un pares o nones, elige pares o nones: ");
        String juego1 = sc.nextLine();
        System.out.println("Elige un número: ");
        int numero=sc.nextInt();
        System.out.println("El enemigo ha elegido 1");
        if (juego1.equalsIgnoreCase("pares")){
            if(numero%2==1 || numero==1){
                System.out.println("!Has ganado! Pasas al siguiente enemigo.");
            }else if(numero%2==0){
                System.out.println("¡Has perdido!");
                System.out.println("=======☠GAME OVER☠=========");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                return;

            }


        }else if(juego1.equalsIgnoreCase("nones")){
            if(numero%2==0){
                System.out.println("!Has ganado! Pasas al siguiente enemigo.");
            }else {
                System.out.println("¡Has perdido!");
                System.out.println("=======☠GAME OVER☠=========");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                return;

            }
        }
        System.out.println("Empieza el combate contra el primer soldado alemán");
        System.out.println("Se decide en un pares o nones, elige pares o nones: ");
        sc.nextLine();
        String juego = sc.nextLine();
        System.out.println("Elige un número: ");
        int numero3=sc.nextInt();
        System.out.println("El enemigo ha elegido 2");
        if (juego.equalsIgnoreCase("pares")){
            if(numero3%2==0){
                System.out.println("!Has ganado! Pasas al combate contra el jefe.");
            }else if(numero3%2==1){
                System.out.println("¡Has perdido!");
                System.out.println("=======☠GAME OVER☠=========");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                return;

            }


        }else if(juego.equalsIgnoreCase("nones")){
            if(numero3%2==1){
                System.out.println("!Has ganado! Pasas al combate contra el jefe.");
            }else {
                System.out.println("¡Has perdido!");
                System.out.println("=======☠GAME OVER☠=========");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
                return;

            }
        }
        combatejefe();
    }
    static void combatejefe(){
        int g=0;
        int p=0;
        System.out.println("el combate contra el jefe se decidira en piedra papel tijera al mejor de 3");
        do{
            System.out.println("elige que sacar 1. piedra 2.papel 3.tijera");
            int numero2 = sc.nextInt();

            Random respuesta = new Random();
            int numero = respuesta.nextInt(3);
            numero= numero+1;
            String eleccionJefe;
            String Tueleccion;
            if (numero == 1) {
                eleccionJefe = "Piedra";
            } else if (numero == 2) {
                eleccionJefe = "Papel";
            } else {
                eleccionJefe = "Tijera";
            }
            if (numero2 == 1) {
                Tueleccion = "Piedra";
            } else if (numero2 == 2) {
                Tueleccion= "Papel";
            } else {
                Tueleccion = "Tijera";
            }
            System.out.println("La jefe eligió: " + eleccionJefe);
            if(numero==numero2){
                System.out.println("empate");
            }else if(numero2==numero+1||numero2==numero-2){
                System.out.println("ganas la ronda");
                g++;
            }else{
                System.out.println("pierdes la ronda");
                p++;
            }
        }while(g!=3 && p!=3);
        if(g==3){
            System.out.println("Has dejado al jefe en el suelo derrotado");
            finales();
        }else if(p==3){
            System.out.println("=======☠GAME OVER☠=========");
            reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");
            return;
        }
    }
    static void finales(){
        System.out.println("Despues de dejar al jefe en el suelo vas a la vitrina llena de tesoros entre ellos:");
        System.out.println("¡EL OJO DE OSIRIS!");
        System.out.println("El ojo de osiris te llama y te sientes atraido hacia el: 1. Cedes a la tentancion 2. Te mantienes firme y sigues explorando");
        int n=sc.nextInt();
        if(n==1){
            System.out.println("En el momento en el que tocas el ojo de osiris tu alma es absorbida para toda la");
            System.out.println("¿eternidad?");
            System.out.println("=======☠GAME OVER☠=========");

        }else{
            System.out.println("Explorando la sala encuentras en la pared un texto grabado: ");
            System.out.println("オシリスの眼に触るる者、永劫の狭間を彷徨ふべし。");
            System.out.println("El jefe malherido utiliza su ultimas fuerzas para llegar al ojo de osiris");
            System.out.println("en el momento en el que lo tocas el ojo de osiris su alma es absorbida");
            System.out.println("El texto de la pared traducida es: La persona que toque el Ojo de Osiris, está destinada a vagar por el umbral de la eternidad.");
            System.out.println("Despues de tocar el Ojo de Osiris el templo comienza a destruirse");
            System.out.println("1.Sales corriendo 2.Intentas llevarte gran parte del tesoro contigo");
            int n2=sc.nextInt();
            if(n2==2){
                System.out.println("=======☠GAME OVER☠=========");
                System.out.println("Eres aplastado por el templo");
                System.out.println("⣿⣿⡿⠟⠋⠉⣁⣀⠐⠒⠀⠤⢭⣉⡛⠻⢿⣿⣿⣿\n" +
                        "⡟⢁⣴⣶⣿⣿⣿⣿⣿⣿⣷⣶⣦⣄⠁⠀⠀⠈⠙⢿\n" +
                        "⡀⢷⣽⡿⠿⠿⣿⡿⣿⣿⡿⠿⠿⣿⣿⣦⠀⠐⢂⠈\n" +
                        "⣇⢸⣿⢲⣿⡷⢸⣿⢰⣾⡇⣿⣷⠈⣿⡿⢁⠂⠀⣼\n" +
                        "⣿⠀⣿⡜⣉⠡⣾⣿⢸⣿⡇⠛⣋⣴⢿⠁⠀⠀⣼⣿\n" +
                        "⣿⡆⢻⣧⢻⣧⠘⡿⠸⢿⡇⢸⣿⣿⡇⡀⠀⣰⣿⣿\n" +
                        "⣿⣇⠸⣿⣾⡿⠿⠿⠶⠾⠷⣿⣿⣿⠀⠂⢀⣿⣿⣿\n" +
                        "⣿⣿⠀⣿⣿⣿⣛⣛⣛⣛⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿\n" +
                        "⣿⡿⠆⠿⠽⠿⠷⠿⠿⠭⠽⢿⣿⠇⠇⢀⡌⢿⣿⣿\n" +
                        "⣿⢱⣥⣭⣽⠟⠛⠓⣒⣒⣒⣒⣒⣒⠊⠥⢀⡄⢹⣿\n" +
                        "⣇⣛⣋⣉⠉⠉⠿⠿⠯⠽⠿⠷⠾⠿⠇⣀⣤⣶⣿⣿");
                reproducirSonido("out/production/INDIANA JONES/Sonidos/435203__lilmati__game-over-03-voices.wav");

            }else if(n2==1){
                System.out.println("Indiana Jones logró saltar al exterior justo cuando el templo comenzó a hundirse entre estruendos. Exhausto, observó cómo las ruinas se perdían en una nube de polvo. “Otro día más en la oficina”, murmuró mientras se alejaba con una media sonrisa.");
            }



        }

    }
}
