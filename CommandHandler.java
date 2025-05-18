package svgapp;

public class CommandHandler {
    private final SvgEditor editor;

    public CommandHandler(SvgEditor editor) {
        this.editor = editor;
    }

    public void execute(String input) {
        editor.processCommand(input);
    }
}
