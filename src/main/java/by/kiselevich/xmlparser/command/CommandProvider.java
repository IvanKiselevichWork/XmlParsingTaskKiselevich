package by.kiselevich.xmlparser.command;

import by.kiselevich.xmlparser.command.home.ShowHomePage;
import by.kiselevich.xmlparser.command.parser.ShowParsedXml;
import by.kiselevich.xmlparser.command.parser.ShowXmlUploadForm;
import by.kiselevich.xmlparser.command.wrongrequest.ShowWrongRequestPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {

    private static final Logger LOG = LogManager.getLogger(CommandProvider.class);

    private final Map<CommandName, Command> commandMap = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        commandMap.put(CommandName.PARSE_XML, new ShowParsedXml());
        commandMap.put(CommandName.XML_UPLOAD_FORM, new ShowXmlUploadForm());
        commandMap.put(CommandName.HOME, new ShowHomePage());
        commandMap.put(CommandName.WRONG_REQUEST, new ShowWrongRequestPage());

    }

    public Command getCommand(String name) {
        if (name == null) {
            LOG.warn("Command is null");
            return commandMap.get(CommandName.HOME);
        }

        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = commandMap.get(commandName);
        } catch (IllegalArgumentException e) {
            LOG.warn(e);
            command = commandMap.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
