package jcat.testcase.gen;

public class JcatTCBuilderGen
{
  protected static String nl;
  public static synchronized JcatTCBuilderGen create(String lineSeparator)
  {
    nl = lineSeparator;
    JcatTCBuilderGen result = new JcatTCBuilderGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "/**" + NL + "*   " + NL + "* @name ";
  protected final String TEXT_3 = NL + "*       " + NL + "* @author " + NL + "*       " + NL + "* @created " + NL + "*       " + NL + "* @description   A builder class for {@link ";
  protected final String TEXT_4 = "}. The setter methods can" + NL + "*               optionally be used to configure the test module. Call build to get an instance of" + NL + "*               {@link ";
  protected final String TEXT_5 = "}." + NL + "*       " + NL + "* @revision   " + NL + "*  " + NL + "*/" + NL + "" + NL + "public class ";
  protected final String TEXT_6 = NL + "{" + NL + "    private String testId = null;" + NL + "    private String servingEUtranCell = null;" + NL + "" + NL + "    /**" + NL + "     * The constructor for this builder, should only be used by {@link ";
  protected final String TEXT_7 = "} " + NL + "     * " + NL + "     */" + NL + "    protected ";
  protected final String TEXT_8 = "(){}" + NL + "    " + NL + "     /**" + NL + "     * @param testId" + NL + "     *            Any string that is a unique identifier for the test" + NL + "     * @return" + NL + "     */" + NL + "    public ";
  protected final String TEXT_9 = " setTestId(String testId) {" + NL + "        if (testId == null)" + NL + "            throw new NullPointerException(errorMessage(\"testId\"));" + NL + "        this.testId = testId;" + NL + "        return this;" + NL + "    }" + NL + "" + NL + "    public String getTestId(){" + NL + "        return testId;" + NL + "    }" + NL + "" + NL + "     /**" + NL + "      * @param servingEUtranCell" + NL + "      *           number of cells be used Throughput" + NL + "      * @return" + NL + "      */" + NL + "     public ";
  protected final String TEXT_10 = " setNumOfCells(String servingEUtranCell) {" + NL + "         if(Integer.parseInt(servingEUtranCell) <= 0 ) {" + NL + "             String errorMessage = String.format(\"servingEUtranCell ID can not be smaller than 0\");" + NL + "             throw new IllegalArgumentException(errorMessage);" + NL + "         }" + NL + "         this.servingEUtranCell = servingEUtranCell;" + NL + "         return this;" + NL + "     }" + NL + "" + NL + "     public String getServingEUtranCell()" + NL + "     {" + NL + "         return servingEUtranCell;" + NL + "     }" + NL + "     " + NL + "     private String errorMessage(String argName) {" + NL + "        return String.format(\"Null argument or error argument: '%s'\", argName);" + NL + "     }" + NL + "     " + NL + "     public ";
  protected final String TEXT_11 = " build(){" + NL + "        return new ";
  protected final String TEXT_12 = "(this);" + NL + "     }" + NL + "}";
  protected final String TEXT_13 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	JcatTCGenArgs args = (JcatTCGenArgs)argument;
    String pkgName = args.getPkgName();
    String className = args.getClassName(); 
    String moduleName=args.getModuleName();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(pkgName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(moduleName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(moduleName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(moduleName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(moduleName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}
