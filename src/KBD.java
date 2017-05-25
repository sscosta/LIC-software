import isel.leic.UsbPort;

import java.awt.event.HierarchyEvent;



    public class KBD { // Ler teclas. Métodos retornam ‘0’..’9’,’A’..’F’ ou NONE.
        public static final char NONE = 0;
        private static int KVAL_MASK = 0x10;
        private static int ACK_MASK = 0x80;
        private static int KBD_MASK = 0x0F;
        public static char[] keyboard = {'1', '4','7','*','2','5','8','0','3','6',
                            '9','#',0,0,0,0};
    public static void main(String[] args) {
        char key;
        while (true){
            key =getKey();
            if ( key != 0)
                System.out.println(key);
        }
    }
        // Inicia a classe
        public static void init(){

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
    //return (char) (HAL.readBits(0x0F) + 0x41);
        // Retorna quando a tecla for premida ou NONE após decorrido ‘timeout’ milisegundos.
        public static char waitKey(long timeout) {
            return 0;
        }
    }