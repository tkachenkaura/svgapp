package svgapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SvgEditor editor = new SvgEditor();
        CommandHandler handler = new CommandHandler(editor);

        editor.printHelp();

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }
            handler.execute(input);
            System.out.print("> ");
        }
        scanner.close();
    }
}

