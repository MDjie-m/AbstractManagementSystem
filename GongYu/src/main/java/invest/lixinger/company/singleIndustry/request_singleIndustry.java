package invest.lixinger.company.singleIndustry;

import invest.lixinger.company.singleIndustry.VO.singleIndustryResult_RootVO;
import invest.lixinger.utils.netRequest;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

public class request_singleIndustry {
    public static void main(String[] args) throws IOException {
        String filePath = request_singleIndustry.class.getClassLoader().getResource("indexReqParam.yml").getPath();
        Map indexReqParam = new Yaml().load(new FileInputStream(filePath));
        String fundamentalURL = (String) indexReqParam.get("singleIndustryURL");
        String paramJson = getParam_singleIndustry.getSingleIndustry();
        String resultJson = netRequest.jsonNetPost(fundamentalURL, paramJson);

        singleIndustryResult_RootVO resultObj = (singleIndustryResult_RootVO) getResult_singleIndustry.getResultObj(resultJson);
        resultObj.getData().forEach(System.out::println);
        System.out.println(resultObj.getData().size());
    }


}
