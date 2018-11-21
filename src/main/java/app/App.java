package app;

import generator.GeneratorAvailableLanguages;
import manager.Manager;

import java.util.Optional;

/**
 * Soft application.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class App {

    /**
     * Entry point of the program
     * @param args[0] : Seed Transducer File Path
     * @param args[1] : Decoration Table File Path
     * @param args[2] : Target Language
     * @param args[3] : (Optionnal) Generation path
     */
    public static void main (String[] args) {

        if(args.length == 3) {
            Optional<GeneratorAvailableLanguages> languageOp = GeneratorAvailableLanguages.fromLabel(args[2]);
            if(languageOp.isPresent()) {
                Manager.process(args[0], args[1], languageOp.get());
            } else {
                throw new RuntimeException("Undefined given language: " + args[2]);
            }

        } else if (args.length == 4) {
            Optional<GeneratorAvailableLanguages> languageOp = GeneratorAvailableLanguages.fromLabel(args[2]);
            if(languageOp.isPresent()) {
                Manager.process(args[0], args[1], args[3], languageOp.get());
            } else {
                throw new RuntimeException("Undefined given language: " + args[2]);
            }
        } else {
            throw new RuntimeException("Invalid amount of arguments given to program, expecting...");
        }

    }

}
