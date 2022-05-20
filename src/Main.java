import Audio_Handler.AudioPlay;

import java.io.File;

//class GUI extends Application {
//    final Slider[]opacityLevel  = new Slider[10];
//    final AudioPlay audioPlayer = new AudioPlay(new File("song.wav"));
////    private void addGains() {
////        for (int i = 0; i < 9; i++) {
////            audioPlayer.getFirEqualizer().getFilter((short) i).setGain(0);
////        }
////    }
//    private void initLevels() {
//        for (int i = 0; i < 9; i++) {
//            opacityLevel[i].setMin(0);
//            opacityLevel[i].setMax(1);
//            opacityLevel[i].setValue(0);
//        }
//    }
//
//    @Override
//    public void start(Stage stage) {
//        Group root = new Group();
//        Scene scene = new Scene(root, 600, 400);
//        stage.setScene(scene);
//        for (int i = 0; i < 9; i++) {
//            scene.setRoot(opacityLevel[i]);
//        }
//
//        for (int i = 0; i < 9; i++) {
//            opacityLevel[i].valueProperty().addListener(new ChangeListener<Number>() {
//                public void changed(ObservableValue<? extends Number> ov,
//                                    Number old_val, Number new_val) {
//                    audioPlayer.getFirEqualizer().getFilter((short) i).setGain(new_val.doubleValue());
//                }
//            });
//        }
//        stage.show();
//
//        Thread playThread = new Thread(audioPlayer::play);
//
//        playThread.start();
//        System.out.println("PLAY");
//    }
//
//    public static void run(String[] args) {
//        launch(args);
//    }
//}

public class Main {
//    GridPane filtersGrid = new GridPane();
//    Slider[] opacityLevel = new Slider[10];
//    final AudioPlay audioPlayer = new AudioPlay(new File("song.wav"));
//
//    private void addGains() {
//        for (int i = 1; i < 10; i++) {
//            audioPlayer.getFirEqualizer().getFilter((short) i).setGain(0);
//        }
//    }
//
//    private void addFilter(int i) {
//        opacityLevel[i] = new Slider();
//        opacityLevel[i].valueProperty().addListener((ov, old_val, new_val) ->
//                audioPlayer.getFirEqualizer().getFilter((short) i).setGain(new_val.doubleValue()));
//        opacityLevel[i].setOrientation(Orientation.VERTICAL);
//        filtersGrid.add(opacityLevel[i], i, 0, 1, 1);
//        filtersGrid.add(new Label(String.valueOf(i)), i, 1, 1, 1);
//    }
//
//    @Override
//    public void start(Stage stage) {
//        addGains();
//        Group root = new Group();
//        Scene scene = new Scene(root, 600, 400);
//        stage.setScene(scene);
//        scene.setRoot(filtersGrid);
//        for (int i = 1; i < 10; i++)
//            addFilter(i);
//        stage.show();
//
//        Thread playThread = new Thread(audioPlayer::play);
//
//        playThread.start();
//        System.out.println("PLAY");
//    }

    public static void main(String[] args) {
        try {
            AudioPlay audioPlayer = new AudioPlay(new File("audio.wav"));
            /*
             * Принцип работы эквалайзера таков:
             * 1. Ставим gain-ы в 0 тогда, когда мы хотим убрать нужные частоты
             * 2. gain ставим в 1, если нужно пропускать необходимые частоты
             * На данный момент, эквалайзер является копией того, что было реализовано в ДЗ по ЦОС
             * В конкретном примере мы пропускаем только ФНЧ
             */
//            // Настройки gain-ов фильтра (это всё будет переведено в GUI)
//            audioPlayer.getFirEqualizer().getFilter((short) 1).setGain(0);
//            audioPlayer.getFirEqualizer().getFilter((short) 2).setGain(0);
//            audioPlayer.getFirEqualizer().getFilter((short) 3).setGain(0);
//            audioPlayer.getFirEqualizer().getFilter((short) 4).setGain(0);
//            audioPlayer.getFirEqualizer().getFilter((short) 5).setGain(0);
//            audioPlayer.getFirEqualizer().getFilter((short) 6).setGain(0);
//            audioPlayer.getFirEqualizer().getFilter((short) 7).setGain(0);
//            audioPlayer.getFirEqualizer().getFilter((short) 8).setGain(0);
//            audioPlayer.getFirEqualizer().getFilter((short) 9).setGain(0);
//
//
            Thread playThread = new Thread(audioPlayer::play);

            playThread.start();
            System.out.println("PLAY");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
