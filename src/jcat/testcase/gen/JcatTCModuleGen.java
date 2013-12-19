package jcat.testcase.gen;

public class JcatTCModuleGen
{
  protected static String nl;
  public static synchronized JcatTCModuleGen create(String lineSeparator)
  {
    nl = lineSeparator;
    JcatTCModuleGen result = new JcatTCModuleGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import java.io.BufferedReader;" + NL + "import java.io.FileReader;" + NL + "import java.io.IOException;" + NL + "import java.util.HashMap;" + NL + "import com.ericsson.ate.lte_ran_iov.testsupport.other.LteRm;" + NL + "import com.ericsson.ate.lte_ran_iov.testsupport.tool.uetools.ueinterfaces.UeNasMode;" + NL + "import com.ericsson.msr.handlers.EnbConfigHandler;" + NL + "import com.ericsson.msr.handlers.EnbConfigHandlerBuilder;" + NL + "import com.ericsson.msr.handlers.EnbHandler;" + NL + "import com.ericsson.msr.handlers.UeHandler;" + NL + "import com.ericsson.msr.handlers.UeNasModeBuilders;" + NL + "import com.ericsson.msr.handlers.enums.Direction;" + NL + "import com.ericsson.msr.handlers.enums.Protocol;" + NL + "import com.ericsson.msr.handlers.enums.UeCategory;" + NL + "import com.ericsson.msr.testhelpers.EnbTestHelper;" + NL + "import com.ericsson.tac.jcat.TestBase;" + NL + "/**" + NL + " *   " + NL + " * @name ";
  protected final String TEXT_3 = NL + " *       " + NL + " * @author " + NL + " *       " + NL + " * @created " + NL + " *       " + NL + " * @description" + NL + " *       " + NL + " * @revision " + NL + " *  " + NL + " */" + NL + "" + NL + "public class ";
  protected final String TEXT_4 = " extends TestBase{" + NL + "    " + NL + "    private String testId = null;" + NL + "    private String servingEUtranCell = null;" + NL + "    private int duration = 30;//duration default value 30s" + NL + "    private int ue_TotalNumber = 1;" + NL + "    private int ue_TputNumber = 1;" + NL + "    " + NL + "    private UeNasMode ueNasMode;" + NL + "    private EnbHandler enbHandler;" + NL + "    private EnbTestHelper enbTestHelper;" + NL + "    private EnbConfigHandlerBuilder enbOriginalConfig;" + NL + "    " + NL + "    public static ";
  protected final String TEXT_5 = "Builder newBuilder() {" + NL + "        return new ";
  protected final String TEXT_6 = "Builder();" + NL + "    }" + NL + "    " + NL + "    protected ";
  protected final String TEXT_7 = "(";
  protected final String TEXT_8 = NL + "        ";
  protected final String TEXT_9 = "Builder builder) {" + NL + "        testId = builder.getTestId();" + NL + "        servingEUtranCell = builder.getServingEUtranCell();" + NL + "    }" + NL + "    " + NL + "    public void execute() {" + NL + "        try {" + NL + "            ueNasMode = UeNasModeBuilders.fromCategoryNumber(LteRm.ue1,UeCategory.CAT4,ue_TotalNumber,Integer.parseInt(servingEUtranCell));" + NL + "            enbHandler = EnbHandler.getInstance(LteRm.enb1);" + NL + "            enbTestHelper = new EnbTestHelper(testId, LteRm.enb1,enbOriginalConfig.build());        " + NL + "            //TODO write your test code here" + NL + "            " + NL + "        } catch (Exception te) {" + NL + "            te.printStackTrace();" + NL + "            fail(\"Execution Exception~!!!!!\");" + NL + "        } finally {" + NL + "            restoreENBStatus();" + NL + "        }" + NL + "    }" + NL + "    " + NL + "     /**" + NL + "     * TODO Deliver in next release" + NL + "     * Roll back the configuration of eNB.<br/>" + NL + "     * @throws Exception" + NL + "     */" + NL + "    private void restoreENBStatus() {" + NL + "        setTestStep(\"Clearup the ENB configuration\");" + NL + "        //TODO write your restore ENB status code here" + NL + "        " + NL + "    }" + NL + "}" + NL + NL + NL;
  protected final String TEXT_10 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	JcatTCGenArgs args = (JcatTCGenArgs)argument;
    String pkgName = args.getPkgName();
    String className = args.getClassName(); 

    stringBuffer.append(TEXT_1);
    stringBuffer.append(pkgName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    return stringBuffer.toString();
  }
}
