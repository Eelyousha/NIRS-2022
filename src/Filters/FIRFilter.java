package Filters;

import Inter.IFIRFil;


import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * Класс - КИХ-фильтр

 */
public class FIRFilter implements Callable<short[]>, IFIRFil {
    // Количество коэффициентов фильтра (порядок фильтра)
    private int countCoefs;
    // Массив коэффициентов фильтра
    private double[] coefsFilter;

    private int lengthOfInputSignal;

    // Входной и выходные сигналы
    private short[] inputSignal;
    private short[] outputSignal;

    // Усиление нашего фильтра (будет использоваться в дальнейшем для эквалайзера)
    private double gain;

    // Конструктор, который инициализирует КИХ-фильтр

    private FIRFilter(double[] coefsFilter, short[] inputSignal) {
        this.coefsFilter = coefsFilter;
        countCoefs = coefsFilter.length;

        this.inputSignal = inputSignal;
        this.outputSignal = new short[inputSignal.length];

        this.lengthOfInputSignal = inputSignal.length;

        this.gain = 1;
    }

    /**
     * Укороченный конструктор, в котором передаётся только размер буфера
     * @param lengthOfInputSignal - длина массива входных данных
     */
    private FIRFilter(int lengthOfInputSignal) {
        this.lengthOfInputSignal = lengthOfInputSignal;
        this.gain = 1;

        this.outputSignal = new short[lengthOfInputSignal];
    }

    // Метод, осуществляющий инициализацию
    public static FIRFilter init(double[] coefsFilter, short[] inputSignal) {
        return new FIRFilter(coefsFilter, inputSignal);
    }

    /**
     * Метод, который формирует конструктор класса Filter
     * @param lengthOfInputSignal - длина входного сигнала
     * @return - возвращает фильтр
     */
    public static FIRFilter init(int lengthOfInputSignal) {
        return new FIRFilter(lengthOfInputSignal);
    }

    /**
     * Внешняя настройка фильтра
     * @param coefsFilter - коэффициенты фильтра
     * @param inputSignal - входной сигнал
     */
    @Override
    public void setSettingsFilter(double[] coefsFilter, short[] inputSignal) {
        this.inputSignal = inputSignal;
        this.coefsFilter = coefsFilter;
        this.countCoefs = coefsFilter.length;

        // Эта строчка важна. Или можно попробовать обнулить имеющийся массив
        Arrays.fill(this.outputSignal, (short) 0);
    }

    /**
     * Метод, который осуществляет настройку параметров фильтра: для данного случая - только вставку коэффициентов
     * @param coefsFilter - коэффициенты фильтра
     */
    @Override
    public void setSettingsFilter(double[] coefsFilter) {
        this.coefsFilter = coefsFilter;
        this.countCoefs = coefsFilter.length;
    }

    /**
     * Метод, который вставляет входной сигнал в фильтр
     * @param inputSignal - входной сигнал
     */
    @Override
    public void setInputSignal(short[] inputSignal) {
        this.inputSignal = inputSignal;

        // Обнуляем массив итоговых значений
        Arrays.fill(this.outputSignal, (short) 0);
    }

    /**
     * Метод, который вставляет уровень усиления сигнала (имитация движения ползунков)
     * @param gain - значение усиления (от 0 до 1)
     */
    @Override
    public void setGain(double gain) {
        this.gain = gain;
    }

    // Метод, который осуществляет фильтрацию данных методом свёртки

    private void convolution() {
        int multiplication;
        for (int i = 0; i < inputSignal.length - countCoefs; ++i) {
            for (int j = 0; j < this.countCoefs; ++j) {
                // Используем формулу для КИХ-фильтра
                multiplication = (int) (inputSignal[i] * coefsFilter[j]);
                outputSignal[i + j] += gain * (short) (multiplication);
            }
        }

    }

    /**
     * Метод, который возвращает нашу свёртку
     * @return итоговый массив short[]
     */
    @Override
    public short[] call() throws Exception {
        this.convolution();
        return this.outputSignal;
    }
}
