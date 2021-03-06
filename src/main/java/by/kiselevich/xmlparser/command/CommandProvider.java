package by.kiselevich.xmlparser.command;

import by.kiselevich.xmlparser.command.home.ShowHomePage;
import by.kiselevich.xmlparser.command.language.ChangeLanguage;
import by.kiselevich.xmlparser.command.parser.ShowParsedXml;
import by.kiselevich.xmlparser.command.parser.ShowXmlUploadForm;
import by.kiselevich.xmlparser.command.signing.*;
import by.kiselevich.xmlparser.command.wrongrequest.ShowWrongRequestPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {

    private static final Logger LOG = LogManager.getLogger(CommandProvider.class);

    private final Map<GuestCommandName, Command> guestCommandMap = new EnumMap<>(GuestCommandName.class);
    private final Map<UserCommandName, Command> userCommandMap = new EnumMap<>(UserCommandName.class);

    public CommandProvider() {
        guestCommandMap.put(GuestCommandName.SIGN_IN, new SignIn());
        guestCommandMap.put(GuestCommandName.SIGN_UP, new SignUp());
        guestCommandMap.put(GuestCommandName.HOME, new ShowHomePage());
        guestCommandMap.put(GuestCommandName.WRONG_REQUEST, new ShowWrongRequestPage());
        guestCommandMap.put(GuestCommandName.CHANGE_LANGUAGE, new ChangeLanguage());

        userCommandMap.put(UserCommandName.SIGN_OUT, new SignOut());
        userCommandMap.put(UserCommandName.XML_UPLOAD_FORM, new ShowXmlUploadForm());
        userCommandMap.put(UserCommandName.PARSE_XML, new ShowParsedXml());
        userCommandMap.put(UserCommandName.HOME, new ShowHomePage());
        userCommandMap.put(UserCommandName.WRONG_REQUEST, new ShowWrongRequestPage());
        userCommandMap.put(UserCommandName.CHANGE_LANGUAGE, new ChangeLanguage());
    }

    public Command getCommand(String commandNameString, UserRole userRole) {
        if (commandNameString == null) {
            LOG.warn("Command is null");
            return guestCommandMap.get(GuestCommandName.HOME);
        }

        Command command = null;
        switch (userRole) {
            case GUEST:
                GuestCommandName guestCommandName;
                try {
                    guestCommandName = GuestCommandName.valueOf(commandNameString.toUpperCase());
                    command = guestCommandMap.get(guestCommandName);
                } catch (IllegalArgumentException e) {
                    LOG.warn(e);
                    command = guestCommandMap.get(GuestCommandName.WRONG_REQUEST);
                }
                break;
            case USER:
                UserCommandName userCommandName;
                try {
                    userCommandName = UserCommandName.valueOf(commandNameString.toUpperCase());
                    command = userCommandMap.get(userCommandName);
                } catch (IllegalArgumentException e) {
                    LOG.warn(e);
                    command = userCommandMap.get(UserCommandName.WRONG_REQUEST);
                }
                break;
            default:
                command = guestCommandMap.get(GuestCommandName.WRONG_REQUEST);
                break;
        }
        return command;
    }
}
