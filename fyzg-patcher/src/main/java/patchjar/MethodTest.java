package patchjar;

import sun.misc.Regexp;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodTest {

    public static void main(String[] args) throws IOException {
        String path = "D:\\workspaces\\IDEA_swhy\\joyin.product-fyzg-public-parent\\joyin.product-fyzg-public-page\\src\\main\\webapp\\products\\fyzg\\salesPlan\\salesPlanManage.jsp";

        Pattern compile = Pattern.compile("\\\\src\\\\main\\\\webapp(.*)");
        Matcher matcher = compile.matcher(path);
        matcher.find();
//        System.out.println(matcher.group(1));

        String path2 = "D:\\workspaces\\IDEA_swhy\\joyin.product-fyzg-public-parent\\joyin.product-fyzg-public-domain\\src\\main\\java\\com\\joyin\\fyzg\\domain\\batch\\AssetScheduleGeneratedService.java";
        Pattern compile2 = Pattern.compile(".*\\\\(.*)\\\\src\\\\main\\\\java(.*)\\\\(.*).java");
        Matcher matcher2 = compile2.matcher(path2);
        matcher2.find();
        System.out.println(matcher2.group(1));
        System.out.println(matcher2.group(2));
        System.out.println(matcher2.group(3));

//        File file = new File("D:\\temp\\patch\\222\\1.class");
//        file.createNewFile();

    }
}
