package com.aleecoder.view;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

/**
 * 声音类
 * @author HuanyuLee
 * @date 2022/8/4
 */
public class Audio {
    byte[] b = new byte[1024 * 1024 * 15];
    private AudioFormat audioFormat = null;
    private SourceDataLine sourceDataLine = null;
    private AudioInputStream audioInputStream = null;

    public Audio(String fileName) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Audio.class.getClassLoader().getResource(fileName)));
            audioFormat = audioInputStream.getFormat();
            DataLine.Info dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
            //FloatControl volctrl=(FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            //volctrl.setValue(-40);//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        try {
            while (true) {
                int len;
                sourceDataLine.open(audioFormat, 1024 * 1024 * 15);
                sourceDataLine.start();
                //System.out.println(audioInputStream.markSupported());
                audioInputStream.mark(12358946);
                while ((len = audioInputStream.read(b)) > 0) {
                    sourceDataLine.write(b, 0, len);
                }
                audioInputStream.reset();
                sourceDataLine.drain();
                sourceDataLine.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        try {
            byte[] b = new byte[1024 * 5];
            int len;
            sourceDataLine.open(audioFormat, 1024 * 5);
            sourceDataLine.start();
            //System.out.println(audioInputStream.markSupported());
            audioInputStream.mark(12358946);
            while ((len = audioInputStream.read(b)) > 0) {
                sourceDataLine.write(b, 0, len);
            }
            // audioInputStream.reset();
            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            audioInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
