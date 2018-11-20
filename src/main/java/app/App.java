package app;

import generator.GeneratorAvailableLanguages;
import generator.GeneratorManager;
import manager.Manager;
import sun.util.locale.provider.AvailableLanguageTags;

import java.util.Optional;

/**
 * Soft application.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class App {

    public static void main (String[] args) {

        // TODO: get transducer and table from file

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
                Manager.process(args[0], args[1], args[2], languageOp.get());
            } else {
                throw new RuntimeException("Undefined given language: " + args[2]);
            }
        } else {
            // TODO
            throw new RuntimeException("Invalid amount of arguments given to program, expecting...");
        }

    }

}
