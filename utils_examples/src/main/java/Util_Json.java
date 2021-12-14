import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Util_Json {
    public static void main(String[] args) {
        String str="{\"sheds\":[{\"shedName\":\"隔离舍\",\"shedId\":\"ff80808174d7e56401764a78f39e005d\"},{\"shedName\":\"母猪楼\",\"shedId\":\"ff80808177d26be701787db96179002a\"},{\"shedName\":\"育肥楼\",\"shedId\":\"ff80808177d26be701792141cc8a0126\"}]}";
        JSONObject parse = (JSONObject) JSON.parse(str);
        JSONArray sheds = parse.getJSONArray("sheds");
        HashMap<String, String> shedsMap = new HashMap<>();
        sheds.stream().forEach(shed->{
            shedsMap.put(((JSONObject)shed).getString("shedName"),((JSONObject)shed).getString("shedId"));
        });

        for (Map.Entry<String, String> entry : shedsMap.entrySet()) {
            System.out.println(entry.toString());
        }
    }
}
