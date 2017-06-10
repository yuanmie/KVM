package cmdline;

import org.apache.commons.cli.*;

import java.util.Arrays;

public class Cmd {
    boolean helpFlag;
    boolean versionFlag;
    String cpOption;
    String XjreOption;
    String className;
    String args[];

    public boolean isHelpFlag() {
        return helpFlag;
    }

    public void setHelpFlag(boolean helpFlag) {
        this.helpFlag = helpFlag;
    }

    public boolean isVersionFlag() {
        return versionFlag;
    }

    public void setVersionFlag(boolean versionFlag) {
        this.versionFlag = versionFlag;
    }

    public String getCpOption() {
        return cpOption;
    }

    public void setCpOption(String cpOption) {
        this.cpOption = cpOption;
    }

    public String getXjreOption() {
        return XjreOption;
    }

    public void setXjreOption(String xjreOption) {
        XjreOption = xjreOption;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public Cmd(String[] args) {
        this.className = "";
        this.cpOption = "";
        this.XjreOption = "";
        parseCmd(args);
    }


    public void parseCmd(String args[]){
        //option的容器
        Options options = new Options();
        //boolean型的option
        options.addOption("help",false,"help information");
        //当第二参数是true时，可以是这样的参数  -O4
        options.addOption("version",false,"print version and exit");
        Option c = Option.builder("cp")  //option的名字,判断的时候要使用这个名字
                .required(false)               //是否必须有这个选项
                .hasArg()                         //带一个参数
                .argName("classname")     //参数的名字
                .desc("class path")  //描述
                .build();                             //必须有

        Option jre   = OptionBuilder.withArgName( "jre" )
                .hasArg()
                .withDescription(  "use given file for log" )
                .create( "Xjre" );
        //将c这个option添加进去
        options.addOption(c);
        options.addOption(jre);

        //parser
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options,args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //询问是否有help
        if(cmd.hasOption("help")) {
            this.helpFlag = true;
//            //调用默认的help函数打印
//            HelpFormatter formatter = new HelpFormatter();
//            formatter.printHelp( "java wordcount [OPTION] <FILENAME>", options );
            return;
        }

        if(cmd.hasOption("version")) {
            //调用默认的help函数打印
            this.versionFlag = true;
            return;
        }

        if(cmd.hasOption("cp")){
//          获得相应的选项（c）的参数
            String classpath = cmd.getOptionValue("cp");
            this.cpOption = classpath;
        }

        if(cmd.hasOption("Xjre")){
//          获得相应的选项（c）的参数
            String jreOption = cmd.getOptionValue("Xjre");
            this.XjreOption = jreOption;
        }

        this.args = cmd.getArgs();
        if(this.args.length > 0){
            this.className = this.args[0];
            this.args = Arrays.copyOfRange(this.args, 1, this.args.length);
        }

    }

}
