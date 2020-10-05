package observe_point.quiz;

import com.cwjcsu.joutable.DefaultTable;
import com.cwjcsu.joutable.DefaultTableFormatter;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.junit.Assert.assertFalse;

public class SearchForUnqualifiedPlayers {

    String endpoint = CoreConstant.RAPID_API_ENDPOINT;

    @Test
    @Description("Check that in each game best Player has to weigh more than 200 pounds, height more than 5 feet, 10 inches")
    public void search_for_unqualified_payers() {
        step("Validate Response's status code");
        Validate.response_status_code(endpoint, 200);

        step("Validate Response's body");
        Validate.response_body_not_empty(endpoint);

        step("Search for Unqualified Players based on Weight and Height");
        assertFalse("Not all Players were qualified for the Games.", all_players_were_qualified());
    }

    private Boolean all_players_were_qualified() {
        DefaultTable dt = new DefaultTable();

        JsonPath jsonPathEvaluator = RestApiClient.responseAsJsonPath(endpoint);
        List<Integer> game_id = jsonPathEvaluator.get("data.id");
        List<Integer> team_full_name = jsonPathEvaluator.get("data.player.first_name");
        List<Integer> player_first_name = jsonPathEvaluator.get("data.player.first_name");
        List<Integer> player_last_name = jsonPathEvaluator.get("data.player.last_name");
        List<Integer> weight_pounds = jsonPathEvaluator.get("data.player.weight_pounds");
        List<Integer> height_feet = jsonPathEvaluator.get("data.player.height_feet");
        List<Integer> height_inches = jsonPathEvaluator.get("data.player.height_inches");

        Integer[] weight = new Integer[weight_pounds.size()];
        Integer[] heightFT = new Integer[height_feet.size()];
        Integer[] heightIN = new Integer[height_inches.size()];

        int i = 0;
        search: {
            for (Integer wp : weight_pounds) {
                for (Integer hf : height_feet) {
                    for (Integer hi : height_inches) {

                        weight[i] = wp;
                        heightFT[i] = hf;
                        heightIN[i] = hi;

                        if (wp != null && !wp.toString().isEmpty() && hf != null && !hf.toString().isEmpty()) {
                            if (wp <= 200 && hf <= 5 && hi <= 10) {
                                System.out.println("\n");
                                dt.setTitle("UNQUALIFIED PLAYER(s)");
                                dt.setHeaders(new String[] { "ID", "NAME", "DETAIL" });
                                dt.addRow(new Object[] { 1, "GAME_ID", game_id.get(i)});
                                dt.addRow(new Object[] { 2, "TEAM_NAME", team_full_name.get(i) });
                                dt.addRow(new Object[] { 3, "PLAYER_NAME", player_first_name.get(i) + " " + player_last_name.get(i) });
                                dt.addRow(new Object[] { 4, "WEIGHT", weight_pounds.get(i) });
                                dt.addRow(new Object[] { 5, "HEIGHT", height_feet.get(i) + " FT, " + height_inches.get(i) + " INCHES" });
                                DefaultTableFormatter dtf = new DefaultTableFormatter(100, 2);
                                System.out.println(dtf.format(dt));
                                break search;
                            }
                        }
                    }
                }
                i++;
            }
        }
        return true;
    }
}
