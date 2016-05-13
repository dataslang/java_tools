package com.dataslang.xmlValidator;

import com.beust.jcommander.JCommander;
import com.dataslang.xmlValidator.commander.GetMetaCommander;
import com.dataslang.xmlValidator.commander.MainCommander;
import com.dataslang.xmlValidator.commander.ValCommander;
import com.dataslang.xmlValidator.commander.XsltCommander;

public class XmlValidator {

    private static final String VALIDATE_OPTION = "validate";
    private static final String TRANSFORM_OPTION = "transform";
    private static final String META_OPTION = "meta";

    public static void main(String[] args){
        MainCommander com = new MainCommander();
        ValCommander val = new ValCommander();
        XsltCommander xsl = new XsltCommander();
        GetMetaCommander dat = new GetMetaCommander();

        JCommander jct = null;

        try {
            jct = new JCommander(com);
            jct.addCommand(VALIDATE_OPTION, val);
            jct.addCommand(TRANSFORM_OPTION, xsl);
            jct.addCommand(META_OPTION, dat);
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

        if(com.help || args.length == 0){
            jct.usage();
            System.exit(0);
        }

        if(jct.getParsedCommand().equalsIgnoreCase(VALIDATE_OPTION)){
            Validator validator = new Validator(val.xml, val.xsd, com.debug);
            validator.validate();
        }else if(jct.getParsedCommand().equalsIgnoreCase(TRANSFORM_OPTION)){
            Transformator transformator = new Transformator(xsl.xml, xsl.xslt, xsl.output, com.debug);
            transformator.transformate();
        }else if(jct.getParsedCommand().equalsIgnoreCase(META_OPTION)){

        }
    }
}
