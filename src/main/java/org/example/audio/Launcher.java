package org.example.audio;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

public class Launcher {

    // see many more examples at:
    // http://www.jfugue.org/examples.html

    // see the complete guide at:
    // http://www.jfugue.org/4/jfbmrkklprpp/TheCompleteGuideToJFugue-v1.pdf

    public static void main(String[] args) {
        simpleExample();
        sleep(500); // 500ms = sleep for 0.5 seconds

        multiple_voices_instruments_rests_chords_and_durations();
        sleep(500);

        introToPatterns1();
        sleep(500);

        introToPatterns2();
        sleep(500);

        introToChordProgressions();
        sleep(500);

        advancedRhythms();
    }

    public static void simpleExample() {
        Player player = new Player();
        player.play("C D E F G A B");
    }

    public static void multiple_voices_instruments_rests_chords_and_durations() {
        Player player = new Player();
        player.play("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq   V1 I[Flute] Rw | Rw | GmajQQQ CmajQ");
    }

    public static void introToPatterns1() {
        Pattern p1 = new Pattern("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq");
        Pattern p2 = new Pattern("V1 I[Flute] Rw     | Rw     | GmajQQQ  CmajQ");
        Player player = new Player();
        player.play(p1, p2);
    }

    public static void introToPatterns2() {
        Pattern p1 = new Pattern("Eq Ch. | Eq Ch. | Dq Eq Dq Cq").setVoice(0).setInstrument("Piano");
        Pattern p2 = new Pattern("Rw     | Rw     | GmajQQQ  CmajQ").setVoice(1).setInstrument("Flute");
        Player player = new Player();
        player.play(p1, p2);
    }

    public static void introToChordProgressions() {
        ChordProgression cp = new ChordProgression("I IV V");

        Chord[] chords = cp.setKey("C").getChords();
        for (Chord chord : chords) {
            System.out.print("Chord "+chord+" has these notes: ");
            Note[] notes = chord.getNotes();
            for (Note note : notes) {
                System.out.print(note+" ");
            }
            System.out.println();
        }

        Player player = new Player();
        player.play(cp);
    }

    public static void advancedRhythms() {
        Rhythm rhythm = new Rhythm()
                .addLayer("O..oO...O..oOO..") // This is Layer 0
                .addLayer("..S...S...S...S.")
                .addLayer("````````````````")
                .addLayer("...............+") // This is Layer 3
                .addOneTimeAltLayer(3, 3, "...+...+...+...+") // Replace Layer 3 with this string on the 4th (count from 0) measure
                .setLength(4); // Set the length of the rhythm to 4 measures
        new Player().play(rhythm.getPattern().repeat(2)); // Play 2 instances of the 4-measure-long rhythm
    }

    // pauses the program for "millisecondsToSleep" milliseconds.
    // Since Thread.sleep() requires users handle an error, this method just ignores it for simplicity's sake
    public static void sleep(int millisecondsToSleep) {
        try {
            Thread.sleep(millisecondsToSleep);
        } catch (InterruptedException ignored) {
            // do nothing
        }
    }

}
