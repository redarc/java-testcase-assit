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
  protected final String TEXT_2 = ";" + NL + "" + NL + "import com.ericsson.msr.handlers.enums.Direction;" + NL + "import com.ericsson.msr.handlers.enums.Protocol;" + NL + "" + NL + "/**" + NL + "*   " + NL + "* @name ";
  protected final String TEXT_3 = NL + "*       " + NL + "* @author " + NL + "*       " + NL + "* @created " + NL + "*       " + NL + "* @description   A builder class for {@link ";
  protected final String TEXT_4 = "}. The setter methods can" + NL + "*               optionally be used to configure the test module. Call build to get an instance of" + NL + "*               {@link ";
  protected final String TEXT_5 = "}." + NL + "*       " + NL + "* @revision   " + NL + "*  " + NL + "*/" + NL + "" + NL + "public class ";
  protected final String TEXT_6 = NL + "{" + NL + "    private String testId = null;" + NL + "    private String description = null;" + NL + "    private Protocol protocol;" + NL + "    private Direction direction;" + NL + "    " + NL + "    private int ue_TotalNumber = 1;" + NL + "    private int ue_TputNumber = 1;" + NL + "    private String servingEUtranCell = null;" + NL + "" + NL + "    private final int MAX_AEROFLEX_UE_NUM = 128;" + NL + "    private final int MAX_EUTRANCELL_NUM = 12;" + NL + "" + NL + "    /**" + NL + "     * The constructor for this builder, should only be used by {@link ";
  protected final String TEXT_7 = "} " + NL + "     * " + NL + "     */" + NL + "    protected ";
  protected final String TEXT_8 = "(){}" + NL + "    " + NL + "     /**" + NL + "     * @param testId" + NL + "     *            Any string that is a unique identifier for the test" + NL + "     * @return" + NL + "     */" + NL + "    public ";
  protected final String TEXT_9 = " setTestId(String testId) {" + NL + "        if (testId == null)" + NL + "            throw new NullPointerException(errorMessage(\"testId\"));" + NL + "        this.testId = testId;" + NL + "        return this;" + NL + "    }" + NL + "" + NL + "    public String getTestId(){" + NL + "        return testId;" + NL + "    }" + NL + "    " + NL + "     /**" + NL + "     * @param description" + NL + "     *            Any string that is a description for the test" + NL + "     * @return" + NL + "     */" + NL + "    public ";
  protected final String TEXT_10 = " setDescription(String description) {" + NL + "        if(description == null) throw new NullPointerException(errorMessage(\"description\"));" + NL + "        this.description = description;" + NL + "        return this;" + NL + "    }" + NL + "" + NL + "    public String getDescription(){" + NL + "        return description;" + NL + "    }" + NL + "" + NL + "    private String errorMessage(String argName) {" + NL + "        return String.format(\"Null argument or error argument: '%s'\", argName);" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * Sets PM_Even_testmodulebuilder parameter 'protocol' to the current protocol" + NL + "     * @param protocol" + NL + "     *            specify the kind of user data traffic" + NL + "     * @throws NullPointerException with argument \"protocol\" if input parameter is null" + NL + "     * @return object" + NL + "     */" + NL + "     public ";
  protected final String TEXT_11 = " setProtocol(Protocol protocol) {" + NL + "         if(protocol == null) throw new NullPointerException(errorMessage(\"protocol\"));" + NL + "         this.protocol = protocol;" + NL + "         return this;" + NL + "     }" + NL + "" + NL + "     public Protocol getProtocol(){" + NL + "         return protocol;" + NL + "     }" + NL + "" + NL + "     /**" + NL + "     * Sets PM_Even_testmodulebuilder parameter 'direction' to the current direction" + NL + "     * @param direction" + NL + "     *            the direction for userdata" + NL + "     * @throws NullPointerException with argument \"direction\" if input parameter is null" + NL + "     * @return object" + NL + "     */" + NL + "     public ";
  protected final String TEXT_12 = " setDirection(Direction direction) {" + NL + "         if(direction == null) throw new NullPointerException(errorMessage(\"direction\"));" + NL + "         this.direction = direction;" + NL + "         return this;" + NL + "     }" + NL + "" + NL + "     public Direction getDirection(){" + NL + "        return direction;" + NL + "     }" + NL + "     " + NL + "      /**" + NL + "      * Must called before setTputNumOfUes()" + NL + "      * @param ue_TotalNumber" + NL + "      *            Total number of UEs used" + NL + "      * @return" + NL + "      */" + NL + "     public ";
  protected final String TEXT_13 = " setTotalNumOfUes(int ue_TotalNumber) {" + NL + "         if(ue_TotalNumber < 0 || ue_TotalNumber > MAX_AEROFLEX_UE_NUM) {" + NL + "             String errorMessage = String.format(\"numOfUes can not be negative: '%s'\", ue_TotalNumber);" + NL + "             throw new IllegalArgumentException(errorMessage);" + NL + "         }" + NL + "         this.ue_TotalNumber = ue_TotalNumber;" + NL + "         return this;" + NL + "     }" + NL + "" + NL + "     public int getTotalNumOfUes()" + NL + "     {" + NL + "         return ue_TotalNumber;" + NL + "     }" + NL + "" + NL + "     /**" + NL + "      * Must called after setTotalNumOfUes()" + NL + "      * @param ue_TputNumber" + NL + "      *            number of UEs used for Tput" + NL + "      * @return" + NL + "      */" + NL + "     public ";
  protected final String TEXT_14 = " setTputNumOfUes(int ue_TputNumber) {" + NL + "         if(ue_TputNumber <= 0 || ue_TputNumber > ue_TotalNumber) {" + NL + "             String errorMessage = String.format(\"numOfUes can not be negative: '%s'\", ue_TputNumber);" + NL + "             throw new IllegalArgumentException(errorMessage);" + NL + "         }" + NL + "         this.ue_TputNumber = ue_TputNumber;" + NL + "         return this;" + NL + "     }" + NL + "" + NL + "     public int getTputNumOfUes()" + NL + "     {" + NL + "         return ue_TputNumber;" + NL + "     }" + NL + "" + NL + "     /**" + NL + "      * @param servingEUtranCell" + NL + "      *           number of cells be used Throughput" + NL + "      * @return" + NL + "      */" + NL + "     public ";
  protected final String TEXT_15 = " setNumOfCells(String servingEUtranCell) {" + NL + "         if(Integer.parseInt(servingEUtranCell) <= 0 || Integer.parseInt(servingEUtranCell) > MAX_EUTRANCELL_NUM) {" + NL + "             String errorMessage = String.format(\"servingEUtranCell ID can not be smaller than 0\");" + NL + "             throw new IllegalArgumentException(errorMessage);" + NL + "         }" + NL + "         this.servingEUtranCell = servingEUtranCell;" + NL + "         return this;" + NL + "     }" + NL + "" + NL + "     public String getServingEUtranCell()" + NL + "     {" + NL + "         return servingEUtranCell;" + NL + "     }" + NL + "     " + NL + "     public ";
  protected final String TEXT_16 = " build(){" + NL + "        return new ";
  protected final String TEXT_17 = "(this);" + NL + "     }" + NL + "}";
  protected final String TEXT_18 = NL;

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
    stringBuffer.append(className);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(moduleName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(moduleName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
