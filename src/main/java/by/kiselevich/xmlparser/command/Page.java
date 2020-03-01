package by.kiselevich.xmlparser.command;

public enum Page {
    XML_PARSER_UPLOAD_FORM("/jsp/xml_parser_upload_form.jsp"),
    SHOW_PARSED_XML("/jsp/parsed_xml.jsp"),
    HOME("/jsp/home.jsp"),
    SIGN_UP_FORM("/jsp/sign_up_form.jsp"),
    SIGN_IN_FORM("/jsp/sign_in_form.jsp"),
    WRONG_REQUEST("/jsp/wrong_request.jsp");

    private String path;

    Page(String page) {
        this.path = page;
    }

    public String getPath() {
        return path;
    }
}
