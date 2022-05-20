package Effects;


/**
 * Класс является реализацией эффекта "задержка"
 */
public class Hor extends Effect_Handler {
    // Поле - выходной аудио-поток
    private short[] outputAudioStream;

    /**
     * Конструктор класса задержки. Является конструктором по умолчанию
     */
    public Hor() {
        super();
    }

    /**
     * Дополнительный конструктор
     * @param inputAudioStream - входной поток данных
     */
    public Hor(short[] inputAudioStream) {
        super();

        this.setSettings(inputAudioStream);
    }

    /**
     * Сеттер, который устанавливает текущий аудио-поток
     * @param inputAudioStream - текущий аудио-поток (входные данные)
     */
    public void setInputAudioStream(short[] inputAudioStream) {
        this.setSettings(inputAudioStream);
    }

    private void setSettings(short[] inputAudioStream) {
        this.inputAudioStream = inputAudioStream;
        this.outputAudioStream = new short[this.inputAudioStream.length];

        System.arraycopy(this.inputAudioStream, 0, this.outputAudioStream, 0, this.outputAudioStream.length);
    }


    // Метод, который реализует эффект "Хор"
    @Override
    public synchronized short[] createEffect() {
        short amplitude, delayAmplitude;
        int checkFlag, delay = 10000, position = 0;

        for (int i = delay; i < this.outputAudioStream.length; ++i) {
            amplitude = this.outputAudioStream[i];
            delayAmplitude = this.outputAudioStream[position];
            checkFlag = delayAmplitude + (int)(1.1 * amplitude);

            if (checkFlag < Short.MAX_VALUE && checkFlag > Short.MIN_VALUE) {
                delayAmplitude = (short) checkFlag;
                this.outputAudioStream[position] = delayAmplitude;
                ++position;
            }
        }
        return this.outputAudioStream;
    }

    /**
     * Геттер обычного массива входных данных
     * @return - массив входных данных
     */
    @Override
    public short[] getOutputAudioStream() {
        return this.outputAudioStream;
    }
}
