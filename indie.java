import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL; // Import necesario para cargar recursos
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;


public class indie4 {
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

    // --- MÉTODOS DE AUDIO 
    public static void reproducirSonido(String ruta) {
        try {
            // Usamos getResource para buscar el archivo dentro del classpath (JAR o carpeta de compilación)
            URL url = indie3.class.getResource(ruta);

            if (url == null) {
                System.out.println("⚠️ Error: No se encuentra el archivo de sonido: " + ruta);
                System.out.println("Asegúrate de que la carpeta 'sonidos' está en la raíz del código fuente (src).");
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

            // Espera hasta que termine de reproducirse (pausa el hilo actual)
            // Nota: sleep pausa el juego. Si no quieres que pause, quita esta línea, pero el clip podría cerrarse antes de sonar.
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            clip.close();

        } catch (Exception e) {
            // e.printStackTrace(); // Descomenta para ver errores detallados
            System.out.println("⚠️ Error al intentar reproducir el sonido: " + ruta);
        }
    }

    public static void detener_musica() {
        try {
            if (musicaAmbiente != null) {
                if (musicaAmbiente.isRunning()) {
                    musicaAmbiente.stop();
                }
                musicaAmbiente.flush();
                musicaAmbiente.close();
                musicaAmbiente = null;
            }
        } catch (Exception e) {
            System.out.println("Error al detener la música: " + e.getMessage());
        }
    }

    public static void reproducir_musica(String ruta, boolean enBucle) {
        try {
            detener_musica(); 

            URL url = indie3.class.getResource(ruta);

            if (url == null) {
                System.out.println("Error: No se encuentra el archivo de música: " + ruta);
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            musicaAmbiente = AudioSystem.getClip();
            musicaAmbiente.open(audio);

            if (enBucle) {
                musicaAmbiente.loop(Clip.LOOP_CONTINUOUSLY);
            }
            musicaAmbiente.start();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo reproducir la música de fondo: " + ruta);
        }
    }
    // -----------------------------------------------------


    public static void main(String[] args) {

        System.out.println("========BIENVENIDO A INDIANA JONES Y EL TEMPLO DE LAS SOMBRAS ETERNAS========");
        System.out.println("___    ____      \n" +
                "                                                                             \n" +
                "                        ______, _ gggggggg  ,ggggpga gggg, [##@  _W###Mg,    \n" +
                "  _____aggggg ggg  p### ########  ######## J########@ ###b_[##B d##@\"9##b_   \n" +
                "  ########### ###B,B### ##444##### l###@  Q###@ 'M### @###@&##B ###@qd###@,  \n" +
                "   \"\"Y###    #####@W### ##    #### l###@ ]#####ggM#### #######B ####C#####@  \n" +
                "     l###    ########## ##   _#### l###@ ######B@##### QD ####B ##@   \"####  \n" +
                "     l###    ##@7###### ##ggg##### j###@_ @####  #### QD `\"##@ \"\"           \n" +
                "     l###    ##D`###### ########@ ######## ##@     \"\"\"                       \n" +
                "     j###    ##D  7#### ###@4\"\"    44\"\"\"\"\"                                   \n" +
                "  ##########_ #@   \"##@                                                      \n" +
                "  ##########P                               ___      ___ ________   ggggg__  \n" +
                "   \"\"\"\"           ________         _____g__:# #p__  ;##M ######### ##\"\"\"\"4#B \n" +
                "                  ############   ##########d& ####__l### #@____   @###ggg___ \n" +
                "                  @###444@###@ 2####9  7####@ ########## #####@   ___   \"###@\n" +
                "                         Q###@ #####9  l##### #B'M###### #D  ____ M#Mggg###B \n" +
                "                         Q###@ ######_a#####@ #B   \"#### ######### \"\"##@\"\"   \n" +
                "                 ggpg    Q###@ T###########@\" #4    `\"4C \"\"\"\"\"               \n" +
                "                 ###@,  ,###@\"   \"@######\"\"                                  \n" +
                "                   \"4@###4#");

    
        reproducirSonido("/sonidos/518308__mrthenoronha__world-clear-8-bit (1).wav");
        // Un pequeño sleep para que no se solape el inicio de la música con el sonido de intro
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        reproducir_musica("/sonidos/737938__ricardoemfield__amazon-forest-only-crickets-and-cicadas.wav", true);
        System.out.println("Modo: 1. Facil, 2.Normal");
        int modo = sc.nextInt();
        PrimeraPantalla();
        if (modo == 1) {
            SegundaPantallafacil();
        } else if (modo == 2) {
            SegundaPantalla();
        }
    }

    static void PrimeraPantalla() {
        int opcion = 0;
        while (opcion != 1) {
            System.out.println("Te encuentras en la entrada del Templo de las Sombras. Hay dos puertas: ");
            System.out.println("1. Puerta de la derecha");
            System.out.println("2. Puerta de la izquierda");
            System.out.print("Elige una puerta: ");
            opcion = sc.nextInt();

            if (opcion == 1) {
                System.out.println("Has entrado por la puerta correcta, la otra era una trampa. Pasas a la siguiente zona.\n ");
                reproducirSonido("/sonidos/243699__ertfelda__hidden-wall-opening.wav");


            } else if (opcion == 2) {
                System.out.println("=======☠GAME OVER☠=========");
                System.out.println("Era una trampa. Has caído al vacío y has muerto");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");

            } else {
                System.out.println("Opción no válida");
            }
        }

    }


    static void SegundaPantalla() {
        reproducir_musica("/sonidos/665195__erokia__gse-1_3-underwater-drone-horror-background-sound.wav", true);
        int opcion;
        boolean JuegoEnCurso = true;

        while (JuegoEnCurso) {
            MostarOpcionesSegundaPantalla();
            opcion = sc.nextInt();
            switch (opcion) {
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
                case 4:
                    MostrarInventario();
                    break;
                case 5:
                    Salasecreta();
                    break;

            }
        }
    }

    static void SegundaPantallafacil() {
        reproducir_musica("/sonidos/665195__erokia__gse-1_3-underwater-drone-horror-background-sound.wav", true);
        int opcion;
        boolean JuegoEnCurso = true;

        while (JuegoEnCurso) {
            MostarOpcionesSegundaPantalla();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    SalaAbajofacil();
                    break;
                case 2:
                    SalaArriba();
                    break;
                case 3:
                    SalaDerechafacil();
                    JuegoEnCurso = false;
                    break;
                case 4:
                    MostrarInventario();
                    break;
                case 5:
                    Salasecreta();
                    break;

            }
        }
    }


    static void MostarOpcionesSegundaPantalla() {
        System.out.println("Te encuentras en una sala con tres puertas");
        System.out.println("1. Sala de abajo");
        System.out.println("2. Sala de arriba(Necesitas llave)");
        System.out.println("3. Sala de derecha(Necesitas llave)");
        System.out.println("4.Revisar inventario");
        System.out.print("Elige una opción o acción: ");

    }

    static void SalaAbajofacil() {
        int v = 0;
        reproducirSonido("/sonidos/470741__adrian-_-115__pasos-tacon-wav.wav");
        reproducirSonido("/sonidos/409231__mariana045__puerta-abriendo.wav");
        System.out.println("Entras en la sala y ves un cofre en el cual se necesita colocar una palabra para abrirlo. Encima del cofre hay un cartel en lo que pone lo siguiente:");
        while (TieneLlave1 == false) {
            System.out.print("Tiene ciudad pero no casas, tiene río pero no agua, tiene bosque pero no árboles. ¿Qué es?: ");
            String respuesta = sc.next();
            if (respuesta.equalsIgnoreCase("mapa")) {
                System.out.println("¡Correcto! Abres el cofre y obtienes la llave de la sala de arriba.");
                reproducirSonido("/sonidos/408001__judith136__20.wav");
                reproducirSonido("/sonidos/787559__interstellarcat__video-game-level-complete-sound-effect.wav");
                TieneLlave1 = true;
            } else {
                System.out.println("¡Incorrecto! Vuelve a intentarlo");
                v++;
                if (v == 3) {
                    System.out.println("=======☠GAME OVER☠=========");
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
                    reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                }
            }
        }
    }

    static void SalaAbajo() {
        int v = 0;
        reproducirSonido("/sonidos/470741__adrian-_-115__pasos-tacon-wav.wav");
        reproducirSonido("/sonidos/409231__mariana045__puerta-abriendo.wav");
        System.out.println("Entras en la sala y ves un cofre en el cual se necesita colocar una palabra para abrirlo. Encima del cofre hay un cartel en lo que pone lo siguiente:");
        while (TieneLlave1 == false) {
            System.out.print("Tiene ciudad pero no casas, tiene río pero no agua, tiene bosque pero no árboles. ¿Qué es?: ");
            String respuesta = sc.next();
            if (respuesta.equalsIgnoreCase("mapa")) {
                System.out.println("¡Correcto! Abres el cofre y obtienes la llave de la sala de arriba.");
                reproducirSonido("/sonidos/408001__judith136__20.wav");
                reproducirSonido("/sonidos/787559__interstellarcat__video-game-level-complete-sound-effect.wav");
                TieneLlave1 = true;
            } else {
                System.out.println("¡Incorrecto! Vuelve a intentarlo");
                v++;
                if (v == 3) {
                    System.out.println("=======☠GAME OVER☠=========");
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
                    reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                    return;
                }
            }
        }
    }

    static void SalaArriba() {
        if (!TieneLlave1) {
            reproducirSonido("/sonidos/378451__vaguaisa__puerta-cerrada.wav");
            System.out.println("La puerta está cerrada, necesitas una llave.\n");
            return;
        }
        reproducirSonido("/sonidos/470741__adrian-_-115__pasos-tacon-wav.wav");
        reproducirSonido("/sonidos/409231__mariana045__puerta-abriendo.wav");
        System.out.println("Usas la llave y entras");
        System.out.println("En la sala, hay tres armas de las cuales solo puedes elegir una");
        System.out.print("1.Látigo ");
        System.out.print("2.Pistola ");
        System.out.print("3.Cuchillo  ");
        System.out.print("Elige un arma: ");
        String arma = sc.next();


        if (arma.equalsIgnoreCase("1")) {
            System.out.println("Has elegido el látigo");
            TieneArma1 = true;
            TieneArma2 = false;
            TieneArma3 = false;
            reproducirSonido("/sonidos/649381__zypce__whip-or-stick-beating.wav");
            System.out.println("Aparecen tres enemigos. Uno con una espada, otro con una pistola y el úlimo sin arma.");
            System.out.println("Puedes desarmar a alguien con el látigo");
            System.out.println("¿A quién desarmas? ");
            System.out.println("1. Enemigo con pistola, 2. Enemigo con espada, 3. Enemigo desarmado");
            int latigo = sc.nextInt();
            if (latigo == 1) {
                System.out.println("Desarmas al enemigo de la pistola");
                System.out.println("Te ataca el enemigo de la espada");
                System.out.println("elige qué hacer: 1 atacar, 2 esquivar");
                int espada = sc.nextInt();
                if (espada == 2) {
                    System.out.println("Esquivas el ataque con éxito");
                    System.out.println("Después de esquivar el ataque, atacas al de la espada");
                    System.out.println("1. Atacar con latigo, 2. Atacar con el puño");
                    int ataque = sc.nextInt();
                    if (ataque < 3 && ataque > 0) {
                        System.out.println("Derrotaste al enemigo de la espada");
                        System.out.println("Te lanza un puñetazo un enemigo");
                        System.out.println("¿Qué haces? 1. Parar golpe y contraatacar  2. Esquivar");
                        int parar = sc.nextInt();
                        if (parar == 1) {
                            System.out.println("Noqueas al atacante");
                            System.out.println("El enemigo desarmado huye");
                            System.out.println("Consigues la llave para la última sala");
                            TieneLlave2 = true;
                        } else if (parar == 2) {
                            System.out.println("Esquivas el primer ataque pero te atacan los dos a la vez y te noquean");
                            System.out.println("=======☠GAME OVER☠=========");
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
                            reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");

                        } else {
                            System.out.println("Opción incorrecta, elige entre 1 y 2");
                        }
                    } else {
                        System.out.println("Opción incorrecta, elige entre 1 y 2");
                    }
                } else if (espada == 1) {
                    System.out.println("=======☠GAME OVER☠=========");
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
                    reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");

                } else {
                    System.out.println("Opción incorrecta, elige entre 1 y 2");
                }
            } else if (latigo >= 2 && latigo <= 3) {
                System.out.println("Te disparan y mueres");
                System.out.println("=======☠GAME OVER☠=========");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");

            } else {
                System.out.println("Opción incorrecta, elige entre 1,2 y 3");
            }


        } else if (arma.equalsIgnoreCase("2")) {
            System.out.println("Has elegido la pistola");
            TieneArma1 = false;
            TieneArma2 = true;
            TieneArma3 = false;
            reproducirSonido("/sonidos/363766__juandaroa02__recargapistola.wav");
            System.out.println("Aparecen tres enemigos. Uno con una espada, otro con una pistola y el úlimo sin arma.");
            System.out.println("Puedes disparar a alguien");
            System.out.println("¿A quién disparar? ");
            System.out.println("1. Enemigo con pistola, 2. Enemigo con espada, 3. Enemigo desarmado");
            int pistola = sc.nextInt();
            if (pistola == 1) {
                System.out.println("Disparas al enemigo de la pistola");
                System.out.println("Te ataca el enemigo de la espada");
                System.out.println("Elige que hacer: 1 atacar, 2 esquivar");
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
                        System.out.println("Se te encasquilla el arma y te noquean");
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
                        System.out.println("=======☠GAME OVER☠=========");
                        reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");

                    } else {
                        System.out.println("Opción incorrecta, elige entre 1 y 2");
                    }
                } else if (espada2 == 2) {
                    System.out.println("=======☠GAME OVER☠=========");
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
                    reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                } else {
                    System.out.println("Opción incorrecta, elige entre 1 y 2");
                }
            } else if (pistola >= 2 && pistola <= 3) {
                System.out.println("Te disparan y mueres");
                System.out.println("=======☠GAME OVER☠=========");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
            } else {
                System.out.println("Opción incorrecta, elige entre 1,2 y 3");
            }


        } else if (arma.equalsIgnoreCase("3")) {
            System.out.println("Has elegido el cuchillo");
            TieneArma1 = false;
            TieneArma2 = false;
            TieneArma3 = true;
            reproducirSonido("/sonidos/471628__lextao__11_cuchilloafilado.wav");
            System.out.println("Aparecen tres enemigos. Uno con una espada, otro con una pistola y el úlimo sin arma.");
            System.out.println("Puedes atacar a alguien con el cuchillo");
            System.out.println("¿A quién atacas? ");
            System.out.println("1. Enemigo con pistola, 2. Enemigo con espada, 3. Enemigo desarmado");
            int cuchillo = sc.nextInt();
            if (cuchillo == 3) {
                System.out.println("Eliminas al enemigo desarmado");
                System.out.println("El enemigo con pistola no tiene angulo de tiro, te ataca el enemigo de la espada");
                System.out.println("Elige que hacer: 1 atacar, 2 esquivar");
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
                            System.out.println("El tirador falla el tiro y le das con el cuchillo");
                            System.out.println("Recoges el arma");
                            System.out.println("Consigues la llave para la última sala");
                            TieneLlave2 = true;
                        } else if (parar >= 1 && parar <= 3) {
                            System.out.println("Recibes el disparo y mueres");
                            System.out.println("=======☠GAME OVER☠=========");
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
                            reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                        } else {
                            System.out.println("Opción incorrecta, elige entre 1, 2 y 3");
                        }
                    } else {
                        System.out.println("Opción incorrecta, elige entre 1 y 2");
                    }
                } else if (espada == 1) {
                    System.out.println("=======☠GAME OVER☠=========");
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
                    reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                } else {
                    System.out.println("Opción incorrecta, elige entre 1 y 2");
                }
            } else if (cuchillo == 1) {
                System.out.println("Te disparan y mueres");
                System.out.println("=======☠GAME OVER☠=========");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
            } else if (cuchillo == 2) {
                System.out.println("Te atacan con la espada y mueres");
                System.out.println("=======☠GAME OVER☠=========");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
            } else {
                System.out.println("Opción incorrecta, elige entre 1,2 y 3");
            }


        }

    }

    static void Salasecreta() {
        System.out.println("Juego creado por Víctor García, Adrián Garde y Santiago Fernández ");
        System.out.println("¡Gracias por jugar!");
    }

    static void SalaDerecha() {
        if (!TieneLlave2) {
            reproducirSonido("/sonidos/378451__vaguaisa__puerta-cerrada.wav");
            System.out.println("Necesitas la llave final.\n");
            return;
        }
        reproducirSonido("/sonidos/470741__adrian-_-115__pasos-tacon-wav.wav");
        reproducirSonido("/sonidos/409231__mariana045__puerta-abriendo.wav");
        sc.nextLine();
        System.out.println("Abres la puerta y entras en la sala final...");
        System.out.print("Un general alemán aparece junto a la vitrina llena de tesoros.");
        System.out.println("Va vestido con un uniforme militar gris con una bandera alemana. Está junto a otros dos soldados alemanes");
        System.out.println("Te grita algo pero no lo entiendes: ¡WER DAS LIEST, IST SCHWUL!");
        System.out.println("Se hace un silencio y empieza el combate.\n");

        System.out.println("Empieza el combate contra el primer soldado alemán");
        System.out.println("Se decide en un pares o nones, elige pares o nones: ");
        sc.nextLine();
        String juego1 = sc.nextLine();
        System.out.println("Elige un número: ");
        int numero = sc.nextInt();
        System.out.println("El enemigo ha elegido 1");
        if (juego1.equalsIgnoreCase("pares")) {
            if (numero % 2 == 1 || numero == 1) {
                System.out.println("¡Has ganado! Pasas al siguiente enemigo.");
            } else if (numero % 2 == 0) {
                System.out.println("¡Has perdido!");
                System.out.println("=======☠GAME OVER☠=========");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                return;

            }


        } else if (juego1.equalsIgnoreCase("nones")) {
            if (numero % 2 == 0) {
                System.out.println("!Has ganado! Pasas al siguiente enemigo.");
            } else {
                System.out.println("¡Has perdido!");
                System.out.println("=======☠GAME OVER☠=========");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                return;

            }
        }
        System.out.println("Empieza el combate contra el primer soldado alemán");
        System.out.println("Se decide en un pares o nones, elige pares o nones: ");
        sc.nextLine();
        String juego = sc.nextLine();
        System.out.println("Elige un número: ");
        int numero3 = sc.nextInt();
        System.out.println("El enemigo ha elegido 2");
        if (juego.equalsIgnoreCase("pares")) {
            if (numero3 % 2 == 0) {
                System.out.println("!Has ganado! Pasas al combate contra el jefe.");
            } else if (numero3 % 2 == 1) {
                System.out.println("¡Has perdido!");
                System.out.println("=======☠GAME OVER☠=========");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                return;

            }


        } else if (juego.equalsIgnoreCase("nones")) {
            if (numero3 % 2 == 1) {
                System.out.println("!Has ganado! Pasas al combate contra el jefe.");
            } else {
                System.out.println("¡Has perdido!");
                System.out.println("=======☠GAME OVER☠=========");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                return;

            }
        }
        combatejefe();
    }
    static void SalaDerechafacil() {
        if (!TieneLlave2) {
            reproducirSonido("/sonidos/378451__vaguaisa__puerta-cerrada.wav");
            System.out.println("Necesitas la llave final.\n");
            return;
        }
        reproducirSonido("/sonidos/470741__adrian-_-115__pasos-tacon-wav.wav");
        reproducirSonido("/sonidos/409231__mariana045__puerta-abriendo.wav");
        sc.nextLine();
        System.out.println("Abres la puerta y entras en la sala final...");
        System.out.print("Un general alemán aparece junto a la vitrina llena de tesoros.");
        System.out.println("Va vestido con un uniforme militar gris con una bandera alemana. Está junto a otros dos soldados alemanes");
        System.out.println("Te grita algo pero no lo entiendes: ¡WER DAS LIEST, IST SCHWUL!");
        System.out.println("Se hace un silencio y empieza el combate.");
        int gan=0;
        String juego1;
        do{
            System.out.println("Empieza el combate contra el primer soldado alemán");
            System.out.println("Se decide en un pares o nones, elige pares o nones: ");
            juego1 = sc.nextLine();
            System.out.println("Elige un número: ");
            int numero = Integer.parseInt(sc.nextLine());
            System.out.println("El enemigo ha elegido 1");
            if (juego1.equalsIgnoreCase("pares")) {
                if (numero % 2 == 1 ) {
                    System.out.println("¡Has ganado! Pasas al siguiente enemigo.");
                    gan=1;
                } else if (numero % 2 == 0) {

                    System.out.println("¡Has perdido!");
                    System.out.println("=======☠GAME OVER☠=========");
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
                    reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");


                }


            } else if (juego1.equalsIgnoreCase("nones")) {
                if (numero % 2 == 0) {
                    System.out.println("!Has ganado! Pasas al siguiente enemigo.");
                    gan=1;
                } else {
                    System.out.println("¡Has perdido!");
                    System.out.println("=======☠GAME OVER☠=========");
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
                    reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");

                }
            }
        }while(gan==0);
        String juego2;
        do{
            gan=0;
            System.out.println("Empieza el combate contra el segundo soldado alemán");
            System.out.println("Se decide en un pares o nones, elige pares o nones: ");
            juego2 = sc.nextLine();
            System.out.println("Elige un número: ");
            int numero3 = Integer.parseInt(sc.nextLine());
            sc.nextLine();
            System.out.println("El enemigo ha elegido 2");
            if (juego2.equalsIgnoreCase("pares")) {
                if (numero3 % 2 == 0) {
                    System.out.println("!Has ganado! Pasas al combate contra el jefe.");
                    gan=1;
                } else if (numero3 % 2 == 1) {
                    System.out.println("¡Has perdido!");
                    System.out.println("=======☠GAME OVER☠=========");
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
                    reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");

                }


            } else if (juego2.equalsIgnoreCase("nones")) {
                if (numero3 % 2 == 1) {
                    System.out.println("!Has ganado! Pasas al combate contra el jefe.");
                    gan=1;
                } else {
                    System.out.println("¡Has perdido!");
                    System.out.println("=======☠GAME OVER☠=========");
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
                    reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");


                }
            }
        }while(gan==0);
        combatejefefacil();
    }

    static void combatejefefacil() {
        int g = 0;
        int p = 0;
        System.out.println("El combate contra el jefe se decidirá en piedra papel tijera al mejor de 3");
        do {
            p=0;
            g=0;
            do {
                System.out.println("Elige qué sacar 1. piedra 2.papel 3.tijera");
                int numero2 = sc.nextInt();

                Random respuesta = new Random();
                int numero = respuesta.nextInt(3);
                numero = numero + 1;
                String eleccionJefe;

                if (numero == 1) {
                    eleccionJefe = "Piedra";
                } else if (numero == 2) {
                    eleccionJefe = "Papel";
                } else {
                    eleccionJefe = "Tijera";
                }

                System.out.println("El jefe eligió: " + eleccionJefe);
                if (numero == numero2) {
                    System.out.println("empate");
                } else if (numero2 == numero + 1 || numero2 == numero - 2) {
                    System.out.println("ganas la ronda");
                    g++;
                } else {
                    System.out.println("pierdes la ronda");
                    p++;
                }
            } while (g < 3 && p < 3);
            if (g == 3) {
                System.out.println("Has dejado al jefe en el suelo derrotado");
                finales();
            } else if (p == 3) {
                System.out.println("=======☠GAME OVER☠=========");
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
                System.out.println("1.volver a empezar la pelea. 2. salir");
                int Of=sc.nextInt();
                if (Of == 2) {
                    return;
                }
            }
        }while (g != 3);
    }
    static void combatejefe () {
        int g = 0;
        int p = 0;
        System.out.println("El combate contra el jefe se decidirá en piedra papel tijera al mejor de 3");
        do {
            System.out.println("Elige qué sacar 1. piedra 2.papel 3.tijera");
            int numero2 = sc.nextInt();

            Random respuesta = new Random();
            int numero = respuesta.nextInt(3);
            numero = numero + 1;
            String eleccionJefe;

            if (numero == 1) {
                eleccionJefe = "Piedra";
            } else if (numero == 2) {
                eleccionJefe = "Papel";
            } else {
                eleccionJefe = "Tijera";
            }

            System.out.println("El jefe eligió: " + eleccionJefe);
            if (numero == numero2) {
                System.out.println("empate");
            } else if (numero2 == numero + 1 || numero2 == numero - 2) {
                System.out.println("ganas la ronda");
                g++;
            } else {
                System.out.println("pierdes la ronda");
                p++;
            }
        } while (g < 3 && p < 3);
        if (g == 3) {
            System.out.println("Has dejado al jefe en el suelo derrotado");
            finales();
        } else if (p == 3) {
            System.out.println("=======☠GAME OVER☠=========");
            System.out.println("⣿⣿⡿⠟⠋⠉⣁⣀⠐⠒⠀⠤⢭⣉⡛⠻⢿⣿⣿⣿\n" +
                    "⡟⢁⣴⣶⣿⣿⣿⣿⣿⣿⣷⣶⣦⣄⠁⠀⠀⠈⠙⢿\n" +
                    "⡀⢷⣽⡿⠿⠿⣿⡿⣿⣿⡿⠿⠿⣿⣿⣦⠀⠐⢂⠈\n" +
                    "⣇⢸⣿⢲⣿⡷⢸⣿⢰⣾⡇⣿⣷⠈⣿⡿⢁⠂⠀⣼\n" +
                    "⣿⠀⣿⡜⣉⠡⣾⣿⢸⣿⡇⠛⣋⣴⢿⠁⠀⠀⣼⣿\n" +
                    "⣿⡆⢻⣧⢻⣧⠘⡿⠸⢿⇇⢸⣿⣿⡇⡀⠀⣰⣿⣿\n" +
                    "⣿⣇⠸⣿⣾⡿⠿⠿⠶⠾⠷⣿⣿⣿⠀⠂⢀⣿⣿⣿\n" +
                    "⣿⣿⠀⣿⣿⣿⣛⣛⣛⣛⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿\n" +
                    "⣿⡿⠆⠿⠽⠿⠷⠿⠿⠭⠽⢿⣿⠇⠇⢀⡌⢿⣿⣿\n" +
                    "⣿⢱⣥⣭⣽⠟⠛⠓⣒⣒⣒⣒⣒⣒⠊⠥⢀⡄⢹⣿\n" +
                    "⣇⣛⣋⣉⠉⠉⠿⠿⠯⠽⠿⠷⠾⠿⠇⣀⣤⣶⣿⣿");
            reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
            return;
        }
    }

    static void finales () {
        System.out.println("Después de dejar al jefe en el suelo vas a la vitrina llena de tesoros entre ellos:");
        System.out.println("¡EL OJO DE OSIRIS!");
        System.out.println("El Ojo de Osiris te llama y te sientes atraido hacia él: 1. Cedes a la tentancion 2. Te mantienes firme y sigues explorando");
        int n = sc.nextInt();
        if (n == 1) {
            System.out.println("En el momento en el que tocas el Ojo de Osiris tu alma es absorbida para toda la");
            System.out.println("¿eternidad?");
            System.out.println("=======☠GAME OVER☠=========");
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
            reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");

        } else {
            System.out.println("Explorando la sala encuentras en la pared un texto grabado: ");
            System.out.println("オシリスの眼に触るる者、永劫の狭間を彷徨ふべし。");
            System.out.println("El jefe malherido utiliza su ultimas fuerzas para llegar al ojo de osiris");
            System.out.println("en el momento en el que lo tocas el ojo de osiris su alma es absorbida");
            System.out.println("El texto de la pared traducida es: La persona que toque el Ojo de Osiris, está destinada a vagar por el umbral de la eternidad.");
            System.out.println("Después de tocar el Ojo de Osiris el templo comienza a destruirse");
            System.out.println("1.Sales corriendo 2.Intentas llevarte gran parte del tesoro contigo");
            int n2 = sc.nextInt();
            if (n2 == 2) {
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
                reproducirSonido("/sonidos/435203__lilmati__game-over-03-voices.wav");
            } else if (n2 == 1) {
                System.out.println("Indiana Jones logró saltar al exterior justo cuando el templo comenzó a hundirse entre estruendos." +
                        " Exhausto, observó cómo las ruinas se perdían en una nube de polvo. \n" +
                        "Otro día más en la oficina”, murmuró mientras se alejaba con una media sonrisa.\n");
                reproducirSonido("/sonidos/518308__mrthenoronha__world-clear-8-bit (1).wav");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⢀⣤⣴⣶⣶⣿⣿⣿⣿⣿⣷⣶⣶⣦⣤⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠰⠛⠋⠉⠉⠉⠀⠀⠀⠀⠀⠀⠉⠉⠉⠙⠛⠄⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣄⣀⣀⡀⠀⠀⠀⠀⠀⠀\n" +
                        "⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦\n" +
                        "⠹⣷⡉⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⡿⠛⠋⣡⣾⠃\n" +
                        "⠀⠈⠻⣶⣀⣸⣿⡇⠀⢀⣭⣭⣭⠉⠉⠉⠉⣭⣭⣤⣤⡄⢸⣿⣇⣀⣶⠟⠁⠀\n" +
                        "⠀⠀⠀⠈⣿⡏⠀⠀⠐⠉⢥⣤⠀⠀⠀⠀⠀⠀⣴⡤⠀⠀⠀⠀⢹⣿⠁⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⢹⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡟⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠉⣿⡇⠀⠶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠶⠀⢸⡿⠉⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠸⣷⡆⠀⠀⠶⢀⣀⣀⣀⣀⡀⠶⠀⠀⢰⣾⠇⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠘⢷⣟⠀⠀⠈⠉⠉⠉⠉⠁⠀⠀⣻⡾⠃⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣷⣟⠀⡀⠘⠃⢀⠀⣻⣾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣷⣤⣤⣾⠿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");


            }

        }
    }

    static void MostrarInventario () {
        System.out.println("\n======== Inventario ========");
        System.out.println("Llave Sala Arriba: " + (TieneLlave1 ? "Sí" : "No"));
        System.out.println("Llave Sala Derecha: " + (TieneLlave2 ? "Sí" : "No"));
        String armaActual = "Ninguna";
        if (TieneArma1) {
            armaActual = "Látigo";
        } else if (TieneArma2) {
            armaActual = "Pistola";
        } else if (TieneArma3) {
            armaActual = "Cuchillo";
        }
        System.out.println("Arma Equipada: " + armaActual);
        System.out.println("\n============================");
    }

}
