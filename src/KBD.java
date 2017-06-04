public class KBD { // Ler teclas. Métodos retornam ‘0’..’9’,’A’..’F’ ou NONE.
    public static void main(String[] args) {
        HAL.init();
        KBD.init();
        char key;
        while (true){
            key =getKey();
            if ( key != 0)
                System.out.println(key);
        }
        //LCD.init();
        //LCD.write(' ');
    }

    private static int KVAL_MASK = 0x10;
    private static int ACK_MASK = 0x80;
    private static int KBD_MASK = 0x0F;
    private static char[] keyboard;
    private static char[] keyboard_hard = {'1', '4','7','*','2','5','8','0','3','6','9','#',0,0,0,0};
    private static char[] keyboard_simul ={'1', '2','3','4','5','6','7','8','9','*','0','#',0,0,0,0};

    // Inicia a classe
    public static void init(){
         keyboard = (HAL.simul?keyboard_simul:keyboard_hard);
    }

    // Retorna de imediato a tecla premida ou NONE se não há tecla premida.
    public static char getKey() {
        char key=0;
        if(HAL.isBit(KVAL_MASK)) {//verifica se Kval está ativo
            key=keyboard [HAL.readBits(KBD_MASK)];
            HAL.setBits(ACK_MASK); //ativa acknowledge
            while (HAL.isBit(KVAL_MASK));

            HAL.clrBits(ACK_MASK);
        }
        return key;
    }

    // Retorna quando a tecla for premida ou NONE após decorrido ‘timeout’ milisegundos.
    public static char waitKey(long timeout) {
        long waitUntil = System.currentTimeMillis()+timeout;

        char key;
        do {
            key=getKey();
        } while (key==0 & waitUntil>=System.currentTimeMillis()+timeout);
        return key;
    }
}