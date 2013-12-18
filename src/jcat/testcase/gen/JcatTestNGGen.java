package jcat.testcase.gen;

public class JcatTestNGGen
{
  protected static String nl;
  public static synchronized JcatTestNGGen create(String lineSeparator)
  {
    nl = lineSeparator;
    JcatTestNGGen result = new JcatTestNGGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "main_enb1 enb nj njenb125" + NL + "main_ue1  ue aeroflex_nj_10" + NL + "main_pgw cpg nj cpg1" + NL + "main_portbase 0" + NL + "main_numofues 1 " + NL + "main_isp1 isp nj njisp213-2-33" + NL + "physical_cell_id=76" + NL + "duplex_mode=tdd" + NL + "af_mode=combined" + NL + "radio_card=RC1" + NL + "Campaign_portbase 0" + NL + "Campaign_view ate_default_atelte";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}
