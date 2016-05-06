package com.dataslang.xmlValidator;

import com.beust.jcommander.JCommander;

public class XmlValidator {

    private static final String VALIDATE_OPTION = "validate";
    private static final String TRANSFORM_OPTION = "transform";

    public static void main(String[] args){
        MainCommander com = new MainCommander();
        ValCommander val = new ValCommander();
        XsltCommander xsl = new XsltCommander();

        JCommander jct = null;

        try {
            jct = new JCommander(com);
            jct.addCommand(VALIDATE_OPTION, val);
            jct.addCommand(TRANSFORM_OPTION, xsl);
            jct.parse(args);
        } catch (Exception e) {
            if(com.debug){
                e.printStackTrace();
                System.exit(1);
            }
            else {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        if(com.help){
            jct.usage();
        }

        if(jct.getParsedCommand().equalsIgnoreCase(VALIDATE_OPTION)){
            Validator validator = new Validator(val.xml, val.xsd, com.debug);
            validator.validate();
        }else if(jct.getParsedCommand().equalsIgnoreCase(TRANSFORM_OPTION)){
            Transformator transformator = new Transformator(xsl.xml, xsl.xslt, xsl.output, com.debug);
            transformator.transformate();
        }
    }
}
