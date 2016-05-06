package com.dataslang.xmlValidator;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Get metadata from PDF")
public class GetDataValidator {
    @Parameter(names = "--pdf", required = true, description = "Exctract metadata from PDF")
    public String pdf;

    @Parameter(names = {"-o", "--output"}, description = "generate the PDF file into this destination")
    public String output;
}
