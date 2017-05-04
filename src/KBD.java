import isel.leic.UsbPort;

/**
 * Created by Samuel on 11/04/2017.
 */


    public class KBD { // Ler teclas. Métodos retornam ‘0’..’9’,’A’..’F’ ou NONE.
        public static final char NONE = 0;
        public static char[] keyboard = {'1', '4','7','*','2','5','8','0','3','6',
                            '9','#',0,0,0,0};
        // Inicia a classe
        public static void init(){
            int s = UsbPort.in();
            System.out.print(s);
            HAL.writeBits(0xFF,0x0);//garante que os bits OUT estão inativos
            while(true){
                if(HAL.isBit(0x10)) {//verifica se Kval está ativo
                    System.out.println(getKey());
                    HAL.writeBits(0x40, 0x40); //ativa acknowledge
                    HAL.clrBits(0x40); //desativa acknowledge
                    //HAL.writeBits(0xFF, 0x0); // apaga os bits OUT
                }
            }

        }
        // Retorna de imediato a tecla premida ou NONE se não há tecla premida.
        public static char getKey() {
            //if(HAL.isBit(0x10))
                return keyboard [HAL.readBits(0x0F)];
            //return NONE;
        }
    //return (char) (HAL.readBits(0x0F) + 0x41);
        // Retorna quando a tecla for premida ou NONE após decorrido ‘timeout’ milisegundos.
        public static char waitKey(long timeout) {
            return 0;
        }
    }